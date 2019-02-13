package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.UserManager;

public class StartPage extends JPanel{

	private MainFrame mf;
	private JPanel stp;
	
	public StartPage(MainFrame mf) {
		this.mf = mf;
		this.stp = this;
		stp.setOpaque(false);
	      stp.setBounds(0, 0, 1024, 768);
	      this.setLayout(null);
	      this.setBackground(Color.WHITE);
	      
	      JButton startbtn = new JButton("새로 시작");
	      JButton nextPage = new JButton("모험 시작");
	      startbtn.setSize(200,200);
	      startbtn.setLocation(500,360);
	      
	      nextPage.setSize(200,200);
	      nextPage.setLocation(300,200);

	      
	      startbtn.addMouseListener(new MouseAdapter() {
	         
	         @Override
	         public void mousePressed(MouseEvent e) {
	            
	            String result = JOptionPane.showInputDialog("모험을 시작할 이름을 정해주세요!");
	            UserManager.createUser(result);
	            
	            /*mf.remove(stp);
	            m.setVisible(true);
	            mf.requestFocus();
	            ((Map) m).start();*/

	         }
	      });
	         nextPage.addMouseListener(new MouseAdapter() {
	        	 public void mousePressed(MouseEvent e) {
	        		 stp.setVisible(false);
	        		 mf.remove(stp);
	        		 mf.add(new Map(mf));
	        		 mf.requestFocus();
	        		
	        		
	        	 }
	         });
	     
	      
	         stp.add(startbtn);
		      stp.add(nextPage);
		      mf.add(this);
	      
	   }
		
	}
	

