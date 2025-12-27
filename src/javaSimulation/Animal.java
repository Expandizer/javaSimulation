package javaSimulation;
import java.util.Random;
import java.util.random.RandomGenerator;

public abstract class Animal {
	
	private static final Random RANDOM = new Random();
	
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
	
	protected int randomStep() {
		return RANDOM.nextInt(3) - 1;     
	}
	
	public void Move(int gridSize) {
		int x = position.getX() + randomStep();
        int y = position.getY() + randomStep();
        position = new Position(x, y).wrap(gridSize);
	}

}

class RedAnimal extends Animal{
	
	public RedAnimal(Position position) {
		super(CellColor.RED, position);
	}
	
}

class GreenAnimal extends Animal{
	
	public GreenAnimal(Position position) {
		super(CellColor.GREEN, position);
	}
	
}

class YellowAnimal extends Animal{
	
	public YellowAnimal(Position position) {
		super(CellColor.YELLOW, position);
	}
	
}



