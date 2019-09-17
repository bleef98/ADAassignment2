package terrainprog;

import java.awt.*;
import java.awt.event.*;
import java.util.Observer;

import javax.swing.*;

public class ScoreMenu extends JPanel {
	
	private int scoreSum;
	JLabel scoreLabel;
	//JButton connBtn;
	JButton resetBtn;
	JButton autoBtn100;
	String chosenDB;
	
	TerrainSet terSet;
	Terrain terrain;
	
	public ScoreMenu() {
		this.setLayout(new FlowLayout());
		setVisible(true);
		
		//scoreSum = terSet.getScore();
		scoreLabel = new JLabel("Score: " + scoreSum + "   ");
		//connBtn = new JButton("Connect to DB");
		resetBtn = new JButton("Reset Grid");
		autoBtn100 = new JButton("Automatic 100%");
		
		
		this.add(scoreLabel);
		//this.add(resetBtn);
		//this.add(connBtn);
		this.add(autoBtn100);
		
		
		//connBtn.addActionListener(this);
		//resetBtn.addActionListener(this);
		//autoBtn100.addActionListener(this);
	}
	
	public void setScoreLabel(String newScoreLabel) {
		this.scoreLabel.setText(newScoreLabel);
		this.scoreLabel.paintImmediately(scoreLabel.getVisibleRect());;
	}
	
	public JLabel getScoreLabel() {
		return this.scoreLabel;
	}
	
	public void setScoreSum(int scoreSum) {
		this.scoreSum = scoreSum;
	}
	
	public int getScoreSum() {
		return scoreSum;
	}
	
	public JButton getResetBtn() {
		return this.resetBtn;
	}
	
	public JButton getAutoBtn() {
		return this.autoBtn100;
	}
	
	/*public void actionPerformed(ActionEvent e) {
		Object btn = e.getSource();
		
		if(btn == resetBtn) {
			try {
				System.out.println("Reset button pressed");
				
				terrain = new Terrain();
				
			}catch(Exception ex) {
				System.out.println("Error: " + ex);
			}
			
		}
		
		if(btn == autoBtn100) {
			try {
				System.out.println("Auto 100 button pressed");
				
				
			}catch(Exception ex) {
				System.out.println("Error: " + ex);
			}
		}
	}*/

}
