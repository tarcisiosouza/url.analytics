package de.l3s.souza.url.analytics;

public class Entity {
	private String text;
	private String type;
	
	public Entity(String text, String type) {
		this.text = text;
		this.type = type;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
