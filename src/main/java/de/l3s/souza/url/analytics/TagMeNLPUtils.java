package de.l3s.souza.url.analytics;

import java.util.ArrayList;

import it.enricocandino.tagme4j.TagMeClient;
import it.enricocandino.tagme4j.model.Annotation;
import it.enricocandino.tagme4j.response.TagResponse;

public class TagMeNLPUtils {

	public ArrayList<Entity> getEntities(String text,TagMeClient tagMeClient) throws Exception {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		TagResponse tagResponse = tagMeClient
                .tag()
                .text(text)
                .execute();

        for (Annotation a : tagResponse.getAnnotations()){
        	Entity entity = new Entity(a.getSpot(),a.getTitle());
            entities.add(entity);
        }
        
        return entities;
	}
		
}
