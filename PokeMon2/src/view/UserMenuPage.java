package view;


import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.BattleManager;


public class UserMenuPage extends JPanel {
	public final static int ESC = 27;
	private JPanel ump;
	private MainFrame mf;
	private Map m;
	private PInfoPage pip;
	private UserInfoPage uip;
	private SavePage sp;
	private UserInvenPage uivp;
	private PBookPage pb;
	
	private JButton saveButton = new JButton(new ImageIcon("images/userMenuImages/savePageButtonBasic.PNG"));
	private JButton pBookButton = new JButton(new ImageIcon("images/userMenuImages/pBookButtonBasic.PNG"));
	private JButton pokemonInfoButton = new JButton(new ImageIcon("images/userMenuImages/pokemonInfoButtonBasic.PNG"));
	private JButton userInfoButton = new JButton(new ImageIcon("images/userMenuImages/userInfoButtonBasic.PNG"));
	private JButton userInvenButton = new JButton(new ImageIcon("images/userMenuImages/userInvenButtonBasic.PNG"));
	private JButton backButton = new JButton(new ImageIcon("images/userMenuImages/backButtonBasic.png"));

	


	public UserMenuPage(MainFrame mf, JPanel panel) {
		
		this.ump = this;
		this.mf = mf;
		this.m = (Map) panel;
		this.uip = new UserInfoPage(mf, this);
		this.pip = new PInfoPage(mf, this);
		this.sp = new SavePage(mf, this);
		this.uivp = new UserInvenPage(mf,this);
		this.pb = new PBookPage(mf,this);
		
		settingButton(saveButton);
		this.setLayout(null);
		saveButton.setBounds(200, 30, 600, 120);
		pBookButton.setBounds(200,170,600, 120);
		pokemonInfoButton.setBounds(200,310,600, 120);
		userInfoButton.setBounds(200,450,600, 120);
		userInvenButton.setBounds(200,590,600, 120);
		backButton.setBounds(900, 610, 90, 120);
		
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				saveButton.setIcon(new ImageIcon("images/userMenuImages/savePageButtonEntered.PNG"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				saveButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				saveButton.setIcon(new ImageIcon("images/userMenuImages/savePageButtonBasic.PNG"));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				ump.setVisible(false);
				sp.setVisible(true);
				mf.add(sp);
			}
		});

		this.add(saveButton);

		settingButton(pBookButton);
		pBookButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pBookButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				pBookButton.setIcon(new ImageIcon("images/userMenuImages/pBookButtonEntered.PNG"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pBookButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				pBookButton.setIcon(new ImageIcon("images/userMenuImages/pBookButtonBasic.PNG"));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				ump.setVisible(false);
				pb.setVisible(true);
				mf.add(pb);
			}
		});
		this.add(pBookButton);

		settingButton(pokemonInfoButton);
		pokemonInfoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pokemonInfoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				pokemonInfoButton.setIcon(new ImageIcon("images/userMenuImages/pokemonInfoButtonEntered.PNG"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pokemonInfoButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				pokemonInfoButton.setIcon(new ImageIcon("images/userMenuImages/pokemonInfoButtonBasic.PNG"));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				ump.setVisible(false);
				pip.setVisible(true);
				mf.add(pip);
			}
		});
		this.add(pokemonInfoButton);

		settingButton(userInfoButton);
		userInfoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				userInfoButton.setIcon(new ImageIcon("images/userMenuImages/userInfoButtonEntered.PNG"));
				userInfoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				userInfoButton.setIcon(new ImageIcon("images/userMenuImages/userInfoButtonBasic.PNG"));
				userInfoButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				ump.setVisible(false);
				uip.setVisible(true);
				mf.add(uip);
				
			}
		});
		this.add(userInfoButton);

		settingButton(userInvenButton);

		userInvenButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				userInvenButton.setIcon(new ImageIcon("images/userMenuImages/userInvenButtonEntered.PNG"));
				userInvenButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				userInvenButton.setIcon(new ImageIcon("images/userMenuImages/userInvenButtonBasic.PNG"));
				userInvenButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				ump.setVisible(false);
				uivp.setVisible(true);
				mf.add(uivp);
			}
		});
		this.add(userInvenButton);
		
		
		settingButton(backButton);
		backButton.setName("backButton");
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
			public void mousePressed(MouseEvent e) {
				mf.remove(ump);
				m.setVisible(true);
				mf.requestFocus();
				((Map) m).start();
			}
		});
		this.add(backButton);
		
		// `키 눌렀을때 이전 메뉴로 돌아감
		mf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 192) {
					mf.remove(ump);
					m.setVisible(true);
					mf.requestFocus();
					((Map) m).start();
				}
			}
		});
	}
	//버튼들 기본 세팅
	public void settingButton(JButton jb) {
		jb.setBorderPainted(false);
		jb.setFocusPainted(false);
		jb.setContentAreaFilled(false);
	}





}
