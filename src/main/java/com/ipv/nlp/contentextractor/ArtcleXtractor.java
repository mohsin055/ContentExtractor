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

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws org.xml.sax.SAXException
     * @throws org.apache.tika.exception.TikaException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, SAXException, TikaException, BoilerpipeProcessingException {
        //Assume sample.txt is in your current directory
        File file = new File("");

        //parse method parameters
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        //FileInputStream inputstream = new FileInputStream(file);
        InputStream inputstream = TikaInputStream.get(new URL("http://www.prothom-alo.com/bangladesh/article/1318116/%E0%A6%A6%E0%A7%8D%E0%A6%AC%E0%A6%BF%E0%A6%A4%E0%A7%80%E0%A7%9F-%E0%A6%B8%E0%A6%BE%E0%A6%AC%E0%A6%AE%E0%A7%87%E0%A6%B0%E0%A6%BF%E0%A6%A8-%E0%A6%95%E0%A7%87%E0%A6%AC%E0%A7%8D%E2%80%8C%E0%A6%B2-%E0%A6%B8%E0%A6%82%E0%A6%AF%E0%A7%8B%E0%A6%97-%E0%A6%89%E0%A6%A6%E0%A7%8D%E0%A6%AC%E0%A7%8B%E0%A6%A7%E0%A6%A8-%E0%A6%95%E0%A6%B0%E0%A6%B2%E0%A7%87%E0%A6%A8"));
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
