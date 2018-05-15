package enamel;

import java.io.File;

public class Sound extends Event {
	
	private String fileName;
	
	public Sound(String fileName) {
		super();
		// TODO Auto-generated constructor stub
		this.setData(fileName);
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return this.fileName;
	}

	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		FileSearch fileSearch = new FileSearch();
		
        //try different directory and filename 
		fileSearch.searchDirectory(new File(System.getProperty("user.dir")), data);
	
		int count = fileSearch.getResult().size();
		if(count == 0){
		    throw new IllegalArgumentException("File does not exist");
		}else{
		 this.fileName = data;
		}
	}

}
