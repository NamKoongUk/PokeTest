package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class NpcBattlePage extends JPanel {
	private JPanel nbp;
	private MainFrame mf;
	private Map m;
	private BattleSkillPage bsp;
	private PInfoPage pip;
	
	//버튼 이미지 올리기
	private Image fightButtonImage = new ImageIcon("images/싸운다버튼.png").getImage();
	private Image changeButtonImage = new ImageIcon("images/포켓몬버튼.png").getImage();
	
	private Image runButtonImage = new ImageIcon("images/도망간다버튼.png").getImage();

	//이미지 버튼화
	private JButton fightButton = new JButton(new ImageIcon(fightButtonImage));
	private JButton changeButton = new JButton(new ImageIcon(changeButtonImage));
	
	private JButton runButton = new JButton(new ImageIcon(runButtonImage));
	
	//Graphics
	//전투 배경 이미지
	Image background = Toolkit.getDefaultToolkit().getImage("images/background1.PNG");
	//내 포켓몬 이미지
	Image mypokemon = Toolkit.getDefaultToolkit().getImage("images/pikachu.PNG");
	
	
	//적 포켓몬 이미지
	Image enpokemon =  Toolkit.getDefaultToolkit().getImage("images/kkobugi.PNG");


	public NpcBattlePage(MainFrame mf, JPanel panel) {
		this.nbp = this;
		this.mf = mf;
		this.m = (Map)panel;
		this.bsp = new BattleSkillPage(mf, this);
		
		//버튼을 맘대로 배치하기 위해
		nbp.setLayout(null);
		//this.pip = new PInfoPage(mf, this);
		
		
		fightButton.setBounds(180, 500, 300, 80);
		this.add(fightButton);
		settingButton(fightButton);
		fightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
				nbp.setVisible(false);
				bsp.setVisible(true);
				mf.add(bsp);
			}
		});
		this.add(fightButton);

		
		changeButton.setBounds(550, 500, 300, 80);
		settingButton(changeButton);
		changeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
				JButton cb = new JButton("포켓몬 목록");
				nbp.setVisible(false);
				mf.add(cb);
			}
		});
		this.add(changeButton);


		
		//나중에 삭제 할 run 버튼
		runButton.setBounds(550, 600, 300, 80);
		settingButton(runButton);
		runButton.setBorderPainted(false);
		runButton.setFocusPainted(false);
		runButton.setContentAreaFilled(false);
		runButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
				mf.remove(nbp);
				m.setVisible(true);
				mf.requestFocus();
				((Map) m).start();
			}
		});
		this.add(runButton);		
	}

	//라벨 대신 배경, 포켓몬 불러오는 그래픽스
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, 1024, 768, this);
		g.drawImage(mypokemon, 210, 150, 250, 250, this);
		g.drawImage(enpokemon, 570, 150, 250, 250, this);
	}

	
	public void settingButton(JButton jb) {
		jb.setBorderPainted(false);
		jb.setFocusPainted(false);
		jb.setContentAreaFilled(false);
	}

	
}

