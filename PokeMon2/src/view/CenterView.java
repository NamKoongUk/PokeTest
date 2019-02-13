package view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CenterView extends JPanel{
	private MainFrame mf;
	private CenterView centerView;
	private Map m;
	private Image backButtonImage = new ImageIcon("images/back.png").getImage();
	private JButton backButton = new JButton(new ImageIcon(backButtonImage));
	
	
	
	public CenterView(MainFrame mf, JPanel oldPage) {
		this.mf=mf;
		this.centerView=this;
		this.m=(Map)oldPage;
		
		this.setLayout(null);
		this.setBounds(0,0,1024,768);
		
		this.setBackground(Color.BLUE);
		
		
		System.out.println("ºæ≈Õ");
		
		backButton.setBounds(920, 600, 70, 50);
	    settingButton(backButton);
		
		this.add(backButton);
		
		
		
		
		
		
		
		
		
		
		
	}
	public void settingButton(JButton jb) {
	      jb.setBorderPainted(false);
	      jb.setFocusPainted(false);
	      jb.setContentAreaFilled(false);
	}
	
}
