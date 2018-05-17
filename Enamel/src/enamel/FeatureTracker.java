package enamel;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FeatureTracker {
	private int chooseExistingCounter, createNewCounter;
	private FileReader reader;

	public FeatureTracker() {
		this.chooseExistingCounter = 0;
		this.createNewCounter = 0;
		try {
			this.reader = new FileReader("featuretracker.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		readFile();
	}

	/*
	 * reads the textfile named "featuretracker.txt" for all counter values for
	 * all features present in the application. It will update the values as it
	 * reads those values.
	 */
	private void readFile() {
		try {
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				readCounter(line);
			}
			reader.close();
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * called by readFile and attempts to read every feature counter value in
	 * the textfile.
	 */
	void readCounter(String line) {
		if (line.length() >= 12 && line.substring(0, 13).equals("chooseexisting")) {
			String breakDown = line.substring(13);
			String[] split = breakDown.split("\\s");
			chooseExistingCounter = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 8 && line.substring(0, 8).equals("createnew")) {
			String breakDown = line.substring(8);
			String[] split = breakDown.split("\\s");
			createNewCounter = Integer.parseInt(split[1]);
		}
	}

	public void saveCounter() {
		try {
			BufferedReader file = new BufferedReader(new FileReader("featuretracker.txt"));
			String saveLine;
			String input = "";
			while ((saveLine = file.readLine()) != null) {
				input += saveLine + '\n';
			}
			input = input.replaceAll("chooseexisting " + "\\d+", "chooseexisting " + this.chooseExistingCounter);
			input = input.replace("createnew " + "\\d+", "createnew " + this.createNewCounter);
			FileOutputStream out = new FileOutputStream("featuretracker.txt");
			out.write(input.getBytes());
			file.close();
			out.close();
		} catch (Exception e) {
			System.out.println("Error overwriting file: " + "featuretracker.txt");
			e.printStackTrace();
		}
	}
	
	public void topFive() {
		//this class will determine the top five most frequently used buttons
	}

	// when this method is called for a specific button, will update with
	// one additional click pressed
	public void incrementCounterChooseExisting() {
		this.chooseExistingCounter++;
		System.out.println("The alpha counter is " + chooseExistingCounter);
	}
	
	public void incrementCounterCreateNew() {
		this.createNewCounter++;
	}
}
