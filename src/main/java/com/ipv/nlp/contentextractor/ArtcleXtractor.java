package com.ipv.nlp.contentextractor;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.html.BoilerpipeContentHandler;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

/**
 * 
 * @author Mohsin Uddin
 */
public class ArtcleXtractor {

  
    public static void main(String[] args) throws FileNotFoundException, IOException, SAXException, TikaException, BoilerpipeProcessingException {
        File file = new File("");

        //parse method parameters
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        //FileInputStream inputstream = new FileInputStream(file);
        InputStream inputstream = TikaInputStream.get(new URL(""));
        ParseContext context = new ParseContext();
        //parsing the file
        parser.parse(inputstream, new BoilerpipeContentHandler(handler, ArticleExtractor.getInstance()), metadata, context);
       
        //String[] metadataNames = metadata.names();
        //for (String name : metadataNames) {
          //  System.out.println(name + ":   " + metadata.get(name));
        //}
        
        String plainText = handler.toString();
        FileWriter writer = new FileWriter("output.txt");
        writer.write(plainText);
        writer.close();
        System.out.println("done");
            

    }

}
