package edu.ucsb.cs56.projects.games.rock_paper_scissors;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;
import java.applet.*;
import java.net.*;


/**
 * This class creates the GUI for the game.  It allows you to select your options for moves, as well as tell you how many games you've played, wont, lost, and tied.  There are also animations that appears on the screen when the player and the computer have chosen their moves.
 *
 *
 * @author Lesley Khuu (previous authors: Gerard Gonzalez and Connor Tinsely (Original:Dennis Huynh and Aki Stankoski)) 
 */

public class GameGUI extends JPanel{
    JFrame frame = new JFrame();
    JPanel thePanel = new JPanel(new GridBagLayout());
    JLabel title = new JLabel("Rock Paper Scissors Game");
    JLabel wins = new JLabel("Wins");
    JLabel losses = new JLabel("Losses");
    JLabel ties = new JLabel("Ties");
    JLabel gamesPlayed = new JLabel("Games Played");
    JButton rock = new JButton("Charmander");
    JButton scissors = new JButton("Squirtle");
    JButton paper = new JButton("Bulbasaur");
    JButton changeGame = new JButton ("Go back to Game Selection");
    

    JTextField played = new JTextField(10);
    JTextField win = new JTextField(10);
    JTextField lose = new JTextField(10);
    JTextField tie = new JTextField(10);
    JTextArea text = new JTextArea(20, 30);
    JScrollPane scroll = new JScrollPane(text);
    Player player = new Player();
    Computer computer = new Computer();
    Game game = new Game();
    String move;
    int winner;
    int games=0;
	JLabel picLabel;
    JLabel label1, label2, label3, label4, label5, label6;
    public void setUpHomeScreen(){
		
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();  
		
		gc.gridx = 0;
		gc.gridy = 1;
		//String path1 = "/Users/edwardgonzalez/Desktop/CHOICE/src/rps/images/charmander.jpg";
		//String path2 = "/Users/edwardgonzalez/Desktop/CHOICE/src/rps/images/squirtle.jpg";
		//String path3 = "/Users/edwardgonzalez/Desktop/CHOICE/src/rps/images/bulbasaur.jpg";
		
		
		try {
			java.net.URL path1 = new URL("http://cs.ucsb.edu/~gegonzalez/cs56/S12/issues/0000513/browse/src/rps/images/charmander.jpg");
			java.net.URL path2 = new URL("http://cs.ucsb.edu/~gegonzalez/cs56/S12/issues/0000513/browse/src/rps/images/squirtle.jpg");
			java.net.URL path3 = new URL("http://cs.ucsb.edu/~gegonzalez/cs56/S12/issues/0000513/browse/src/rps/images/bulbasaur.jpg");
			
			ImageIcon image = new ImageIcon(path1);
			label1 = new JLabel(" ", image, JLabel.LEFT);
			frame.add(label1,gc);
			label1.setVisible(false);		
			
			ImageIcon image2 = new ImageIcon(path2);
			label2 = new JLabel(" ", image2, JLabel.LEFT);
			frame.add(label2,gc);
			label2.setVisible(false);
			
			ImageIcon image3 = new ImageIcon(path3);
			label3 = new JLabel(" ", image3, JLabel.LEFT);
			frame.add(label3,gc);
			label3.setVisible(false);
			
			gc.gridx = 3;
			gc.gridy = 1;
			
			label4 = new JLabel(" ", image, JLabel.LEFT);
			frame.add(label4,gc);
			label4.setVisible(false);		
			
			label5 = new JLabel(" ", image2, JLabel.LEFT);
			frame.add(label5,gc);
			label5.setVisible(false);
			
			label6 = new JLabel(" ", image3, JLabel.LEFT);
			frame.add(label6,gc);
			label6.setVisible(false);

			
			
		}
		catch (IOException ioe) {
			//log the error
		}
		
		
		
		
        gc.insets = new Insets(5,75,5,5);
        gc.gridx=1;
        gc.gridy=0;
        frame.add(title, gc);
        gc.gridx=1;
        gc.gridy=1;
        text.setEditable(false);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(400,300));
        frame.add(scroll, gc);
        gc.gridx=1;
        gc.gridy=2;
        frame.add(gamesPlayed, gc);
        gc.gridx=1;
        gc.gridy=3;
        played.setEditable(false);
        frame.add(played, gc);
        gc.gridx=0;
        gc.gridy=4;
        rock.addActionListener(new RockListener());
        frame.add(rock, gc);
        gc.gridy=5;
        paper.addActionListener(new PaperListener());
        frame.add(paper, gc);
        gc.gridy=6;
        scissors.addActionListener(new ScissorsListener());
        frame.add(scissors, gc);
        gc.gridx=2;
        gc.gridy=4;
        gc.gridx=1;
        changeGame.addActionListener( new ChangeGameListener());
        frame.add(changeGame, gc);
        gc.gridx=2;
        gc.insets = new Insets(5,5,5,5);
        frame.add(wins, gc);
        gc.gridy=5;
        frame.add(losses, gc);
        gc.gridy=6;
        frame.add(ties, gc);
        gc.insets = new Insets(5,5,5,75);
        gc.gridx=3;
        gc.gridy=4;
        win.setEditable(false);
        frame.add(win, gc);
        gc.gridy=5;
        lose.setEditable(false);
        frame.add(lose, gc);
        gc.gridy=6;
        tie.setEditable(false);
        frame.add(tie, gc);
        frame.setSize(500,500);
        frame.setBackground(Color.WHITE);
        frame.pack();
       frame.setVisible(true);    
        
		
    }//end setUpHomeScreen
    
	
    
    class RockListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
			label3.setVisible(false);
			label2.setVisible(false);
			label1.setVisible(true);

            player.setMove("Charmander");
            computer.setMove();
            winner = game.getWinner(player.getMove(), computer.getMove());
            text.append("------------------\n");
            text.append("   Game: " + (games+1) + "\n");
            text.append("------------------\n");
            text.append("You played: Charmander \n");
        	if(computer.getMove()==0){
            		 move = "Bulbasaur";
				label4.setVisible(false);
				label5.setVisible(false);
				label6.setVisible(true);
				
		}
                if(computer.getMove()==1){
            		 move = "Squirtle";
					label4.setVisible(false);
					label6.setVisible(false);
					label5.setVisible(true);
					
                }
                if(computer.getMove()==2){
            		 move = "Charmander";
					label6.setVisible(false);
					label5.setVisible(false);
					label4.setVisible(true);
                }
            text.append("Computer played: " + move + "\n");
                if(winner==1){
            		 text.append("You WIN!\n\n");
		}
                if(winner==2){
            		 text.append("You LOSE!\n\n");
                }
                if(winner==3){
            		 text.append("You TIE!\n\n");
                }
            win.setText(""+game.getScore(0));
            lose.setText(""+game.getScore(1));
            tie.setText(""+game.getScore(2));
            games++;
            played.setText(""+games);
        }
    }//end RockListener
        
    class PaperListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
			label1.setVisible(false);
			label2.setVisible(false);
			label3.setVisible(true);
            player.setMove("Bulbasaur");
            computer.setMove();
            winner = game.getWinner(player.getMove(), computer.getMove());
            text.append("------------------\n");
            text.append("   Game: " + (games+1) + "\n");
            text.append("------------------\n");
            text.append("You played: Bulbasaur \n");
        	if(computer.getMove()==0){
				move = "Charmander";
				label6.setVisible(false);
				label5.setVisible(false);
				label4.setVisible(true);
				
			}
			if(computer.getMove()==1){
				move = "Bulbasaur";
				label5.setVisible(false);
				label4.setVisible(false);
				label6.setVisible(true);
				
			}
			if(computer.getMove()==2){
				move = "Squirtle";
				label6.setVisible(false);
				label4.setVisible(false);
				label5.setVisible(true);
			}
            text.append("Computer played: " + move + "\n");
                if(winner==1){
            		 text.append("You WIN!\n\n");
		}
                if(winner==2){
            		 text.append("You LOSE!\n\n");
                }
                if(winner==3){
            		 text.append("You TIE!\n\n");
                }
            win.setText(""+game.getScore(0));
            lose.setText(""+game.getScore(1));
            tie.setText(""+game.getScore(2));
            games++;
            played.setText(""+games);
        }
    }//end PaperListener
        
    class ScissorsListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
			label1.setVisible(false);
			label3.setVisible(false);
			label2.setVisible(true);

            player.setMove("Squirtle");
            computer.setMove();
            winner = game.getWinner(player.getMove(), computer.getMove());
            text.append("------------------\n");
            text.append("   Game: " + (games+1) + "\n");
            text.append("------------------\n");
            text.append("You played: Squirtle \n");
        	if(computer.getMove()==0){
				move = "Squirtle";
				label6.setVisible(false);
				label4.setVisible(false);
				label5.setVisible(true);
				
			}
			if(computer.getMove()==1){
				move = "Charmander";
				label5.setVisible(false);
				label6.setVisible(false);
				label4.setVisible(true);
				
			}
			if(computer.getMove()==2){
				move = "Bulbasaur";
				label5.setVisible(false);
				label4.setVisible(false);
				label6.setVisible(true);
			}
            text.append("Computer played: " + move + "\n");
                if(winner==1){
            		 text.append("You WIN!\n\n");
		}
                if(winner==2){
            		 text.append("You LOSE!\n\n");
                }
                if(winner==3){
            		 text.append("You TIE!\n\n");
                }
            win.setText(""+game.getScore(0));
            lose.setText(""+game.getScore(1));
            tie.setText(""+game.getScore(2));
            games++;
            played.setText(""+games);
        }
    }//end ScissorsListener
  
          
    class ChangeGameListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    frame.dispose();
	    new RunGame();
	    }
    }
   

}//end GameGUI



