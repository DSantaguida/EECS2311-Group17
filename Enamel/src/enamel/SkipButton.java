package enamel;

import java.util.LinkedList;
import java.util.List;

public class SkipButton extends NodeButton implements Sequential {
	protected Node nextNode;
	protected Timeline t;
	
	/**
	 * @return the pauseTime
	 */
//	public int getPauseTime() {
//		return pauseTime;
//	}
//
//	/**
//	 * @param pauseTime the pauseTime to set
//	 */
//	public void setPauseTime(int pauseTime) {
//		this.pauseTime = pauseTime;
//	}

	public SkipButton(int buttonNumber) {
		super(buttonNumber);
		this.nextNode = null;
		this.t = new Timeline();
//		this.setPauseTime(0);
	}

	public SkipButton(int buttonNumber, Node nextNode) {
		super(buttonNumber);
		this.nextNode = nextNode;
		this.t = new Timeline();
//		this.setPauseTime(0);
		// TODO Auto-generated constructor stub
	}

	public SkipButton(SkipButton other) {
		super(other);
		this.nextNode = other.nextNode;
		this.t = other.t;
//		this.setPauseTime(other.pauseTime);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String[] getResponses() {
		List<String> l = new LinkedList<>();
		for (Event e : this.t.getEvents()) {
			if (e.getClass() == Response.class) {
				l.add(e.getData());
			}
		}
		return l.toArray(new String[l.size()]);
	}
			
	@Override
	public void addToResponse(String addition) {
		this.t.addEvent(new Response(addition));
	}
	
	public void setNextNode(Node next) {
		this.nextNode = next;
	}
	
	public Node getNextNode() {
		return this.nextNode;
	}

//	/**
//	 * @return the listOfPins
//	 */
//	public int[] getPins(int cellNumber) {
//	}

	/**
	 * @param pins the listOfPins to set
	 */
	@Override
	public void setPins(int[] pins, int cellNumber) {
		String s = "";
		for (int i = 0; i < pins.length; i++) {
			s += pins[i];
		}
		setPins(s, cellNumber);
	}
	
	@Override
	public void setPins(String pins, int cellNumber) {
		this.t.addEvent(new DisplayPins(pins, cellNumber));
	}
	
//	public void setPin(int cellNumber, int pin, int value) {
//		if (this.pins.get(cellNumber) == null) {
//			this.pins.put(cellNumber, new int[8]);
//		}
//		this.pins.get(cellNumber)[pin-1] = value;
//	}
	
	@Override
	public void setAudioFile(String audioFile) {
		this.t.addEvent(new Sound(audioFile));
		
	}

	@Override
	public void addEvent(Event e) {
		this.t.addEvent(e);
	}
	
	@Override
	public void addEvent(Event e, int index) {
		this.t.insert(index, e);
	}
	
	@Override
	public void addPause(int time) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Event getEvent(int index) {
		return this.t.getEvent(index);
	}

	@Override
	public Timeline getTimeline() {
		// TODO Auto-generated method stub
		return this.t;
	}
		
	@Override
	public String toString() {
		return t.toString();
	}
}

