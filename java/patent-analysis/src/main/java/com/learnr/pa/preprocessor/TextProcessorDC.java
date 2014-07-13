package com.learnr.pa.preprocessor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import textProcessor.TextProcessor;
import textProcessor.options.Options;
public class TextProcessorDC extends TextProcessor {

	private List<String> testData;
	
	public TextProcessorDC(Options options){		
		super(options);
	}
	
	public TextProcessorDC(){		
		super();
	}
	
	public void buildDocStringArray(){
		buildDocStringArrayFromMergedFile();
	}
	
	public void buildDocStringArrayFromMergedFile(){
		
		
		docStringArray.clear();
		
		if (testData != null && testData.size() > 0){
			Iterator<String> testDataIterator = testData.iterator();
			while (testDataIterator.hasNext()){
				String data = testDataIterator.next();
				docStringArray.add(data);
			}
		}		
	}

	public void buildStopWordSet()
    {
        stopWordSet.clear();
        BufferedReader br = null;
        String stopWordListFileName = "stopwordlist.txt";
        try
        {
           br = new BufferedReader(new InputStreamReader(new FileInputStream(stopWordListFileName)));
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder("Cannot open file: ")).append(stopWordListFileName).toString());
        }
        String line = "";
        String term = "";
        System.out.println((new StringBuilder(String.valueOf(System.getProperty("line.separator")))).append("Loading the stop word list file: ").append(stopWordListFileName).toString());
        try
        {
            while((line = br.readLine()) != null) 
            {
                if(doesStem)
                {
                    term = stemFromTextString(line.trim());
                } else
                {
                    term = line.trim().toLowerCase();
                }
                if(term.length() > 0)
                {
                    stopWordSet.add(term);
                }
            }
            br.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

	public List<String> getTestData() {
		return testData;
	}

	public void setTestData(List<String> testData) {
		this.testData = testData;
	}
	
}
