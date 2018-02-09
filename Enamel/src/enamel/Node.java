package enamel;

import java.util.*;

public class Node {
	private int id;
	private String name;
	private String response;
	private String repeatText;
	Map<Integer, NodeButton> buttonList;
	private Map<Integer, int[]> pins;
	
	public Node(int id) {
		this(id, String.valueOf(id), null, null);
	}
	
	public Node(int id, String name) {
		this(id, name, null, null);
	}
	
	public Node(int id, String name, String response) {
		this(id, name, null, response);
	}
	
	public Node(int id, String name, Map<Integer, int[]> pins) {
		this(id, name, pins, null);
	
	}
	
	public Node(int id, String name, Map<Integer, int[]> pins, String response) {
		this.id = id;
		this.name = name;
		this.response = response;
		this.pins = pins;
		this.buttonList = new HashMap<>();
	}
	
	public Node(int id, Map<Integer, int[]> pins) {
		this(id, String.valueOf(id), pins, null);
	}
	
	public Node(int id, String name, Map<Integer, int[]> listOfPins, String response, Map<Integer, NodeButton> buttonList) {
		this(id, name, listOfPins, response, null, buttonList);
	}
	
	public Node(int id, String name, Map<Integer, int[]> listOfPins, String response, String repeatText) {
		this.id = id;
		this.name = name;
		this.pins = listOfPins;
		this.response = response;
		this.repeatText = repeatText;
		this.buttonList = new HashMap<>(buttonList);
	}
	
	public Node(int id, String name, Map<Integer, int[]> listOfPins, String response, String repeatText, Map<Integer, NodeButton> buttonList) {
		this.id = id;
		this.name = name;
		this.pins = listOfPins;
		this.response = response;
		this.repeatText = repeatText;
		this.buttonList = new HashMap<>(buttonList);
	}
	
	public Node(Node other) {
		this.id = other.id;
		this.name = other.name;
		this.pins = other.pins;
		this.response = other.response;
		this.repeatText = other.repeatText;
		this.buttonList = other.buttonList;
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
	
	public void setRepeatedText(String repeatText) {
		this.repeatText = repeatText;
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
		this.pins.get(cellNumber)[pin] = value;
	}
	
	public void addButton(int number) {
		this.buttonList.put(number, new NodeButton(number));
	}
	
	public void addButton(int number, String response, Node nextNode) {
		this.buttonList.put(number, new NodeButton(number, response, nextNode));
	}
	
	public void addButton(int number, Node nextNode) {
		this.buttonList.put(number, new NodeButton(number, nextNode));
	}
	
	public NodeButton getButton(int buttonNumber) {
		return this.buttonList.get(buttonNumber);
	}
	
	public Node getNext(int buttonNumber) { 
		return this.buttonList.get(buttonNumber).getNextNode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buttonList == null) ? 0 : buttonList.hashCode());
		result = prime * result + pins.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((repeatText == null) ? 0 : repeatText.hashCode());
		result = prime * result + ((response == null) ? 0 : response.hashCode());
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
		if (buttonList == null) {
			if (other.buttonList != null) {
				return false;
			}
		} else if (!buttonList.equals(other.buttonList)) {
			return false;
		}
		if (!pins.equals(other.pins)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (repeatText == null) {
			if (other.repeatText != null) {
				return false;
			}
		} else if (!repeatText.equals(other.repeatText)) {
			return false;
		}
		if (response == null) {
			if (other.response != null) {
				return false;
			}
		} else if (!response.equals(other.response)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.valueOf(id);
	}

}