package com.jsofttechnologies.report.generator;

import com.jsofttechnologies.report.utlil.ReportColumn;
import com.jsofttechnologies.report.utlil.ReportHeader;
import org.apache.commons.lang.StringUtils;

import java.lang.annotation.Annotation;
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

    public abstract Class getType();

    public abstract Object render(ReportColumn reportColumn, Object item);

    public abstract String renderView(String header, List<Map<String, ColumnProperty>> values);

    public Object generate(Object entity) {

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
            Map<String, List<Map<String, ColumnProperty>>> reportMap = new HashMap<>();

            for (Object en : list) {
                Class cls = en.getClass();
                List<Map<String, ColumnProperty>> reportContent = null;
                Annotation reportHeader = cls.getDeclaredAnnotation(ReportHeader.class);
                if (reportHeader != null) {
                    if (reportMap.containsKey(((ReportHeader) reportHeader).name())) {
                        reportContent = reportMap.get(((ReportHeader) reportHeader).name());
                    } else {
                        reportContent = new ArrayList<>();
                        reportMap.put(((ReportHeader) reportHeader).name(), reportContent);
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
                            String fieldName = "get" + StringUtils.capitalize(field.getName());
                            Method getMethod = cls.getDeclaredMethod(fieldName, null);
                            getMethod.setAccessible(Boolean.TRUE);
                            Object value = getMethod.invoke(en, null);
                            Object result = render(reportColumn, value);
                            values.put(field.getName(), new ColumnProperty(reportColumn, result));
                        } catch (IllegalAccessException e) {
                            logger.log(Level.WARNING, e.getMessage(), e);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    } else {
                        continue;
                    }
                }

                reportContent.add(values);
            }

            for (String header : reportMap.keySet()) {
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

        public ReportHeader reportHeader(){
            return reportHeader;
        }
    }
}
