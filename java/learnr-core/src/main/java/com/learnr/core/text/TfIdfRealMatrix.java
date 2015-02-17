package com.learnr.core.text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TfIdfRealMatrix {
	
	private static final Logger logger = LoggerFactory.getLogger(TfIdfRealMatrix.class);

	/**
	 * Calculates the count of documents in which a respective word is preent
	 * 
	 * @param documents
	 *            (List of term Frequency Vectors)
	 * @param word
	 * @return count
	 */
	private int documentFrequency(List<Map<String, Integer>> documents, String word) {
		int count = 0;
		for (int i = 0; i < documents.size(); i++) {
			if (documents.get(i).containsKey(word)) {
				count = count + 1;
			}
		}
		return count;
		
		
		
	}

	/**
	 * claculates the term frequency. If a word is not present in the respective document then its term frequency is
	 * zero. Else term Frequency is = 1+log(frequency)
	 * 
	 * @param frequency
	 *            of a word in the document
	 * @return tF
	 */
	private double tFCalculator(int frequency) {
		double tF;
		if (frequency == 0) {
			return 0;
		} else {
			tF = 1 + Math.log(frequency);
			return tF;
		}
	}

	/**
	 * 
	 * @param totalNoOfDocuments
	 * @param actualNoOfDocuments
	 *            ( count of documents in which a respective word is present)
	 * @return iDF
	 */
	private double iDFCalculator(int totalNoOfDocuments, int actualNoOfDocuments) {
		double iDF;
		iDF = Math.log((double) totalNoOfDocuments / (double) actualNoOfDocuments);
		return iDF;
	}

	/**
	 * 
	 * @param documents
	 *            (list of term Frequency vectors)
	 * @param dimension
	 *            (dimension of the corpus)
	 * @return RealMatix of tF-Idf values of corpus after normalization(along each document)
	 */
	public RealMatrix tFIdfMatrix(List<Map<String, Integer>> documents, List<String> dimension) {
		logger.info(" Generating tFIdfMattrix ");
		RealMatrix weightMatrix = new Array2DRowRealMatrix(documents.size(), dimension.size());
		List<Double> weights = new ArrayList<Double>();
		for (int i = 0; i < documents.size(); i++) {
			weights = calculateWeights(documents, documents.get(i), dimension);
			for (int j = 0; j < dimension.size(); j++) {
				weightMatrix.setEntry(i, j, weights.get(j));
			}
			weights.clear();
		}
		return weightMatrix;
	}

	/**
	 * calculates the weights of each word in the document and normalizes the weight
	 * 
	 * @param documents
	 *            (list of term Frequency vectors)
	 * @param document
	 *            (one respective term vector)
	 * @param dimension
	 *            ( different words in the corpus)
	 * @return listOfWeights
	 */
	private List<Double> calculateWeights(List<Map<String, Integer>> documents, Map<String, Integer> document,
			List<String> dimension) {
		double tF, iDf, weight, normaliseFactor = 0;
		List<Double> listOfWeights = new ArrayList<Double>();
		for (int i = 0; i < dimension.size(); i++) {
			if (document.containsKey(dimension.get(i))) {
				tF = tFCalculator(document.get(dimension.get(i)));
				iDf = iDFCalculator(documents.size(), documentFrequency(documents, dimension.get(i)));
				weight = tF * iDf;
				listOfWeights.add(weight);
			} else {
				weight = 0.0;
				listOfWeights.add(0.0);
			}

		}
		for (int i = 0; i < listOfWeights.size(); i++) {
			normaliseFactor = normaliseFactor + listOfWeights.get(i);
		}
		for (int i = 0; i < listOfWeights.size(); i++) {

			listOfWeights.add(listOfWeights.get(0) / normaliseFactor);
			listOfWeights.remove(0);
		}
		return listOfWeights;
	}

	/**
	 * Normalizes each word weight (average of all weigths of each word in all the documents)
	 * 
	 * @param realmatrix
	 *            ( a termFrequencyVectors*dimension matrix )
	 * @return List Of Final Weights
	 */
	public List<Double> finalWeightList(RealMatrix realmatrix) {
		logger.info("Generating a final tF-Idf list of corpus  ");
		double mean, sum;
		List<Double> tfIdfValues = new ArrayList<Double>();
		for (int i = 0; i < realmatrix.getColumnDimension(); i++) {
			mean = 0;
			sum = 0;
			for (int j = 0; j < realmatrix.getRowDimension(); j++) {
				sum = sum + realmatrix.getEntry(j, i);
			}
			mean = sum / realmatrix.getColumnDimension();
			tfIdfValues.add(mean);
		}
		return tfIdfValues;
	}
}
