package enamel;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FeatureTracker {
	private int chooseExistingCounter, createNewCounter, saveCounter;
	private int  button1, button2, button3, button4, button5, button6, button7, button8, button9, button10;
	private int runCounter, startCounter, deleteCounter, addCounter, mainMenuCounter;
	private int[] counterNum;
	private FileReader reader;

	public FeatureTracker() {
		this.chooseExistingCounter = 0;
		this.createNewCounter = 0;
		this.saveCounter = 0;
		this.button1 = 0;
		this.button2 = 0;
		this.button3 = 0;
		this.button4 = 0;
		this.button5 = 0;
		this.button6 = 0;
		this.button7 = 0;
		this.button8 = 0;
		this.button9 = 0;
		this.button10 = 0;
		this.runCounter = 0;
		this.startCounter = 0;
		this.deleteCounter = 0;
		this.addCounter = 0;
		this.mainMenuCounter = 0;
		this.counterNum = new int[20];
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
		if (line.length() >= 14 && line.substring(0, 13).equals("chooseexisting")) {
			String breakDown = line.substring(14);
			String[] split = breakDown.split("\\s");
			chooseExistingCounter = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 9 && line.substring(0, 8).equals("createnew")) {
			String breakDown = line.substring(8);
			String[] split = breakDown.split("\\s");
			createNewCounter = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 4 && line.substring(0, 3).equals("save")) {
			String breakDown = line.substring(3);
			String[] split = breakDown.split("\\s");
			saveCounter = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 7 && line.substring(0, 6).equals("button1")) {
			String breakDown = line.substring(6);
			String[] split = breakDown.split("\\s");
			button1 = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 7 && line.substring(0, 6).equals("button2")) {
			String breakDown = line.substring(6);
			String[] split = breakDown.split("\\s");
			button2 = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 7 && line.substring(0, 6).equals("button3")) {
			String breakDown = line.substring(6);
			String[] split = breakDown.split("\\s");
			button3 = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 7 && line.substring(0, 6).equals("button4")) {
			String breakDown = line.substring(6);
			String[] split = breakDown.split("\\s");
			button4 = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 7 && line.substring(0, 6).equals("button5")) {
			String breakDown = line.substring(6);
			String[] split = breakDown.split("\\s");
			button5 = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 7 && line.substring(0, 6).equals("button6")) {
			String breakDown = line.substring(6);
			String[] split = breakDown.split("\\s");
			button6 = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 7 && line.substring(0, 6).equals("button7")) {
			String breakDown = line.substring(6);
			String[] split = breakDown.split("\\s");
			button7 = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 7 && line.substring(0, 6).equals("button8")) {
			String breakDown = line.substring(6);
			String[] split = breakDown.split("\\s");
			button8 = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 7 && line.substring(0, 6).equals("button9")) {
			String breakDown = line.substring(6);
			String[] split = breakDown.split("\\s");
			button9 = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 8 && line.substring(0, 7).equals("button10")) {
			String breakDown = line.substring(7);
			String[] split = breakDown.split("\\s");
			button10 = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 10 && line.substring(0, 9).equals("runcounter")) {
			String breakDown = line.substring(9);
			String[] split = breakDown.split("\\s");
			runCounter = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 12 && line.substring(0, 11).equals("startcounter")) {
			String breakDown = line.substring(11);
			String[] split = breakDown.split("\\s");
			startCounter = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 13 && line.substring(0, 12).equals("deletecounter")) {
			String breakDown = line.substring(12);
			String[] split = breakDown.split("\\s");
			deleteCounter = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 10 && line.substring(0, 9).equals("addcounter")) {
			String breakDown = line.substring(9);
			String[] split = breakDown.split("\\s");
			addCounter = Integer.parseInt(split[1]);
		}
		else if (line.length() >= 15 && line.substring(0, 14).equals("mainmenucounter")) {
			String breakDown = line.substring(14);
			String[] split = breakDown.split("\\s");
			mainMenuCounter = Integer.parseInt(split[1]);
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
			input = input.replace("button1" + "\\d+", "button1 " + this.button1);
			input = input.replace("button2" + "\\d+", "button2 " + this.button2);
			input = input.replace("button3" + "\\d+", "button3 " + this.button3);
			input = input.replace("button4" + "\\d+", "button4 " + this.button4);
			input = input.replace("button5" + "\\d+", "button5 " + this.button5);
			input = input.replace("button6" + "\\d+", "button6 " + this.button6);
			input = input.replace("button7" + "\\d+", "button7 " + this.button7);
			input = input.replace("button8" + "\\d+", "button8 " + this.button8);
			input = input.replace("button9" + "\\d+", "button9 " + this.button9);
			input = input.replace("button10" + "\\d+", "button10 " + this.button10);
			input = input.replace("runcounter" + "\\d+", "runcounter " + this.runCounter);
			input = input.replace("startcounter" + "\\d+", "startcounter " + this.startCounter);
			input = input.replace("deletecounter" + "\\d+", "deletecounter " + this.deleteCounter);
			input = input.replace("addcounter" + "\\d+", "addcounter " + this.addCounter);
			input = input.replace("mainmenucounter" + "\\d+", "mainmenucounter " + this.mainMenuCounter);
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
	
	public void incrementButton1() {
		this.button1++;
		counterNum[3]++;
	}
	
	public void incrementButton2() {
		this.button2++;
		counterNum[4]++;
	}
	
	public void incrementButton3() {
		this.button3++;
		counterNum[5]++;
	}
	
	public void incrementButton4() {
		this.button4++;
		counterNum[6]++;
	}
	
	public void incrementButton5() {
		this.button5++;
		counterNum[7]++;
	}
	
	public void incrementButton6() {
		this.button6++;
		counterNum[8]++;
	}
	
	public void incrementButton7() {
		this.button7++;
		counterNum[9]++;
	}
	
	public void incrementButton8() {
		this.button8++;
		counterNum[10]++;
	}
	
	public void incrementButton9() {
		this.button9++;
		counterNum[11]++;
	}
	
	public void incrementButton10() {
		this.button10++;
		counterNum[12]++;
	}
	
	public void incrementRunCounter() {
		this.runCounter++;
		counterNum[13]++;
	}
	
	public void incrementStartCounter() {
		this.startCounter++;
		counterNum[14]++;
	}
	
	public void incrementDeleteCounter() {
		this.deleteCounter++;
		counterNum[15]++;
	}
	
	public void incrementAddCounter() {
		this.addCounter++;
		counterNum[16]++;
	}
	
	public void incrementMainMenuCounter() {
		this.mainMenuCounter++;
		counterNum[17]++;
	}
}
