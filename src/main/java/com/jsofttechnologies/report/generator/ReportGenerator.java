package com.jsofttechnologies.report.generator;

import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserDetail;
import com.jsofttechnologies.report.utlil.ReportColumn;
import com.jsofttechnologies.report.utlil.ReportConverter;
import com.jsofttechnologies.report.utlil.ReportHeader;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.core.MultivaluedMap;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jerico on 4/7/2015.
 */
public abstract class ReportGenerator {

    public abstract String getContentType();

    public abstract String getDefaultName();

    public abstract String getMediaType();

    public abstract void setHeaders(MultivaluedMap<String, Object> headers);

    public abstract Class getType();

    public abstract Object render(ReportColumn reportColumn, Object item);

    public abstract Object renderView(ColumnKey header, List<Map<String, ColumnProperty>> values);

    public Object generate(Object entity) throws InstantiationException, InvocationTargetException {

        Logger logger = Logger.getLogger(ReportGenerator.class.getName());
        StringBuilder builder = new StringBuilder();

        if (entity.getClass().isArray() || entity instanceof Collection) {
            List<Object> list = null;
            if (entity.getClass().isArray()) {
                Object[] obs = (Object[]) entity;
                list = Arrays.asList(obs);
            } else if (entity instanceof Collection) {
                list = (List) entity;
            }
            Map<ColumnKey, List<Map<String, ColumnProperty>>> reportMap = new HashMap<>();

            for (Object en : list) {
                Class cls = en.getClass();
                List<Map<String, ColumnProperty>> reportContent = null;

                ReportHeader reportHeader = (ReportHeader) cls.getDeclaredAnnotation(ReportHeader.class);

                if (reportHeader != null) {
                    if (reportMap.containsKey(new ColumnKey(reportHeader.name(), reportHeader))) {
                        reportContent = reportMap.get(new ColumnKey(reportHeader.name(), reportHeader));
                    } else {
                        reportContent = new ArrayList<>();
                        reportMap.put(new ColumnKey(reportHeader.name(), reportHeader), reportContent);
                    }
                } else {
                    continue;
                }


                Map<String, ColumnProperty> values = new HashMap<>();

                Field[] fields = cls.getDeclaredFields();

                for (Field field : fields) {
                    if (field.isAnnotationPresent(ReportColumn.class)) {
                        try {
                            ReportColumn reportColumn = field.getAnnotation(ReportColumn.class);
                            ReportConverter reportConverter = reportColumn.converter().newInstance();
                            Object value = null;
                            String fieldName = "get" + StringUtils.capitalize(field.getName());

                            if (!reportColumn.field().isEmpty()) {
                                Method firstMethod = en.getClass().getDeclaredMethod(fieldName);
                                Object returnObject = firstMethod.invoke(en, null);
                                value = reportConverter.getValue(en, field, getValue(reportColumn.field(), returnObject));
                            } else {
                                Method getMethod = cls.getDeclaredMethod(fieldName, null);
                                value = reportConverter.getValue(en, field, getMethod.invoke(en, null));

                            }

                            Object result = render(reportColumn, value);
                            values.put(field.getName(), new ColumnProperty(reportColumn, result));

                        } catch (IllegalAccessException e) {
                            logger.log(Level.WARNING, e.getMessage(), e);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    } else {
                        continue;
                    }
                }

                reportContent.add(values);
            }

            for (ColumnKey header : reportMap.keySet()) {
                builder.append(renderView(header, reportMap.get(header)));
            }

        }


        return builder.toString();
    }

    public static final class ColumnProperty {

        private ReportColumn reportColumn;
        private Object value;
        private ReportHeader reportHeader;

        public ColumnProperty(ReportColumn reportColumn, Object value) {
            this.reportColumn = reportColumn;
            this.value = value;
        }

        public ReportColumn getReportColumn() {
            return reportColumn;
        }

        public Object getValue() {
            return value;
        }

        public ReportHeader reportHeader() {
            return reportHeader;
        }
    }

    public static final class ColumnKey {
        private String header;
        private ReportHeader reportHeader;

        public ColumnKey(String header, ReportHeader reportHeader) {
            this.header = header;
            this.reportHeader = reportHeader;
        }

        public String getHeader() {
            return header;
        }

        public ReportHeader getReportHeader() {
            return reportHeader;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ColumnKey columnKey = (ColumnKey) o;

            if (header != null ? !header.equals(columnKey.header) : columnKey.header != null) return false;
            return !(reportHeader != null ? !reportHeader.equals(columnKey.reportHeader) : columnKey.reportHeader != null);

        }

        @Override
        public int hashCode() {
            int result = header != null ? header.hashCode() : 0;
            result = 31 * result + (reportHeader != null ? reportHeader.hashCode() : 0);
            return result;
        }
    }

    public static Object getValue(String field, Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object o = null;

        if (field.contains(".")) {
            List<String> splitted = Arrays.asList(field.split("\\."));
            LinkedList<String> linkSplitted = new LinkedList<>(splitted);
            String first = splitted.get(0);
            String fieldName = "get" + StringUtils.capitalize(first);
            if (linkSplitted.size() > 1) {
                linkSplitted.removeFirst();
            }

            Method method1 = obj.getClass().getDeclaredMethod(fieldName);
            Object returnObject = method1.invoke(obj, null);


            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < linkSplitted.size(); i++) {
                builder.append(linkSplitted.get(i));
                if (i < linkSplitted.size() - 1) {
                    builder.append(".");
                }
            }
            return getValue(builder.toString(), returnObject);
        } else {
            String fieldName = "get" + StringUtils.capitalize(field);
            o = obj.getClass().getDeclaredMethod(fieldName, null).invoke(obj, null);
        }

        return o;
    }


    public static void main(String... args) {
        WarAgent warAgent = new WarAgent();
        FlowUser flowUser = new FlowUser();
        flowUser.setUsername("rickzx98");

        FlowUserDetail flowUserDetail = new FlowUserDetail();
        flowUserDetail.setFullName("Jerico de Guzman");

        flowUser.setFlowUserDetail(flowUserDetail);

        warAgent.setUser(flowUser);
        warAgent.setRegion("BUHAIN");

        try {
            String fieldName = "get" + StringUtils.capitalize("user");

            Method firstMethod = warAgent.getClass().getDeclaredMethod(fieldName);

            Object returnObject = firstMethod.invoke(warAgent, null);

            Object value = getValue("flowUserDetail.fullName", returnObject);

            System.out.println(value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
