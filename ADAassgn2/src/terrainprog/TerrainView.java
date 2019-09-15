package terrainprog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class TerrainView extends JFrame{
	
	int width;
	int height;
	int scoreSum = 0;
	
	Container content;
	
	//components
	private JButton[][] terrainCells;
	
	//colours
	private Color[] colours = {Color.BLUE, Color.CYAN, Color.MAGENTA, Color.RED};
	
	//upper left corner of the board is (0,0)
	//initial start
	private int row;	//bottom row
	
	//get the column input by user
	private int column; //get column input	//********************************************//
	
	
	TerrainSet terSet = new TerrainSet();
	
	public void setWidthHeight() {
		this.width = terSet.getWidth();
		this.height = terSet.getHeight();
	}
	
	//constructor
	public TerrainView() {
		super("Terrain program");
		setWidthHeight();
		
		terrainCells = new JButton[width][height];
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		content = getContentPane();
		panel.getRootPane();
		content.setLayout(new GridLayout(this.width,this.height));
		
		//event handlers
		TerButtonHandler terBtnHandle = new TerButtonHandler();
		
		row = height - 1;
		column = 0;
		
		//create and add terrain to view
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				int randNum = terSet.getTerVal(x, y);
				terrainCells[x][y] = new JButton(String.valueOf(randNum));
				
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
				
				content.add(terrainCells[x][y]);
				terrainCells[x][y].addActionListener(terBtnHandle);
				
			}
		}
		
		JLabel scoreCount = new JLabel(String.valueOf(scoreSum));
		
		//window size
		setSize (800, 800);
		setResizable(false);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		
		/*JButton bestBtn = new JButton("Best path");
		panel.add(bestBtn);
		
		content.add(panel);*/
	
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
		
		int num = terSet.getTerVal(x, y);
		
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
			return true;
		}
		
		//if user is not at the start then valid moves can only be one row above
		//and left, middle, or right column relative of where the user is
		else if(rowDelta == -1 && (colDelta == -1 || colDelta == 0 || colDelta == 1)) {
			scoreSum += num;
			return true;
		}
		
		return false;
	}
	
	
	
	/*public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Color[] colours = {Color.BLUE, Color.CYAN, Color.MAGENTA, Color.RED};
		
		for(int y = 0; y < 20; y++) {
			for(int x = 0; x < 20; x++) {
				if((terSet.getTerVal(x, y)) <= 5) {
					g.setColor(colours[0]);
				}
				else if((terSet.getTerVal(x, y) > 5) && (terSet.getTerVal(x, y) <= 10)) {
					g.setColor(colours[1]);
				}
				else if((terSet.getTerVal(x, y) > 10) && (terSet.getTerVal(x, y) <= 15)) {
					g.setColor(colours[2]);
				}
				else if((terSet.getTerVal(x, y) > 15) && (terSet.getTerVal(x, y) <= 20)) {
					g.setColor(colours[3]);
				}
				
				
			}
		}
	}*/

}
