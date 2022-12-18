package ru.shulz;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ZipParsingTest {
    ClassLoader cl = ZipParsingTest.class.getClassLoader();

    @Test
    void zipParseTest() throws Exception {
        try (
                InputStream is = cl.getResourceAsStream("zip/test.zip");
                ZipInputStream zis = new ZipInputStream(is, Charset.forName("windows-1251"))
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String entryName = entry.getName();
                if (entryName.contains(".xlsx")) {
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(0)
                            .getCell(0).getStringCellValue()).contains("тест");
                    assertThat(content.excel.getSheetAt(1).getRow(0)
                            .getCell(0).getStringCellValue()).contains("тест2");
                } else if (entryName.contains(".pdf")) {
                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("книги");
                } else if (entryName.contains(".csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(0)[0]).contains("name");
                    assertThat(content.get(0)[1]).contains("age");
                    assertThat(content.get(1)[0]).contains("Anastasiya");
                    assertThat(content.get(1)[1]).contains("36");
                    assertThat(content.get(2)[0]).contains("Vlad");
                    assertThat(content.get(2)[1]).contains("29");
                }
            }
        }
    }
}
