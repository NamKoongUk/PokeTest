package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;



public class PBookPage extends JPanel{
	
	private JPanel pb;
	private MainFrame mf;
	private UserMenuPage ump;
	
	private ImageIcon backButtonImage = new ImageIcon(("images/userMenuImages/backButtonBasic.PNG"));
	private ImageIcon leftButtonImage = new ImageIcon(("images/userMenuImages/leftButtonBasic.PNG"));
	private ImageIcon rightButtonImage = new ImageIcon(("images/userMenuImages/rightButtonBasic.PNG"));
	private ImageIcon getPokeImage = new ImageIcon(("images/userMenuImages/getPoke.PNG"));
	//이미지를 가질 리스트
	ArrayList<ImageIcon> imageList = new ArrayList<>();
	//화면에 보여질 포켓몬 이미지 라벨
	private JLabel[] pInfo = new JLabel[6];
	private JButton leftButton = new JButton(leftButtonImage);
	private JButton rightButton = new JButton(rightButtonImage);
	private JLabel getPokeLabel = new JLabel(getPokeImage);
	private JButton backButton = new JButton(backButtonImage);
	private ImageIcon image;
	//기본 시작페이지
	private int page = 1;
	//마지막 포켓몬 번호
	private int pokemonMax = 29;
	
	
	public PBookPage(MainFrame mf,UserMenuPage ump) {
		pBookImage();
		this.mf = mf;
		this.pb = this;
		this.ump = ump;
		this.setLayout(null);
		JTextArea getPoke = new JTextArea();
		
		mf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == UserMenuPage.ESC) {
					mf.remove(pb);
					pb.setVisible(false);
				}
			}
		});
		
		JLabel labelMain = new JLabel();
		labelMain.setText("main");
		JLabel label = new JLabel();
		label.setText("pokemon book Page");
		label.setBounds(450, 20, 200, 40);
		this.setBackground(Color.WHITE);

		for(int i=0; i<=5; i++) {
			pInfo[i] = new JLabel(imageList.get(i));
		}

		
		leftButton.setBounds(20, 320, 95, 95);
		leftButton.setBorderPainted(false);
		leftButton.setFocusPainted(false);
		leftButton.setContentAreaFilled(false);
		//왼쪽으로 가기 버튼
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				leftButton.setIcon(new ImageIcon("images/userMenuImages/leftButtonEntered.PNG"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				leftButton.setIcon(new ImageIcon("images/userMenuImages/leftButtonBasic.PNG"));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//왼쪽 버튼
				if(page>1) {
					page --;
					clickPBookleftButton(page);
				} else {
					System.out.println("first Page");
				}
				
			}
		});
		
		rightButton.setBounds(900, 320, 95, 95);
		rightButton.setBorderPainted(false);
		rightButton.setFocusPainted(false);
		rightButton.setContentAreaFilled(false);
		//오른쪽으로 가기 버튼
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				rightButton.setIcon(new ImageIcon("images/userMenuImages/rightButtonEntered.PNG"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				rightButton.setIcon(new ImageIcon("images/userMenuImages/rightButtonBasic.PNG"));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(page<imageList.size()/6) {
					clickPBookRightButton(page);
					page ++;
				} else {
					System.out.println("Last Page");
				}
			}
		});
		
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
			public void mousePressed(MouseEvent e) {
				mf.remove(pb);
				ump.setVisible(true);
				mf.requestFocus();
			}
		});
		getPoke.setBounds(775, 15, 120, 40);
		
		
		setBoundPInfo();

		for(int i=0; i<=pInfo.length-1; i++) {
			this.add(pInfo[i]);
		}
		this.add(getPokeLabel);
		this.add(label);
		this.add(backButton);
		this.add(getPoke);
		this.add(leftButton);
		this.add(rightButton);
		this.add(backButton);
		
	}
	//이미지 list저장 메소드
	public void pBookImage() {
		for(int i=0; i<pokemonMax; i++) {
			if(i<9) {
				image = new ImageIcon(("images/userMenuImages/pBook/00"+(i+1)+".png"));
			} else {
				image = new ImageIcon(("images/userMenuImages/pBook/0"+(i+1)+".png"));
			}
			imageList.add(image);
		}
	}
	public void clickPBookRightButton(int page) {
		int max = page*6;
		int min = max-6;
		for(int i=0; i<=5; i++) {
			this.remove(pInfo[i]);
		}
		int index =0;
		for(int i=min; i<max; i++) {
			pInfo[index] = new JLabel(imageList.get(i+6));
			this.add(pInfo[index]);
			index++;
		}
		setBoundPInfo();
	}
	public void clickPBookleftButton(int page) {
		int max = page*6;
		int min = max-6;
		for(int i=0; i<=5; i++) {
			this.remove(pInfo[i]);
		}
		int index = 5;
		for(int i=max; i>min; i--) {
			pInfo[index] = new JLabel(imageList.get(i-1));
			this.add(pInfo[index]);
			index--;
		}
		setBoundPInfo();
	}
	public void setBoundPInfo() {
		
		pInfo[0].setBounds(125, 70, 250, 250);
		pInfo[1].setBounds(385, 70, 250, 250);
		pInfo[2].setBounds(645, 70, 250, 250);
		pInfo[3].setBounds(125, 350, 250, 250);
		pInfo[4].setBounds(385, 350, 250, 250);
		pInfo[5].setBounds(645, 350, 250, 250);
		
		
	}

	
}
