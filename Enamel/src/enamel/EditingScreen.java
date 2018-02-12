package enamel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EditingScreen implements ActionListener {

	private JFrame frame;
	List<JButton> buttons = new ArrayList<JButton>();
	JPanel panel;
	JPanel subPanel;
	JPanel card;
	JPanel blankCard;
	JPanel clearCard;
	JPanel blankCard2;
	JPanel card2;
	JPanel clearCard2;
	JLabel lblSelectCell2;
	JLabel lblSelectToClear2;
	JLabel lblSelectCell_1_2;
	JTextField txtEnterWordHere2;
	JTextField txtEnterCharHere2;
	JPanel optionCard;
	JPanel blankOCard;
	JPanel nodeCard;
	JLabel lblAvaliableButtons;
	JLabel lblSelectToClear;
	JLabel lblCurrentButton;
	JLabel lblButtonType;
	JLabel lblCellModifier;
	JComboBox cellBox;
	JComboBox buttonBox;
	JRadioButton radioButton;
	JRadioButton radioButton_1;
	JRadioButton radioButton_2;
	JRadioButton radioButton_3;
	JRadioButton radioButton_4;
	JRadioButton radioButton_5;
	int buttonCount = 5;
	int boxCount = 4;
	int currentButton = 0;
	private JTextField txtEnterCharHere;
	private JTextField txtEnterWordHere;
	private JTextField textField;
	private JPanel buttonCard;
	private JLabel lblSelectCell;
	private JLabel lblSelectCell_1;
	private JLabel lblAddSoundFile;
	private JButton btnChooseFile;
	private JLabel lblCurrentFile;
	private JButton btnCreatewav;
	private JButton btnNewButton;
	private JTextField speakText2;
	private JTextField pauseText;
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

	public EditingScreen() {
		initialize();

	}

	private void initialize() { // Initialize GUI
		int x1 = 0;

		// Initialize Main JFrame
		frame = new JFrame();
		frame.setBounds(100, 100, 975, 739);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Initialize Main JPanel
		panel = new JPanel();
		panel.setBounds(0, 0, 957, 692);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.getAccessibleContext().setAccessibleName("Main panel");

		// Label to display the amount of buttons
		lblAvaliableButtons = new JLabel("Avaliable Buttons:");
		lblAvaliableButtons.setForeground(Color.BLACK);
		lblAvaliableButtons.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAvaliableButtons.setBounds(50, 600, 183, 16);
		panel.add(lblAvaliableButtons);

		lblCurrentButton = new JLabel("Current Button: 0");
		lblCurrentButton.setForeground(Color.BLACK);
		lblCurrentButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCurrentButton.setBounds(50, 571, 183, 16);
		panel.add(lblCurrentButton);

		optionCard = new JPanel();
		optionCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		optionCard.setBounds(772, 13, 152, 603);
		optionCard.setLayout(new CardLayout());
		panel.add(optionCard);

		blankOCard = new JPanel();
		blankOCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		blankOCard.setBounds(772, 13, 152, 603);
		optionCard.add(blankOCard);

		nodeCard = new JPanel();
		nodeCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nodeCard.setBounds(772, 13, 152, 603);
		optionCard.add(nodeCard, "Node");
		nodeCard.setLayout(null);

		buttonCard = new JPanel();
		buttonCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		buttonCard.setBounds(772, 13, 152, 603);
		optionCard.add(buttonCard, "Button");
		buttonCard.setLayout(null);

		// Label to display button options
		lblButtonType = new JLabel("Button Type:");
		lblButtonType.setBounds(12, 13, 123, 25);
		buttonCard.add(lblButtonType);
		lblButtonType.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblButtonType.setForeground(Color.BLACK);

		// Combo Box to display button options
		buttonBox = new JComboBox();
		buttonBox.setBounds(12, 51, 119, 22);
		buttonCard.add(buttonBox);
		buttonBox.addItem("Skip");
		buttonBox.addItem("Repeat");

		// Label to display options for cell modifier
		lblCellModifier = new JLabel("Cell Modifier:");
		lblCellModifier.setBounds(12, 89, 123, 25);
		buttonCard.add(lblCellModifier);
		lblCellModifier.setForeground(Color.BLACK);
		lblCellModifier.setFont(new Font("Tahoma", Font.PLAIN, 20));

		// Combo Box to display options for each button
		cellBox = new JComboBox();
		cellBox.setBounds(12, 130, 119, 22);
		buttonCard.add(cellBox);
		cellBox.addItem("Do nothing");
		cellBox.addItem("Clear");
		cellBox.addItem("Pins");
		cellBox.addItem("Character");
		cellBox.addItem("Word");

		// Label to display options for cell modifier
		JLabel lblCellModifier2 = new JLabel("Cell Modifier:");
		lblCellModifier2.setBounds(12, 89, 123, 25);
		nodeCard.add(lblCellModifier2);
		lblCellModifier2.setForeground(Color.BLACK);
		lblCellModifier2.setFont(new Font("Tahoma", Font.PLAIN, 20));

		// Combo Box to display options for each button
		JComboBox cellBox2 = new JComboBox();
		cellBox2.setBounds(12, 130, 119, 22);
		nodeCard.add(cellBox2);
		cellBox2.addItem("Do nothing");
		cellBox2.addItem("Clear");
		cellBox2.addItem("Pins");
		cellBox2.addItem("Character");
		cellBox2.addItem("Word");

		// Main Card for card layout
		card = new JPanel(new CardLayout());
		card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		card.setBounds(12, 179, 119, 189);
		buttonCard.add(card);

		card2 = new JPanel(new CardLayout());
		card2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		card2.setBounds(12, 179, 119, 189);
		nodeCard.add(card2);

		blankCard2 = new JPanel();
		blankCard2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// blankCard.setBackground(Color.RED);
		card2.add(blankCard2, "Do nothing");

		// Card for Clear Option
		clearCard2 = new JPanel();
		clearCard2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		card2.add(clearCard2, "Clear");
		GridBagLayout gbl_clearCard = new GridBagLayout();
		gbl_clearCard.columnWidths = new int[] { 0, 0 };
		gbl_clearCard.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_clearCard.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_clearCard.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		clearCard2.setLayout(gbl_clearCard);

		lblSelectCell2 = new JLabel("Select cell:");
		GridBagConstraints gbc_lblSelectCell = new GridBagConstraints();
		gbc_lblSelectCell.insets = new Insets(0, 0, 5, 0);
		gbc_lblSelectCell.gridx = 0;
		gbc_lblSelectCell.gridy = 0;
		clearCard2.add(lblSelectCell2, gbc_lblSelectCell);

		// Combo Box to choose cell for clear card
		JComboBox clearChoose2 = new JComboBox();
		GridBagConstraints gbc_clearChoose2 = new GridBagConstraints();
		gbc_clearChoose2.insets = new Insets(0, 0, 5, 0);
		gbc_clearChoose2.fill = GridBagConstraints.HORIZONTAL;
		gbc_clearChoose2.gridx = 0;
		gbc_clearChoose2.gridy = 1;
		for (int i = 0; i < boxCount; i++) {
			clearChoose2.addItem(i);
		}
		clearCard2.add(clearChoose2, gbc_clearChoose2);

		// Toggle to clear
		JRadioButton clearRadioButton2 = new JRadioButton("");
		GridBagConstraints gbc_clearRadioButton2 = new GridBagConstraints();
		gbc_clearRadioButton2.insets = new Insets(0, 0, 5, 0);
		gbc_clearRadioButton2.gridx = 0;
		gbc_clearRadioButton2.gridy = 2;
		clearCard2.add(clearRadioButton2, gbc_clearRadioButton2);
		lblSelectToClear2 = new JLabel("Select to clear cell");
		GridBagConstraints gbc_lblSelectToClear2 = new GridBagConstraints();
		gbc_lblSelectToClear2.gridx = 0;
		gbc_lblSelectToClear2.gridy = 3;
		clearCard2.add(lblSelectToClear2, gbc_lblSelectToClear2);

		// Pins Card
		JPanel pinsCard2 = new JPanel();
		pinsCard2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		card2.add(pinsCard2, "Pins");
		GridBagLayout gbl_pinsCard2 = new GridBagLayout();
		gbl_pinsCard2.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_pinsCard2.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_pinsCard2.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pinsCard2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pinsCard2.setLayout(gbl_pinsCard2);

		// ComboBox to choose which cell to edit
		JComboBox blockChooser2 = new JComboBox();
		GridBagConstraints gbc_comboBox2 = new GridBagConstraints();
		gbc_comboBox2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox2.gridx = 0;
		gbc_comboBox2.gridy = 0;
		for (int i = 0; i < boxCount; i++) {
			blockChooser2.addItem(i);
		}
		pinsCard2.add(blockChooser2, gbc_comboBox2);

		// Add all pins
		JRadioButton pin21 = new JRadioButton("");
		GridBagConstraints gbc_radioButton_2_6 = new GridBagConstraints();
		gbc_radioButton_2_6.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_2_6.anchor = GridBagConstraints.WEST;
		gbc_radioButton_2_6.gridx = 1;
		gbc_radioButton_2_6.gridy = 2;
		pinsCard2.add(pin21, gbc_radioButton_2_6);

		JRadioButton pin22 = new JRadioButton("");
		GridBagConstraints gbc_radioButton_2_7 = new GridBagConstraints();
		gbc_radioButton_2_7.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_2_7.gridx = 2;
		gbc_radioButton_2_7.gridy = 2;
		pinsCard2.add(pin22, gbc_radioButton_2_7);

		JRadioButton pin23 = new JRadioButton("");
		GridBagConstraints gbc_radioButton_2_8 = new GridBagConstraints();
		gbc_radioButton_2_8.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_2_8.gridx = 1;
		gbc_radioButton_2_8.gridy = 3;
		pinsCard2.add(pin23, gbc_radioButton_2_8);

		JRadioButton pin24 = new JRadioButton("");
		GridBagConstraints gbc_radioButton_2_9 = new GridBagConstraints();
		gbc_radioButton_2_9.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_2_9.gridx = 2;
		gbc_radioButton_2_9.gridy = 3;
		pinsCard2.add(pin24, gbc_radioButton_2_9);

		JRadioButton pin25 = new JRadioButton("");
		GridBagConstraints gbc_radioButton_2_10 = new GridBagConstraints();
		gbc_radioButton_2_10.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_2_10.gridx = 1;
		gbc_radioButton_2_10.gridy = 4;
		pinsCard2.add(pin25, gbc_radioButton_2_10);

		JRadioButton pin26 = new JRadioButton("");
		GridBagConstraints gbc_radioButton_2_11 = new GridBagConstraints();
		gbc_radioButton_2_11.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_2_11.gridx = 2;
		gbc_radioButton_2_11.gridy = 4;
		pinsCard2.add(pin26, gbc_radioButton_2_11);

		JRadioButton pin27 = new JRadioButton("");
		GridBagConstraints gbc_pin2_7 = new GridBagConstraints();
		gbc_pin2_7.insets = new Insets(0, 0, 0, 5);
		gbc_pin2_7.gridx = 1;
		gbc_pin2_7.gridy = 5;
		pinsCard2.add(pin27, gbc_pin2_7);

		JRadioButton pin28 = new JRadioButton("");
		GridBagConstraints gbc_pin2_8 = new GridBagConstraints();
		gbc_pin2_8.insets = new Insets(0, 0, 0, 5);
		gbc_pin2_8.gridx = 2;
		gbc_pin2_8.gridy = 5;
		pinsCard2.add(pin28, gbc_pin2_8);

		// Character card
		JPanel charCard2 = new JPanel();
		charCard2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		card2.add(charCard2, "Character");
		GridBagLayout gbl_charCard2 = new GridBagLayout();
		gbl_charCard2.columnWidths = new int[] { 0, 0 };
		gbl_charCard2.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_charCard2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_charCard2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		charCard2.setLayout(gbl_charCard2);

		lblSelectCell_1_2 = new JLabel("Select cell:");
		GridBagConstraints gbc_lblSelectCell_2_1 = new GridBagConstraints();
		gbc_lblSelectCell_2_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblSelectCell_2_1.gridx = 0;
		gbc_lblSelectCell_2_1.gridy = 0;
		charCard2.add(lblSelectCell_1_2, gbc_lblSelectCell_2_1);

		// Cell chooser for character card
		JComboBox charChoose2 = new JComboBox();
		GridBagConstraints gbc_charChoose2 = new GridBagConstraints();
		gbc_charChoose2.insets = new Insets(0, 0, 5, 0);
		gbc_charChoose2.fill = GridBagConstraints.HORIZONTAL;
		gbc_charChoose2.gridx = 0;
		gbc_charChoose2.gridy = 1;
		for (int i = 0; i < boxCount; i++) {
			charChoose2.addItem(i);
		}
		charCard2.add(charChoose2, gbc_charChoose2);

		// Text field for choosing character
		txtEnterCharHere2 = new JTextField();
		txtEnterCharHere2.setText("Enter Char Here");
		GridBagConstraints gbc_txtEnterCharHere2 = new GridBagConstraints();
		gbc_txtEnterCharHere2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEnterCharHere2.gridx = 0;
		gbc_txtEnterCharHere2.gridy = 3;
		charCard2.add(txtEnterCharHere2, gbc_txtEnterCharHere2);
		txtEnterCharHere2.setColumns(10);

		// Word card
		JPanel wordCard2 = new JPanel();
		wordCard2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		card2.add(wordCard2, "Word");
		wordCard2.setLayout(new BorderLayout(0, 0));

		// Text field for adding word
		txtEnterWordHere2 = new JTextField();
		txtEnterWordHere2.setText("Enter Word Here");
		wordCard2.add(txtEnterWordHere2, BorderLayout.NORTH);
		txtEnterWordHere2.setColumns(10);
		
		JLabel lblAddwav = new JLabel("Add .wav:");
		lblAddwav.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddwav.setBounds(12, 381, 123, 16);
		nodeCard.add(lblAddwav);
		
		JLabel lblNoFileChosen = new JLabel("No file chosen");
		lblNoFileChosen.setBounds(12, 398, 99, 16);
		nodeCard.add(lblNoFileChosen);
		
		JButton btnChooseFile_1 = new JButton("Choose File");
		btnChooseFile_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String file = "";
				JFileChooser chooser = new JFileChooser(new File("FactoryScenarios/AudioFiles"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Audio Files", "wav");
				chooser.setFileFilter(filter);
				int returnval = chooser.showOpenDialog(null);
				if (returnval == JFileChooser.APPROVE_OPTION) {
					file = "FactoryScenarios/AudioFiles" + chooser.getSelectedFile().getName();
				}
				lblNoFileChosen.setText("" + chooser.getSelectedFile().getName());
			}
		});
		btnChooseFile_1.setBounds(12, 419, 123, 25);
		nodeCard.add(btnChooseFile_1);
		
		JButton btnClearFile = new JButton("Clear File");
		btnClearFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNoFileChosen.setText("No File Chosen");
			}
		});
		btnClearFile.setBounds(12, 457, 123, 25);
		nodeCard.add(btnClearFile);
		
		JButton btnCreatewav_1 = new JButton("Create .wav");
		btnCreatewav_1.setBounds(12, 495, 123, 25);
		nodeCard.add(btnCreatewav_1);
		
		JLabel lblSpeak = new JLabel("Speak:");
		lblSpeak.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSpeak.setBounds(12, 522, 113, 25);
		nodeCard.add(lblSpeak);
		
		speakText2 = new JTextField();
		speakText2.setBounds(12, 560, 123, 22);
		nodeCard.add(speakText2);
		speakText2.setColumns(10);
		
		JLabel lblPause = new JLabel("Pause:");
		lblPause.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPause.setBounds(12, 13, 66, 16);
		nodeCard.add(lblPause);
		
		pauseText = new JTextField();
		pauseText.setBounds(73, 13, 62, 22);
		nodeCard.add(pauseText);
		pauseText.setColumns(10);
		// Initial card
		blankCard = new JPanel();
		blankCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		card.add(blankCard, "Do nothing");

		// Card for Clear Option
		clearCard = new JPanel();
		clearCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		card.add(clearCard, "Clear");
		GridBagLayout gbl_clearCard2 = new GridBagLayout();
		gbl_clearCard2.columnWidths = new int[] { 0, 0 };
		gbl_clearCard2.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_clearCard2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_clearCard2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		clearCard.setLayout(gbl_clearCard2);

		lblSelectCell = new JLabel("Select cell:");
		GridBagConstraints gbc_lblSelectCell2 = new GridBagConstraints();
		gbc_lblSelectCell2.insets = new Insets(0, 0, 5, 0);
		gbc_lblSelectCell2.gridx = 0;
		gbc_lblSelectCell2.gridy = 0;
		clearCard.add(lblSelectCell, gbc_lblSelectCell2);

		// Combo Box to choose cell for clear card
		JComboBox clearChoose = new JComboBox();
		GridBagConstraints gbc_clearChoose = new GridBagConstraints();
		gbc_clearChoose.insets = new Insets(0, 0, 5, 0);
		gbc_clearChoose.fill = GridBagConstraints.HORIZONTAL;
		gbc_clearChoose.gridx = 0;
		gbc_clearChoose.gridy = 1;
		for (int i = 0; i < boxCount; i++) {
			clearChoose.addItem(i);
		}
		clearCard.add(clearChoose, gbc_clearChoose);

		// Toggle to clear
		JRadioButton clearRadioButton = new JRadioButton("");
		GridBagConstraints gbc_clearRadioButton = new GridBagConstraints();
		gbc_clearRadioButton.insets = new Insets(0, 0, 5, 0);
		gbc_clearRadioButton.gridx = 0;
		gbc_clearRadioButton.gridy = 2;
		clearCard.add(clearRadioButton, gbc_clearRadioButton);
		lblSelectToClear = new JLabel("Select to clear cell");
		GridBagConstraints gbc_lblSelectToClear = new GridBagConstraints();
		gbc_lblSelectToClear.gridx = 0;
		gbc_lblSelectToClear.gridy = 3;
		clearCard.add(lblSelectToClear, gbc_lblSelectToClear);

		// Pins Card
		JPanel pinsCard = new JPanel();
		pinsCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		card.add(pinsCard, "Pins");
		GridBagLayout gbl_pinsCard = new GridBagLayout();
		gbl_pinsCard.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_pinsCard.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_pinsCard.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pinsCard.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pinsCard.setLayout(gbl_pinsCard);

		// ComboBox to choose which cell to edit
		JComboBox blockChooser = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		for (int i = 0; i < boxCount; i++) {
			blockChooser.addItem(i);
		}
		pinsCard.add(blockChooser, gbc_comboBox);

		// Add all pins
		JRadioButton pin1 = new JRadioButton("");
		GridBagConstraints gbc_radioButton_6 = new GridBagConstraints();
		gbc_radioButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_6.anchor = GridBagConstraints.WEST;
		gbc_radioButton_6.gridx = 1;
		gbc_radioButton_6.gridy = 2;
		pinsCard.add(pin1, gbc_radioButton_6);

		JRadioButton pin2 = new JRadioButton("");
		GridBagConstraints gbc_radioButton_7 = new GridBagConstraints();
		gbc_radioButton_7.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_7.gridx = 2;
		gbc_radioButton_7.gridy = 2;
		pinsCard.add(pin2, gbc_radioButton_7);

		JRadioButton pin3 = new JRadioButton("");
		GridBagConstraints gbc_radioButton_8 = new GridBagConstraints();
		gbc_radioButton_8.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_8.gridx = 1;
		gbc_radioButton_8.gridy = 3;
		pinsCard.add(pin3, gbc_radioButton_8);

		JRadioButton pin4 = new JRadioButton("");
		GridBagConstraints gbc_radioButton_9 = new GridBagConstraints();
		gbc_radioButton_9.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_9.gridx = 2;
		gbc_radioButton_9.gridy = 3;
		pinsCard.add(pin4, gbc_radioButton_9);

		JRadioButton pin5 = new JRadioButton("");
		GridBagConstraints gbc_radioButton_10 = new GridBagConstraints();
		gbc_radioButton_10.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_10.gridx = 1;
		gbc_radioButton_10.gridy = 4;
		pinsCard.add(pin5, gbc_radioButton_10);

		JRadioButton pin6 = new JRadioButton("");
		GridBagConstraints gbc_radioButton_11 = new GridBagConstraints();
		gbc_radioButton_11.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_11.gridx = 2;
		gbc_radioButton_11.gridy = 4;
		pinsCard.add(pin6, gbc_radioButton_11);

		JRadioButton pin7 = new JRadioButton("");
		GridBagConstraints gbc_pin7 = new GridBagConstraints();
		gbc_pin7.insets = new Insets(0, 0, 0, 5);
		gbc_pin7.gridx = 1;
		gbc_pin7.gridy = 5;
		pinsCard.add(pin7, gbc_pin7);

		JRadioButton pin8 = new JRadioButton("");
		GridBagConstraints gbc_pin8 = new GridBagConstraints();
		gbc_pin8.insets = new Insets(0, 0, 0, 5);
		gbc_pin8.gridx = 2;
		gbc_pin8.gridy = 5;
		pinsCard.add(pin8, gbc_pin8);

		// Character card
		JPanel charCard = new JPanel();
		charCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		card.add(charCard, "Character");
		GridBagLayout gbl_charCard = new GridBagLayout();
		gbl_charCard.columnWidths = new int[] { 0, 0 };
		gbl_charCard.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_charCard.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_charCard.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		charCard.setLayout(gbl_charCard);

		lblSelectCell_1 = new JLabel("Select cell:");
		GridBagConstraints gbc_lblSelectCell_1 = new GridBagConstraints();
		gbc_lblSelectCell_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblSelectCell_1.gridx = 0;
		gbc_lblSelectCell_1.gridy = 0;
		charCard.add(lblSelectCell_1, gbc_lblSelectCell_1);

		// Cell chooser for character card
		JComboBox charChoose = new JComboBox();
		GridBagConstraints gbc_charChoose = new GridBagConstraints();
		gbc_charChoose.insets = new Insets(0, 0, 5, 0);
		gbc_charChoose.fill = GridBagConstraints.HORIZONTAL;
		gbc_charChoose.gridx = 0;
		gbc_charChoose.gridy = 1;
		for (int i = 0; i < boxCount; i++) {
			charChoose.addItem(i);
		}
		charCard.add(charChoose, gbc_charChoose);

		// Text field for choosing character
		txtEnterCharHere = new JTextField();
		txtEnterCharHere.setText("Enter Char Here");
		GridBagConstraints gbc_txtEnterCharHere = new GridBagConstraints();
		gbc_txtEnterCharHere.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEnterCharHere.gridx = 0;
		gbc_txtEnterCharHere.gridy = 3;
		charCard.add(txtEnterCharHere, gbc_txtEnterCharHere);
		txtEnterCharHere.setColumns(10);

		// Word card
		JPanel wordCard = new JPanel();
		wordCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		card.add(wordCard, "Word");
		wordCard.setLayout(new BorderLayout(0, 0));

		// Text field for adding word
		txtEnterWordHere = new JTextField();
		txtEnterWordHere.setText("Enter Word Here");
		wordCard.add(txtEnterWordHere, BorderLayout.NORTH);
		txtEnterWordHere.setColumns(10);

		// Have the button say something to you
		JLabel lblSayOutLoud = new JLabel("Speak:");
		lblSayOutLoud.setBounds(12, 530, 119, 25);
		buttonCard.add(lblSayOutLoud);
		lblSayOutLoud.setFont(new Font("Tahoma", Font.PLAIN, 20));

		textField = new JTextField();
		textField.setBounds(15, 568, 116, 22);
		buttonCard.add(textField);
		textField.setColumns(10);

		lblAddSoundFile = new JLabel("Add .wav:");
		lblAddSoundFile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddSoundFile.setBounds(12, 381, 123, 16);
		buttonCard.add(lblAddSoundFile);

		btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String file = "";
				JFileChooser chooser = new JFileChooser(new File("FactoryScenarios/AudioFiles"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Audio Files", "wav");
				chooser.setFileFilter(filter);
				int returnval = chooser.showOpenDialog(null);
				if (returnval == JFileChooser.APPROVE_OPTION) {
					file = "FactoryScenarios/AudioFiles" + chooser.getSelectedFile().getName();
				}
				lblCurrentFile.setText("" + chooser.getSelectedFile().getName());

			}

		});
		btnChooseFile.setBounds(12, 423, 119, 25);
		buttonCard.add(btnChooseFile);

		lblCurrentFile = new JLabel("No file chosen");
		lblCurrentFile.setBounds(12, 402, 106, 16);
		buttonCard.add(lblCurrentFile);

		btnCreatewav = new JButton("Create .wav");
		btnCreatewav.setBounds(12, 492, 119, 25);
		buttonCard.add(btnCreatewav);

		btnNewButton = new JButton("Clear file");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCurrentFile.setText("No file chosen");
			}
		});
		btnNewButton.setBounds(12, 454, 120, 25);
		buttonCard.add(btnNewButton);

		JButton btnNode = new JButton("Node");
		btnNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) (optionCard.getLayout());
				cl.show(optionCard, "Node");
			}
		});
		btnNode.setBounds(279, 255, 97, 25);
		panel.add(btnNode);

		// Apply button
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String option = "" + cellBox.getSelectedItem();
				if (option.equals("Clear")) {
					int cell = Integer.parseInt("" + clearChoose.getSelectedItem());
				} else if (option.equals("Pins")) {
					int cell = Integer.parseInt("" + blockChooser.getSelectedItem());
					System.out.println(cell);
				} else if (option.equals("Character")) {
					int cell = Integer.parseInt("" + charChoose.getSelectedItem());
					if (txtEnterCharHere.getText().length() != 1) {
						// Display Some Error Here
					}

				} else if (option.equals("Word")) {
					if (txtEnterWordHere.getText().length() != boxCount) {
						// Display Some Error Here
					}
				} else {
					// Display some error here for nothing selected
				}

			}
		});
		btnApply.setBounds(791, 636, 97, 25);
		panel.add(btnApply);

		cellBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (card.getLayout());
				cl.show(card, (String) cellBox.getSelectedItem());
			}
		});
		cellBox2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (card2.getLayout());
				cl.show(card2, (String) cellBox2.getSelectedItem());
			}
		});

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
			buttons.get(i).setBounds(x1, 625, 50, 25);
			buttons.get(i).addActionListener(this);
			buttons.get(i).getAccessibleContext().setAccessibleName("Button " + i);
			buttons.get(i).getAccessibleContext().setAccessibleDescription("Button " + i);
			panel.add(buttons.get(i));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < buttonCount; i++) // Sets the current button to edit
		{
			if (e.getSource().equals(buttons.get(i))) {
				currentButton = i;
			}
		}
		lblCurrentButton.setText("Current Button: " + currentButton);

	}
}