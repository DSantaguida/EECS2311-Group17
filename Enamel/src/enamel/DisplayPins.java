package enamel;

public class DisplayPins extends Event {
	
	private BrailleCell pins;
	private int cellNumber;
	
	public DisplayPins() {
		this("00000000", 0);
		// TODO Auto-generated constructor stub
		
	}
	
	public DisplayPins(String pins, int cellNumber) {
		super();
		this.pins = new BrailleCell();
		this.pins.setPins(pins);
		this.cellNumber = cellNumber;
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		String result = "";
		for (int i = 0; i < pins.getNumberOfPins(); i++) {
			if (pins.getPinState(i) == true) {
				result = result + "1";
			} else {
				result = result + "0";
			}
		}
		return result;
	}

	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		this.pins.setPins(data);
	}
	
	public void setCellNumber(int num) {
		this.cellNumber = num;
	}
	
	public int getCellNumber() {
		return this.cellNumber;
	}

}
