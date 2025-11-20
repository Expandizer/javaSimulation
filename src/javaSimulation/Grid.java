package javaSimulation;

import java.awt.*;
import javax.swing.*;

public class Grid extends JPanel {

	private static final long serialVersionUID = 1L;
	
	// Grid attributes
    public int GridSize; // n x n cells
    private int cellLength, cellWidth;
    private int cellPadding; // space between cells

    private Cell[][] cells;
    
    private Color defaultCellColor = new Color(255, 255, 255);
    private Color defaultGridColor = new Color(222, 222, 222);
    
    public Grid(int GridSize, int cellLength, int cellWidth, int cellPadding) {

        this.GridSize = GridSize;
        this.cellLength = cellLength;
        this.cellWidth = cellWidth;
        this.setCellPadding(cellPadding);

        // Initialize the grid layout and matrix
        this.cells = new Cell[GridSize][GridSize];
        setBackground(defaultGridColor);
        setLayout(new GridLayout(GridSize, GridSize, cellPadding, cellPadding));

        addCells();
    }

    private void addCells() {

        int cellNum = 0;

        for (int row = 0; row < GridSize; row++) {
            for (int col = 0; col < GridSize; col++) {

                Cell cell = new Cell(row, col, cellLength, cellWidth, defaultCellColor, cellNum);
                cells[row][col] = cell;
                add(cell.getButton());
                cellNum++;
            }
        }
    }

    public void colorCell(Position pos, Color color) {
    	
        int x = pos.getX();
        int y = pos.getY();

        try {
            cells[x][y].setColor(color);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: position (" + x + ", " + y + ") is outside the grid.");
        }
    }

    public void markAnimal(Animal animal) {
    	
        String colorName = animal.getColor().toString().toUpperCase();

        switch (colorName) {
            case "RED":
                colorCell(animal.getPosition(), new Color(245, 83, 113));
                break;
            case "GREEN":
                colorCell(animal.getPosition(), new Color(153, 255, 182));
                break;
            case "YELLOW":
                colorCell(animal.getPosition(), new Color(255, 255, 161));
                break;
            default:
                System.out.println("Unknown animal color: " + colorName);
        }
    }

    public void clearCell(Position pos) {
    	cells[pos.getX()][pos.getY()].setColor(new Color(255, 255, 255));
    }
    
    public void clearGrid() {
    	for (int i = 0; i < GridSize; i++) {
    		for (int j = 0; j < GridSize; j++) {
    			clearCell(cells[i][j].getPosition());
    		}
    	}
    }
    
	public int getCellPadding() {
		return cellPadding;
	}

	public void setCellPadding(int cellPadding) {
		this.cellPadding = cellPadding;
	}
}


