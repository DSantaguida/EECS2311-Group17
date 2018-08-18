package enamel;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FeatureTracker {
	private int chooseExistingCounter, createNewCounter, saveCounter;
	private int[] counterNum;
	private FileReader reader;

	public FeatureTracker() {
		this.chooseExistingCounter = 0;
		this.createNewCounter = 0;
		this.saveCounter = 0;
		this.counterNum = new int[3];
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
		else if (line.length() >= 3 && line.substring(0, 3).equals("save")) {
			String breakDown = line.substring(3);
			String[] split = breakDown.split("\\s");
			saveCounter = Integer.parseInt(split[1]);
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
			input = input.replace("save" + "\\d+", "save " + this.saveCounter);
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
		int index1, index2, index3, index4, index5;
		int value1, value2, value3, value4, value5;
		value1 = Integer.MIN_VALUE;
		value2 = Integer.MIN_VALUE;
		value3 = Integer.MIN_VALUE;
		value4 = Integer.MIN_VALUE;
		value5 = Integer.MIN_VALUE;
		for (int i = 0; i < counterNum.length; i++) {
			if (counterNum[i] > value1) {
				value5 = value4;
				value4 = value3;
				value3 = value2;
				value2 = value1;
				value1 = counterNum[i];
				index1 = i;
			}
			else if (counterNum[i] > value2) {
				value5 = value4;
				value4 = value3;
				value3 = value2;
				value2 = counterNum[i];
				index2 = i;
			}
			else if (counterNum[i] > value3) {
				value5 = value4;
				value4 = value3;
				value3 = counterNum[i];
				index3 = i;
			}
			else if (counterNum[i] > value4) {
				value5 = value4;
				value4 = counterNum[i];
				index4 = i;
			}
			else if (counterNum[i] > value5) {
				value5 = counterNum[i];
				index5 = i;
			}
		}
	}

	// when this method is called for a specific button, will update with
	// one additional click pressed
	public void incrementCounterChooseExisting() {
		this.chooseExistingCounter++;
		//array index 0
		counterNum[0]++;
	}
	
	public void incrementCounterCreateNew() {
		this.createNewCounter++;
		//array index 1
		counterNum[1]++;
	}
	
	public void incrementCounterSave() {
		this.saveCounter++;
		counterNum[2]++;
	}
}
