
public class Square {

	private java.awt.Color _border;
	private java.awt.geom.Rectangle2D.Double _square;
	
	public Square(int row , int col) {
		_border = java.awt.Color.orange;
		float screenX = col * AtaxxConstants.cellWidth;
		float screenY = row * AtaxxConstants.cellHeight;
		_square = new java.awt.geom.Rectangle2D.Double(screenX , screenY , AtaxxConstants.cellWidth , AtaxxConstants.cellHeight);
	}
	
	public void DrawAndFill(java.awt.Graphics2D betterBrush) {
		java.awt.Paint oldColor = betterBrush.getPaint();
		betterBrush.setPaint(_border);
		betterBrush.draw(_square);
		betterBrush.setPaint(oldColor);
	}
}
