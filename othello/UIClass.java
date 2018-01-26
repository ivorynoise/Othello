package othello;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.Scanner;

public class UIClass  extends JFrame implements ActionListener{
	
	
	
	private static final long serialVersionUID = 1L;
	JPanel[] row = new JPanel[9];
    JButton[] button = new JButton[65];
    String[] buttonString = {"Read Input"};
    int[] dimW = {300,50,100};
    int[] dimH = {35, 40};
    Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
    Dimension doneButtonDimension = new Dimension(dimW[2], dimH[0]);
    Dimension rColumnDimension = new Dimension(dimW[1], dimH[1]);
    boolean[] function = new boolean[4];
    double[] temporary = {0, 0};
    JTextArea display = new JTextArea(2,20);
    Font font = new Font("Times new Roman", Font.BOLD, 14);
    char r='r';
	char w='w';
	Player player1=new Player("Red", r);
	Player player2=new Player("White", w);
	Board b=new Board(r,w);
	boolean player1Turn=true;
	Player currPlayer;
	char tempBoard[][];
	boolean correctInput=false;
      
    UIClass() {
        super("OTHALO");
        setDesign();
        setSize(428, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(9, 9);
        setLayout(grid);
        
        for(int i = 0; i < 4; i++)
            function[i] = false;
        
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER,1,1);
        for(int i = 0; i < 9; i++)
            row[i] = new JPanel();
        row[0].setLayout(f1);
        for(int i = 1; i < 9; i++)
            row[i].setLayout(f2);
        
        for(int i = 0; i < 65; i++) {
            button[i] = new JButton();
            button[i].setBackground(Color.blue);
            button[i].setFont(font);
            button[i].addActionListener(this);
        }
        button[0].setBackground(Color.cyan);
        button[0].setText(buttonString[0]);
        
        display.setFont(font);
        display.setEditable(true);
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        display.setPreferredSize(displayDimension);
        //display.addComponentListener(bu);
        button[0].setPreferredSize(doneButtonDimension);
        for(int i = 1; i < 65; i++)
            button[i].setPreferredSize(rColumnDimension);
       
        
        row[0].add(display);
        row[0].add(button[0]);        
        add(row[0]);
        int k=1;
        for(int i = 1; i < 9; i++){
        	for(int j = 0; j < 8; j++){
        		row[i].add(button[k]);
        		k++;
        	}
        	add(row[i]);
        }
        
        
        setVisible(true);
    }
    
    public final void setDesign() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception e) { 
        	System.out.println("set design error");
        	return;
        }
    }
  
	public void startOthalo(){
		
		if(b.gameStatus()==Board.INCOMPLETE){
			
			for(int i=0;i<8;i++){
	    		int j=0;
	    		for(;j<8;j++){
	    			if(b.board[i][j]=='r'){
	    				button[i*8+j+1].setBackground(Color.RED);
	    			}
	    			if(b.board[i][j]=='w'){
	    				button[i*8+j+1].setBackground(Color.WHITE);
	    			}
	    			else button[i*8+j+1].setBackground(Color.blue);
	    		}
			}
			
			
			if(player1Turn){
				currPlayer=player1;
			}
			else{
				currPlayer=player2;
			}
			
			try {
				 tempBoard=b.returnBoardWithAllPosibleMoves(currPlayer.getSymbol());
			} catch (WrongSymbolException e1) {
				player1Turn=!player1Turn;
				startOthalo();
				return;
			}
			
			display.setText(currPlayer.getName()+" your turn  ");
			if(currPlayer==player1){
				for(int i=0;i<8;i++){
					int j=0;
					for(;j<8;j++){
						if(tempBoard[i][j]=='r'){
							button[i*8+j+1].setBackground(Color.RED);
						}
						if(tempBoard[i][j]=='w'){
							button[i*8+j+1].setBackground(Color.WHITE);
						}
						if(tempBoard[i][j]=='/'){
							button[i*8+j+1].setBackground(Color.MAGENTA);
						}
					}
				}
			}
			else{
				for(int i=0;i<8;i++){
					int j=0;
					for(;j<8;j++){
						if(tempBoard[i][j]=='r'){
							button[i*8+j+1].setBackground(Color.RED);
						}
						if(tempBoard[i][j]=='w'){
							button[i*8+j+1].setBackground(Color.WHITE);
						}
						if(tempBoard[i][j]=='/'){
							button[i*8+j+1].setBackground(Color.yellow);
						}
					}
				}
			}
		}
		else{
			int gameStatus=b.gameStatus();
			
			if (gameStatus == Board.DRAW) {
				display.setText("Draw!");
				//System.out.println("Draw!");
			} else if (gameStatus == Board.PLAYER1WON) {
				display.setText(player1.getName() + " you won!");
				//System.out.println(player1.getName() + " you won!");
			} else {
				display.setText(player2.getName() + " you won!");
				//System.out.println(player2.getName() + " you won!");
			}
			return;
		}
	}

	public void playMove(){
		
		tempBoard=b.board;
		if(currPlayer==player1){
			for(int i=0;i<8;i++){
				int j=0;
				for(;j<8;j++){
					if(tempBoard[i][j]=='r'){
						button[i*8+j+1].setBackground(Color.RED);
					}
					if(tempBoard[i][j]=='w'){
						button[i*8+j+1].setBackground(Color.WHITE);
					}
					if(tempBoard[i][j]==' '){
						
						button[i*8+j+1].setBackground(Color.blue);
					}if(tempBoard[i][j]=='/'){
						
						button[i*8+j+1].setBackground(Color.cyan);
					}
				}
			}
		}
		else{
			for(int i=0;i<8;i++){
				int j=0;
				for(;j<8;j++){
					if(tempBoard[i][j]=='r'){
						button[i*8+j+1].setBackground(Color.RED);
					}
					if(tempBoard[i][j]=='w'){
						button[i*8+j+1].setBackground(Color.WHITE);
					}
					if(tempBoard[i][j]==' '){
						button[i*8+j+1].setBackground(Color.blue);
					}
				}
			}
		}
		player1Turn=!player1Turn;
		startOthalo();
	}
	
	 public void actionPerformed(ActionEvent ae) {
		 
		 if(ae.getSource() == button[0]){
				//display.append("7");
				//display.getText();
				display.setText("");
				playMove();
			}
		 else{
	    	for(int i=0;i<8;i++){
	    		int j=0;
	    		for(;j<8;j++){
	    			if(ae.getSource() == button[i*8+j+1]){
	    				display.setText("");
	    				
	    				try {
							b.move(currPlayer.getSymbol(), i, j);
							
						} catch (WrongInputException e) {
							display.setText("wrong input");
							return ;
						} catch (WrongSymbolException e) {
							display.setText("something went wrong");
							return;
						}
	    				display.setText(j+"   "+i);
	    				
	    			}

	    			
	    		}
	    	}
	    	display.setText("");
			playMove();
		 }
	    	

	    }
    	
	public static void main(String[] args) {
		
		UIClass u=new UIClass();
		u.startOthalo();
		}

}
