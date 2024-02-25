package com.suresofttech.fitness.model;

public class FitnessEntry {
	private String date;
	private int time;
	private String description;
	private BodyPart part;
	
	public FitnessEntry(String date, int time, BodyPart part){
		this.date = date;
		this.time = time;
		this.part = part;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public BodyPart getPart() {
		return part;
	}
	
	public void setPart(BodyPart part) {
		this.part = part;
	}
}
