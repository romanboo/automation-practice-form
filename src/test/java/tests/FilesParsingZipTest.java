package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;



public class FilesParsingZipTest {

    private ClassLoader cl = FilesParsingZipTest.class.getClassLoader();

    @Test
    void unpackAndCheckZipFilesTest() throws Exception {
        try (InputStream is = new FileInputStream("src/test/resources/sample.zip");
             ZipInputStream zis = new ZipInputStream(is)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.contains("Пример"));
                } else if (entry.getName().endsWith(".xls") || entry.getName().endsWith(".xlsx")) {
                    XLS xls = new XLS(zis);
                    Assertions.assertTrue(xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue()
                            .contains("test1"));
                } else if (entry.getName().endsWith(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csvReader.readAll();
                    Assertions.assertTrue(data.stream()
                            .anyMatch(row -> String.join(",", row).contains("extension")));
                }
            }
        }
    }
}