package enamel;

public class Pause extends Event {
	private int time;

	public Pause() {
		this(0);
		// TODO Auto-generated constructor stub
	}
	
	public Pause(int pauseTime) {
		time = pauseTime;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return Integer.toString(time);
	}

	@Override
	public void setData(String data) throws NumberFormatException {
		// TODO Auto-generated method stub
		if (data.isEmpty() || data == null)
			throw new NumberFormatException("Is not a number only string");
		time = Integer.parseInt(data);
	}

}
