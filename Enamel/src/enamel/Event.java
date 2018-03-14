package enamel;

public abstract class Event {
	
	public abstract String getData();
	public abstract void setData(String data);
	
	
	@Override
	public String toString() {
		return this.getData();
	}
}
