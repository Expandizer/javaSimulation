package javaSimulation;

public class Position {
	
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int X) {
		this.x = X;
	}
	
	public void setY(int Y) {
		this.y = Y;
	}
	
	public Position wrap(int gridSize) {
	    int newX = (x + gridSize) % gridSize;
	    int newY = (y + gridSize) % gridSize;
	    return new Position(newX, newY);
	}

	public String getCoordinates() {
		return "("+this.x+"," +this.y+")";
	}
}
