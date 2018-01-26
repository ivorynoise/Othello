package othello;

public class Board {

	char[][] board;
	char player1Symbol;
	char player2Symbol;
	int blocksFilledUp;
	public static final int INCOMPLETE=0;
	public static final int PLAYER1WON=1;
	public static final int PLAYER2WON=2;
	public static final int DRAW=3;
	
	public Board(char player1Symbol,char player2Symbol){
		this.player1Symbol=player1Symbol;
		this.player2Symbol=player2Symbol;
		board=new char[8][8];
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				board[i][j]=' ';
			}
		}	
		board[3][3]=player1Symbol;
		board[3][4]=player2Symbol;
		board[4][4]=player1Symbol;
		board[4][3]=player2Symbol;
		blocksFilledUp=4;
	}
	
	public int gameStatus(){ 
		
		int player1count=0;
		int player2count=0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				
				if(board[i][j]==player1Symbol){
					player1count++;
				}
				if(board[i][j]==player2Symbol){
					player2count++;
				}
			}
		}
		
		if(player1count==0){
			return PLAYER2WON;
		}
		if(player2count==0){
			return PLAYER1WON;
		}
		if(player1count+player2count<64){//blocksFilledUp<64){
			return INCOMPLETE;
		}
		if(player1count>player2count){
			return PLAYER1WON;
		}
		else if(player1count<player2count){
			return PLAYER2WON;
		}
		return DRAW;
		
	}
	
	public void move(char playerSymbol,int x, int y) throws WrongInputException,WrongSymbolException{
		if(playerSymbol!=player1Symbol && playerSymbol!=player2Symbol){
			throw new WrongSymbolException();
		}
		if(x<0||x>7||y<0||y>7||board[x][y]!=' '){
			throw new WrongInputException();
		}
		
		char otherPlayerSymbol;
		if(playerSymbol==player1Symbol){
			otherPlayerSymbol=player2Symbol;
		}else{
			otherPlayerSymbol=player1Symbol;
		}
		int iMin ;
		int jMin;
		int iMax;
		int jMax;
		
		if(x>0){
			 iMin=x-1;
		}
		else iMin=x;
		
		if(y>0){
			 jMin=y-1;
		}
		else jMin=y;
		
		if(x<7){
			 iMax=x+1;
		}
		else iMax=x;
		
		if(y<7){
			 jMax=y+1;
		}
		else jMax=y;
		int temp=jMin;
		boolean validMove=false;
		for(;iMin<=iMax;iMin++){
			jMin=temp;
			for(;jMin<=jMax;jMin++){
				if(!(iMin==x&&jMin==y)){
					
					if(board[iMin][jMin]==otherPlayerSymbol){
						int diffX=iMin-x;
						int diffY=jMin-y;
						int a=iMin;
						int b=jMin;
						while(board[a][b]!=playerSymbol){
							a=a+diffX;
							b=b+diffY;
							if(a>7||a<0||b>7||b<0){
								break;
							}
							if(board[a][b]==' '){
								break;
							}
							if(board[a][b]==playerSymbol){
								board[a][b]=playerSymbol;
								validMove=true;
								//System.out.println("valid move");
								a=a-diffX;
								b=b-diffY;
								while(!(a==iMin&&b==jMin)){
									board[a][b]=playerSymbol;
									a=a-diffX;
									b=b-diffY;
								}
								board[a][b]=playerSymbol;
								a=a-diffX;
								b=b-diffY;
								board[a][b]=playerSymbol;
								blocksFilledUp++;
								break;
							}
						}
					}
				}
			}			
		}
		if(!validMove){
			throw new WrongInputException();
		}
	}

	public void printBoard() {
		
		System.out.println("---------------------------------");
		for(int i=0;i<8;i++){
			System.out.print("| ");
			for(int j=0;j<8;j++){
				System.out.print(board[i][j]+" | ");				
			}
			System.out.println();
			System.out.println("---------------------------------");
		}
		System.out.println();
		
	}
	
	public void printBoardWithAllPosibleMoves(char playerSymbol) throws WrongSymbolException{
		if(playerSymbol!=player1Symbol && playerSymbol!=player2Symbol){
			throw new WrongSymbolException();
		}		
		char otherPlayerSymbol;
		if(playerSymbol==player1Symbol){
			otherPlayerSymbol=player2Symbol;
		}else{
			otherPlayerSymbol=player1Symbol;
		}
		char[][] tempBoard=new char[8][8];
		boolean validMove=false;
		for(int x =0;x<8;x++){
			for(int y =0;y<8;y++){
			
		int iMin ;
		int jMin;
		int iMax;
		int jMax;
		
		if(x>0){
			 iMin=x-1;
		}
		else iMin=x;
		
		if(y>0){
			 jMin=y-1;
		}
		else jMin=y;
		
		if(x<7){
			 iMax=x+1;
		}
		else iMax=x;
		
		if(y<7){
			 jMax=y+1;
		}
		else jMax=y;
		int temp=jMin;
		
		for(;iMin<=iMax;iMin++){
			jMin=temp;
			for(;jMin<=jMax;jMin++){
				if(board[x][y]!=' '){
					tempBoard[x][y]=board[x][y];
					break;
				}
				else{

					if(!(iMin==x&&jMin==y)){

						if(board[iMin][jMin]==otherPlayerSymbol){
							int diffX=iMin-x;
							int diffY=jMin-y;
							int a=iMin;
							int b=jMin;
							while(board[a][b]!=playerSymbol){
								a=a+diffX;
								b=b+diffY;
								if(a>7||a<0||b>7||b<0){
									break;
								}
								if(board[a][b]==' '){
									break;
								}
								if(board[a][b]==playerSymbol){
									tempBoard[x][y]='/';
									validMove=true;
									//System.out.println("good ");
									break;						
								}
							}
						}
					}
				}			
			}
		}
			}
		}
		if(!validMove){
			System.out.println("game ended");
			throw new WrongSymbolException();
		}
		System.out.println("---------------------------------");
		System.out.println("   0   1   2   3   4   5   6   7");
		for(int i=0;i<8;i++){
			System.out.print(i+"| ");
			for(int j=0;j<8;j++){
				System.out.print(tempBoard[i][j]+" | ");				
			}
			System.out.println();
			System.out.println("---------------------------------");
		}
		System.out.println();
	}

	public char[][] returnBoardWithAllPosibleMoves(char playerSymbol) throws WrongSymbolException{
		if(playerSymbol!=player1Symbol && playerSymbol!=player2Symbol){
			throw new WrongSymbolException();
		}		
		char otherPlayerSymbol;
		if(playerSymbol==player1Symbol){
			otherPlayerSymbol=player2Symbol;
		}else{
			otherPlayerSymbol=player1Symbol;
		}
		char[][] tempBoard=new char[8][8];
		boolean validMove=false;
		for(int x =0;x<8;x++){
			for(int y =0;y<8;y++){
			
		int iMin ;
		int jMin;
		int iMax;
		int jMax;
		
		if(x>0){
			 iMin=x-1;
		}
		else iMin=x;
		
		if(y>0){
			 jMin=y-1;
		}
		else jMin=y;
		
		if(x<7){
			 iMax=x+1;
		}
		else iMax=x;
		
		if(y<7){
			 jMax=y+1;
		}
		else jMax=y;
		int temp=jMin;
		
		for(;iMin<=iMax;iMin++){
			jMin=temp;
			for(;jMin<=jMax;jMin++){
				if(board[x][y]!=' '){
					tempBoard[x][y]=board[x][y];
					break;
				}
				else{

					if(!(iMin==x&&jMin==y)){

						if(board[iMin][jMin]==otherPlayerSymbol){
							int diffX=iMin-x;
							int diffY=jMin-y;
							int a=iMin;
							int b=jMin;
							while(board[a][b]!=playerSymbol){
								a=a+diffX;
								b=b+diffY;
								if(a>7||a<0||b>7||b<0){
									break;
								}
								if(board[a][b]==' '){
									break;
								}
								if(board[a][b]==playerSymbol){
									tempBoard[x][y]='/';
									validMove=true;
									//System.out.println("good ");
									break;						
								}
							}
						}
					}
				}			
			}
		}
			}
		}
		if(!validMove){
			
		//return	returnBoardWithAllPosibleMoves(otherPlayerSymbol);
			//System.out.println("game ended");
			throw new WrongSymbolException();
		}
		
		return tempBoard;
//		System.out.println("---------------------------------");
//		System.out.println("   0   1   2   3   4   5   6   7");
//		for(int i=0;i<8;i++){
//			System.out.print(i+"| ");
//			for(int j=0;j<8;j++){
//				System.out.print(tempBoard[i][j]+" | ");				
//			}
//			System.out.println();
//			System.out.println("---------------------------------");
//		}
//		System.out.println();
	}



}
		

		
	
