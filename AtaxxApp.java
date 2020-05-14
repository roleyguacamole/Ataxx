@SuppressWarnings("serial")
public class AtaxxApp extends javax.swing.JFrame {
	
	private AtaxxPanel _ataxxPanel;
	
	public AtaxxApp() {
		super("Ataxx");
		this.setResizable(false);
		_ataxxPanel = new AtaxxPanel();
		_ataxxPanel.setPreferredSize(new java.awt.Dimension(AtaxxConstants.screenWidth , AtaxxConstants.screenHeight));
		this.setSize(AtaxxConstants.screenWidth , AtaxxConstants.screenHeight);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.add(_ataxxPanel);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new AtaxxApp();
	}

}
