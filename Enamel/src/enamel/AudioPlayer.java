package enamel;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.LinkedList;
import java.util.logging.Level;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class AudioPlayer extends Player {
	

	LinkedList<JPanel> panelList = new LinkedList<JPanel>();
	LinkedList<JButton> buttonList = new LinkedList<JButton>();
	JFrame frame;
	AWTEventListener listener;
	VoiceManager vm;
	Voice voice;

	
	public AudioPlayer(int cellNum, int buttonNum)
	{
		super(cellNum, buttonNum);
		frame = new JFrame();
		frame.setVisible(true);
		vm = VoiceManager.getInstance();
	    voice = vm.getVoice ("kevin16");
	    voice.allocate();
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		String hold;
		for (int i = 0; i < brailleList.size(); i++){
			hold = "On cell " + i + ", these pins are active:";
			for (int j = 0; j < 8; j++)
			{
				if (brailleList.get(i).getPinState(j))
				{
					hold += "pin " + (j + 1) + ", ";
				}
			}
			if (hold.length() > 33)
				voice.speak(hold);
		}
	}

	@Override
	public void addSkipButtonListener(int index, String param, ScenarioParser sp) {
		//Need Skip Button to implement index
		System.out.println("TEST");
		frame.addKeyListener(new KeyListener(){


			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("press");
				KeyEvent evt = (KeyEvent) e;
				if (evt.getID() == KeyEvent.KEY_PRESSED && evt.getKeyCode() == KeyEvent.VK_1){
				if (sp.userInput) {
					sp.skip(param);
					//logger.log(Level.INFO, "Button {0} was pressed", index+1);
					sp.userInput = false;
				}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

		});

		 }


	@Override
	public void removeButtonListener(int index) {
		// TODO Auto-generated method stub
		if (index >= this.buttonNumber || index < 0) {
            throw new IllegalArgumentException("Invalid index.");
        }
		
		
	}

	@Override
	public void addRepeatButtonListener(int index, ScenarioParser sp) {
		// TODO Auto-generated method stub
		//Look at how to determine when the clip stops reading
		//Initialize the button
		//initialize click event handler

		frame.addKeyListener(new KeyListener(){
		

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

				KeyEvent evt = (KeyEvent) e;
				if (evt.getID() == KeyEvent.KEY_PRESSED && evt.getKeyCode() == KeyEvent.VK_2){
					if (sp.userInput) {
						repeat++;
						logger.log(Level.INFO, "Repeat Button was pressed.");
						logger.log(Level.INFO, "Repeat Button was pressed {0} times", repeat);
						sp.repeatText();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
}
