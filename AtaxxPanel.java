@SuppressWarnings("serial")
public class AtaxxPanel extends javax.swing.JPanel {
	
	private Piece[][] _board;
	private Square[][] _grid;
	private java.awt.Color _playerColor = java.awt.Color.blue;
	private java.awt.Color _cpuColor = java.awt.Color.green;
	
	public AtaxxPanel() {
		this.setBackground(java.awt.Color.BLACK);
		this.setSize(AtaxxConstants.screenWidth , AtaxxConstants.screenHeight);
		_board = new Piece[7][7];
		_grid = new Square[7][7];
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				_board[i][j] = null;
				_grid[i][j] = new Square(j , i);
			}
		}
		_board[0][0] = new Piece(_playerColor , true , 0 , 0);
		_board[6][6] = new Piece(_playerColor , true , 6 , 6);
		_board[0][6] = new Piece(_cpuColor , false , 6 , 0);
		_board[6][0] = new Piece(_cpuColor , false , 0 , 6);
	}
	
	public void paintComponent(java.awt.Graphics aBrush) {
		super.paintComponent(aBrush);
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D)aBrush;
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				_grid[i][j].DrawAndFill(betterBrush);
				if(_board[i][j] != null) {
					_board[i][j].DrawAndFill(betterBrush);
				}
			}
		}
	}
	
}
