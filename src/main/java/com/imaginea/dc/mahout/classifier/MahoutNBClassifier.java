package com.imaginea.dc.mahout.classifier;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.mahout.classifier.naivebayes.BayesUtils;
import org.apache.mahout.classifier.naivebayes.NaiveBayesModel;
import org.apache.mahout.classifier.naivebayes.StandardNaiveBayesClassifier;
import org.apache.mahout.common.lucene.AnalyzerUtils;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.Vector.Element;
import org.apache.mahout.vectorizer.TFIDF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.Multiset;
import com.imaginea.dc.service.impl.NewsArticleServiceImpl;

public class MahoutNBClassifier extends AbstractMahoutClassifier {
	
	private final Logger LOGGER = LoggerFactory.getLogger(NewsArticleServiceImpl.class);
	
	protected String modelPath ;
	protected String labelIndexPath;
	protected String dictionaryPath ;
	protected String documentFrequencyPath ;
	protected String luceneAnalyser;
	
	protected StandardNaiveBayesClassifier classifier;
	
	protected Map<Integer, String> labels;
	protected Map<String, Integer> dictionary ;
	protected Map<Integer, Long> documentFrequency;
	
	protected Analyzer analyzer;
	
	protected int labelCount ;
	protected int trainingDocumentCount ;
	
	public MahoutNBClassifier(String modelPath, String labelIndexPath, String dictionaryPath, String documentFrequencyPath,
			String luceneAnalyser) {
		super();
		this.modelPath = modelPath;
		this.labelIndexPath = labelIndexPath;
		this.dictionaryPath = dictionaryPath;
		this.documentFrequencyPath = documentFrequencyPath;
		this.luceneAnalyser = luceneAnalyser;
		
		try {
			Configuration configuration = new Configuration();
			NaiveBayesModel model = NaiveBayesModel.materialize(new Path(modelPath),  new Configuration());
			classifier = new StandardNaiveBayesClassifier(model);
			
			labels = BayesUtils.readLabelIndex(configuration, new Path(labelIndexPath));
			dictionary = readDictionnary(configuration, new Path(dictionaryPath));
			documentFrequency = readDocumentFrequency(configuration, new Path(documentFrequencyPath));	
			analyzer = AnalyzerUtils.createAnalyzer(luceneAnalyser);
		} catch (IOException e) {	
			LOGGER.error("Exception while configuring the mahout model", e);
		} catch (ClassNotFoundException e) {
			LOGGER.error("Exception while configuring the mahout model", e);
		}
		
		labelCount = labels.size();
		//TODO: Fix this hard coding
		trainingDocumentCount =1000;
	}

	public String classify(String text) throws IOException {
		
		Multiset<String> words = ConcurrentHashMultiset.create();
		TokenStream ts = analyzer.tokenStream("text", new StringReader(text));
		CharTermAttribute termAtt = ts.addAttribute(CharTermAttribute.class);
		ts.reset();
		int wordCount = 0;
		while (ts.incrementToken()) {
			if (termAtt.length() > 0) {
				String word = ts.getAttribute(CharTermAttribute.class).toString();
				Integer wordId = dictionary.get(word);
				// if the word is not in the dictionary, skip it
				if (wordId != null) {
					words.add(word);
					wordCount++;
				}
			}
		}

		// create vector wordId => weight using tfidf
		//TODO: Fix this hard coding
		Vector vector = new RandomAccessSparseVector(10000);
		TFIDF tfidf = new TFIDF();
		for (Multiset.Entry<String> entry:words.entrySet()) {
			String word = entry.getElement();
			int count = entry.getCount();
			Integer wordId = dictionary.get(word);
			Long freq = documentFrequency.get(wordId);
			double tfIdfValue = tfidf.calculate(count, freq.intValue(), wordCount, trainingDocumentCount);
			vector.setQuick(wordId, tfIdfValue);
		}
		// With the classifier, we get one score for each label 
		// The label with the highest score is the one the text/message is more likely to
		// be associated to
		Vector resultVector = classifier.classifyFull(vector);
		double bestScore = -Double.MAX_VALUE;
		int bestCategoryId = -1;
		
		for(Element element: resultVector.all()) {
			int categoryId = element.index();
			double score = element.get();
			if (score > bestScore) {
				bestScore = score;
				bestCategoryId = categoryId;
			}
			System.out.print(" " + labels.get(categoryId) + ": " + score);
		}

		return labels.get(bestCategoryId);
	}
}
