package enamel;

import java.util.*;
import java.io.*;

public class ScenarioWriter {
	private PrintWriter fileWriter;
	private Scenario s; 
	private Set<Node> written;
	private String[] arr = {
			"ONEE",
			"TWOO",
			"THREE",
			"FOURR",
			"FIVEE",
			"SIXX",
			"SEVENN",
			"EIGHTT",
			"NINE",
			"TEN"
	};;
	
	public ScenarioWriter(Scenario scen) {
		this.s = scen;
		this.written = new HashSet<>();
		initializeScanner();
		
	}
	
	private void initializeScanner() {
		System.out.println(s.getHead());
		try {
			this.fileWriter = new PrintWriter( new FileWriter(new File(System.getProperty("user.dir") + "/" + this.s.getName())));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			File file = new File(System.getProperty("user.dir") + this.s.getName());
			try {
				file.createNewFile();
				this.initializeScanner();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void changeFileName(String name) {
		this.s.setFileName(name);
		this.initializeScanner();
	}
	
	public void save() throws IOException {
		write("Cell " + s.getNumCells() + "\n");
		write("Button " + s.getNumButtons() + "\n");
		nodeWriter(s.getHead());
		this.fileWriter.close();
	}
	
	private void nodeWriter(Node n) throws IOException {
		if (!written.contains(n)) {
			write(n);
			written.add(n);
		}
		for (Node node : s.getNextNodes(n)) {
			nodeWriter(node);
		}
	}
	
	public void write(Node n) throws IOException {
		if (!n.equals(this.s.getHead())) {
			write("/~" + n.getName() + "\n");
			write("/~reset-buttons");
			for (int i = 0; i < s.getNumCells(); i++) {
				if (n.getPins(i) != null) {
					write("/~disp-cell-clear:" + i + "\n");
				}
			}			
		}
		if (n.getPauseTime() != 0) {
			write("/~pause:" + n.getPauseTime() + "\n");
		}
		if (!n.equals(this.s.getHead())) {
			write("/~reset-buttons");			
		}
//		if (!n.getRepeatedText().equals("")) {
//			write("/~repeat\n" + n.getRepeatedText() + "\n~endrepeat");
//		}
		boolean first = true;
		for (int i = 0; i < s.getNumCells(); i++) {
			if (n.getPins(i) != null) {
				if (first) {
					write("/~disp-cell-clear:" + i + "\n");
				}
				first = false;
				String s = "/~disp-cell-pins:" + i + " ";
				for (int j : n.getPins(i)) {
					s += String.valueOf(j);
				}
				write(s + "\n");
			}
		}
		if (!n.getAudioFile().equals("")){
			write("/~sound:" + n.getAudioFile() +"\n");
		}
		if (!n.getResponse().equals("")) {
			write(n.getResponse() + "\n");
		}
		Object[] arr = n.getButtons();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].getClass() == SkipButton.class) {
				SkipButton b = (SkipButton) arr[i];
				write("/~skip-button:" + b.getNumber() + " " + b.nextNode + "\n");
			} else if(arr[i].getClass() == RepeatButton.class) {
				RepeatButton b = (RepeatButton) arr[i];
				write("/~repeat-button:" + b.getNumber() + "\n");
			}
		}
		write("/~user-input" + "\n");
		for (Object button : arr) {
			if (button.getClass() == SkipButton.class) {
				write((SkipButton) button);
			}
		}
		write("\n");
		
	}
	
	private void write(SkipButton b) {
		write("/~" + getButtonName(b) + "\n");
		for (int i = 0; i < s.getNumCells(); i++) {
			if (b.getPins(i) != null) {
				write("/~disp-cell-clear:" + i + "\n");
			}
		}
		if (!b.getAudioFile().equals("")){
			write("/~sound:" + b.getAudioFile() +"\n");
		}
		if (!b.getResponse().equals("")) {
			write(b.getResponse() + "\n");
		}
		if (b.getPauseTime() != 0) {
			write("/~pause:" + b.getPauseTime() + "\n");
		}
		write("/~skip:" + b.getNextNode());
	}
	
	private void write(String s) {
		this.fileWriter.write(s);
	}
	
	public String getButtonName(SkipButton b) {
		return arr[b.getNumber()];
	}
}
