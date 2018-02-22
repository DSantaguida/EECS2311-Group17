package enamel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecorderInput {

	private JFrame frame;
	private JTextField textField;
	public static String cust_file;
	Voice voice;
	VoiceManager vm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecorderInput window = new RecorderInput();
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
	public RecorderInput() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 181);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					cust_file = textField.getText();
					
					textField.setVisible(false);
					vm = VoiceManager.getInstance();
			        voice = vm.getVoice ("kevin16");
			        voice.allocate();
			        voice.speak(cust_file);
			       
				}
			}
		});
		textField.setBounds(10, 49, 414, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterTheName = new JLabel("Enter the Name of the Recorded File:");
		lblEnterTheName.setBounds(10, 24, 194, 14);
		frame.getContentPane().add(lblEnterTheName);
		
		JButton btnStartRecording = new JButton("Start Recording");
		btnStartRecording.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Recorder1 window = new Recorder1();
				
			}
		});
		btnStartRecording.setBounds(10, 91, 414, 40);
		frame.getContentPane().add(btnStartRecording);
	}
}
