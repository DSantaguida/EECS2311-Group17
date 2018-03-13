package enamel;

import java.io.*;
import java.util.*;

public class Node {
	private int id;
	private String name;
	private String response;
	private String repeatText;
	private Map<Integer, NodeButton> buttonList;
	private Map<Integer, int[]> pins;
	private String audioFile;
	private int pauseTime;
	
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

	public Node(int id) {
		this(id, String.valueOf(id), new HashMap<Integer, int[]>(), "");
	}
	
	public Node(int id, String name) {
		this(id, name, new HashMap<Integer, int[]>(), "");
	}
	
	public Node(int id, String name, String response) {
		this(id, name, new HashMap<Integer, int[]>(), response);
	}
	
	public Node(int id, String name, Map<Integer, int[]> pins) {
		this(id, name, pins, "");
	
	}
	
	public Node(int id, String name, Map<Integer, int[]> pins, String response) {
		this.id = id;
		this.name = name;
		this.response = response;
		this.pins = pins;
		this.buttonList = new HashMap<>();
		this.audioFile = "";
		this.setPauseTime(0);
	}
	
	public Node(int id, Map<Integer, int[]> pins) {
		this(id, String.valueOf(id), pins, "");
	}
	
	public Node(int id, String name, Map<Integer, int[]> listOfPins, String response, Map<Integer, NodeButton> buttonList) {
		this(id, name, listOfPins, response, "", buttonList);
	}
	
	public Node(int id, String name, Map<Integer, int[]> listOfPins, String response, String repeatText) {
		this.id = id;
		this.name = name;
		this.pins = listOfPins;
		this.response = response;
		this.repeatText = repeatText;
		this.buttonList = new HashMap<>(buttonList);
		this.audioFile = "";
		this.setPauseTime(0);
	}
	
	public Node(int id, String name, Map<Integer, int[]> listOfPins, String response, String repeatText, Map<Integer, NodeButton> buttonList) {
		this.id = id;
		this.name = name;
		this.pins = listOfPins;
		this.response = response;
		this.repeatText = repeatText;
		this.buttonList = new HashMap<>(buttonList);
		this.audioFile = "";
		this.setPauseTime(0);
	}
	
	public Node(Node other) {
		this.id = other.id;
		this.name = other.name;
		this.pins = new HashMap<>(other.pins);
		this.response = other.response;
		this.repeatText = other.repeatText;
		this.buttonList = new HashMap<>(other.buttonList);
		this.audioFile = other.audioFile;
		this.setPauseTime(other.pauseTime);	
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
	
	public String getResponse() {
		return this.response;
	}
	
	public void addToResponse(String addition) {
		this.response += addition;
	}
	
	public void setRepeatedText(String repeatText) {
		this.repeatText = repeatText;
	}
	
	public void addToRepeatedText(String addition) {
		this.repeatText += addition;
	}
	
	public String getRepeatedText() {
		return this.repeatText;
	}
	
	public int[] getPins(int cellNumber) {
		return this.pins.get(cellNumber);
	}
	
	public void setPins(int[] pins, int cellNumber) {
		this.pins.put(cellNumber, pins);
	}
	
	public void setPin(int cellNumber, int pin, int value) {
		if (this.pins.get(cellNumber) == null) {
			this.pins.put(cellNumber, new int[8]);
		}
		this.pins.get(cellNumber)[pin-1] = value;
	}
	
	public void addButton(int number) {
		this.buttonList.put(number, new SkipButton(number));
	}
	
	public void addButton(int number, String response, String audioFile, Node nextNode) {
		this.buttonList.put(number, new SkipButton(number, response, audioFile ,nextNode));
	}
	
	public void addButton(int number, Node nextNode) {
		this.buttonList.put(number, new SkipButton(number, nextNode));
	}
	
	public NodeButton removeButton(int number) {
		return this.buttonList.remove(number);
	}
	
	public NodeButton getButton(int buttonNumber) {
		if (!this.buttonList.containsKey(buttonNumber)) {
			throw new IllegalArgumentException("This button does not exist yet");
		}
		return this.buttonList.get(buttonNumber);
	}
	
	public NodeButton[] getButtons() {
		NodeButton[] result = new NodeButton[this.buttonList.values().size()];
		int count = 0;
		System.out.println(this.buttonList.keySet());
		for (NodeButton b: this.buttonList.values())
		{
			System.out.println(b + "\t" + count);
			result[count] = b;
			count++;
		}
		//System.out.println(result.length);
		return result;
	}
	
	public void addRepeatButton(int number) {
		this.buttonList.put(number, new RepeatButton(number));
	}
	
	public void addRepeatButton(int number, String repeatText) {
		this.buttonList.put(number, new RepeatButton(number, repeatText));
	}
	
	public Node getNext(int buttonNumber) {
		
		NodeButton button = this.buttonList.get(buttonNumber);
		if (button.getClass() == SkipButton.class) {
			return ((SkipButton) button).getNextNode();
		}
		return null;
	}
	
	public void setAudioFile(String audioFile) {
		
		FileSearch fileSearch = new FileSearch();
        
		fileSearch.searchDirectory(new File(System.getProperty("user.dir")), audioFile);
	
		int count = fileSearch.getResult().size();
		if (count == 0) {
		    throw new IllegalArgumentException("File does not exist");
		} else {
		 this.audioFile = audioFile;
		}
		
	}
	
	public String getAudioFile() {
		return this.audioFile;
	}

	public Node getNextNode(String name) {
		for (int i : this.buttonList.keySet()) {
			NodeButton b = this.getButton(i);
			if (b.getClass() == SkipButton.class) {
				SkipButton button = ((SkipButton) b);
				if (button.getNextNode() != null && button.getNextNode().getName().equals(name)) {
					return button.getNextNode();
				}
			}
		}
		throw new IllegalArgumentException("Node name does not exist as next node");
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Node other = (Node) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
