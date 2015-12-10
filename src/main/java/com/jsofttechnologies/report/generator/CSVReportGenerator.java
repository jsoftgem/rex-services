package com.jsofttechnologies.report.generator;

import com.jsofttechnologies.report.utlil.ReportColumn;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

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
    public Object renderView(ColumnKey header, List<Map<String, ColumnProperty>> values) {
        fileCounter++;
        try {
            File tempFile;
            String rootVar = System.getProperty("user.home");

            if (rootVar != null) {
                tempFile = new File(System.getProperty("user.home"), "report-generator_" + fileCounter);
            } else {
                tempFile = new File("report-generator_" + fileCounter);
            }

            tempFile.createTempFile("csv", "temp");

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

            Iterator headerIterator = headers.iterator();
            while (headerIterator.hasNext()) {
                printer.print(headerIterator.next());
            }
            printer.println();
            int counter = 0;
            for (Map<String, ColumnProperty> column : values) {
                counter++;
                for (String key : column.keySet()) {
                    ColumnProperty columnProperty = column.get(key);
                    printer.print(columnProperty.getValue());
                }
                if (counter % 100 == 0) {
                    printer.flush();
                    fileWriter.flush();
                }
                printer.println();
            }

            printer.close();
            fileWriter.close();


            Scanner scanner = new Scanner(tempFile);
            StringBuilder csvBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                csvBuilder.append(scanner.nextLine());
                csvBuilder.append("\n");
            }
            scanner.close();
            return csvBuilder.toString();
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
