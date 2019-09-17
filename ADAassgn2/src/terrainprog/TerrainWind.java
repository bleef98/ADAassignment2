package terrainprog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import terrainprog.Terrain.TerButtonHandler;

public class TerrainWind extends JFrame {
	
	private Terrain terrain;
	private ScoreMenu scoreMenu;
	private TerrainSet terSet;
	
	/*int width;
	int height;
	int row;
	int column;
	int scoreSum = 0;
	
	TerButtonHandler terBtnHandle;*/
	
	JPanel terPanel;
	JPanel scorePanel;
	//JButton[][] terrainCells;
	
	public TerrainWind() throws Exception{
		
		terrain = new Terrain();
		scoreMenu = terrain.getScoreMenu();
		terSet = new TerrainSet();
		
		//setWidthHeight();
		
		/*terrainCells = terrain.getArray();
		width = terrain.getWidth();
		height = terrain.getHeight();
		row = terrain.getRow();
		column = terrain.getCol();*/
		
		//event handlers
		//terBtnHandle = new TerButtonHandler();
		//AddActionListener();
		
		terPanel = new JPanel();
		scorePanel = new JPanel();
		
		
		
		terPanel.add(terrain);
		scorePanel.add(scoreMenu);
		
		
		setLayout(new FlowLayout());
		this.getContentPane().add(terPanel);
		this.getContentPane().add(scorePanel);
		pack();
		//setSize(800,800);
		setResizable(false);
		setVisible(true);
		
	}
	
	/*public void setWidthHeight() {
		this.width = terSet.getWidth();
		this.height = terSet.getHeight();
	}
	
	public void AddActionListener() {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				
				terrainCells[x][y].addActionListener(terBtnHandle);
				
			}
		}
	}
	
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

	}
	
	public void clicked(int x, int y) {
		
		//scoreMenu.getScoreLabel().setText("Score:" + String.valueOf(scoreSum));
		scoreMenu.setScoreLabel("Score:" + String.valueOf(scoreSum));
		
		if(validMove(x,y) == true) {
			System.out.println("Valid move");
			//change the colour of buttons to show where user is at
			//terrainCells[row][column].setBackground(Color.LIGHT_GRAY);
			terrainCells[x][y].setBackground(Color.LIGHT_GRAY);
			System.out.println("Score: " + scoreSum);
			row = x;
			column = y;
			
			
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
	*/
	
}
