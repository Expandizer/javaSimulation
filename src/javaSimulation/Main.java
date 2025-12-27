package javaSimulation;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		// Declaring the frame
		JFrame frame = new JFrame("Animal Ecosystem Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Grid grid = new Grid(15,60,60,2);
		
		Simulation sim1 = new Simulation(grid, 2000);
		sim1.timer.start();
		
		// Frame settings
		frame.add(grid);
		frame.pack(); // Sizes the frame based on component sizes
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
	}
}
