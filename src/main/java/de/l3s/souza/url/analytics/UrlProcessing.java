package de.l3s.souza.url.analytics;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class UrlProcessing {
	
	private String OrigURL; //Original URL
	private String UrlAnnotations;
	private String postProcessedUrl; //URL path after pre-processing step
	private String annotationType;
	private Annotations annotation;
	
	public String getOrigURL() {
		return OrigURL;
	}
	
	public String getAnnotationType() {
		return annotationType;
	}

	public void setAnnotationType(String annotationType) {
		this.annotationType = annotationType;
	}

	public ArrayList<Entity> getEntities ()
	{
		return annotation.getEntities();
	}
	public String getPath() {
		return postProcessedUrl;
	}

	public void setPath(String path) {
		this.postProcessedUrl = path;
	}

	public void setOrigURL(String origURL) {
		OrigURL = origURL;
	}
		
	public String getAnnotations() {
		return UrlAnnotations;
	}

	public void setAnnotations(String annotations) {
		this.UrlAnnotations = annotations;
	}

	public UrlProcessing () throws Exception
	{
		annotation = new Annotations();
		annotation.initialize();	
	}
	public void findEntities (String url) throws Exception {
		
		OrigURL = url;
		URLUtils urlUtils = new URLUtils ();
		urlUtils.loadStopwords();
		postProcessedUrl = urlUtils.preProcess(OrigURL);
		annotation.annotate(postProcessedUrl);
		UrlAnnotations = annotation.getAnnotationText();
		annotationType = annotation.getAnnotationType();
	}

	
}