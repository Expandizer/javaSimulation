package javaSimulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.Timer;


public class Simulation {
	
	private final Grid grid;
	private final List<Animal> Animals = new ArrayList<>();
	private final List<List<Animal>> collisionList = new ArrayList<>();
	private final Set<Cell> blockedCells = new HashSet<>(); // used Cell instead of Position in order to manipulate the colors of the blockedCells (where collisions happen)
	private final int StepDelay = 500; // ms between two steps of the simulation
	private Timer timer;
	private double AnimalDensity = 0.10; // 10% of the GridSize is Animals
	private int redCount, greenCount, yellowCount;
	private final List<Position> occupiedCells = new ArrayList<>();
	
	public Simulation(Grid grid){
		this.grid = grid;
		//spawn()
		//Start Simulation Rules : Spawning, cell updates, movements
	}
	
	public void Spawn() {
		
		int n = this.grid.GridSize;
		double density = this.AnimalDensity;
		
		int totalAnimals = (int) Math.floor( n * n * density );
		int perType = totalAnimals / 3;
		int remainder = totalAnimals % 3;
		Random rand = new Random(); // Used bellow to generate animals
		
		this.redCount = perType + (remainder > 0 ? 1:0);
		this.greenCount = perType + (remainder > 1 ? 1:0);
		this.yellowCount = perType;
		
		// Create Animals and add them to the animals list unless in occupied cells
		
		Position redPos = new Position(rand.nextInt(n), rand.nextInt(n));
		for (int i = 0; i < redCount; i++) {
			while (isOccupied(redPos)) {
				redPos = new Position(rand.nextInt(n), rand.nextInt(n));
			}
			RedAnimal red = new RedAnimal(redPos);
			Animals.add(red);
			occupiedCells.add(redPos);
		}
		
		Position greenPos = new Position(rand.nextInt(n), rand.nextInt(n));
		for (int i = 0; i < greenCount; i++) {
			while (isOccupied(greenPos)) {
				greenPos = new Position(rand.nextInt(n), rand.nextInt(n));
			}
			GreenAnimal green = new GreenAnimal(greenPos);
			Animals.add(green);
			occupiedCells.add(greenPos);
			}
		
		Position yellowPos = new Position(rand.nextInt(n), rand.nextInt(n));
		for (int i = 0; i < yellowCount; i++) {
			while(isOccupied(yellowPos)) {
				yellowPos = new Position(rand.nextInt(n), rand.nextInt(n));
			}
			YellowAnimal yellow = new YellowAnimal(yellowPos);
			Animals.add(yellow);
			occupiedCells.add(yellowPos);
		}
		
		// Color marking all spawned animals
		markAnimals();
	}
	
	private void markAnimals() {
		
		List<Animal> Animals = this.Animals;
		
		for(Animal a : Animals) {
			this.grid.markAnimal(a);
		}
	}
	
	private boolean isOccupied(Position pos) {
		return occupiedCells.contains(pos);
	}
		
	// collisionDetector : detects animal collision and stores them in a list
	
	private void collisionDetector() {
		
		collisionList.clear();

		for (int i = 0; i < Animals.size(); i++) {
	        Animal a = Animals.get(i);
	        for (int j = i + 1; j < Animals.size(); j++) {
	            
	        	Animal b = Animals.get(j);

	            if (a.getPosition().equals(b.getPosition())) {
	                collisionList.add(new ArrayList<>(Arrays.asList(a, b)));
	            }
			}
		}
	}
	
	// killAnimal : De-references an animal completely
	
	private void killAnimal(Animal animal) {
		
		grid.clearCell(animal.getPosition());
		occupiedCells.remove(animal.getPosition());
		Animals.remove(animal);
		
	}
	
	// Fight Logic : RED eats GREEN eats Yellow eats RED
	
	private void resolveClash(Animal A, Animal B) {

		// RED eats GREEN
		if ((A.getColor() == CellColor.RED) && (B.getColor() == CellColor.GREEN)) { killAnimal(B); return; }
		if ((A.getColor() == CellColor.GREEN) && (B.getColor() == CellColor.RED)) { killAnimal(A); return; }
		
		// GREEN eats YELLOW 
		if ((A.getColor() == CellColor.GREEN) && (B.getColor() == CellColor.YELLOW)) { killAnimal(B); return; }
		if ((A.getColor() == CellColor.YELLOW) && (B.getColor() == CellColor.GREEN)) { killAnimal(A); return; }
		
		// YELLOW eats RED
		if ((A.getColor() == CellColor.YELLOW) && (B.getColor() == CellColor.RED)) { killAnimal(B); return; }
		if ((A.getColor() == CellColor.RED) && (B.getColor() == CellColor.YELLOW)) { killAnimal(A); return; }
		
	}
	
	// createAnimal : gives birth to a new Animal
	
	private Animal createAnimal(Animal parent) {
		
		// the new born inherits traits of the parents
		
		CellColor color = parent.getColor();
		Position coordinates = new Position(
				parent.getPosition().getX()+1, 
				parent.getPosition().getY()+1
		);
		
		return switch(color) {
			case RED -> new RedAnimal(coordinates);
			case GREEN -> new GreenAnimal(coordinates);
			case YELLOW -> new YellowAnimal(coordinates);
		};
	}
	
	// collisionAction : decides what to do for each occurring collision on the grid
	
	private void collisionAction() {
		
		for (List<Animal> collision : collisionList) {
			
			Animal A = collision.get(0);
			Animal B = collision.get(1);
			
			if (A.getColor() == B.getColor()){
				createAnimal(A); // either A or B, they are the same
			}
			else {
				resolveClash(A, B);
			}
		}
	}
	
	// Step Method : Describes what will happen at each TimeStep : reproduce, kill and move.
	
	private void Step() {
	
		//Cells loop
			//Collision detector
				//Same Color condition
					// Fuck and reproduce
				//Different Color condition
					// Kill and the winner lives
			//OutofGrid detector
	}
	
	public int getRedCount() {
		return this.redCount;
	}
	
	public int getGreenCount() {
		return this.greenCount;
	}
	
	public int getYellowCount() {
		return this.yellowCount;
	}
}
