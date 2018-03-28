package enamel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ScenarioNode {
	
	private boolean repeat;
	//tracks how many nodes were created (may need to be static?):
	private int nodeTrack;
	private String[] skipName;
	private BrailleCell b;
	private Scenario p;
	private Node thisNode;
	private Node nextNode1;
	private String buttonSound = "";
	private String buttonMessage;
	private String nextNodeName;
	private int numOfCells;
	private int[] numberOfButtons; //keeping track of number of buttons created per node
	private int numOfButtons; //number of buttons passed by scenario file, used for scenario formatting
	private boolean userInput;
	private boolean createdNode;
	private int buttonCount;
	private int skipButtonCounter;
	
	private FileReader reader;
	
	/*
	 * What does this class do?
	 * It will take in each "node" of the scenario.
	 * Within the provided scenarios, each scenario node is delimited by a
	 * certain phrase.
	 */
	public ScenarioNode(){
		b = new BrailleCell();
		p = new Scenario();
		thisNode = p.createNode();
		p.addNode(thisNode);
		skipName = new String[50];
		numberOfButtons = new int[50];
		skipButtonCounter = 0;
	}
	
	public int nodeTrack() {
		return this.nodeTrack;
	}
	
	public void nodeTrackIncrement() {
		this.nodeTrack++;
	}
	
	void nodeDelimiter(String fileLine) {

		if (fileLine.length() >= 4 && fileLine.substring(0, 4).equals("Cell") && 
			fileLine.substring(5).matches("^[0-9]*[1-9][0-9]*$")) {
			int num = Integer.parseInt(fileLine.substring(5));
			this.numOfCells = num;
			this.nodeTrack = 0;
			this.numberOfButtons[nodeTrack] = 0;
			p.setCells(this.numOfCells);
		}
		else if (fileLine.equals("\\s")){
			
		}
		else if (fileLine.length() >= 6 && fileLine.substring(0, 6).equals("Button") && 
				fileLine.substring(7).matches("^[0-9]*[1-9][0-9]*$")) {
			int num = Integer.parseInt(fileLine.substring(7));
			this.numOfButtons = num;
			p.setButtons(this.numOfButtons);
		}
		else if (!userInput && fileLine.length() >= 8 && fileLine.substring(0, 8).equals("/~sound:")) {
			fileLine.substring(8);
		}
		else if (fileLine.length() >= 7 && fileLine.substring(0, 7).equals("/~skip:")) {
			String skipLine = fileLine.substring(7);
			this.nextNodeName = skipLine;
			try{
				this.nextNode1 = thisNode.getNextNode(nextNodeName);
			}
			catch(IllegalArgumentException e)
			{
				this.nextNode1 = p.createNode(nextNodeName);
				p.addNode(nextNode1);
			}
			SkipButton b = (SkipButton) thisNode.getButton(buttonCount);
			b.addToResponse(buttonMessage);			
			b.setAudioFile(this.buttonSound);
			b.setNextNode(nextNode1);
			buttonMessage = "";
			this.buttonSound = "";
		//	this.thisNode.addButton(this.buttonCount, this.buttonMessage, this.buttonSound, this.nextNode1);
			
			this.buttonCount++;
			//need to connect each response node to the next node (NEXTT)
			if (this.buttonCount >= numberOfButtons[nodeTrack]){
				this.userInput = false;
				nodeTrackIncrement();
				this.buttonCount = 0;
				createdNode = true;
			}
		}
		else if (this.createdNode && fileLine.substring(0).equals("/~" + this.nextNodeName)) {
			thisNode = thisNode.getNextNode(nextNodeName);
			createdNode = false;
		}
		else if (fileLine.length() >= 8 && fileLine.substring(0, 8).equals("/~pause:")) {
			//where to store pause element? ***********
			//this.pause = fileLine.substring(8);
		}
		else if (fileLine.length() >= 16 && fileLine.substring(0, 16).equals("/~repeat-button:")) {
			String repeatButtonLine = fileLine.substring(16);
			int buttonIndex = Integer.parseInt(repeatButtonLine);
			//buttonIndex may not be a unique integer because skip button also produces integers
			//to account for this, we add 100
			thisNode.addRepeatButton(buttonIndex);
			//this.numberOfButtons[nodeTrack]++; THIS should not increment because this does not create new node
		}
		else if (fileLine.length() >= 8 && fileLine.substring(0, 8).equals("/~repeat")) {
			this.repeat = true;
			if (!thisNode.getRepeatedText().isEmpty()){
				thisNode.setRepeatedText(null);
			}
		}
		else if (this.repeat) {
			if (fileLine.length() >= 11 && fileLine.substring(0, 11).equals("/~endrepeat")) {
				this.repeat = false;
			} else {
				thisNode.addToRepeatedText(fileLine);
			}
		}
		else if (fileLine.length() >= 15 && fileLine.substring(0, 15).equals("/~reset-buttons")) {
			//sets all buttons to be unresponsive
		}
		else if (fileLine.length() >= 14 && fileLine.substring(0, 14).equals("/~skip-button:")) {
			String skipLine = fileLine.substring(14); //gives string after "/~skip-button:"
			String[] split = skipLine.split("\\s"); //split string delimited by space
			int buttonIndex = Integer.parseInt(split[0]); //jbutton index
			String buttonName = split[1]; //key phrase that button will skip to
			this.skipName[this.numberOfButtons[nodeTrack]] = buttonName;
			skipButtonCounter++;
			if (this.thisNode.getButtons().length != buttonIndex) {
				try{
					this.nextNode1 = thisNode.getNextNode(buttonName);
				}
				catch(IllegalArgumentException e)
				{
					this.nextNode1 = p.createNode(buttonName);
					p.addNode(nextNode1);
				}
				
				SkipButton b = (SkipButton) thisNode.getButton(buttonCount);
				if (!buttonSound.equals(""))
					b.setAudioFile(buttonSound);
				b.setNextNode(nextNode1);
				this.nextNode1.addButton(buttonIndex);
				thisNode = nextNode1;
				//this.userInput = false;
			}
			else{
			this.thisNode.addButton(buttonIndex);
			this.numberOfButtons[nodeTrack]++;
			}
		}
		else if (fileLine.length() >= 15 && fileLine.substring(0, 15).equals("/~disp-clearAll")) {
			int[] pins = new int[8];
			for (int i = 0; i < this.numOfCells; i++) {
				thisNode.setPins(pins, i);
			}
		}
		else if (fileLine.length() >= 17 && fileLine.substring(0, 17).equals("/~disp-cell-pins:")) {
			String breakDown = fileLine.substring(17);
			String[] split = breakDown.split("\\s");
			int brailleCell = Integer.parseInt(split[0]);
			if (!userInput) {
				thisNode.setPins(split[1], brailleCell);
			}
			else {
				NodeButton button = thisNode.getButton(buttonCount);
				if (button.getClass() == SkipButton.class) {
					
					((SkipButton)button).setPins(split[1], brailleCell);
				}
			};
		}
		else if (fileLine.length() >= 14 && fileLine.substring(0, 14).equals("/~disp-string:")) {
			String line = fileLine.substring(14);
			/*
			 * Functionality note:  if string is less than cells displayed, will leave remaining cells empty.
			 * If string is greater than cells displayed, then it will be cut off.
			 */
			
			for (int i = 1; i < line.length() && line.length() <= this.numOfCells; i++) {
				char a = line.charAt(i);
				String brailleString = b.getCharacter(a);
				if (!userInput) {
					thisNode.setPins(brailleString, i);
				}
				else {
					NodeButton button = thisNode.getButton(buttonCount);
					if (button.getClass() == SkipButton.class) {
						int[] arr = new int[brailleString.length()];
						for (int j = 0; j < brailleString.length(); j++){
							arr[j] = brailleString.charAt(j);
						}
						((SkipButton)button).setPins(arr, i);
					}
				}
			}
				//display string in braille cells, if greater than number of cells, then string will be cut off
				//if smaller than number of cells, remaining cells are cleared
		}
		else if (fileLine.length() >= 17 && fileLine.substring(0, 17).equals("/~disp-cell-char:")) {
			/*
			 * Limitation: this does not read capital letters, only lowercase (limitation of braille alphabet
			 * in BrailleCell class).  Will need to add to alphabet for uppercase.
			 */
			String charac = fileLine.substring(17);
			String[] param = charac.split("\\s");
			int brailleCell = Integer.parseInt(param[0]);
			char dispChar = param[1].charAt(0);
			String characterBraille = b.getCharacter(dispChar);
			if (!userInput) {
				thisNode.setPins(characterBraille, brailleCell);
			}
			else {
				NodeButton button = thisNode.getButton(buttonCount);
				if (button.getClass() == SkipButton.class) {
					int[] arr = new int[characterBraille.length()];
					for (int i = 0; i < characterBraille.length(); i++){
						arr[i] = characterBraille.charAt(i);
					}
					((SkipButton)button).setPins(arr, brailleCell);
				}
			}
		}
//		else if (fileLine.length() >= 18 && fileLine.substring(0, 18).equals("/~disp-cell-raise:")) {
//			String breakdown = fileLine.substring(18);
//			String[] param = breakdown.split("\\s");
//			int brailleCell = Integer.parseInt(param[0]);
//			int pinToRaise = Integer.parseInt(param[1]);
//			thisNode.setPin(brailleCell, pinToRaise, 1);
//		}
//		else if (fileLine.length() >= 18 && fileLine.substring(0, 18).equals("/~disp-cell-lower:")) {
//			String breakdown = fileLine.substring(18);
//			String[] param = breakdown.split("\\s");
//			int brailleCell = Integer.parseInt(param[0]);
//			int pinToRaise = Integer.parseInt(param[1]);
//			thisNode.setPin(brailleCell, pinToRaise, 0);
//		}
		else if (fileLine.length() >= 18 && fileLine.substring(0, 18).equals("/~disp-cell-clear:")) {
			String breakdown = fileLine.substring(18);
			String[] param = breakdown.split("\\s");
			int brailleCell = Integer.parseInt(param[0]);
			int[] pins = new int[8];
			if (!userInput) {
				thisNode.setPins(pins, brailleCell);
			}
			else {
				NodeButton button = thisNode.getButton(buttonCount);
				if (button.getClass() == SkipButton.class) {
					((SkipButton)button).setPins(pins, brailleCell);
				}
			}
			
		}
		/* this key phrase is not on the scenario format document
		else if (fileLine.length() >= 21 && fileLine.substring(0, 21).equals("/~disp-cell-lowerPins")) {
			dispCellRaise("0");
		}
		*/
		else if (fileLine.length() >= 12 && fileLine.substring(0, 12).equals("/~user-input")) {
			//nothing needs to be done here as the nodes and edges are created with /~skip-button
			//nodeTrackIncrement();
			this.userInput = true;
		}

		else if (this.userInput && fileLine.substring(0).equals("/~" + this.skipName[buttonCount])) {
			//detecting /~ONEE, i.e., first button
		}
		else if (this.userInput && fileLine.length() > 1
				&& !(fileLine.substring(0, 2).equals("/~"))) {
			buttonMessage = fileLine;
			if (this.thisNode.getButton(buttonCount).getClass() == SkipButton.class)
				((SkipButton)this.thisNode.getButton(buttonCount)).addToResponse(buttonMessage);
		}
		else if (this.userInput && fileLine.length() >= 8
				&& fileLine.substring(0, 8).equals("/~sound:")) {
			String soundFile = fileLine.substring(8);
			buttonSound = soundFile;
		}
		else if (this.userInput && fileLine.length() >= 18
				&& fileLine.substring(0, 18).equals("/~disp-cell-clear:")) {
			String breakdown = fileLine.substring(18);
			String[] param = breakdown.split("\\s");
			int brailleCell = Integer.parseInt(param[0]);
			int[] pins = new int[8];
			NodeButton button = thisNode.getButton(buttonCount);
			if (button.getClass() == SkipButton.class) {
				((SkipButton)button).setPins(pins, brailleCell);
			}
			
		}
		else if (fileLine.trim().length() > 0){ //no key phrase, therefore must be plain text
			if (!userInput) {
				thisNode.addToResponse(fileLine + "\n");
			}
			else {
				NodeButton button = thisNode.getButton(buttonCount);
				if (button.getClass() == SkipButton.class) {
					((SkipButton)button).addToResponse(fileLine + "\n");
				}
			}
		}
		
	}
	
	private void play() {
		try {
		 BufferedReader bufferedReader = new BufferedReader(reader);

		    String line;
		    
		    while ((line = bufferedReader.readLine()) != null) {
		    	nodeDelimiter(line);
		    }
		    
		    reader.close();
		    bufferedReader.close();
		    
//		    for(Node n: p.getNodes())
//		    {
//		    	System.out.println("Hi");
//		    	NodeButton[] array = n.getButtons();
//		    	System.out.println(Arrays.toString(array));
//		    	for(int i = 0; i < array.length; i++)
//		    	{
//		    		NodeButton b = array[i];
//		    		if (b.getClass() == SkipButton.class) {
//						SkipButton button = ((SkipButton) b);
//						p.setEdge(n, button.getNextNode(), b.getNumber());
//		    		}
//		    		
//		    	}
//		    }
		    for (NodeButton b: thisNode.getButtons())
		    {
		    	if (b.getClass() == SkipButton.class)
		    	{
		    		if(((SkipButton)b).getNextNode() == null)
		    			thisNode.removeButton(b.getNumber());
		    	}
		    }
		    createEdges(p.getHead());
		    for (int i = 0; i < numOfButtons; i++)
		    {
		    	thisNode.addButton(i);
		    }
		    new EditingScreen(p);
		    
		}
		catch (IOException e) {
			e.printStackTrace();
		}
			/*
			if (!fileScanner.hasNextLine()) {
				fileScanner.close();
				// The if statement is created to check if there is an
				// /~endrepeat for a previously
				// declared /~repeat in the scenario file.
				if (repeat) {
					errorLog("Exception error: IllegalArgumentException",
							"Expected the keyphrase: \n" + "/~endrepeat "
									+ "\n ,somewhere in the scenario file, to indicate when "
									+ " to stop storing the text to be repeated, but the keyphrase was "
									+ "nowhere to be found." + "\n The program ended due to an "
									+ "incorrect formatted scenario file.");
				}
				exit();
			}*/
		} /*catch (Exception e) {
			errorLog("Exception error : " + e.toString(),
					"Strange error occurred if you are able to read this message. Possibilities "
							+ "could include possible file corruption, or that you have enter characters that "
							+ "could not be read/interpreted.");
		}*/
	public void createEdges(Node n)
	{
		
		NodeButton[] array = n.getButtons();
    	for(int i = 0; i < array.length; i++)
    	{
    		NodeButton b = array[i];
    		if (b.getClass() == SkipButton.class) {
				SkipButton button = ((SkipButton) b);
				p.setEdge(n, button.getNextNode(), b.getNumber());
				createEdges(button.getNextNode());
    		}
    		
    	}
	}
	
	public Scenario setScenarioFile(String scenarioFile) {
		//try {
			
			//File f = new File(scenarioFile);
			//fileScanner = new Scanner(f);
			//String absolutePath = f.getAbsolutePath();
			//scenarioFilePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
			try {
				p.setName(scenarioFile);
			    this.reader = new FileReader(scenarioFile);
			   

			   } catch (IOException e) {
			    e.printStackTrace();
			   }
			play();
			
		/*} catch (Exception e) {
			errorLog("Exception error: " + e.toString(),
					"Expected the directory path of the scenario file to"
							+ " a file exists in the project folder. \n Could not find directory to path: "
							+ scenarioFile + " \n Perhaps" + " you forgot to add the file to the directory or "
							+ " you are looking for a different directory?");
		}*/
			return p;
	}
	
	private void errorLog(String exception, String message) {
		Logger logger = Logger.getLogger("ERROR_LOG");
		FileHandler fh;

		// The try-catch block is to format the Logger class so that the error
		// log file is easier to understand.
		try {
			File f = new File("ERROR_LOG.txt");
			fh = new FileHandler(f.toString());

			logger.addHandler(fh);
			logger.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			logger.warning(exception);
			logger.info(message);
			fh.close();

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		exit();
	}
	
	private void exit() {
		Thread.currentThread().interrupt();
	}

}
