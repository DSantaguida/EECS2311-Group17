package enamel;

import java.util.ArrayList;

public class Timeline {
	private ArrayList<Event> t;
	
	public Timeline(){
		this.t = new ArrayList<>();
	}
	
	public Timeline(Timeline other) {
		this.t = new ArrayList<>(other.t);
	}
	
	public void addEvent(Event e) {
		t.add(e);
	}
	
	public void removeEvent(Event e) {
		t.remove(e);
	}
	
	public Event removeEvent(int index) {
		return t.remove(index);
	}
	
	public void change(int index, Event e) {
		t.set(index, e);
	}
	
	public void insert(int index, Event e) {
		t.add(index, e);
	}
	
	public void changePosition(int oldIndex, int newIndex, Event e) {
		if (newIndex <= oldIndex) {
			t.add(newIndex, e);
		} else {
			t.add(newIndex+1, e);
			t.remove(oldIndex);
		}
	}
	
	public ArrayList<Event> getEvents() {
		return new ArrayList<>(this.t);
	}
	
	public Event getEvent(int index) {
		return this.t.get(index);
	}
	public void clearTimeline() {
		this.t.clear();
	}
	public int size() {
		return this.t.size();
	}

	@Override
	public String toString() {
		return t.toString();
	}
}
