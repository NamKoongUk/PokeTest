package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class BattleSkillPage extends JPanel implements KeyListener {
	private MainFrame mf;
	private Map m;
	private JPanel bsp;
	private BattlePage bp;
	private NpcBattlePage nbp;
	
	//버튼 이미지
	private Image skill1ButtonImage = new ImageIcon("images/스킬버튼.png").getImage();
	private Image skill2ButtonImage = new ImageIcon("images/스킬버튼.png").getImage();
	private Image skill3ButtonImage = new ImageIcon("images/스킬버튼.png").getImage();
	private Image skill4ButtonImage = new ImageIcon("images/스킬버튼.png").getImage();
	private Image backButtonImage = new ImageIcon("images/back.png").getImage();
	
	//버튼화
	private JButton skill1b = new JButton(new ImageIcon(skill1ButtonImage));
	private JButton skill2b = new JButton(new ImageIcon(skill2ButtonImage));
	private JButton skill3b = new JButton(new ImageIcon(skill3ButtonImage));
	private JButton skill4b = new JButton(new ImageIcon(skill4ButtonImage));
	private JButton backButton = new JButton(new ImageIcon(backButtonImage));
	

	
	public BattleSkillPage(MainFrame mf, BattlePage bp) {
		this.mf = mf;
		this.bsp = this;
		this.bp = bp;
		bsp.setOpaque(false);
		bsp.setBounds(0, 0, 1024, 768);
		
		bsp.setBackground(Color.LIGHT_GRAY);
		bsp.setLayout(null);
		this.addKeyListener(this);
		
		
		
		skill1b.setBounds(200, 200, 250, 100);
		skill1b.addMouseListener(new MouseAdapter(){
		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			//임시
			mf.remove(bsp);
			bp.setVisible(true);
			mf.requestFocus();
		}
	});
		
		
		skill2b.setBounds(550, 200, 250, 100);
		skill2b.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				//임시
				mf.remove(bsp);
				bp.setVisible(true);
				mf.requestFocus();
			}
		});

		skill3b.setBounds(200, 400, 250, 100);
		skill3b.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				//임시
				mf.remove(bsp);
				bp.setVisible(true);
				mf.requestFocus();
			}
		});

		skill4b.setBounds(550, 400, 250, 100);
		skill4b.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				//임시
				mf.remove(bsp);
				bp.setVisible(true);
				mf.requestFocus();
			}
		});

		
		backButton.setBounds(100, 600, 100, 80);
		backButton.setBorderPainted(false);
		backButton.setFocusPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				mf.remove(bsp);
				bp.setVisible(true);
				mf.requestFocus();

			}
		});
		
		this.add(skill1b);
		this.add(skill2b);
		this.add(skill3b);
		this.add(skill4b);
		this.add(backButton);
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

	
	
	
	
	//NpcBattlePage 받는 생성자
	public BattleSkillPage(MainFrame mf, NpcBattlePage nbp) {
		this.mf = mf;
		this.bsp = this;
		this.nbp = nbp;
		bsp.setOpaque(false);
		bsp.setBounds(0, 0, 1024, 768);
		
		bsp.setBackground(Color.LIGHT_GRAY);
		bsp.setLayout(null);
		this.addKeyListener(this);
		
		
		
		skill1b.setBounds(200, 200, 250, 100);
		skill1b.addMouseListener(new MouseAdapter(){
		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			//임시
			mf.remove(bsp);
			nbp.setVisible(true);
			mf.requestFocus();
		}
	});
		
		
		skill2b.setBounds(550, 200, 250, 100);
		skill2b.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				//임시
				mf.remove(bsp);
				nbp.setVisible(true);
				mf.requestFocus();
			}
		});

		skill3b.setBounds(200, 400, 250, 100);
		skill3b.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				//임시
				mf.remove(bsp);
				nbp.setVisible(true);
				mf.requestFocus();
			}
		});

		skill4b.setBounds(550, 400, 250, 100);
		skill4b.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				//임시
				mf.remove(bsp);
				nbp.setVisible(true);
				mf.requestFocus();
			}
		});

		
		backButton.setBounds(100, 600, 100, 80);
		backButton.setBorderPainted(false);
		backButton.setFocusPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				mf.remove(bsp);
				nbp.setVisible(true);
				mf.requestFocus();

			}
		});
		
		this.add(skill1b);
		this.add(skill2b);
		this.add(skill3b);
		this.add(skill4b);
		this.add(backButton);
		
	}
	
}
