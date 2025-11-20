package javaSimulation;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		// Declaring the frame
		JFrame frame = new JFrame("Animal Ecosystem Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Grid grid = new Grid(15,60,60,2);
		
		Simulation sim1 = new Simulation(grid);
		sim1.Spawn();
		System.out.println(sim1.getRedCount());
		System.out.println(sim1.getGreenCount());
		System.out.println(sim1.getYellowCount());
		
		// Frame settings
		frame.add(grid);
		frame.pack(); // Sizes the frame based on component sizes
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
	}
}
