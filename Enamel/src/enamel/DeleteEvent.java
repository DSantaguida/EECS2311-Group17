package enamel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class DeleteEvent {

	private JFrame Dframe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteEvent window = new DeleteEvent();
					window.Dframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeleteEvent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dframe = new JFrame();
		Dframe.setBounds(100, 100, 368, 269);
		Dframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dframe.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 560, 337);
		Dframe.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDeleteEvent = new JLabel("Delete Event:");
		lblDeleteEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDeleteEvent.setBounds(12, 13, 200, 39);
		panel.add(lblDeleteEvent);
		
		JLabel lblWhichEventWould = new JLabel("Which event would you like to delete?");
		lblWhichEventWould.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWhichEventWould.setBounds(12, 63, 311, 25);
		panel.add(lblWhichEventWould);
		
		JComboBox deleteBox = new JComboBox();
		deleteBox.setBounds(12, 88, 140, 22);
		panel.add(deleteBox);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(229, 166, 97, 25);
		panel.add(btnDelete);
	}
}
