package enamel;

public class Response extends Event {
	private String text;
	
	public Response(String response) {
		this.text = response;
	}
	
	public String getData() {
		// TODO Auto-generated method stub
		return this.text;
	}

	public void setData(String data) {
		// TODO Auto-generated method stub
		this.text = data;
	}
	
	

}
