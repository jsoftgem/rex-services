package com.jsofttechnologies.report.generator;

import com.jsofttechnologies.report.utlil.ReportColumn;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerico on 4/7/2015.
 */
public class HtmlReportGenerator extends ReportGenerator {

    private static HtmlReportGenerator htmlReportGenerator;

    private HtmlReportGenerator() {

    }

    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public Class getType() {
        return String.class;
    }

    @Override
    public Object render(ReportColumn column, Object item) {
        return "<td align=\"" + column.align() + "\" style=\"" + column.style() + "\">" + item + "</td>";
    }

    @Override
    public String renderView(String header, List<Map<String, ReportGenerator.ColumnProperty>> values) {

        StringBuilder builder = new StringBuilder();
        builder.append("<div>");
        builder.append("<h3 style=\"text-align:left;width:668px\">").append(header).append("</h5>");

        builder.append("<h6 style=\"text-align:left;width:668px\">")
                .append("<div>Total: ").append(values.size()).append("</div>")
                .append("<div>Date: ").append(DateFormat.getInstance().format(new Date())).append("</div></h6>");

        builder.append("<table border=\"1\">");


        if (!values.isEmpty()) {
            builder.append("<thead>");
            for (String field : values.get(0).keySet()) {
                builder.append("<th").append(" colspan=\"").append(values.get(0).get(field).getReportColumn().colSpan())
                        .append("\" style=\"").append(values.get(0).get(field).getReportColumn().headerStyle()).append("\" ")
                        .append("align=\"").append(values.get(0).get(field).getReportColumn().align()).append("\">");
                builder.append(values.get(0).get(field).getReportColumn().name());
                builder.append("</th>");
            }
            builder.append("</thead>");
            builder.append("<tbody>");
            int counter = 0;
            for (Map<String, ReportGenerator.ColumnProperty> value : values) {
                counter++;
                if (counter % 2 == 0) {
                    builder.append("<tr style=\"background:whitesmoke\">");
                } else {
                    builder.append("<tr>");
                }
                for (String field : value.keySet()) {
                    builder.append(value.get(field).getValue());
                }
                builder.append("</tr>");
            }
            builder.append("</tbody>");
        }


        builder.append("</table>");
        builder.append("</div>");
        return builder.toString();
    }

    public static final HtmlReportGenerator getInstance() {
        if (htmlReportGenerator != null) {
            synchronized (htmlReportGenerator) {
                return htmlReportGenerator;
            }
        } else {
            htmlReportGenerator = new HtmlReportGenerator();
        }
        return htmlReportGenerator;
    }


}
