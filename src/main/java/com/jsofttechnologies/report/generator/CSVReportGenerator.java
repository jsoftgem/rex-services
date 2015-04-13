package com.jsofttechnologies.report.generator;

import com.jsofttechnologies.report.utlil.ReportColumn;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerico on 4/11/2015.
 */
public class CSVReportGenerator extends ReportGenerator {
    private static int fileCounter = 0;
    private static CSVReportGenerator csvReportGenerator;

    @Override
    public String getContentType() {
        return MediaType.APPLICATION_OCTET_STREAM;
    }

    @Override
    public Class getType() {
        return String.class;
    }

    @Override
    public Object render(ReportColumn reportColumn, Object item) {
        return item;
    }

    @Override
    public String renderView(ColumnKey header, List<Map<String, ColumnProperty>> values) {
        fileCounter++;
        try {
            File tempFile = new File("report-generator_" + fileCounter);

            tempFile.createNewFile();

            FileWriter fileWriter = new FileWriter(tempFile);

            CSVPrinter printer = null;

            List<String> headers = new ArrayList<>();
            for (String field : values.get(0).keySet()) {
                ReportColumn reportColumn = values.get(0).get(field).getReportColumn();
                headers.add(reportColumn.name());

            }
            switch (header.getReportHeader().csvFormat()) {
                case "excel":
                    printer = new CSVPrinter(fileWriter, CSVFormat.EXCEL);
                    break;
                case "rfc":
                    printer = new CSVPrinter(fileWriter, CSVFormat.RFC4180);
                    break;
                case "mysql":
                    printer = new CSVPrinter(fileWriter, CSVFormat.MYSQL);
                    break;
                case "tdf":
                    printer = new CSVPrinter(fileWriter, CSVFormat.TDF);
                    break;
                default:
                    printer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);

            }

            printer.printRecords(headers.iterator());

            int counter = 0;
            for (Map<String, ColumnProperty> column : values) {
                counter++;
                printer.printRecords(column.values().iterator());
                if (counter % 100 == 0) {
                    printer.flush();
                }
            }
            printer.close();
            return tempFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final CSVReportGenerator getInstance() {
        if (csvReportGenerator != null) {
            synchronized (csvReportGenerator) {
                return csvReportGenerator;
            }
        } else {
            csvReportGenerator = new CSVReportGenerator();
        }
        return csvReportGenerator;
    }

}
