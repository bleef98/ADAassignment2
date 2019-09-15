package terrainprog;

import java.awt.GridLayout;

import javax.swing.*;

public class TerrainWind extends JPanel{
	
	public int grid[][];
	
	public TerrainWind() {
		JFrame frame = new JFrame();
		frame.setTitle("Terrain Program");
		frame.setSize (600, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//frame.getContentPane().add(new TerrainView());
		frame.add(new TerrainView());
	}
	
	public static void main(String[] args) {
		new TerrainWind();
	}

}
