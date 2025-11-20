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
		
}

class RedAnimal extends Animal{
	
	public RedAnimal(Position position) {
		super(CellColor.RED, position);
	}
	
	public Position Move(Position pos) {
		
		int X = getPosition().getX()+1;
		int Y = getPosition().getY()-2;
		
		return new Position(X,Y);
	}

}

class GreenAnimal extends Animal{
	
	public GreenAnimal(Position position) {
		super(CellColor.GREEN, position);
	}
	
	public Position Move(Position pos) {
		int X = getPosition().getX()-1;
		int Y = getPosition().getY()+1;
		
		return new Position(X,Y);
	}
	
}

class YellowAnimal extends Animal{
	
	public YellowAnimal(Position position) {
		super(CellColor.YELLOW, position);
	}
	
	public Position Move(Position pos) {
		int X = getPosition().getX()+1;
		int Y = getPosition().getY()+1;
		
		return new Position(X,Y);
	}

}



