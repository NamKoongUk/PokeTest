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
import javax.swing.JTextArea;

import controller.BattleManager;

public class PInfoPage extends JPanel{
	private MainFrame mf;
	private Map m;
	private JPanel pip;
	private UserMenuPage ump;

	private JLabel[] pInfo = new JLabel[4];
	private JTextArea[] pInfoText = new JTextArea[4];
	private JButton backButton = new JButton(new ImageIcon("images/userMenuImages/backButtonBasic.png"));

	public PInfoPage(MainFrame mf, UserMenuPage ump) {
		
		this.mf = mf;
		this.pip = this;
		this.ump = ump;
		pip.setOpaque(false);
		pip.setBounds(0, 0, 1024, 768);
/*		pm.setPokemon(new Pokemon(1,"이상해씨",50,3));
		pm.setPokemon(new Pokemon(4,"파이리",50,3));
		pm.setPokemon(new Pokemon(6,"리자몽",50,1));
		pm.setPokemon(new Pokemon(5,"리자드",50,2));*/
		for(int i=0; i < pInfo.length; i++) {
			pInfo[i] = new JLabel();
			pInfo[i] = new JLabel(new ImageIcon("images/userMenuImages/pInfo4.png"));
		}
		for(int i=0; i < pInfo.length; i++) {
			pInfoText[i] = new JTextArea();
			
			pInfoText[i].setBackground(new Color(0,0,0,0));
		}
		
		
		
/*		for(int i=0; i < pm.getPokemon().size(); i++) {
			System.out.println(i);
			String pName = pm.getPokemon().get(i).getPokemonName();
			String pNum = pm.getPokemon().get(i).getPokemonNumber()+"";
			String pSpeed = pm.getPokemon().get(i).getPokemonSpeed()+"";
			String pAtk = pm.getPokemon().get(i).getPokemonAtk()+"";
			String pHp = pm.getPokemon().get(i).getPokemonHp()+"";
			String pLevel = pm.getPokemon().get(i).getPokemonLevel()+"";
			
			pInfo[i].setIcon(new ImageIcon("images/userMenuImages/pBook/00"+pm.getPokemon().get(i).getPokemonNumber()+".png"));
			pInfoText[i].setText("\t포켓몬 이름 : "+ pName + "\n"
								+"\t레    벨 : " + pLevel + "\n"
								+"\t공 격 력 : " + pAtk + "\n"
								+"\t스 피 드 : " + pSpeed + "\n"
								+"\t체    력  : " + pHp + "\n"
								);
		}*/
		//BattleManager.showP(this);        //활성화
		
		mf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == UserMenuPage.ESC) {
					mf.remove(pip);
					pip.setVisible(false);
				}
			}
		});
		
		JLabel label = new JLabel();
		label.setText("  ");

		pip.setBackground(Color.WHITE);
		pip.setLayout(null);
		backButton.setBounds(900, 610, 90, 120);
		backButton.setBorderPainted(false);
		backButton.setFocusPainted(false);
		backButton.setContentAreaFilled(false);

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
				mf.remove(pip);
				ump.setVisible(true);
				mf.requestFocus();

			}
		});

		label.setBounds(450, 20, 200, 40);
		pInfo[0].setBounds(180, 30, 300, 160);
		pInfo[1].setBounds(520, 30, 300, 160);
		pInfo[2].setBounds(180, 380, 300, 160);
		pInfo[3].setBounds(520, 380, 300, 160);
		
		pInfoText[0].setBounds(180,220,300,130);
		pInfoText[1].setBounds(520,220,300,130);
		pInfoText[2].setBounds(180,570,300,130);
		pInfoText[3].setBounds(520,570,300,130);

		pip.add(label);
		
		pip.add(backButton);
		pip.add(pInfo[0]);
		pip.add(pInfo[1]);
		pip.add(pInfo[2]);
		pip.add(pInfo[3]);
		pip.add(pInfoText[0]);
		pip.add(pInfoText[1]);
		pip.add(pInfoText[2]);
		pip.add(pInfoText[3]);
	}

	public JLabel[] getpInfo() {
		return pInfo;
	}

	public void setpInfo(JLabel[] pInfo) {
		this.pInfo = pInfo;
	}

	public JTextArea[] getpInfoText() {
		return pInfoText;
	}

	public void setpInfoText(JTextArea[] pInfoText) {
		this.pInfoText = pInfoText;
	}


}
