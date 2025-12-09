package javaSimulation;

public abstract class Animal {
	
	private final CellColor color;
	private Position position;
	
	protected Animal(CellColor color, Position position) {
		this.color = color;
		this.position = position;
	}

	public CellColor getColor() {
		return this.color;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public void setPosition(Position pos) {
		this.position = pos;
	}
	
	public abstract void Move(int gridSize);

}

class RedAnimal extends Animal{
	
	public RedAnimal(Position position) {
		super(CellColor.RED, position);
	}
	
	public void Move(int gridSize) {
		
		int X = getPosition().getX()+1;
		int Y = getPosition().getY()-2;
	    
		Position newPos = new Position(X, Y).wrap(gridSize);
	    setPosition(newPos);
	}
}

class GreenAnimal extends Animal{
	
	public GreenAnimal(Position position) {
		super(CellColor.GREEN, position);
	}
	
	public void Move(int gridSize) {
		
		int X = getPosition().getX()-1;
		int Y = getPosition().getY()+1;
	    
		Position newPos = new Position(X, Y).wrap(gridSize);
	    setPosition(newPos);
	}
	
}

class YellowAnimal extends Animal{
	
	public YellowAnimal(Position position) {
		super(CellColor.YELLOW, position);
	}
	
	public void Move(int gridSize) {
		
		int X = getPosition().getX()+1;
		int Y = getPosition().getY()+1;
	    
		Position newPos = new Position(X, Y).wrap(gridSize);
	    setPosition(newPos);
	}

}



