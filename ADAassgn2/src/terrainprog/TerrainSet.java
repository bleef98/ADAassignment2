package terrainprog;

import java.util.Random;

public class TerrainSet {
	
	int[][] terrain;
	Random rand = new Random();
	
	int width = 10;
	int height = 10;

	public TerrainSet() {
		
		/**
		 * openTerData();
		 * readTerData();
		 * closeTerData();
		 */
		 createTer();
		  
	}

	private void createTer() {
		terrain = new int[width][height];
		
		for(int row = 0; row < width; row++) {
			 for(int col = 0; col < height; col++) {
				 terrain[row][col] = rand.nextInt(20) - 5;
			 }
		 }
	}
	
	public int getTerVal(int x, int y) {
		int value = terrain[x][y];
		
		return value;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	
	/*public void openTerData(){
	 * 
	 * }
	 */
	
	/*public void readTerData(){
	 * 
	 * }
	 */
	
	/*public void closeTerData(){
	 * 
	 * }
	 */
}
