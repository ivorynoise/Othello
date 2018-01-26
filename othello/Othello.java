package othello;

import java.util.Scanner;

public class Othello {
	
	
	public Player takePlayerInput(int i){
		Scanner s= new Scanner(System.in);
		System.out.println("enter player "+i+"'s name");
		String name =s.nextLine();
		System.out.println("enter player "+i+"'s symbol");
		char symbol =s.nextLine().charAt(0);
		return new Player(name, symbol);		
	}
	
	public void startOthello(){
		Player player1=takePlayerInput(1);
		Player player2=takePlayerInput(2);
		while(player2.getName().equals(player1.getName())||player1.getSymbol()==player2.getSymbol()){
			System.out.println("either name or symbol of player2 is same take  different values");
			player2=takePlayerInput(2);
			
		}
		
		Board b=new Board(player1.getSymbol(), player2.getSymbol());
		
		boolean player1Turn=true;
		while(b.gameStatus()==Board.INCOMPLETE){
			
			Player currPlayer;
			if(player1Turn){
				currPlayer=player1;
			}
			else{
				currPlayer=player2;
			}
			try {
				b.printBoardWithAllPosibleMoves(currPlayer.getSymbol());
			} catch (WrongSymbolException e1) {
				System.out.println("sorry u have no move ur move is skipped");
				player1Turn=!player1Turn;
				continue;
			}
			//b.printBoard();
			boolean correctInput=false;
			while(!correctInput){
				System.out.println(currPlayer.getName()+" your turn. enter your cordinates");
				Scanner s=new Scanner(System.in);
				int x=s.nextInt();
				int y=s.nextInt();

				try {
					b.move(currPlayer.getSymbol(), x, y);
					correctInput=true;
				} catch (WrongInputException e) {
					System.out.println("wrong input. Try again!");

				} catch (WrongSymbolException e) {
					System.out.println("something went wrong");
					return;
				}
						
				
			}
			player1Turn=!player1Turn;
			
		}
		b.printBoard();
		int gameStatus=b.gameStatus();
		
		if (gameStatus == Board.DRAW) {
			System.out.println("Draw!");
		} else if (gameStatus == Board.PLAYER1WON) {
			System.out.println(player1.getName() + " you won!");
		} else {
			System.out.println(player2.getName() + " you won!");
		}
		
		
	}
	
	public static void main(String[] args) { 
		
		
		System.out.println("lets start the game");
		Othello o=new Othello();
		o.startOthello();
		
	}

}
