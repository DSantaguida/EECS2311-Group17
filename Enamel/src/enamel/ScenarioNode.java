package enamel;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.sun.speech.freetts.Voice;

public class ScenarioNode {
	
	private ScenarioNode node;
	private Scanner fileScanner;
	private String scenarioFilePath;
	private Voice voice;
	private boolean repeat;
	//tracks how many nodes were created (may need to be static?):
	private int numOfNodes;
	//key phrases:
	private String cellClear;
	private String sound;
	private String skip;
	private String pause;
	private String repeatButton;
	private boolean resetButtons;
	private String[] skipButton;
	private boolean clearAll;
	private String displayPins;
	private String displayString;
	private String dispCellChar;
	private String dispCellRaise;
	private String dispCellLower;
	private String dispCellClear;
	private boolean userInput;
	private String text;
	
	/*
	 * What does this class do?
	 * It will take in each "node" of the scenario.
	 * Within the provided scenarios, each scenario node is delimited by a
	 * certain phrase.
	 */
	
	public ScenarioNode(){
		//default constructor
	}
	
	public ScenarioNode(String stuff, ScenarioNode next) {
		numNodeIncrement();
	}
	
	public int numberOfNodes() {
		return this.numOfNodes;
	}
	
	private void numNodeIncrement() {
		this.numOfNodes++;
	}
	
	private void nodeDelimiter(String fileLine) {
		if (this.numOfNodes == 0){ //creating first node
			if (fileLine.length() >= 8 && fileLine.substring(0, 8).equals("/~sound:")) {
				this.sound = fileLine.substring(8);
			}
			else if (fileLine.length() >= 7 && fileLine.substring(0, 7).equals("/~skip:")) {
				this.skip = fileLine.substring(7);
			}
			else if (fileLine.length() >= 8 && fileLine.substring(0, 8).equals("/~pause:")) {
				this.pause = fileLine.substring(8);
			}
			else if (fileLine.length() >= 16 && fileLine.substring(0, 16).equals("/~repeat-button:")) {
				this.repeatButton = fileLine.substring(16);
			}
			else if (fileLine.length() >= 8 && fileLine.substring(0, 8).equals("/~repeat")) {
				//repeatedText.clear();
				repeat = true;
			}
			else if (fileLine.length() >= 15 && fileLine.substring(0, 15).equals("/~reset-buttons")) {
				this.resetButtons = true;
			}
			else if (fileLine.length() >= 14 && fileLine.substring(0, 14).equals("/~skip-button:")) {
				String skipLine = fileLine.substring(14);
				this.skipButton = skipLine.split("\\s");
			}
			else if (fileLine.length() >= 15 && fileLine.substring(0, 15).equals("/~disp-clearAll")) {
				this.clearAll = true;
			}
			else if (fileLine.length() >= 17 && fileLine.substring(0, 17).equals("/~disp-cell-pins:")) {
				this.displayPins = fileLine.substring(17);
			}
			else if (fileLine.length() >= 14 && fileLine.substring(0, 14).equals("/~disp-string:")) {
				this.displayString = fileLine.substring(14);
			}
			else if (fileLine.length() >= 17 && fileLine.substring(0, 17).equals("/~disp-cell-char:")) {
				this.dispCellChar = fileLine.substring(17);
			}
			else if (fileLine.length() >= 18 && fileLine.substring(0, 18).equals("/~disp-cell-raise:")) {
				this.dispCellRaise = fileLine.substring(18);
			}
			else if (fileLine.length() >= 18 && fileLine.substring(0, 18).equals("/~disp-cell-lower:")) {
				this.dispCellLower = fileLine.substring(18);
			}
			else if (fileLine.length() >= 18 && fileLine.substring(0, 18).equals("/~disp-cell-clear:")) {
				this.dispCellClear = fileLine.substring(18);
			}
			/* this key phrase is not on the scenario format document
			else if (fileLine.length() >= 21 && fileLine.substring(0, 21).equals("/~disp-cell-lowerPins")) {
				dispCellRaise("0");
			}
			*/
			else if (fileLine.length() >= 12 && fileLine.substring(0, 12).equals("/~user-input")) {
				this.userInput = true;
				ScenarioNode p = new ScenarioNode();
			}
			else { //no key phrase, therefore must be plain text
				this.text = fileLine;
			}
		}
		if (this.numOfNodes > 0){ // i.e., not the first node
			//will fill in with the same control statements as above
		}
	}
	
	private void play() {
		String fileLine;
		try {
			while (fileScanner.hasNextLine()) {
				fileLine = fileScanner.nextLine();
				nodeDelimiter(fileLine);
			}
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
			}
		} catch (Exception e) {
			errorLog("Exception error : " + e.toString(),
					"Strange error occurred if you are able to read this message. Possibilities "
							+ "could include possible file corruption, or that you have enter characters that "
							+ "could not be read/interpreted.");
		}
	}
	
	public void setScenarioFile(String scenarioFile) {
		try {

			File f = new File(scenarioFile);
			fileScanner = new Scanner(f);
			String absolutePath = f.getAbsolutePath();
			scenarioFilePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
			play();
		} catch (Exception e) {
			errorLog("Exception error: " + e.toString(),
					"Expected the directory path of the scenario file to"
							+ " a file exists in the project folder. \n Could not find directory to path: "
							+ scenarioFile + " \n Perhaps" + " you forgot to add the file to the directory or "
							+ " you are looking for a different directory?");
		}
	}
	
	private void errorLog(String exception, String message) {
		Logger logger = Logger.getLogger("ERROR_LOG");
		FileHandler fh;

		System.out.println(message);

		speak("Error! Something went wrong in the program! Please consult a teacher "
				+ "or administrator for assistance! Also please view the ERROR_LOG file for more details");
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
	
	private void speak(String text) {
		try {
			voice.speak(text);
		} catch (Exception e) {
			errorLog("Exception error: " + e.toString(),
					"Possible error could be that you are missing some specific jar files"
							+ "from the FreeTTS website or that you may have removed them by accident.");
		}
	}
	
	private void exit() {
		Thread.currentThread().interrupt();
	}

}