package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserInvenPage extends JPanel {
	private JPanel uivp;
	private MainFrame mf;
	private UserMenuPage ump;
	
	private JButton backButton = new JButton(new ImageIcon(("images/userMenuImages/backButtonBasic.PNG")));
	private JButton useButton = new JButton(new ImageIcon(("images/userMenuImages/useButton.PNG")));
	
	private JButton upButton = new JButton(new ImageIcon(("images/userMenuImages/upButtonBasic.PNG")));
	private JButton downButton = new JButton(new ImageIcon(("images/userMenuImages/downButtonBasic.PNG")));
	
	private JLabel itemInfoLabel = new JLabel(new ImageIcon(("images/userMenuImages/itemInfo.PNG")));
	private JLabel itemListLabel = new JLabel(new ImageIcon(("images/userMenuImages/itemList.PNG")));
	
	public UserInvenPage(MainFrame mf,UserMenuPage ump) {
		this.mf = mf;
		this.ump = ump;
		this.uivp = this;
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		JLabel label = new JLabel();
		label.setText("가방 페이지");
		
		mf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == UserMenuPage.ESC) {
					mf.remove(uivp);
					uivp.setVisible(false);
				}
			}
		});
		
		itemInfoLabel.setBounds(500, 100, 300, 300);
		itemListLabel.setBounds(100,30,400,650);
		
	
		useButton.setBounds(500, 450, 300, 150);
		useButton.setBorderPainted(false);
		useButton.setFocusPainted(false);
		useButton.setContentAreaFilled(false);
		useButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				useButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				useButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				//클릭시 이벤트
			}
		});
		this.add(useButton);
		
		upButton.setBounds(150, 20, 95, 95);
		upButton.setBorderPainted(false);
		upButton.setFocusPainted(false);
		upButton.setContentAreaFilled(false);
		upButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				upButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				upButton.setIcon(new ImageIcon("images/userMenuImages/upButtonEntered.PNG"));
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				upButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				upButton.setIcon(new ImageIcon("images/userMenuImages/upButtonBasic.PNG"));
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				//클릭시 이벤트
			}
		});
		this.add(upButton);
	
		downButton.setBounds(150, 650, 95, 95);
		downButton.setBorderPainted(false);
		downButton.setFocusPainted(false);
		downButton.setContentAreaFilled(false);
		downButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				downButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				downButton.setIcon(new ImageIcon("images/userMenuImages/downButtonEntered.PNG"));
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				downButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				downButton.setIcon(new ImageIcon("images/userMenuImages/downButtonBasic.PNG"));
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				//클릭시 이벤트
			}
		});
		this.add(downButton);
		
		label.setBounds(450, 20, 200, 40);
		backButton.setBounds(900, 610, 90, 120);
		backButton.setBorderPainted(false);
		backButton.setFocusPainted(false);
		backButton.setContentAreaFilled(false);
		//뒤로가기 버튼
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(new ImageIcon("images/userMenuImages/backButtonEntered.PNG"));
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(new ImageIcon("images/userMenuImages/backButtonBasic.PNG"));
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				//클릭시 이벤트
				mf.remove(uivp);
				ump.setVisible(true);
				mf.requestFocus();
			}
		});
		this.add(backButton);
		this.add(itemInfoLabel);
		this.add(itemListLabel);
		
		this.add(label);
	}

}
