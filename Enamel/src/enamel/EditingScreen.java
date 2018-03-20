package enamel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class EditingScreen implements ActionListener {
	// UNCOMMENT 973 WHEN DONE
	private JFrame frame;
	List<JButton> buttons = new ArrayList<JButton>();
	Node currentNode;
	NodeButton currentNodeButton;
	private static HashMap<Character, String> alphabet = new HashMap<Character, String>();
	Scenario scenario;
	private ScenarioWriter writer;

	JPanel panel;
	JPanel subPanel;
	JPanel optionCard;
	JPanel eventPanel;
	JPanel nodeCard;
	JLabel lblAvaliableButtons;
	JLabel lblCurrentButton;
	JRadioButton radioButton;
	JRadioButton radioButton_1;
	JRadioButton radioButton_2;
	JRadioButton radioButton_3;
	JRadioButton radioButton_4;
	JRadioButton radioButton_5;
	int buttonCount = 5;
	int boxCount = 4;
	int currentButton = 0;
	private JPanel buttonCard;
	private GraphCanvas graphCanvas;
	protected boolean testActionListenerActive = true;

	// int[] indvCell = new int[boxCount];

	// Node currentNode;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditingScreen window = new EditingScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditingScreen(Scenario hold) {
		alphabet.put('a', "10000000");
		alphabet.put('b', "11000000");
		alphabet.put('c', "10100000");
		alphabet.put('d', "10011000");
		alphabet.put('e', "10001000");
		alphabet.put('f', "11010000");
		alphabet.put('g', "11011000");
		alphabet.put('h', "11001000");
		alphabet.put('i', "01010000");
		alphabet.put('j', "01011000");
		alphabet.put('k', "10100000");
		alphabet.put('l', "11100000");
		alphabet.put('m', "10110000");
		alphabet.put('n', "10111000");
		alphabet.put('o', "10101000");
		alphabet.put('p', "11110000");
		alphabet.put('q', "11111000");
		alphabet.put('r', "11101000");
		alphabet.put('s', "01110000");
		alphabet.put('t', "01111000");
		alphabet.put('u', "10100100");
		alphabet.put('v', "11100100");
		alphabet.put('w', "01011100");
		alphabet.put('x', "10110100");
		alphabet.put('y', "10111100");
		alphabet.put('z', "10101100");
		alphabet.put(' ', "11111111");
		// scenario = new Scenario(hold);
		scenario = hold;
		currentNode = scenario.getHead();
		currentNodeButton = scenario.getHead().getButton(0);
		currentButton = 0;
		buttonCount = scenario.getNumButtons();
		boxCount = scenario.getNumCells();
		writer = new ScenarioWriter(scenario);
		initialize();
		loadNodeEvents();
	}

	public EditingScreen() {
		alphabet.put('a', "10000000");
		alphabet.put('b', "11000000");
		alphabet.put('c', "10100000");
		alphabet.put('d', "10011000");
		alphabet.put('e', "10001000");
		alphabet.put('f', "11010000");
		alphabet.put('g', "11011000");
		alphabet.put('h', "11001000");
		alphabet.put('i', "01010000");
		alphabet.put('j', "01011000");
		alphabet.put('k', "10100000");
		alphabet.put('l', "11100000");
		alphabet.put('m', "10110000");
		alphabet.put('n', "10111000");
		alphabet.put('o', "10101000");
		alphabet.put('p', "11110000");
		alphabet.put('q', "11111000");
		alphabet.put('r', "11101000");
		alphabet.put('s', "01110000");
		alphabet.put('t', "01111000");
		alphabet.put('u', "10100100");
		alphabet.put('v', "11100100");
		alphabet.put('w', "01011100");
		alphabet.put('x', "10110100");
		alphabet.put('y', "10111100");
		alphabet.put('z', "10101100");
		alphabet.put(' ', "11111111");
		currentNode = new Node(1);
		currentNode.addButton(0);
		currentNodeButton = currentNode.getButton(0);
		currentButton = 0;
		initialize();

		loadNodeEvents();
	}

	private void initialize() { // Initialize GUI
		int x1 = 0;

		// Initialize Main JFrame
		frame = new JFrame();
		frame.setVisible(true);

		frame.setBounds(100, 100, 975, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		graphCanvas = new GraphCanvas(scenario, scenario.getHead());
		graphCanvas.setBounds(0, 0, 550, 525);
		frame.getContentPane().add(graphCanvas);
		graphCanvas.setVisible(true);

		// Initialize Main JPanel
		panel = new JPanel();
		panel.setBounds(12, 0, 957, 740);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		// Label to display the amount of buttons
		lblAvaliableButtons = new JLabel("Available Buttons:");
		lblAvaliableButtons.setForeground(Color.BLACK);
		lblAvaliableButtons.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAvaliableButtons.setBounds(12, 663, 183, 16);
		panel.add(lblAvaliableButtons);

		lblCurrentButton = new JLabel("Current Button: " + currentButton);
		// lblCurrentButton.getAccessibleContext().setAccessibleName(lblCurrentButton.getText());
		lblCurrentButton.setForeground(Color.BLACK);
		lblCurrentButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCurrentButton.setBounds(12, 634, 183, 16);
		panel.add(lblCurrentButton);

		optionCard = new JPanel();
		optionCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		optionCard.setBounds(645, 13, 279, 666);
		optionCard.setLayout(new CardLayout());
		panel.add(optionCard);

		eventPanel = new JPanel();
		eventPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		eventPanel.setBounds(772, 13, 152, 603);
		optionCard.add(eventPanel);

		initEventMods();

		nodeCard = new JPanel();
		nodeCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nodeCard.setBounds(772, 13, 152, 603);
		optionCard.add(nodeCard, "Node");
		nodeCard.setLayout(new BoxLayout(nodeCard, BoxLayout.Y_AXIS));

		buttonCard = new JPanel();
		buttonCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		buttonCard.setBounds(772, 13, 152, 603);
		optionCard.add(buttonCard, "Button");
		buttonCard.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 692, 457, 35);
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(698, 692, 195, 35);
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton run = new JButton("Run");
		panel_2.add(run);
		run.getAccessibleContext().setAccessibleName("Run a scenario");
		run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						String file = "";
						JFileChooser chooser = new JFileChooser(new File("FactoryScenarios"));
						FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
						chooser.setFileFilter(filter);
						int returnval = chooser.showOpenDialog(null);
						if (returnval == JFileChooser.APPROVE_OPTION) {
							file = "FactoryScenarios/" + chooser.getSelectedFile().getName();
						}
						ScenarioParser s = new ScenarioParser(true);
						s.setScenarioFile(file);
					}

				});
				frame.dispose();
				t.start();
			}

		});

		// Apply button
		JButton btnApply = new JButton("Save");
		panel_2.add(btnApply);
		btnApply.addActionListener(new ActionListener() {//
			public void actionPerformed(ActionEvent arg0) {

				// if (lblCurrentButton.getText().equals("Node Selected")) {
				// String option = "" + nodeCellBox.getSelectedItem();
				// currentNode.setResponse(speakText2.getText());
				// currentNode.setRepeatedText(repeatText.getText());
				//
				// if (!lblNoFileChosen.getText().equals("No file chosen"))
				// currentNode.setAudioFile(lblNoFileChosen.getText());
				// // ADD PAUSE
				// if (option.equals("Clear")) {
				// int cell = Integer.parseInt("" +
				// clearChoose2.getSelectedItem());
				// int[] pins = new int[8];
				// String[] hold = alphabet.get(' ').split("");
				// if (clearRadioButton2.isSelected()) {
				// for (int i = 0; i < 8; i++) {
				// pins[i] = Integer.parseInt(hold[i]);
				// }
				// currentNode.setPins(pins, cell);
				// }
				//
				// }
				//
				// else if (option.equals("Pins")) {
				// int cell = Integer.parseInt("" +
				// blockChooser2.getSelectedItem());
				//
				// if (pin21.isSelected())
				// currentNode.setPin(cell, 1, 1);
				// else
				// currentNode.setPin(cell, 1, 0);
				//
				// if (pin22.isSelected())
				// currentNode.setPin(cell, 2, 1);
				// else
				// currentNode.setPin(cell, 2, 0);
				//
				// if (pin23.isSelected())
				// currentNode.setPin(cell, 3, 1);
				// else
				// currentNode.setPin(cell, 3, 0);
				//
				// if (pin24.isSelected())
				// currentNode.setPin(cell, 4, 1);
				// else
				// currentNode.setPin(cell, 4, 0);
				//
				// if (pin25.isSelected())
				// currentNode.setPin(cell, 5, 1);
				// else
				// currentNode.setPin(cell, 5, 0);
				//
				// if (pin26.isSelected())
				// currentNode.setPin(cell, 6, 1);
				// else
				// currentNode.setPin(cell, 6, 0);
				//
				// if (pin27.isSelected())
				// currentNode.setPin(cell, 7, 1);
				// else
				// currentNode.setPin(cell, 7, 0);
				//
				// if (pin28.isSelected())
				// currentNode.setPin(cell, 8, 1);
				// else
				// currentNode.setPin(cell, 8, 0);
				// }
				//
				// else if (option.equals("Character")) {
				// int cell = Integer.parseInt("" +
				// charChoose2.getSelectedItem());
				//
				// // System.out.println(nodeEnterCharHere.getText().length());
				// if (nodeEnterCharHere.getText().length() > 1) {
				// // Display Some Error Here
				//
				// } else {
				// int[] pins = new int[8];
				// char[] x = nodeEnterCharHere.getText().toCharArray();
				// String[] hold = alphabet.get(x[0]).split("");
				// for (int i = 0; i < 8; i++) {
				// pins[i] = Integer.parseInt(hold[i]);
				// System.out.println(pins[i]);
				// }
				// currentNode.setPins(pins, cell);
				//
				// }
				// }
				//
				// else if (option.equals("Word")) {
				// if (nodeEnterWordHere.getText().length() > boxCount) {
				// // Display Some Error Here
				// } else {
				// String[] word = nodeEnterWordHere.getText().split("");
				// int[] pins = new int[8];
				// for (int i = 0; i < word.length; i++) {
				// char[] x = word[i].toCharArray();
				//
				// String[] hold = alphabet.get(x[0]).split("");
				// for (int j = 0; j < 8; j++) {
				// pins[j] = Integer.parseInt(hold[j]);
				//
				// }
				// currentNode.setPins(pins, i);
				// }
				// }
				// }
				//
				// else {
				// // Display some error here for nothing selected
				// }
				// }
				//
				// else if (lblCurrentButton.getText().split("
				// ")[0].equals("Current"))// CHANGE
				// // BUTTON
				// // STUFF
				// {
				// String option = "" + btnCellBox.getSelectedItem();
				// int[] pins = new int[8];
				//
				// if (buttonBox.getSelectedItem().equals("Repeat")) {
				// if (currentNodeButton.getClass() == SkipButton.class) {
				// currentNode.addRepeatButton(currentButton,
				// repeatText.getText());
				// }
				// } else {
				// if (currentNode.getButton(currentButton).equals(null)) {
				// // SkipButton skip = new SkipButton();
				// int number = currentButton;
				// String response = textField.getText();
				// Node next = null; // DONT KNOW WHAT TO PUT HERE
				//
				// if (option.equals("Clear")) {
				// int cell = Integer.parseInt("" +
				// clearChoose.getSelectedItem());
				// String[] hold = alphabet.get(' ').split("");
				// if (clearRadioButton.isSelected()) {
				// for (int i = 0; i < 8; i++) {
				// pins[i] = Integer.parseInt(hold[i]);
				// }
				// }
				// if (currentNodeButton.getClass() == SkipButton.class) {
				// ((SkipButton) currentNodeButton).setPins(pins, cell);
				// }
				//
				// } else if (option.equals("Pins")) {
				// int cell = Integer.parseInt("" +
				// blockChooser.getSelectedItem());
				//
				// if (pin1.isSelected())
				// pins[0] = 1;
				// else//
				// pins[0] = 0;
				//
				// if (pin2.isSelected())
				// pins[1] = 1;
				// else
				// pins[1] = 0;
				//
				// if (pin3.isSelected())
				// pins[2] = 1;
				// else
				// pins[2] = 0;
				//
				// if (pin4.isSelected())
				// pins[3] = 1;
				// else
				// pins[3] = 0;
				//
				// if (pin5.isSelected())
				// pins[4] = 1;
				// else
				// pins[4] = 0;
				//
				// if (pin6.isSelected())
				// pins[5] = 1;
				// else
				// pins[5] = 0;
				//
				// if (pin7.isSelected())
				// pins[6] = 1;
				// else
				// pins[6] = 0;
				//
				// if (pin8.isSelected())
				// pins[7] = 1;
				// else
				// pins[7] = 0;
				//
				// if (currentNodeButton.getClass() == SkipButton.class) {
				// ((SkipButton) currentNodeButton).setPins(pins, cell);
				// }
				//
				// }
				//
				// else if (option.equals("Character")) {
				// int cell = Integer.parseInt("" +
				// charChoose.getSelectedItem());
				//
				// // System.out.println(nodeEnterCharHere.getText().length());
				// if (txtEnterCharHere.getText().length() > 1) {
				// // Display Some Error Here
				//
				// } else {
				// char[] x = txtEnterCharHere.getText().toCharArray();
				// String[] hold = alphabet.get(x[0]).split("");
				// for (int i = 0; i < 8; i++) {
				// pins[i] = Integer.parseInt(hold[i]);
				//
				// }
				// }
				// if (currentNodeButton.getClass() == SkipButton.class) {
				// ((SkipButton) currentNodeButton).setPins(pins, cell);
				// }
				// } else if (option.equals("Word")) {
				// if (txtEnterWordHere.getText().length() > boxCount) {
				// // Display Some Error Here
				// } else {
				// String[] word = txtEnterWordHere.getText().split("");
				// for (int i = 0; i < word.length; i++) {
				// char[] x = word[i].toCharArray();
				//
				// String[] hold = alphabet.get(x[0]).split("");
				// for (int j = 0; j < 8; j++) {
				// pins[j] = Integer.parseInt(hold[j]);
				//
				// }
				// if (currentNodeButton.getClass() == SkipButton.class) {
				// ((SkipButton) currentNodeButton).setPins(pins, i);
				// }
				// }
				// }
				// } else {
				// // Do nothing for now
				// }
				//
				// NodeButton button = currentNode.getButton(currentButton);
				// if (currentNodeButton.getClass() == SkipButton.class) {
				//
				// }
				// if (!lblCurrentFile.getText().equals("No file chosen")) {
				// // currentNodeButton = new SkipButton(1);
				//
				// if (currentNodeButton.getClass() == SkipButton.class) {
				// int cell = Integer.parseInt("" +
				// blockChooser.getSelectedItem());
				// ((SkipButton) currentNodeButton).setResponse(response);
				// ((SkipButton)
				// currentNodeButton).setAudioFile(lblCurrentFile.getText());
				// if (comboBoxConnectTo.getSelectedItem().equals("New Node"))
				// {
				// //((SkipButton)
				// currentNodeButton).setNextNode(scenario.createNode());
				// Node hold = scenario.createNode();
				// scenario.setEdge(currentNode, hold, currentButton);
				// }
				// else {//
				// ((SkipButton)
				// currentNodeButton).setNextNode((Node)comboBoxConnectTo.getSelectedItem());
				// }
				// }
				//
				// } else {
				// // SkipButton button = new SkipButton(number,
				// // response, next);
				// }
				//
				// }
				//
				// }
				// }
				// writer.changeFileName(scenario.getName());
				// try {
				// writer.save();
				// } catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
			}
		});
		btnApply.getAccessibleContext().setAccessibleName("Click to apply changes");

		for (int i = 0; i < buttonCount; i++) // Display buttons based on the
												// amount of buttons passed in
												// through the text file
		{
			x1 = x1 + 50;
			buttons.add(new JButton("" + i));
			buttons.get(i).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					CardLayout cl = (CardLayout) (optionCard.getLayout());
					cl.show(optionCard, "Button");

				}

			});
			buttons.get(i).setBounds(x1, 0, 50, 25);
			buttons.get(i).addActionListener(this);
			buttons.get(i).getAccessibleContext().setAccessibleName("Button " + i);
			panel_1.add(buttons.get(i));
		}
		CardLayout cl = (CardLayout) (optionCard.getLayout());
		cl.show(optionCard, "Do Nothing");
		lblCurrentButton.setText("Node Selected");

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(223, 644, 152, 35);
		panel.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				StartScreen go = new StartScreen();
				go.frame.setVisible(true);
			}
		});
		panel_3.add(btnMainMenu);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(481, 606, 152, 109);
		panel.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));

		JLabel lblPreviousNodes = new JLabel("Previous Nodes:");
		panel_6.add(lblPreviousNodes);

		JComboBox<Node> comboBoxPrevNodes = new JComboBox<>();
		panel_6.add(comboBoxPrevNodes);

		JButton btnNode = new JButton("Current Node");
		panel_6.add(btnNode);

		JLabel lblNextNodes = new JLabel("Next Nodes:");
		panel_6.add(lblNextNodes);

		JComboBox<Node> comboBoxNextNodes = new JComboBox<>();
		panel_6.add(comboBoxNextNodes);
		
		btnNode.setVisible(true);
		btnNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) (optionCard.getLayout());
				nodeCard.removeAll();
				nodeCard.revalidate();
				nodeCard.repaint();

				loadNodeEvents();
			}
		});

		

		for (Node node : this.scenario.getPrevNodes(this.currentNode)) {
			System.out.println(currentNode + "NO");
			comboBoxPrevNodes.addItem(node);
		}
		for (Node node : this.scenario.getNextNodes(this.currentNode)) {
			comboBoxNextNodes.addItem(node);
		}
		
		comboBoxNextNodes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (testActionListenerActive) {
					testActionListenerActive = false;
					currentNode = (Node) comboBoxNextNodes.getSelectedItem();
					CardLayout cl = (CardLayout) (optionCard.getLayout());
					cl.show(optionCard, "Do Nothing");
					lblCurrentButton.setText("Node Selected");
					graphCanvas.setNode(currentNode);
					graphCanvas.repaint();
					comboBoxNextNodes.removeAllItems();
					for (Node node : scenario.getNextNodes(currentNode)) {
						comboBoxNextNodes.addItem(node);
					}
					comboBoxPrevNodes.removeAllItems();
					for (Node node : scenario.getPrevNodes(currentNode)) {
						comboBoxPrevNodes.addItem(node);
					}
					loadNodeEvents();
					testActionListenerActive = true;
				}

			}
		});
		
		comboBoxPrevNodes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (testActionListenerActive) {
					testActionListenerActive = false;
					currentNode = (Node) comboBoxPrevNodes.getSelectedItem();
					CardLayout cl = (CardLayout) (optionCard.getLayout());
					cl.show(optionCard, "Do Nothing");
					lblCurrentButton.setText("Node Selected");
					graphCanvas.setNode(currentNode);
					graphCanvas.repaint();
					comboBoxNextNodes.removeAllItems();
					if (scenario.hasNextNodes(currentNode)) {
						for (Node node : scenario.getNextNodes(currentNode)) {
							comboBoxNextNodes.addItem(node);
						}
					}
					comboBoxPrevNodes.removeAllItems();
					for (Node node : scenario.getPrevNodes(currentNode)) {
						comboBoxPrevNodes.addItem(node);
					}
					loadNodeEvents();
					testActionListenerActive = true;
				}
			}
		});
		loadNodeEvents();
	}

	private void initEventMods() {
		JPanel panel_5 = new JPanel();
		eventPanel.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblEventEditor = new JLabel("Event Editor:");
		lblEventEditor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(lblEventEditor);

		JButton btnAddEvent = new JButton("Add");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEvent newevent = new AddEvent(currentNode, scenario);
			}
		});
		panel_5.add(btnAddEvent);

		JButton btnDeleteEvent = new JButton("Delete");
		panel_5.add(btnDeleteEvent);
	}

	public void loadNodeEvents() {
		int count = 0;
		eventPanel.removeAll();
		initEventMods();
		Timeline t = currentNode.getTimeline();
		for (Event e : t.getEvents()) {
			count++;
			System.out.println(count);
			eventPanel.add(new JLabel("Event " + count));
			JButton eventButton = new JButton("Edit event");
			eventButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// EDIT EVENT SCREEN

				}

			});
			eventPanel.add(eventButton);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < buttonCount; i++) // Sets the current button to edit
		{
			if (e.getSource().equals(buttons.get(i))) {
				currentButton = i;
				// currentNodeButton = currentNode.getButton(i);
				lblCurrentButton.setText("Current Button: " + currentButton);
			}
		}

	}
}
