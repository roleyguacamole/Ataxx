
public class Piece {
	
	private java.awt.geom.Ellipse2D.Double _circle;
	private java.awt.Color _pieceColor;
	private boolean _isPlayers;
	
	public Piece(java.awt.Color aColor , boolean players , int x , int y) {
		_pieceColor = aColor;
		_isPlayers = players;
		this.SetCell(x , y);
	}
	
	public void SetCell(int x , int y) {
		float newX = AtaxxConstants.cellWidth * x;
		float newY = AtaxxConstants.cellHeight * y;
		this.SetLocation(newX , newY);
	}
	
	private void SetLocation(float x , float y) {
		_circle = new java.awt.geom.Ellipse2D.Double(x + 0.5 , y + 0.5 , AtaxxConstants.cellWidth - 1 , AtaxxConstants.cellHeight - 1);
	}
	
	public void ChangeColor(java.awt.Color aColor) {
		_pieceColor = aColor;
		_isPlayers = !_isPlayers;
	}
	
	public boolean IsPlayers() {
		return _isPlayers;
	}
	
	public void DrawAndFill(java.awt.Graphics2D betterBrush) {
		java.awt.Paint oldColor = betterBrush.getPaint();
		betterBrush.setPaint(_pieceColor);
		betterBrush.draw(_circle);
		betterBrush.fill(_circle);
		betterBrush.setPaint(oldColor);
	}
	
}
