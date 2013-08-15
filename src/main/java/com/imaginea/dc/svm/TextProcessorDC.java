package com.imaginea.dc.svm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.imaginea.dc.entities.NewsArticle;

import textProcessor.TextProcessor;
import textProcessor.options.Options;

public class TextProcessorDC extends TextProcessor {

	private List<NewsArticle> newsArticleList;
	
	public TextProcessorDC(Options options){
		super(options);
	}
	
	public void buildDocStringArrayFromMergedFile(){
		if (newsArticleList == null){
			super.buildDocStringArrayFromMergedFile();
			return;
		}
		
		docStringArray.clear();
		Iterator<NewsArticle> iterator = newsArticleList.iterator();
		while (iterator.hasNext()){
			NewsArticle article = iterator.next();
			String content = article.getContent();
			boolean isPositive = article.getIsPositive();			
			if (isPositive)
				content = "1	"+content;
			else
				content = "0	"+content;
			docStringArray.add(content);
		}
	}

	public List<NewsArticle> getNewsArticleList() {
		return newsArticleList;
	}

	public void setNewsArticleList(List<NewsArticle> newsArticleList) {
		this.newsArticleList = newsArticleList;
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
	
}
