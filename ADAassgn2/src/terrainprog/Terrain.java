package terrainprog;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;

import javax.swing.*;


public class Terrain extends JPanel{
	int width;
	int height;
	int scoreSum = 0;
	
	Container terContent;
	
	//components
	private JButton[][] terrainCells;
	
	private int bestArray[][];
	
	//colours
	private Color[] colours = {Color.BLUE, Color.CYAN, Color.MAGENTA, Color.RED};

	//upper left corner of the board is (0,0)
	//initial start
	private int row;	//bottom row
		
	//get the column input by user
	private int column; 
	
	TerrainSet terSet;
	
	ScoreMenu scoreMenu;
	
	
	public void setWidthHeight() {
		this.width = terSet.getWidth();
		this.height = terSet.getHeight();
	}
	
	//constructor
	public Terrain() throws Exception {
		terSet = new TerrainSet();
		scoreMenu  = new ScoreMenu();
		setWidthHeight();
		terrainCells = new JButton[width][height];
		this.setLayout(new GridLayout(this.width, this.height));
		bestArray = new int[width][height];
		
		addActionListener();
		
		
		createTerrain();
		
		setSize(1400, 1400);
		setVisible(true);
	}
	
	public void createTerrain() {
		row = height - 1;
		column = 0;
		
		//event handlers
		TerButtonHandler terBtnHandle = new TerButtonHandler();
		
		//create and add terrain to view
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				int randNum = terSet.getTerVal(x, y);
				terrainCells[x][y] = new JButton(String.valueOf(randNum));
				terrainCells[x][y].setPreferredSize(new Dimension(60, 60));
				if(randNum <= 0) {
					terrainCells[x][y].setBackground(colours[0]);
				}
				else if((randNum > 0) && (randNum <= 5)) {
					terrainCells[x][y].setBackground(colours[1]);
				}
				else if((randNum > 5) && (randNum <= 10)) {
					terrainCells[x][y].setBackground(colours[2]);
				}
				else if((randNum > 10) && (randNum <= 15)) {
					terrainCells[x][y].setBackground(colours[3]);
				}
				
				this.add(terrainCells[x][y]);
				terrainCells[x][y].addActionListener(terBtnHandle);
				
			}
		}
	}
	
	
	public ScoreMenu getScoreMenu() {
		return this.scoreMenu;
	}
	
	public void addActionListener() {
		scoreMenu.getResetBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Reset button pressed");
				
			}
		});
		scoreMenu.getAutoBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Auto button pressed");
				
				findBestArray();
				int startCol = findStartCol();
				//printBestArray();
				displayBestTerrain(startCol);
				scoreMenu.setScoreLabel("Score:" + String.valueOf(scoreSum));
				
			}
		});
	}
	
	/*public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.column;
	}
	
	public JButton[][] getArray(){
		return this.terrainCells;
	}*/
	

	
	public void findBestArray() {
		for(int i = 0; i < height; i++) {
			//get the integers associated with bottom row of jbuttons
			bestArray[height-1][i] = Integer.parseInt(terrainCells[height-1][i].getText());
		}
		
		for(int row = height-2; row >= 0; row--) {
			for(int col = 0; col < width; col++) {
				int bestPrevCell = bestArray[row+1][col];
				
				if(col != 0) {
					if(bestPrevCell > bestArray[row+1][col-1]) {
						bestPrevCell = bestArray[row+1][col-1];
					}
				}
				
				if(col != 9) {
					if(bestPrevCell > bestArray[row+1][col+1]) {
						bestPrevCell = bestArray[row+1][col+1];
					}
				}
				
				bestArray[row][col] = Integer.parseInt(terrainCells[row][col].getText());
			}
		}
	}
	
	public int findStartCol() {
		int bestArrayScore = bestArray[0][0];
		int bestArrayCol = 0;
		
		for(int i = 0; i < width; i++) {
			if(bestArrayScore > bestArray[0][i]) {
				bestArrayCol = bestArray[0][i];
				bestArrayCol = i;
			}
		}
		
		return bestArrayCol;
	}
	
	public void displayBestTerrain(int bestCol) {
		int curCol = bestCol;
		int smallestInt = bestArray[0][bestCol];
		int num;
		terrainCells[0][bestCol].setBackground(Color.LIGHT_GRAY);
		num = Integer.parseInt(terrainCells[0][bestCol].getText());
		scoreSum += num;
		for(int row = 0; row < width - 1; row++) {
			int tempCol = curCol;
			
			if(row != width-1) {
				smallestInt = bestArray[row+1][tempCol];
			}
			
			if(tempCol != 0 && row != 9) {
				if(smallestInt > bestArray[row+1][tempCol -1]) {
					smallestInt = bestArray[row+1][tempCol-1];
					curCol = tempCol-1;
				}
			}
			
			if(tempCol != 9 && row != 9) {
				if(smallestInt > bestArray[row+1][tempCol+1]) {
					//smallestInt = bestArray[row+1[tempCol+1];
					smallestInt = bestArray[row+1][tempCol-1];
					curCol = tempCol+1;
				}
			}
			
			terrainCells[row+1][curCol].setBackground(Color.LIGHT_GRAY);
			scoreSum += Integer.parseInt(terrainCells[row+1][curCol].getText());
		}
	}
	
	/*public void printBestArray() {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				System.out.print(bestArray[x][y] + " | ");
			}
			System.out.println();
		}
	}*/
	
	public class TerButtonHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			Object eventSource = e.getSource();	//find source of event
			
			for(int x = 0; x < width; x++) {
				for(int y = 0; y < height; y++) {
					if(eventSource == terrainCells[x][y]) {	//check what cell matches with mouse click
						clicked(x,y);
						
						return;
					}
				}
			}
		}
		
		public void clicked(int x, int y) {
			
			//scoreMenu.getScoreLabel().setText("Score:" + String.valueOf(scoreSum));
			
			
			if(validMove(x,y) == true) {
				System.out.println("Valid move");
				//change the colour of buttons to show where user is at
				//terrainCells[row][column].setBackground(Color.LIGHT_GRAY);
				terrainCells[x][y].setBackground(Color.LIGHT_GRAY);
				System.out.println("Score: " + scoreSum);
				row = x;
				column = y;
				
				scoreMenu.setScoreLabel("Score:" + String.valueOf(scoreSum));
			}else {	//if not a valid move, no action taken
				System.out.println("Invalid move");
				return;
			}
			
		}
		
		public boolean validMove(int x, int y) {
			
			int rowDelta = x - row;
			int colDelta = y - column;
			int num = terSet.getTerVal(x, y);
			
			
			
			//if at the start of the terrain program
			//x click is height - 1
			//row delta would be -1
			if((x == height - 1) && (rowDelta == 0)) {
				scoreSum += num;
				System.out.println("Num: " + num);
				terSet.setScore(scoreSum);
				return true;
			}
			
			//if user is not at the start then valid moves can only be one row above
			//and left, middle, or right column relative of where the user is
			else if(rowDelta == -1 && (colDelta == -1 || colDelta == 0 || colDelta == 1)) {
				scoreSum += num;
				System.out.println("Num: " + num);
				terSet.setScore(scoreSum);
				return true;
			}
			
			return false;
		}

	}
	
	
	
	
	
	
}
