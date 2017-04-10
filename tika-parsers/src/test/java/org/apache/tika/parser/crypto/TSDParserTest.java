package org.apache.tika.parser.crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.tika.TikaTest;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.xml.sax.ContentHandler;

public class TSDParserTest extends TikaTest {

    @Test
    public void testTSDFileData() throws Exception {

        try (InputStream inputXml = 
                         TSDParserTest.class.getResourceAsStream("/test-documents/MANIFEST.XML.TSD");
             InputStream inputTxt1 = 
                         TSDParserTest.class.getResourceAsStream("/test-documents/Test1.txt.tsd");
             InputStream inputTxt2 = 
                         TSDParserTest.class.getResourceAsStream("/test-documents/Test2.txt.tsd");
             InputStream inputDocx = 
                         TSDParserTest.class.getResourceAsStream("/test-documents/Test3.docx.tsd");
             InputStream inputPdf = 
                         TSDParserTest.class.getResourceAsStream("/test-documents/Test4.pdf.tsd");
             InputStream inputPng = 
                         TSDParserTest.class.getResourceAsStream("/test-documents/Test5.PNG.tsd");) {

            TSDParser tsdParser = new TSDParser();

            ContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            ParseContext parseContext = new ParseContext();
            tsdParser.parse(inputXml, handler, metadata, parseContext);

            assertNotNull(handler);
            assertNotNull(metadata);
            assertContains("Description=Time Stamped Data Envelope", metadata.toString());
            assertContains("Content-Type=application/timestamped-data", metadata.toString());
            assertContains("File-Parsed=true", metadata.toString());
                        
            handler = new BodyContentHandler();
            metadata = new Metadata();
            parseContext = new ParseContext();
            tsdParser.parse(inputTxt1, handler, metadata, parseContext);
            
            assertNotNull(handler);
            assertNotNull(metadata);
            assertContains("Description=Time Stamped Data Envelope", metadata.toString());
            assertContains("Content-Type=application/timestamped-data", metadata.toString());
            assertContains("File-Parsed=true", metadata.toString());
                        
            handler = new BodyContentHandler();
            metadata = new Metadata();
            parseContext = new ParseContext();
            tsdParser.parse(inputTxt2, handler, metadata, parseContext);

            assertNotNull(handler);
            assertNotNull(metadata);
            assertContains("Description=Time Stamped Data Envelope", metadata.toString());
            assertContains("Content-Type=application/timestamped-data", metadata.toString());
            assertContains("File-Parsed=true", metadata.toString());
            
            handler = new BodyContentHandler();
            metadata = new Metadata(); 
            parseContext = new ParseContext();
            tsdParser.parse(inputDocx, handler, metadata, parseContext);

            assertNotNull(handler);
            assertNotNull(metadata);
            assertContains("Description=Time Stamped Data Envelope", metadata.toString());
            assertContains("Content-Type=application/timestamped-data", metadata.toString());
            assertContains("File-Parsed=true", metadata.toString());
            
            handler = new BodyContentHandler();
            metadata = new Metadata();
            parseContext = new ParseContext();
            tsdParser.parse(inputPdf, handler, metadata, parseContext);

            assertNotNull(handler);
            assertNotNull(metadata);
            assertContains("Description=Time Stamped Data Envelope", metadata.toString());
            assertContains("Content-Type=application/timestamped-data", metadata.toString());
            assertContains("File-Parsed=true", metadata.toString());
            
            handler = new BodyContentHandler();
            metadata = new Metadata();
            parseContext = new ParseContext();
            tsdParser.parse(inputPng, handler, metadata, parseContext);

            assertNotNull(handler);
            assertNotNull(metadata);
            assertContains("Description=Time Stamped Data Envelope", metadata.toString());
            assertContains("Content-Type=application/timestamped-data", metadata.toString());
            assertContains("File-Parsed=true", metadata.toString());
            
        } 
    }

    @Test
    public void testTSDFileDataRecursiveMetadataXML() throws Exception {
        
        List<Metadata> list = getRecursiveMetadata("MANIFEST.XML.TSD");
        assertEquals(2, list.size());
        assertContains(TSDParser.class.getName(),
                Arrays.asList(list.get(0).getValues("X-Parsed-By")));
    }

    @Test
    public void testTSDFileDataRecursiveMetadataTxt1() throws Exception {
        
        List<Metadata> list = getRecursiveMetadata("Test1.txt.tsd");
        assertEquals(2, list.size());
        assertContains(TSDParser.class.getName(),
                Arrays.asList(list.get(0).getValues("X-Parsed-By")));
    }

    @Test
    public void testTSDFileDataRecursiveMetadataTxt2() throws Exception {
        
        List<Metadata> list = getRecursiveMetadata("Test2.txt.tsd");
        assertEquals(2, list.size());
        assertContains(TSDParser.class.getName(),
                Arrays.asList(list.get(0).getValues("X-Parsed-By")));
    }

    @Test
    public void testTSDFileDataRecursiveMetadataDocx() throws Exception {
        
        List<Metadata> list = getRecursiveMetadata("Test3.docx.tsd");
        assertEquals(2, list.size());
        assertContains(TSDParser.class.getName(),
                Arrays.asList(list.get(0).getValues("X-Parsed-By")));
    }

    @Test
    public void testTSDFileDataRecursiveMetadataPdf() throws Exception {
        
        List<Metadata> list = getRecursiveMetadata("Test4.pdf.tsd");
        assertEquals(2, list.size());
        assertContains(TSDParser.class.getName(),
                Arrays.asList(list.get(0).getValues("X-Parsed-By")));
    }

    //@Test
    public void testTSDFileDataRecursiveMetadataPng() throws Exception {
        
        List<Metadata> list = getRecursiveMetadata("Test5.PNG.tsd");
        assertEquals(2, list.size());
        assertContains(TSDParser.class.getName(),
                Arrays.asList(list.get(0).getValues("X-Parsed-By")));
    }

}
