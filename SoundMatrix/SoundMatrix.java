//RIYA PATEL

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import sun.audio.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class SoundMatrix extends JPanel implements ActionListener, Runnable {


	private JToggleButton buttons[];
	private boolean toggle=true;
	private Container container;
	private GridLayout grid1, grid2;
	private String notes[] = {"G3","A3","B3","C4","D4","E4","F4","FSharp4","G4","GSharp4","A4","ASharp4","B4","C5","CSharp5","D5","DSharp5","E5","F5","FSharp5","G5","GSharp5","A5","B5","C6","D6","E6"};
	AudioPlayer MGP = AudioPlayer.player;
	AudioStream BGM;
	AudioData MD;
	ContinuousAudioDataStream loop = null;
	File musicFiles[] = {new File("G3.wav"), new File("A3.wav"), new File("B3.wav"),
	new File("C4.wav"),new File("D4.wav"),new File("E4.wav"),new File("F4.wav"),new File("FSharp4.wav"),new File("G4.wav"),new File("GSharp4.wav"),new File("A4.wav"),new File("ASharp4.wav"), new File("B4.wav"),
	new File("C5.wav"), new File("CSharp5.wav"),new File("D5.wav"),new File("DSharp5.wav"),new File("E5.wav"),new File("F5.wav"),new File("FSharp5.wav"),new File("G5.wav"),new File("GSharp5.wav"),new File("A5.wav"),new File("B5.wav"),
	new File("C6.wav"),new File("D6.wav"),new File("E6.wav")};
	Clip clip;
	JPanel buttonPanel;
	JFrame frame;
	ArrayList<File> musicQ = new ArrayList<File>();
	ArrayList<Integer> beatQ = new ArrayList<Integer>();
	int curBeat = 0;
	Thread t;
	ArrayList<File> cScaleNotes = new ArrayList<File>();
	ArrayList<Integer> cScaleBeats = new ArrayList<Integer>();
	ArrayList<File> bdayNotes = new ArrayList<File>();
	ArrayList<Integer> bdayBeats = new ArrayList<Integer>();
	ArrayList<File> oldMacNotes = new ArrayList<File>();
	ArrayList<Integer> oldMacBeats = new ArrayList<Integer>();
	ArrayList<File> saveNotes = new ArrayList<File>();
	ArrayList<Integer> saveBeats = new ArrayList<Integer>();
	String[] menu = {"C major scale", "Happy Birthday", "Old McDonald", "Save","Play Saved", "Random", "Add Column", "Remove Column", "Clear"};
	JComboBox dropDown = new JComboBox(menu);
	JLabel labelText = new JLabel();
	

	public SoundMatrix()
	{
		frame=new JFrame("SoundMatrix");
		//super("GridLayout Demo");
		frame.add(this);
		grid2 = new GridLayout(28,17);
		buttonPanel=new JPanel();
		buttonPanel.setLayout(grid2);
		
		dropDown.addActionListener(this);
		
		frame.add(dropDown, BorderLayout.NORTH);
		frame.add(labelText);
		
		//setting up buttons
		buttons = new JToggleButton[476];

		for (int count = 0; count<buttons.length; count++)
		{
			buttons[count] = new JToggleButton();
			if (count==0)
			{
				buttons[count].setEnabled(false);
			}
			/*if (count==476)
			{
				buttons[count].setText("C scale");
			}
			if (count==477)
			{
				buttons[count].setText("Birthday");
			}
			if (count==478)
			{
				buttons[count].setText("Old Mac");
			}
			if (count==479)
			{
				buttons[count].setText("Save");
			}
			if (count==480)
			{
				buttons[count].setText("Play saved");
			}
			if (count==481)
			{
				buttons[count].setText("Random");
			}*/
			if (count>0 && count<17)
			{
				buttons[count].setText(""+(count));
				buttons[count].setEnabled(false);
			}
			else if (count>0 && count%17==0)
			{
				buttons[count].setText(notes[count/17-1]);
				buttons[count].setEnabled(false);
			}
			buttons[count].addActionListener(this);
			buttonPanel.add(buttons[count]);
		}
		
		//3 songs
		
		cScaleNotes.add(new File("C4.wav"));
		cScaleNotes.add(new File("D4.wav"));
		cScaleNotes.add(new File("E4.wav"));
		cScaleNotes.add(new File("F4.wav"));
		cScaleNotes.add(new File("G4.wav"));
		cScaleNotes.add(new File("A4.wav"));
		cScaleNotes.add(new File("B4.wav"));
		cScaleNotes.add(new File("C5.wav"));
		cScaleNotes.add(new File("B4.wav"));
		cScaleNotes.add(new File("A4.wav"));
		cScaleNotes.add(new File("G4.wav"));
		cScaleNotes.add(new File("F4.wav"));
		cScaleNotes.add(new File("E4.wav"));
		cScaleNotes.add(new File("D4.wav"));
		cScaleNotes.add(new File("C4.wav"));
		cScaleNotes.add(new File("C4.wav"));
		cScaleNotes.add(new File("E4.wav"));
		cScaleNotes.add(new File("G4.wav"));
		cScaleNotes.add(new File("C5.wav"));
		
		cScaleBeats.add(1);
		cScaleBeats.add(2);
		cScaleBeats.add(3);
		cScaleBeats.add(4);
		cScaleBeats.add(5);
		cScaleBeats.add(6);
		cScaleBeats.add(7);
		cScaleBeats.add(8);
		cScaleBeats.add(9);
		cScaleBeats.add(10);
		cScaleBeats.add(11);
		cScaleBeats.add(12);
		cScaleBeats.add(13);
		cScaleBeats.add(14);
		cScaleBeats.add(15);
		cScaleBeats.add(16);
		cScaleBeats.add(16);
		cScaleBeats.add(16);
		cScaleBeats.add(16);
		
		bdayNotes.add(new File("C4.wav"));
		bdayNotes.add(new File("C4.wav"));
		bdayNotes.add(new File("D4.wav"));
		bdayNotes.add(new File("C4.wav"));
		bdayNotes.add(new File("F4.wav"));
		bdayNotes.add(new File("E4.wav"));
		bdayNotes.add(new File("B3.wav"));
		bdayNotes.add(new File("C4.wav"));
		bdayNotes.add(new File("C4.wav"));
		bdayNotes.add(new File("C4.wav"));
		bdayNotes.add(new File("D4.wav"));
		bdayNotes.add(new File("C4.wav"));
		bdayNotes.add(new File("G4.wav"));
		bdayNotes.add(new File("F4.wav"));
		bdayNotes.add(new File("A3.wav"));
		bdayNotes.add(new File("C4.wav"));
		
		bdayBeats.add(1);
		bdayBeats.add(2);
		bdayBeats.add(3);
		bdayBeats.add(4);
		bdayBeats.add(5);
		bdayBeats.add(6);
		bdayBeats.add(6);
		bdayBeats.add(6);
		bdayBeats.add(9);
		bdayBeats.add(10);
		bdayBeats.add(11);
		bdayBeats.add(12);
		bdayBeats.add(13);
		bdayBeats.add(14);
		bdayBeats.add(14);
		bdayBeats.add(14);
		
		oldMacNotes.add(new File("C4.wav"));
		oldMacNotes.add(new File("C4.wav"));
		oldMacNotes.add(new File("C4.wav"));
		oldMacNotes.add(new File("G3.wav"));
		oldMacNotes.add(new File("A3.wav"));
		oldMacNotes.add(new File("A3.wav"));
		oldMacNotes.add(new File("G3.wav"));
		oldMacNotes.add(new File("E4.wav"));
		oldMacNotes.add(new File("E4.wav"));
		oldMacNotes.add(new File("D4.wav"));
		oldMacNotes.add(new File("D4.wav"));
		oldMacNotes.add(new File("C4.wav"));
		
		oldMacBeats.add(1);
		oldMacBeats.add(2);
		oldMacBeats.add(3);
		oldMacBeats.add(4);
		oldMacBeats.add(5);
		oldMacBeats.add(6);
		oldMacBeats.add(7);
		oldMacBeats.add(10);
		oldMacBeats.add(11);
		oldMacBeats.add(12);
		oldMacBeats.add(13);
		oldMacBeats.add(14);
		
		frame.add(buttonPanel,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		frame.setVisible(true);
		t=new Thread(this);
		t.start();
		
	}

	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource()==dropDown)
		{
			JComboBox cb = (JComboBox)event.getSource();
			String msg = (String)cb.getSelectedItem();
			
			if (msg.equals("C major scale"))
			{
				curBeat=1;
				musicQ.clear();
				beatQ.clear();
				for (JToggleButton but:buttons)
					but.setSelected(false);
				for (int i=0; i<cScaleNotes.size(); i++)
				{
					File note = cScaleNotes.get(i);
					int beat = cScaleBeats.get(i);
					musicQ.add(note);
					beatQ.add(beat);
					int fileLoc = 0;
					for(int x=0; x<musicFiles.length; x++)
					{
						if((musicFiles[x].getName()).equals(note.getName()))
						{
							fileLoc=x;
						}
					}
					int count = ((fileLoc+1)*17)+(beat);
					buttons[count].setSelected(true);
				}
				/*for (File note: cScaleNotes)
					musicQ.add(note);
				for (int beat: cScaleBeats)
					beatQ.add(beat);*/
				
			}
			else if (msg.equals("Happy Birthday"))
			{
				curBeat=1;
				musicQ.clear();
				beatQ.clear();
				for (JToggleButton but:buttons)
					but.setSelected(false);
				for (int i=0; i<bdayNotes.size(); i++)
				{
					File note = bdayNotes.get(i);
					int beat = bdayBeats.get(i);
					musicQ.add(note);
					beatQ.add(beat);
					int fileLoc = 0;
					for(int x=0; x<musicFiles.length; x++)
					{
						if((musicFiles[x].getName()).equals(note.getName()))
						{
							fileLoc=x;
						}
					}
					int count = ((fileLoc+1)*17)+(beat);
					buttons[count].setSelected(true);
				}
			}
			else if (msg.equals("Old McDonald"))
			{
				curBeat=1;
				musicQ.clear();
				beatQ.clear();
				for (JToggleButton but:buttons)
					but.setSelected(false);
				for (int i=0; i<oldMacNotes.size(); i++)
				{
					File note = oldMacNotes.get(i);
					int beat = oldMacBeats.get(i);
					musicQ.add(note);
					beatQ.add(beat);
					int fileLoc = 0;
					for(int x=0; x<musicFiles.length; x++)
					{
						if((musicFiles[x].getName()).equals(note.getName()))
						{
							fileLoc=x;
						}
					}
					int count = ((fileLoc+1)*17)+(beat);
					buttons[count].setSelected(true);
				}
			}
			else if (msg.equals("Save"))
			{
				saveBeats.clear();
				saveNotes.clear();
				for (File note: musicQ)
				{
					saveNotes.add(note);
				}
				for (int beat: beatQ)
				{
					saveBeats.add(beat);
				}
			}
			else if (msg.equals("Play Saved"))
			{
				curBeat=1;
				musicQ.clear();
				beatQ.clear();
				for (JToggleButton but:buttons)
					but.setSelected(false);
				for (int i=0; i<saveNotes.size(); i++)
				{
					File note = saveNotes.get(i);
					int beat = saveBeats.get(i);
					musicQ.add(note);
					beatQ.add(beat);
					int fileLoc = 0;
					for(int x=0; x<musicFiles.length; x++)
					{
						if((musicFiles[x].getName()).equals(note.getName()))
						{
							fileLoc=x;
						}
					}
					int count = ((fileLoc+1)*17)+(beat);
					buttons[count].setSelected(true);
				}
			}
			else if (msg.equals("Clear"))
			{
				for (JToggleButton but:buttons)
					but.setSelected(false);
				musicQ.clear();
				beatQ.clear();
			}
			else if (msg.equals("Random"))
			{
				curBeat=1;
				for (JToggleButton but:buttons)
					but.setSelected(false);
				musicQ.clear();
				beatQ.clear();
				for (int i=0; i<16; i++)
				{
					int randBeat = (int)(Math.random()*16)+1;
					beatQ.add(randBeat);
					int randNote = (int)(Math.random()*27);
					musicQ.add(musicFiles[randNote]);
					int fileLoc = 0;
					for(int x=0; x<musicFiles.length; x++)
					{
						if((musicFiles[x].getName()).equals((musicFiles[randNote]).getName()))
						{
							fileLoc=x;
						}
					}
					int count = ((fileLoc+1)*17)+(randBeat);
					buttons[count].setSelected(true);
				}
			}
		}
		
		for (int i=0; i<buttons.length; i++)
		{
			//special buttons
			/*if (event.getSource()==buttons[0])
			{
				for (JToggleButton but:buttons)
					but.setSelected(false);
				musicQ.clear();
				beatQ.clear();

			}
			else if (event.getSource()==buttons[476])
			{
				curBeat=1;
				musicQ.clear();
				beatQ.clear();
				for (JToggleButton but:buttons)
					but.setSelected(false);
				for (File note: cScaleNotes)
				{
					musicQ.add(note);
				}
				for (int beat: cScaleBeats)
				{
					beatQ.add(beat);
				}
			}
			else if (event.getSource()==buttons[477])
			{
				curBeat=1;
				musicQ.clear();
				beatQ.clear();
				for (JToggleButton but:buttons)
					but.setSelected(false);
				for (File note: bdayNotes)
				{
					musicQ.add(note);
				}
				for (int beat: bdayBeats)
				{
					beatQ.add(beat);
				}
			}
			else if (event.getSource()==buttons[478])
			{
				curBeat=1;
				musicQ.clear();
				beatQ.clear();
				for (JToggleButton but:buttons)
					but.setSelected(false);
				for (File note: oldMacNotes)
				{
					musicQ.add(note);
				}
				for (int beat: oldMacBeats)
				{
					beatQ.add(beat);
				}
			}
			else if (event.getSource()==buttons[479])
			{
				for (File note: musicQ)
				{
					saveNotes.add(note);
				}
				for (int beat: beatQ)
				{
					saveBeats.add(beat);
				}
			}
			else if (event.getSource()==buttons[480])
			{
				curBeat=1;
				musicQ.clear();
				beatQ.clear();
				for (JToggleButton but:buttons)
					but.setSelected(false);
				for (File note: saveNotes)
				{
					musicQ.add(note);
				}
				for (int beat: saveBeats)
				{
					beatQ.add(beat);
				}
			}*/
			/*if (event.getSource()==buttons[481])
			{
				//random
				curBeat=1;
				musicQ.clear();
				beatQ.clear();
				for (JToggleButton but:buttons)
					but.setSelected(false);
				for (File note: oldMacNotes)
				{
					musicQ.add(note);
				}
				for (int beat: oldMacBeats)
				{
					beatQ.add(beat);
				}
			}*/
			//checking if note is already in arraylist
			// 1 arraylist is for the notes and 1 is for the beats
			// if the note and beat of the button pressed has the same index in both arraylists, remove that index from both lists
			//else
			//{
				if (buttons[i] == event.getSource())
				{

					int b = i%17;
					File f = musicFiles[i/17-1];
					boolean move = false;

					if (beatQ.contains(b))
					{
						for (int x=0; x<beatQ.size(); x++)
						{
							if (beatQ.get(x) == b && musicQ.get(x)==f)
							{
								musicQ.remove(x);
								beatQ.remove(x);
								move = true;
								x--;
							}
						}
					}

					//else add it to the arraylist
					if (!move)
					{
						musicQ.add(musicFiles[i/17-1]);
						beatQ.add(i%17);	
					}
					//prints out notes and beats
					/*for(File g:musicQ)
							System.out.println(g);
					for(int g:beatQ)
							System.out.println(g+"");*/
				}
			//}
		}
	}
	
	public void run()
	{
		while (true)
		{
			if(curBeat>16)
				curBeat=1;
			
				for (int b=0; b<beatQ.size(); b++)
				{
					if (beatQ.contains(curBeat))
					{
						//this loop is for multiple notes
						for (int x=0; x<beatQ.size(); x++)
						{
							if(beatQ.get(x) == curBeat)
							{
								music(musicQ.get(x));
							}
						}
					}
				}
				
			
			curBeat++;
			try
			{
				t.sleep(300);
			}catch(InterruptedException e)
			{
			}
		}
	}
	
	public void music(File note)
	{
		
		try {
				
				AudioInputStream ais = AudioSystem.getAudioInputStream(note);

				AudioFormat format = ais.getFormat();

				DataLine.Info info = new DataLine.Info(Clip.class,format);

				clip = (Clip) AudioSystem.getLine(info);

				clip.open(ais);
				
				clip.start();
					
				}catch(Exception e){}
	}
	
	
	public static void main(String args[] )
	{
		SoundMatrix application = new SoundMatrix();

	}
}