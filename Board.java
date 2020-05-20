
public class Board {

	private Square[][] _grid;
	private Piece[][] _pieces;
	
	public Board() {
		_grid = new Square[7][7];
		_pieces = new Piece[7][7];
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				_pieces[i][j] = null;
				_grid[i][j] = new Square(i , j);
			}
		}
		_pieces[0][0] = new Piece(true , 0 , 0);
		_pieces[6][6] = new Piece(true , 6 , 6);
		_pieces[0][6] = new Piece(false , 0 , 6);
		_pieces[6][0] = new Piece(false , 6 , 0);
	}
	
	private Board(Piece[][] pieces) {
		_grid = new Square[7][7];
		_pieces = new Piece[7][7];
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				_pieces[i][j] = null;
				_grid[i][j] = new Square(i , j);
				if(pieces[i][j] != null) {
					_pieces[i][j] = new Piece(pieces[i][j].IsPlayers(), i , j);
				}
			}
		}
	}
	
	public void DrawAndFill(java.awt.Graphics2D betterBrush) {
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				_grid[i][j].DrawAndFill(betterBrush);
				if(_pieces[i][j] != null) {
					_pieces[i][j].DrawAndFill(betterBrush);
				}
			}
		}
	}
	
	public void AddPiece(int row , int col , boolean players) {
		_pieces[row][col] = new Piece(players , row , col);
		Infect(players , row , col);
	}
	
	public void MovePiece(int oldRow , int oldCol , int newRow , int newCol) {
		_pieces[newRow][newCol] = _pieces[oldRow][oldCol];
		_pieces[oldRow][oldCol] = null;
		_pieces[newRow][newCol].SetCell(newRow , newCol);
		boolean player = _pieces[newRow][newCol].IsPlayers();
		Infect(player , newRow , newCol);
	}
	
	public boolean HasPiece(int row , int col) {
		if(_pieces[row][col] != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean IsPlayers(boolean player , int row , int col) {
		if(_pieces[row][col] != null && player == _pieces[row][col].IsPlayers())
			return true;
		else {
			return false;
		}
	}
	
	private void Infect(boolean players, int row , int col) {
		for(int i = row - 1 ; i <= row + 1; i++) {
			for(int j = col - 1; j <= col + 1; j++) {
				if(i >= 0 && i <= 6 && j >=0 && j <=6) {
					if(IsPlayers(!players , i , j)) {
						_pieces[i][j].ChangeColor();
					}
				}
			}
		}
	}
	
	public Board Copy() {
		Board toReturn = new Board(_pieces);
		return toReturn;
	}
}

