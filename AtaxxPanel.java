@SuppressWarnings("serial")
public class AtaxxPanel extends javax.swing.JPanel {
	
	private Board _board;
	private boolean _pieceSelected;
	private int _selectedRow;
	private int _selectedCol;
	private boolean _player0Turn;
	private MouseListener _mListener;
	private Reset _reset;
	private SimpleAI _player1;
	
	public AtaxxPanel() {
		this.setBackground(java.awt.Color.BLACK);
		this.setSize(AtaxxConstants.screenWidth , AtaxxConstants.screenHeight);
		_board = new Board();
		_pieceSelected = false;
		_player0Turn = true;
		_mListener = new MouseListener();
		_player1 = new SimpleAI();
		this.addMouseListener(_mListener);
		_reset = new Reset();
		this.getInputMap().put(javax.swing.KeyStroke.getKeyStroke("R"), "reset");
		this.getActionMap().put("reset", _reset);
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
			if(_player0Turn) {
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
						repaint();
						_player1.move(_board , false);
					}
					else if(IsValidMove(row , col)) {
						_board.MovePiece(_selectedRow , _selectedCol , row , col);
						_pieceSelected = false;
						repaint();
						_player1.move(_board , false);
					}
					else {
						_pieceSelected = false;
					}
				}
			}
			
			repaint();
		}
	}
	
	private class Reset extends javax.swing.AbstractAction{
		public Reset() {
			super();
		}
		
		public void actionPerformed(java.awt.event.ActionEvent e) {
			_board = new Board();
			_pieceSelected = false;
			_player0Turn = true;
			repaint();
		}
	}
}
