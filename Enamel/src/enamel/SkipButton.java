package enamel;

import java.io.*;
import java.util.*;

public class SkipButton extends NodeButton {
	protected String response;
	protected Node nextNode;
	protected Map<Integer, int[]> pins;
	protected String audioFile;
	protected int pauseTime;
	
	/**
	 * @return the pauseTime
	 */
	public int getPauseTime() {
		return pauseTime;
	}

	/**
	 * @param pauseTime the pauseTime to set
	 */
	public void setPauseTime(int pauseTime) {
		this.pauseTime = pauseTime;
	}

	public SkipButton(int buttonNumber) {
		super(buttonNumber);
		this.response = "";
		this.nextNode = null;
		this.audioFile = "";
		this.pins = new HashMap<>();
		this.setPauseTime(0);
	}

	public SkipButton(int buttonNumber, String response) {
		super(buttonNumber);
		this.response = response;
		this.nextNode = null;
		this.audioFile = "";
		this.pins = new HashMap<>();
		this.setPauseTime(0);
	}

	public SkipButton(int buttonNumber, String response, Node nextNode) {
		super(buttonNumber);
		this.response = response;
		this.nextNode = nextNode;
		this.audioFile = "";
		this.pins = new HashMap<>();
		this.setPauseTime(0);

		// TODO Auto-generated constructor stub
	}
	
	public SkipButton(int buttonNumber, String response, String audioFile, Node nextNode) {
		super(buttonNumber);
		this.response = response;
		this.nextNode = nextNode;
		this.audioFile = audioFile;
		this.pins = new HashMap<>();
		this.setPauseTime(0);
		// TODO Auto-generated constructor stub
	}

	public SkipButton(int buttonNumber, String response, Node nextNode,  Map<Integer, int[]> pins) {
		super(buttonNumber);
		this.response = response;
		this.nextNode = nextNode;
		this.pins = new HashMap<>(pins);
		this.audioFile = "";
		this.setPauseTime(0);
		// TODO Auto-generated constructor stub
	}
	
	public SkipButton(int buttonNumber, String response, Node nextNode, Map<Integer, int[]> pins, String audioFile) {
		super(buttonNumber);
		this.response = response;
		this.nextNode = nextNode;
		this.pins = new HashMap<>(pins);
		this.audioFile = audioFile;
		this.setPauseTime(0);
		// TODO Auto-generated constructor stub
	}

	public SkipButton(int buttonNumber, Node nextNode) {
		super(buttonNumber);
		this.response = "";
		this.nextNode = nextNode;
		this.pins = new HashMap<>();
		this.audioFile = "";
		this.setPauseTime(0);
		// TODO Auto-generated constructor stub
	}

	public SkipButton(SkipButton other) {
		super(other);
		this.response = other.response;
		this.nextNode = other.nextNode;
		this.pins = new HashMap<>(other.pins);
		this.audioFile = other.audioFile;
		this.setPauseTime(other.pauseTime);
		// TODO Auto-generated constructor stub
	}
	
	public String getResponse() {
		return this.response;
	}
			
	public void setResponse(String response) {
		this.response = response;
	}
	
	public void setNextNode(Node next) {
		this.nextNode = next;
	}
	
	public Node getNextNode() {
		return this.nextNode;
	}

	/**
	 * @return the listOfPins
	 */
	public int[] getPins(int cellNumber) {
		return this.pins.get(cellNumber);
	}

	/**
	 * @param pins the listOfPins to set
	 */
	public void setPins(int[] pins, int cellNumber) {
		this.pins.put(cellNumber, pins);
	}
	
	public void setPin(int cellNumber, int pin, int value) {
		if (this.pins.get(cellNumber) == null) {
			this.pins.put(cellNumber, new int[8]);
		}
		this.pins.get(cellNumber)[pin-1] = value;
	}
	
	public void setAudioFile(String audioFile) {
		
		FileSearch fileSearch = new FileSearch();

	        //try different directory and filename 
		fileSearch.searchDirectory(new File(System.getProperty("user.dir")), audioFile);

		int count = fileSearch.getResult().size();
		if(count ==0){
		    throw new IllegalArgumentException("File does not exist");
		}else{
		 this.audioFile = audioFile;
		}
		
		
	}
	
	public String getAudioFile() {
		return this.audioFile;
	}

}

