@SuppressWarnings("serial")
public class AtaxxPanel extends javax.swing.JPanel {
	
	private Board _board;
	private boolean _pieceSelected;
	private int _selectedRow;
	private int _selectedCol;
	private boolean _player0Turn;
	private MouseListener _listener;
	
	public AtaxxPanel() {
		this.setBackground(java.awt.Color.BLACK);
		this.setSize(AtaxxConstants.screenWidth , AtaxxConstants.screenHeight);
		_board = new Board();
		_pieceSelected = false;
		_player0Turn = true;
		_listener = new MouseListener();
		this.addMouseListener(_listener);
	}
	
	public void paintComponent(java.awt.Graphics aBrush) {
		super.paintComponent(aBrush);
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D)aBrush;
		_board.DrawAndFill(betterBrush);
	}
	
	private boolean IsValidAdd(int row , int col) {
		return (row >= _selectedRow - 1 && row <= _selectedRow + 1 && col >= _selectedCol - 1 && col <= _selectedCol + 1 && !_board.HasPiece(row, col));
	}
	
	private boolean IsValidMove(int row , int col) {
		return (row >= _selectedRow - 2 && row <= _selectedRow + 2 && col >= _selectedCol - 2 && col <= _selectedCol + 2 && !_board.HasPiece(row, col));
	}
	
	private class MouseListener extends java.awt.event.MouseAdapter{
		public MouseListener() {
			super();
		}
		
		public void mousePressed(java.awt.event.MouseEvent e) {
			int row = e.getY() / AtaxxConstants.cellHeight;
			int col = e.getX() / AtaxxConstants.cellWidth;
			if(_board.IsPlayers(_player0Turn, row, col)) {
				_pieceSelected = true;
				_selectedRow = row;
				_selectedCol = col;
			}
			else if(_pieceSelected) {
				if(IsValidAdd(row , col)) {
					_board.AddPiece(row , col , _player0Turn);
					_pieceSelected = false;
					_player0Turn = !_player0Turn;
				}
				else if(IsValidMove(row , col)) {
					_board.MovePiece(_selectedRow , _selectedCol , row , col);
					_pieceSelected = false;
					_player0Turn = !_player0Turn;
				}
				else {
					_pieceSelected = false;
				}
			}
			
			repaint();
		}
	}
}
