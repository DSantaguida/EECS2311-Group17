package enamel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class AddEvent {

	private JFrame frame;
	private JTextField textField;
	private JTextField positionField;
	String file;
	String pins = "";
	JRadioButton radioButton;
	JRadioButton radioButton_1;
	JRadioButton radioButton_2;
	JRadioButton radioButton_3;
	JRadioButton radioButton_4;
	JRadioButton radioButton_5;
	JRadioButton radioButton_6;
	JRadioButton radioButton_7;
	Node node;
	Scenario s;
	private JPanel panel_2;
	private JLabel lblChooseCell;
	private JComboBox cellBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEvent window = new AddEvent();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddEvent() {
		initialize();
	}
	
	public AddEvent(Node node, Scenario s) {
		initialize();
		this.node = node;
		this.s = s;
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 332);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 432, 285);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 109, 397, 176);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		radioButton = new JRadioButton("");
		radioButton.setBounds(55, 34, 25, 25);
		radioButton.setVisible(false);
		panel_1.add(radioButton);
		
		radioButton_1 = new JRadioButton("");
		radioButton_1.setBounds(55, 63, 25, 25);
		radioButton_1.setVisible(false);
		panel_1.add(radioButton_1);
		
		radioButton_2 = new JRadioButton("");
		radioButton_2.setBounds(55, 93, 25, 25);
		radioButton_2.setVisible(false);
		panel_1.add(radioButton_2);
		
		radioButton_3 = new JRadioButton("");
		radioButton_3.setBounds(55, 119, 25, 25);
		radioButton_3.setVisible(false);
		panel_1.add(radioButton_3);
		
		radioButton_4 = new JRadioButton("");
		radioButton_4.setBounds(84, 34, 25, 25);
		radioButton_4.setVisible(false);
		panel_1.add(radioButton_4);
		
		radioButton_5 = new JRadioButton("");
		radioButton_5.setBounds(84, 63, 25, 25);
		radioButton_5.setVisible(false);
		panel_1.add(radioButton_5);
		
		radioButton_6 = new JRadioButton("");
		radioButton_6.setBounds(84, 93, 25, 25);
		radioButton_6.setVisible(false);
		panel_1.add(radioButton_6);
		
		radioButton_7 = new JRadioButton("");
		radioButton_7.setBounds(84, 119, 25, 25);
		radioButton_7.setVisible(false);
		panel_1.add(radioButton_7);
		


		
		JLabel lblTitle = new JLabel("");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(0, 0, 385, 25);
		panel_1.add(lblTitle);
		
		textField = new JTextField();
		textField.setBounds(0, 32, 397, 25);
		panel_1.add(textField);
		textField.setVisible(false);
		textField.setColumns(10);
		
		JLabel lblChooseWav = new JLabel("");
		lblChooseWav.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChooseWav.setBounds(0, 63, 397, 30);
		panel_1.add(lblChooseWav);
		
		JButton btnChoosewav = new JButton("Choose .wav");
		btnChoosewav.setBounds(0, 32, 127, 25);
		btnChoosewav.setVisible(false);
		btnChoosewav.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser(new File("FactoryScenarios/"));
				//Create textfield to allow user to name the file and save as string
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Sound Files", "wav");
				chooser.setFileFilter(filter);
				int returnval = chooser.showOpenDialog(null);
				if (returnval == JFileChooser.APPROVE_OPTION) {
					file = chooser.getSelectedFile().getName();
				}
				lblChooseWav.setText("Selected sound file: " + file);
			}
			
		});
		panel_1.add(btnChoosewav);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 28, 184, 22);
		comboBox.addItem("");
		comboBox.addItem("Response");
		comboBox.addItem("Sound");
		comboBox.addItem("Pins");
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (comboBox.getSelectedItem().equals("Response"))
				{
					cellBox.setVisible(false);
					setPinVisible(false);
					lblChooseCell.setText("");
					lblTitle.setText("Type your response here:");
					lblChooseWav.setText("");
					
					textField.setVisible(true);
				}
				else if (comboBox.getSelectedItem().equals("Sound"))
				{
					cellBox.setVisible(false);
					setPinVisible(false);
					lblChooseCell.setText("");
					textField.setVisible(false);
					lblTitle.setText("Choose the sound file you wish to play:");
					btnChoosewav.setVisible(true);
				}
				else if (comboBox.getSelectedItem().equals("Pins"))
				{
					cellBox.setVisible(true);
					lblTitle.setText("Set active pins:");
					textField.setVisible(false);
					lblChooseCell.setText("Choose Cell:");
					lblChooseWav.setText("");
					btnChoosewav.setVisible(false);
					setPinVisible(true);
				}
				else if (comboBox.getSelectedItem().equals(""))
				{
					lblChooseCell.setText("");
					cellBox.setVisible(false);
					setPinVisible(false);
					textField.setVisible(false);
					lblTitle.setText("");
					lblChooseWav.setText("");
					btnChoosewav.setVisible(false);
				}
			}
			
		});
		panel.add(comboBox);
		
		JLabel lblChooseEventType = new JLabel("Choose Event Type:");
		lblChooseEventType.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChooseEventType.setBounds(12, 0, 184, 30);
		panel.add(lblChooseEventType);
		
		JLabel lblEventPosition = new JLabel("Event Position:");
		lblEventPosition.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEventPosition.setBounds(12, 52, 133, 26);
		panel.add(lblEventPosition);
		
		positionField = new JTextField();
		positionField.setBounds(12, 74, 116, 22);
		panel.add(positionField);
		positionField.setColumns(10);
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (comboBox.getSelectedItem().equals("Response"))
				{
					Response res = new Response(textField.getText());
					int pos = Integer.parseInt(positionField.getText());
					node.getTimeline().insert(pos, res);
					frame.dispose();					
				}
				else if (comboBox.getSelectedItem().equals("Sound"))
				{
					Sound sd = new Sound(file);
					int pos = Integer.parseInt(positionField.getText());
					node.getTimeline().insert(pos, sd);
					frame.dispose();
				}
				else if (comboBox.getSelectedItem().equals("Pins"))
				{
					if(radioButton.isSelected())
						pins += 1;
					else
						pins += 0;
					if(radioButton_1.isSelected())
						pins += 1;
					else
						pins += 0;
					if(radioButton_2.isSelected())
						pins += 1;
					else
						pins += 0;
					if(radioButton_3.isSelected())
						pins += 1;
					else
						pins += 0;
					if(radioButton_4.isSelected())
						pins += 1;
					else
						pins += 0;
					if(radioButton_5.isSelected())
						pins += 1;
					else
						pins += 0;
					if(radioButton_6.isSelected())
						pins += 1;
					else
						pins += 0;
					if(radioButton_7.isSelected())
						pins += 1;
					else
						pins += 0;
					
					DisplayPins dp = new DisplayPins(pins, Integer.parseInt((String) cellBox.getSelectedItem()));
					node.getTimeline().insert(Integer.parseInt(positionField.getText()), dp);
					frame.dispose();
					
				}
				else if (comboBox.getSelectedItem().equals(""))
				{
					frame.dispose();
				}
			}
		});
		btnAddEvent.setBounds(288, 138, 97, 25);
		panel_1.add(btnAddEvent);	
		
		panel_2 = new JPanel();
		panel_2.setBounds(208, 0, 212, 105);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		lblChooseCell = new JLabel("");
		lblChooseCell.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChooseCell.setBounds(0, 0, 153, 29);
		panel_2.add(lblChooseCell);
		
		cellBox = new JComboBox();
		cellBox.setVisible(false);
		cellBox.setBounds(0, 25, 112, 22);
//		for (int i = 0; i < s.getNumButtons(); i++)
//		{
//			cellBox.addItem(i);
//		}
		panel_2.add(cellBox);
		
	}
	
	private void setPinVisible(boolean b)
	{
		radioButton.setVisible(b);
		radioButton_1.setVisible(b);
		radioButton_2.setVisible(b);
		radioButton_3.setVisible(b);
		radioButton_4.setVisible(b);
		radioButton_5.setVisible(b);
		radioButton_6.setVisible(b);
		radioButton_7.setVisible(b);
	}
}
