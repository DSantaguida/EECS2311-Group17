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
			this.fileWriter = new PrintWriter( new FileWriter(new File(System.getProperty("user.dir") + "/test_" + this.s.getFileName())));
			System.out.println("First");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			File file = new File(System.getProperty("user.dir") + "/test_" + this.s.getFileName());
			try {
				System.out.println("first nested");
				file.createNewFile();
				this.initializeScanner();
				System.out.println("finished");
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
	
	private void write(Node n) throws IOException {
		if (!n.equals(this.s.getHead())) {
			write("/~" + n.getName() + "\n");
			write("/~reset-buttons\n");
			}			
		
		
		write(n.getTimeline());

//		if (!n.getResponse().equals("")) {
//			write(n.getResponse() + "\n");
//		}
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
		write(b.getTimeline());
		write("/~skip:" + b.getNextNode());
	}
	
	private void write(String s) {
		this.fileWriter.write(s);
	}
	
	private void write(Timeline t)
	{
		for(Event e: t.getEvents())
		{
			if (e.getClass() == Pause.class)
			{
				write("/~pause:" + ((Pause)e).getData() + "\n");
			}
			else if (e.getClass() == Response.class)
			{
				write(((Response)e).getData() + "\n");
			}
			else if (e.getClass() == Sound.class)
			{
				write("/~sound:" + ((Sound)e).getData() +"\n");
			}
			else if(e.getClass() == DisplayPins.class)
			{
				write("/~disp-cell-pins:" + ((DisplayPins)e).getCellNumber() + " " + ((DisplayPins)e).getPins());
			}
		}
	}
	
	public String getButtonName(SkipButton b) {
		return arr[b.getNumber()];
	}
}
