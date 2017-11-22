package de.l3s.souza.url.analytics;

public class Entity {
	private String text;
	private String wikiEntity;
	
	public Entity(String text, String type) {
		this.text = text;
		this.wikiEntity = type;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	public String getwikiEntity() {
		return wikiEntity;
	}
	public void setwikiEntity(String type) {
		this.wikiEntity = type;
	}
	
	
}
