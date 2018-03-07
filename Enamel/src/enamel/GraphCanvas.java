package enamel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.BasicStroke;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

@SuppressWarnings("serial")
public class GraphCanvas extends JPanel {
	public int startingX;
	public int startingY;
	public int width;
	public int height;
	public Node n;
	public Scenario s;
	
	
	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public GraphCanvas(Scenario s, Node n) {
		super();
		this.n = n;
		this.s = s;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */ 
	private void initialize() {
		this.startingX = 50;
		this.startingY = 23;
		this.width = 500;
		this.height = 484;
		
	}
	
	public void update() {
		repaint();
	}
	
	public void setNode(Node node) {
		this.n = node;
	}
    /**
     * @wbp.parser.entryPoint
     */
    public void paint(Graphics g) {
        Graphics2D graphics2 = (Graphics2D) g;
        graphics2.setStroke(new BasicStroke(2));
        Rectangle Rectangle = new Rectangle (this.startingX, this.startingY, this.width, this.height);
        graphics2.draw(Rectangle);
        
        int width = 200;
        int height = 75;
        
        int nodeX = this.width / 2 + this.startingX - width / 2;
        int nodeY = this.height / 2 + this.startingY- height / 2;
        
        graphics2.drawString(n.getName(), nodeX + 3, this.height/2);
        graphics2.drawString(n.getResponse().substring(0, Math.min(n.getResponse().length(), 26)) + "...",
        		nodeX + 20, this.height/2+30);

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(nodeX, nodeY, width, height, 10, 10);
        graphics2.draw(roundedRectangle);
        
        int lineEnd = this.height/2+this.startingY-height/2-100;
        graphics2.drawLine(this.width/2 +this.startingX, this.height/2+this.startingY-height/2, this.width/2 + this.startingX, lineEnd);
        
        lineEnd = this.height/2+this.startingY+height/2+100;
      
        roundedRectangle = new RoundRectangle2D.Float(this.width/2+this.startingX-width+10, lineEnd, width, height, 10, 10);
        
        roundedRectangle = new RoundRectangle2D.Float(this.width/2+this.startingX+10, lineEnd, width, height, 10, 10);
        this.createRectangles(graphics2, nodeX, nodeY + height, width, lineEnd, 10,  height);
    }
    
    
    public void createRectangles(Graphics2D g, int firstX, int bottom, int firstWidth, int y, int space, int height) {
    	if (!s.hasNextNodes(n)) {
    		return;
    	}
    	Node[] nextNodes = (Node[]) s.getNextNodes(n);
    	int num = nextNodes.length;
    	int spaces = (num + 1) * space;
    	int width = (this.width - spaces) / Math.max(num, 1);
    	int rect = space + width;
    	int x = 0;
    	int dline = firstWidth/(num+1);
    	for (int i = 0; i < num; i++) {
    		x = space + this.startingX + rect * i;
    		g.draw(new RoundRectangle2D.Float(x, y, width, height, 10, 10));
    		g.drawString(nextNodes[i].getName(), x+3, y+14);
            g.drawString(nextNodes[i].getResponse().substring(0, Math.min(nextNodes[i].getResponse().length(), width*9/55-74/11)) + "...",
            		x + 20, y+44);
    		g.drawLine(firstX + dline * (i + 1), bottom, x + width / 2, y);
    	}
    }

}
