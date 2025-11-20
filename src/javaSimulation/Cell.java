package javaSimulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;

public class Cell {
	
	private boolean Blocked = false;
    private Position position;
    private JButton button;

    public Cell(int row, int col, int cellLength, int cellWidth, Color defaultColor, int cellNum) {
        this.position = new Position(row, col);
        this.button = new JButton();
        this.button.setPreferredSize(new Dimension(cellLength, cellWidth));
        this.button.setMargin(new Insets(0, 0, 0, 0));
        this.button.setBackground(defaultColor);
    }
    
    public Position getPosition() { return position; }
    public JButton getButton() { return button; }

    public void setColor(Color color) {
        button.setBackground(color);
    }

	public boolean isBlocked() {
		return Blocked;
	}
	
	public void setBlocked(boolean blocked) {
		this.Blocked = blocked;
	}
}
