package War;


//Can still add the flipping of cards when war is 
//activated and show what cards are won and lost
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WarGUI extends JFrame
{
	//initialize the game parts
	private JFrame frame;
	private GameEngineGUI Game;
	private ImageIcon cardBack;
	private JLabel back1;
	private JLabel back2;
	private JButton playButton;
	private JButton stopButton;
	private JPanel buttonPanel;
	private JPanel deck1Panel;
	private JPanel deck2Panel;
	private JLabel topLabel;
	private JPanel flipPanel;
	private JPanel deck1BackPanel;
	private JPanel deck2BackPanel;
	
	private JPanel deck1PlayPanel;
	private JPanel deck2PlayPanel;
	private JLabel deck1Lable;
	private JLabel deck2Lable;
	
	private ImageIcon card1Image;
	private ImageIcon card2Image;
	
	private JLabel card1;
	private JLabel card2;
	
	
	/**
	 * constructor. makes the initial war game GUI
	 */
	public WarGUI()
	{
		
		frame = new JFrame();
		frame.setLayout(new BorderLayout()); //allows the elements to start top middle and fill accordingly
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//make label for card back. 
		cardBack = new ImageIcon("CardPics//back.jpg");
		//make toe instances for the Back of the card
		back1 = new JLabel(cardBack);
		back2 = new JLabel(cardBack);
		
		playButton = new JButton ("WAR!"); //create button
		playButton.addActionListener(new ButtonListener());
		stopButton = new JButton ("End Game"); //create button
		stopButton.addActionListener(new ButtonListener());
		
		//make panels for all instances
		buttonPanel = new JPanel();//south panel for card flip
		buttonPanel.add(playButton);
		buttonPanel.add(stopButton);
		
		buttonPanel.setBackground(Color.DARK_GRAY);
		deck1Panel = new JPanel();//where deck 1 will sit in west
		
		deck1Panel.setBackground(Color.CYAN);
		deck2Panel = new JPanel();//where deck 2 will sit in east
		
		deck2Panel.setBackground(Color.CYAN);
		topLabel = new JLabel("Welcome To War!", JLabel.CENTER); //North label for title
		
		//break center panel into 2 panels
		flipPanel = new JPanel(new GridLayout(1, 4));
		
		deck1BackPanel = new JPanel(); //left side of center
		
		deck1BackPanel.add(back1);//prints back of card
		deck1BackPanel.setBackground(Color.RED);
		
		deck1PlayPanel = new JPanel();
		//card1Image = new ImageIcon();
		deck1PlayPanel.setBackground(Color.GREEN);
		
		
		deck2BackPanel = new JPanel(); //right side of panel
		//deck2BackPanel.setLayout(new FlowLayout());
		deck2BackPanel.add(back2); //prints back of card
		deck2BackPanel.setBackground(Color.RED);

		deck2PlayPanel = new JPanel();
		//deck2PlayPanel.setLayout(new FlowLayout());
		deck2PlayPanel.setBackground(Color.GREEN);
		

	    

		flipPanel.add(deck1BackPanel);
		flipPanel.add(deck1PlayPanel);
		flipPanel.add(deck2PlayPanel);
		flipPanel.add(deck2BackPanel);
		
		

		
		//add to panels
		
		
		deck1Lable = new JLabel("Deck 1"); //create label
		deck1Panel.add(deck1Lable);
		
		deck2Lable = new JLabel("Deck 2"); //create label
		deck2Panel.add(deck2Lable);
		
		
		frame.add(buttonPanel, BorderLayout.SOUTH);//display button inside the panel
		
		frame.add(deck1Panel, BorderLayout.WEST);
		
		frame.add(deck2Panel, BorderLayout.EAST);
		
		frame.add(topLabel, BorderLayout.NORTH);
		
		frame.add(flipPanel, BorderLayout.CENTER);
		
		frame.setVisible(true); //sets the window so it can be seen
		
		
		
		//frame.pack();//sets the window size that allows for all the elements
		//OR______
		frame.setSize(1000, 700); //force a window size
		frame.validate();

		//button.addActionListener(new ButtonListener());
//		 button.addActionListener(new ActionListener() {
//
//			   public void actionPerformed(ActionEvent e) {
//			    // Execute when button is pressed
//			    System.out.println("You clicked the 'PLAY!' button");
//
//			    //play();
//			   }
//			  });
//		//start game
		Game = new GameEngineGUI();

	}

	/**
	 * button action for when it is pressed
	 * @author James
	 *
	 */
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			//JButton war = (JButton)(e.getSource());
			JButton end = (JButton)(e.getSource());
	         if (end.getText().equals("End Game"))
	         {
	        	 String endString = Game.compareFinalHand();
	   	      	 JOptionPane.showMessageDialog(null , endString);
	   	      	 System.exit(0);
	         }
	         else //play game
	         {
				Game.flip();
				card1Image = new ImageIcon(Game.image1);
				card2Image = new ImageIcon(Game.image2);
				deck1PlayPanel.removeAll();
				deck2PlayPanel.removeAll();
				card1 = new JLabel(card1Image);
				card2 = new JLabel(card2Image);
				deck1PlayPanel.add(card1);
				deck2PlayPanel.add(card2);
				frame.validate();
				
				int hand = Game.compare(Game.deck1, Game.deck2);
				if (hand == 1)
				{
					Game.addToDeck(Game.deck1, Game.deck2);
					Game.removeFromDeck(Game.deck2);
					
				}
				else if(hand == 2)
				{
					Game.addToDeck(Game.deck2, Game.deck1);
					Game.removeFromDeck(Game.deck1);
				}
				//if they are the same
				else
				{
					//call war
					Game.war(); 
	
					//3 face down cards for deck 1
					for (int i =0; i < 3; i++)
					{
						card1Image = new ImageIcon("CardPics//back.jpg");
						
						card1 = new JLabel(card1Image);;
						deck1PlayPanel.add(card1);
						frame.validate();
					}
					//3 face down cards for deck 2
					for (int i =0; i < 3; i++)//(Card i : Game.warDeck2)//
					{
						//card2Image = new ImageIcon("CardPics//"+i.toString()+".jpg");
						card2Image = new ImageIcon("CardPics//back.jpg");
						card2 = new JLabel(card2Image);
						deck2PlayPanel.add(card2);
						frame.validate();
					}
					//flip and print new compare cards
					//Game.flip();
					card1Image = new ImageIcon(Game.image1);
					card2Image = new ImageIcon(Game.image2);
					card1 = new JLabel(card1Image);
					card2 = new JLabel(card2Image);
					deck1PlayPanel.add(card1);
					deck2PlayPanel.add(card2);
					frame.validate();
				}
	        }
		}
	}

	
	/**
	 * call game
	 */
	public static void main(String [] args)
	{
		new WarGUI();
	}

}
