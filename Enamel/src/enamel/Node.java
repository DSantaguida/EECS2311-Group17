package enamel;

import java.util.*;

public class Node implements Sequential {
	private int id;
	private String name;
	private String repeatText;
	private Map<Integer, NodeButton> buttonList;
	private Timeline t;


	/**
	 * @param pauseTime the pauseTime to set
	 */
	@Override
	public void addPause(int pauseTime) {
	}

	public Node(int id) {
		this(id, String.valueOf(id));
	}
	
	
	public Node(int id, String name) {
		this.id = id;
		this.name = name;
		this.buttonList = new HashMap<>();
		this.t = new Timeline();
	}
	
	public Node(int id, String name, Map<Integer, NodeButton> buttonList) {
		this(id, name, "", buttonList);
	}
	
	public Node(int id, String name, String repeatText) {
		this.id = id;
		this.name = name;
		this.repeatText = repeatText;
		this.buttonList = new HashMap<>(buttonList);
	}
	
	public Node(int id, String name, String repeatText, Map<Integer, NodeButton> buttonList) {
		this.id = id;
		this.name = name;
		this.repeatText = repeatText;
		this.buttonList = new HashMap<>(buttonList);
	}
	
	public Node(Node other) {
		this.id = other.id;
		this.name = other.name;
		this.t = new Timeline(other.t);
		this.repeatText = other.repeatText;
		this.buttonList = new HashMap<>(other.buttonList);
	}
	
	public Timeline getTimeline()
	{
		return this.t;
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
	
	public void setRepeatedText(String repeatText) {
		this.repeatText = repeatText;
	}
	
	public void addToRepeatedText(String addition) {
		this.repeatText += addition;
	}
	
	public String getRepeatedText() {
		return this.repeatText;
	}
	
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
	
	public void addButton(int number) {
		this.buttonList.put(number, new SkipButton(number));
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
	
	@Override
	public void setAudioFile(String audioFile) {
		this.t.addEvent(new Sound(audioFile));
		
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
	
	@Override
	public Event getEvent(int index) {
		return this.t.getEvent(index);
	}
	
	@Override
	public void addEvent(Event e) {
		this.t.addEvent(e);
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

	@Override
	public void addEvent(Event e, int index) {
		this.t.insert(index, e);
	}

}
