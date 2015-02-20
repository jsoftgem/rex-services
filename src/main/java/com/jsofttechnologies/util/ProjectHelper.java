package com.jsofttechnologies.util;

import com.jsofttechnologies.model.DataTables;
import com.jsofttechnologies.model.DataTablesColumn;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URLDecoder;
import java.util.*;


/**
 * Created by Jerico on 7/13/2014.
 */
public class ProjectHelper {

    private Map<String, Object> json;

    public ProjectHelper createJson() {
        json = new HashMap<>();
        return this;
    }

    public ProjectHelper addField(String name, Object value) {
        json.put(name, value);
        return this;
    }

    public String buildJsonString() {
        return json(json);
    }

    public static Response error(Exception e) {
        e.printStackTrace();
        return Response.serverError()
                .entity("{\"msg\":\"" + e.getMessage() + "\"}")
                .type(MediaType.APPLICATION_JSON).build();

    }

    public static Response error(String msg) {
        return Response.serverError()
                .entity("{\"msg\":\"" + msg + "\"}")
                .type(MediaType.APPLICATION_JSON).build();

    }

    public static String json(Map<String, Object> jsonMap) {
        String json = null;
        StringBuilder builder = new StringBuilder("{");
        int l = jsonMap.keySet().size();
        int c = 0;
        for (String key : jsonMap.keySet()) {
            c++;
            builder.append("\"" + key + "\":\"" + jsonMap.get(key) + "\"");
            if (c < l) {
                builder.append(",");
            }
        }
        builder.append("}");
        json = builder.toString();

        return json;
    }

    public static String message(String msg) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("msg", msg);
        return json(jsonMap);
    }

    public static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }


    public static DataTables getDataTableFromQuery(String query) throws UnsupportedEncodingException {
        DataTables dataTables = new DataTables();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            String key = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
            String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");

            if (isColumn(key)) {

                Integer index = getColumnIndex(key);

                DataTablesColumn column = null;
                if (dataTables.getColumns() != null) {
                    column = new DataTablesColumn();
                    column.setIndex(index);

                    if (dataTables.getColumns().contains(column)) {
                        column = dataTables.getColumns().get(dataTables.getColumns().indexOf(column));
                    } else {
                        dataTables.getColumns().add(column);
                    }

                } else {
                    List<DataTablesColumn> columns = new ArrayList<>();
                    column = new DataTablesColumn();
                    columns.add(column);
                    column.setIndex(index);
                    dataTables.setColumns(columns);
                }


                String field = getColumnField(key);

                switch (field) {
                    case "data":
                        column.setData(value);
                        break;
                    case "name":
                        column.setName(value);
                        break;
                    case "searchable":
                        column.setSearchable(Boolean.valueOf(value));
                        break;
                    case "orderable":
                        column.setOrderable(Boolean.valueOf(value));
                        break;
                    case "value":
                        column.setSearchValue(value);
                        break;
                    case "regex":
                        column.setSearchRegex(Boolean.valueOf(value));
                        break;


                }

            } else {
                if (key.equalsIgnoreCase("draw")) {
                    dataTables.setDraw(Integer.valueOf(value));
                } else if (key.equalsIgnoreCase("order[0][column]")) {
                    dataTables.setOrder(Integer.valueOf(value));
                } else if (key.equalsIgnoreCase("order[0][dir]")) {
                    dataTables.setDir(value);
                } else if (key.equalsIgnoreCase("start")) {
                    dataTables.setStart(Integer.valueOf(value));
                } else if (key.equalsIgnoreCase("length")) {
                    dataTables.setLength(Integer.valueOf(value));
                } else if (key.equalsIgnoreCase("search[value]")) {
                    dataTables.setSearchValue(value);
                } else if (key.equalsIgnoreCase("search[regex]")) {
                    dataTables.setSearchRegex(Boolean.valueOf(value));
                }
            }

        }
        return dataTables;
    }


    public static Boolean isColumn(String value) {
        return value.contains("columns");
    }


    public static Integer getColumnIndex(String value) {
        int length = "columns".length();
        int start = length + 1;
        int end = value.indexOf("]");
        String result = value.substring(start, end);
        return Integer.valueOf(result);
    }

    public static String getColumnField(String value) {
        int pre = value.lastIndexOf("[");
        int start = pre + 1;
        int end = value.lastIndexOf("]");
        return value.substring(start, end);
    }


    public static String read(InputStream stream) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    public static String getPayloadValue(String fieldName, String input) {

        input = input.substring(input.indexOf(fieldName) + fieldName.length());


        input = input.substring(input.indexOf("=") + 1, input.lastIndexOf("\""));

        if (input.contains("\"")) {
            input = input.replaceAll("\"", "");
        }

        return input;
    }


}
