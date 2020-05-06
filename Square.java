
public class Square {

	private java.awt.Color _border;
	private java.awt.geom.Rectangle2D.Double _square;
	
	public Square(int x , int y) {
		_border = java.awt.Color.orange;
		float screenX = x * AtaxxConstants.cellWidth;
		float screenY = y * AtaxxConstants.cellHeight;
		_square = new java.awt.geom.Rectangle2D.Double(screenX , screenY , AtaxxConstants.cellWidth , AtaxxConstants.cellHeight);
	}
	
	public void DrawAndFill(java.awt.Graphics2D betterBrush) {
		java.awt.Paint oldColor = betterBrush.getPaint();
		betterBrush.setPaint(_border);
		betterBrush.draw(_square);
		betterBrush.setPaint(oldColor);
	}
}
