
public class Piece {
	
	private java.awt.geom.Ellipse2D.Double _circle;
	private java.awt.Color _pieceColor;
	private java.awt.Color _otherColor;
	private boolean _isPlayer0;
	
	public Piece(boolean players , int row , int col) {
		if(players) {
			_pieceColor = AtaxxConstants.player0Color;
			_otherColor = AtaxxConstants.player1Color;
		}
		else {
			_pieceColor = AtaxxConstants.player1Color;
			_otherColor = AtaxxConstants.player0Color;
		}
		_isPlayer0 = players;
		this.SetCell(row , col);
	}
	
	public void SetCell(int row , int col) {
		float newX = AtaxxConstants.cellWidth * col;
		float newY = AtaxxConstants.cellHeight * row;
		this.SetLocation(newX , newY);
	}
	
	private void SetLocation(float x , float y) {
		_circle = new java.awt.geom.Ellipse2D.Double(x + 0.5 , y + 0.5 , AtaxxConstants.cellWidth - 1 , AtaxxConstants.cellHeight - 1);
	}
	
	public void ChangeColor() {
		java.awt.Color temp = _pieceColor;
		_pieceColor = _otherColor;
		_otherColor = temp;
		_isPlayer0 = !_isPlayer0;
	}
	
	public boolean IsPlayers() {
		return _isPlayer0;
	}
	
	public void DrawAndFill(java.awt.Graphics2D betterBrush) {
		java.awt.Paint oldColor = betterBrush.getPaint();
		betterBrush.setPaint(_pieceColor);
		betterBrush.draw(_circle);
		betterBrush.fill(_circle);
		betterBrush.setPaint(oldColor);
	}
	
}
