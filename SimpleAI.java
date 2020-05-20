
public class SimpleAI {

	private java.util.Random _generator;
	
	public SimpleAI() {
		_generator = new java.util.Random();
	}
	
	public void move(Board board , boolean player) {
		Board boardCopy;
		double max = Double.NEGATIVE_INFINITY;
		int thisCount;
		int[] newMove;
		newMove = new int[4];
		boolean canMove = false;
		boolean move = false;
		for(int row = 0; row < 7; row++) {
			for(int col = 0; col < 7; col++) {
				if(board.HasPiece(row , col) && board.IsPlayers(player, row, col)) {
					for(int newRow = row-2; newRow <= row+2; newRow++) {
						for(int newCol = col-2; newCol <= col+2; newCol++) {
							if(IsValidAdd(newRow , newCol , row , col , board)) {
								boardCopy = board.Copy();
								boardCopy.AddPiece(newRow, newCol, false);
								thisCount = CountPieces(boardCopy);
								if(thisCount > max) {
									max = thisCount;
									newMove[0] = row;
									newMove[1] = col;
									newMove[2] = newRow;
									newMove[3] = newCol;
									canMove = true;
									move = false;
								}
								else if(thisCount == max) {
									int nextInt = _generator.nextInt(100);
									if(nextInt >= 50) {
										newMove[0] = row;
										newMove[1] = col;
										newMove[2] = newRow;
										newMove[3] = newCol;
										canMove = true;
										move = false;
									}
								}
							}
							else if(IsValidMove(newRow , newCol , row , col , board)) {
								boardCopy = board.Copy();
								boardCopy.MovePiece(row ,  col , newRow , newCol);
								thisCount = CountPieces(boardCopy);
								if(thisCount > max) {
									max = thisCount;
									newMove[0] = row;
									newMove[1] = col;
									newMove[2] = newRow;
									newMove[3] = newCol;
									canMove = true;
									move = true;
								}
								else if(thisCount == max) {
									int nextInt = _generator.nextInt(100);
									if(nextInt >= 50) {
										newMove[0] = row;
										newMove[1] = col;
										newMove[2] = newRow;
										newMove[3] = newCol;
										canMove = true;
										move = true;
									}
								}
							}
						}
					}
				}
			}
		}
		if(canMove) {
			if(move) {
				board.MovePiece(newMove[0] ,  newMove[1] ,  newMove[2] ,  newMove[3]);
			}
			else {
				board.AddPiece(newMove[2] ,  newMove[3] ,  player);
			}
		}
	}
	
	private int CountPieces(Board board) {
		int count = 0;
		for(int row = 0; row < 7; row++) {
			for(int col = 0; col < 7; col++) {
				if(board.HasPiece(row, col) && board.IsPlayers(true, row, col)) {
					count -= 1;
				}
				else if(board.HasPiece(row, col) && board.IsPlayers(false, row, col)) {
					count += 1;
				}
			}
		}
		return count;
	}
	
	private boolean IsValidAdd(int row , int col , int oldRow , int oldCol , Board board) {
		return (row >= 0 && row <=6 && col >= 0 && col <= 6 && row >= oldRow - 1 && row <= oldRow + 1 && col >= oldCol - 1 && col <= oldCol + 1 && !board.HasPiece(row, col));
	}
	
	private boolean IsValidMove(int row , int col , int oldRow , int oldCol , Board board) {
		return (row >= 0 && row <=6 && col >= 0 && col <= 6 && row >= oldRow - 2 && row <= oldRow + 2 && col >= oldCol - 2 && col <= oldCol + 2 && !board.HasPiece(row, col));
	}
}
