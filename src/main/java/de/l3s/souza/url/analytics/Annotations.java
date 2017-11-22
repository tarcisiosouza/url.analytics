package de.l3s.souza.url.analytics;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import edu.stanford.nlp.util.Triple;
import it.enricocandino.tagme4j.TagMeClient;

public class Annotations {
	
	private static StanfordNLPUtils nlp;
	private static TagMeNLPUtils tnlp;
	private static LanguageProcessor lp;
	private static Vector<String> lang;
	private static List<Triple<String, Integer, Integer>> annotations;
	private String annotationText;
	private String annotationType;
	private ArrayList<Entity> entities;
	public void initialize() throws Exception
	{
		//nlp = new StanfordNLPUtils ();
		lp = new LanguageProcessor ();
		tnlp = new TagMeNLPUtils ();
		entities = new ArrayList<Entity> ();
		annotationText = null;
		annotationType = null;
	}
	
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}


	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}


	public void setLanguage (String input)
	{
		lang = lp.detectLanguage(input);
	}
	
	public Vector<String> getLanguage ()
	{
		return lang;
	}
	
	public static Vector<String> getLang() {
		return lang;
	}

	public static void setLang(Vector<String> lang) {
		Annotations.lang = lang;
	}

	public static List<Triple<String, Integer, Integer>> getAnnotations() {
		return annotations;
	}

	public static void setAnnotations(
			List<Triple<String, Integer, Integer>> annotations) {
		Annotations.annotations = annotations;
	}

	public String getAnnotationText() {
		return annotationText;
	}

	public void setAnnotationText(String annotationText) {
		this.annotationText = annotationText;
	}

	public String getAnnotationType() {
		return annotationType;
	}

	public void setAnnotationType(String annotationType) {
		this.annotationType = annotationType;
	}

	public void annotate (String input,TagMeClient tagMeClient) throws Exception
	{
		
		entities = tnlp.getEntities(input,tagMeClient);
	}
	
}
