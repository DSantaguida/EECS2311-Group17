package enamel;

public interface Sequential {
	public void addEvent(Event e);
	public void addEvent(Event e, int index);
	public String[] getResponses();
	public void addToResponse(String response);
	public void setPins(String pins, int cellNumber);
	public void setPins(int[] pins, int cellNumber);
	public void setAudioFile(String filename);
	public void addPause(int time);
	public Event getEvent(int index);
	public Timeline getTimeline();
}
