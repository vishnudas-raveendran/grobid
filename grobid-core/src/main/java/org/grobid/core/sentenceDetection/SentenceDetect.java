package org.grobid.core.sentenceDetection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import org.grobid.core.utilities.GrobidProperties;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceDetect {

	public String[] getSentences(String paragraph) throws IOException, URISyntaxException {
		//String paragraph = "This is a statement. This is another statement. Now is an abstract word for time, that is always flying.";
		 
        // refer to model file "en-sent,bin", available at link http://opennlp.sourceforge.net/models-1.5/
		InputStream is = new FileInputStream(getFilePath2sentDetecter());
        SentenceModel model = new SentenceModel(is);
        
        // feed the model to SentenceDetectorME class
        SentenceDetectorME sdetector = new SentenceDetectorME(model);
        
        // detect sentences in the paragraph
        String sentences[] = sdetector.sentDetect(paragraph);
 
       is.close(); 
        //List<String> sents = new ArrayList<String>(Arrays.asList(sentences));
        return sentences;
	}
	
	protected static File getFilePath2sentDetecter() {
        File theFile = new File(GrobidProperties.get_GROBID_HOME_PATH().getAbsoluteFile() + File.separator +"models"+File.separator+"sentence-detecter"+File.separator+"en-sent.bin");
        if (!theFile.exists()) {
            System.err.println();
        }
        return theFile;
    }

}
