package oducs350.fall25.green2.io;

// JDK
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileIOTest {
    private Path testTextFile;
    private Path pdfTestFile;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary TXT file with known contents
        testTextFile = Files.createTempFile("fileio_test", ".txt");
        List<String> lines = List.of("apple", "banana", "cherry");
        Files.write(testTextFile, lines);

        // Create temporary PDF
        pdfTestFile = Files.createTempFile("fileio_pdf_test", ".pdf");
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            PDPageContentStream content = new PDPageContentStream(doc, page);
            content.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            content.beginText();
            content.newLineAtOffset(100, 700);
            content.showText("This is a test PDF file.");
            content.endText();
            content.close();

            doc.save(pdfTestFile.toFile());
        }
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up the temp file after each test
        Files.deleteIfExists(testTextFile);
        Files.deleteIfExists(pdfTestFile);
    }

    @Test
    void testReadTextFile() throws IOException {
        List<String> result = FileIO.readTextFile(testTextFile.toString());

        Assertions.assertEquals(List.of("apple", "banana", "cherry"), result);
    }

    @Test
    void testReadPDFFile() throws IOException {
        List<String> result = FileIO.readPDFFile(pdfTestFile.toString());
        Assertions.assertEquals(List.of("This is a test PDF file."), result);
    }

}
