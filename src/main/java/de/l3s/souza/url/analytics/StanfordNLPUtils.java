package de.l3s.souza.url.analytics;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.AnswerAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.Triple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class StanfordNLPUtils {

		private ArrayList<Entity> result = new ArrayList<Entity>();
		private Map<Language, AbstractSequenceClassifier<CoreLabel>> classifiers;
		private HashSet<String> addedEntities = new HashSet <String>();
		/**
		 * 
		 */
		public StanfordNLPUtils() {
			String serializedClassifier = "english.all.3class.distsim.crf.ser.gz";
			AbstractSequenceClassifier<CoreLabel> engLishClassifier = CRFClassifier
					.getClassifierNoExceptions(serializedClassifier);
			AbstractSequenceClassifier<CoreLabel> germanClassifier = CRFClassifier
					.getClassifierNoExceptions("dewac_175m_600.crf.ser.gz");

			classifiers = new HashMap<Language, AbstractSequenceClassifier<CoreLabel>>();

			classifiers.put(Language.EN, engLishClassifier);
			classifiers.put(Language.DE, germanClassifier);

		}

		/**
		 * Configure with classifier to use for German language
		 * 
		 * @param nr
		 *            0 for first classifier, 1 for second one
		 */
		public void changeGermanClassifier(int nr) {
			AbstractSequenceClassifier<CoreLabel> germanClassifier;
			if (nr == 0)
				germanClassifier = CRFClassifier.getClassifierNoExceptions("hgc_175m_600.crf.ser.gz ");
			else
				germanClassifier = CRFClassifier.getClassifierNoExceptions("dewac_175m_600.crf.ser.gz");
			classifiers.put(Language.DE, germanClassifier);
		}

		public List<CoreMap> splitSentences(String text, Language lang) {

			Properties props = new Properties();
			// props.setProperty("annotators",
			// "tokenize, ssplit, pos, lemma, parse, sentiment");
			props.setProperty("annotators", "tokenize, ssplit");
			StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

			Annotation annotation = pipeline.process(text);
			List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);

			return sentences;
		}

		public String[] splitSentencesToStrings(String text, Language lang) {

			List<CoreMap> sentences = splitSentences(text, lang);

			List<String> strs = new ArrayList<String>();

			for (CoreMap sentence : sentences) {
				strs.add(sentence.toString());
			}

			return strs.toArray(new String[strs.size()]);
		}

		public List<Triple<String, Integer, Integer>> annotateEntities(String text, Language lang) throws Exception {

			AbstractSequenceClassifier<CoreLabel> classifier = classifiers.get(lang);
			if (classifier == null) {
				throw new Exception("StanfordNLPUtils. Language is unknown: " + lang.toString());
			}
			List<Triple<String, Integer, Integer>> annotations = classifier.classifyToCharacterOffsets(text);
			
			// classifier.

			return annotations;

		}

		public ArrayList<Entity> getEntities(String text,Language lang) throws Exception {
				
			addedEntities = new HashSet <String>();
			AbstractSequenceClassifier<CoreLabel> classifier = classifiers.get(lang);
			if (classifier == null) {
				throw new Exception("StanfordNLPUtils. Language is unknown: " + lang.toString());
			}
			
			String annotatedText = classifier.classifyWithInlineXML(text);
			addEntities (annotatedText);
			
			//Map<Integer, CorefChain> graph = document.get(CorefChainAnnotation.class);
		    return result;
			//return graph;
		}
		
		private void addEntities (String annotText)
		{
			if (annotText.contains("<I-LOC>"))
			{
				final Pattern pattern = Pattern.compile("<I-LOC>(.+?)</I-LOC>");
				final Matcher m = pattern.matcher(annotText);
				
				while (	m.find())
				{
					String name = m.group(1);
					Entity entity = new Entity (name,"Location");
					
					if (!addedEntities.contains(name.toLowerCase()))
						result.add(entity);
					
					addedEntities.add(name.toLowerCase());
				}
				
			}
			
			if (annotText.contains("<I-PER>"))
			{
				final Pattern pattern = Pattern.compile("<I-PER>(.+?)</I-PER>");
				final Matcher m = pattern.matcher(annotText);
				while (	m.find())
				{
					String name = m.group(1);
					Entity entity = new Entity (name,"Person");
					
					if (!addedEntities.contains(name.toLowerCase()))
						result.add(entity);
					
					addedEntities.add(name.toLowerCase());
				}
			}
			
			if (annotText.contains("<I-ORG>"))
			{
				final Pattern pattern = Pattern.compile("<I-ORG>(.+?)</I-ORG>");
				final Matcher m = pattern.matcher(annotText);
				while (	m.find())
				{
					String name = m.group(1);
					Entity entity = new Entity (name,"Organization");
					
					if (!addedEntities.contains(name.toLowerCase()))
						result.add(entity);
					
					addedEntities.add(name.toLowerCase());
				}
			}
			
		}
		
		String CapsFirst(String str) {
		    String[] words = str.split(" ");
		    StringBuilder ret = new StringBuilder();
		    for(int i = 0; i < words.length; i++) {
		        ret.append(Character.toUpperCase(words[i].charAt(0)));
		        ret.append(words[i].substring(1));
		        if(i < words.length - 1) {
		            ret.append(' ');
		        }
		    }
		    return ret.toString();
		}
	
	}
