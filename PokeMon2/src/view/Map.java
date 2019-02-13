package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


class Map extends JPanel implements Runnable, KeyListener {

	private MainFrame mf;
	private Map m;
	private PInfoPage pip;
	private UserMenuPage ump; 
	private MarketView marketView;//SM_�߰�
	private int movementSP = 3;

	
	private BattlePage bp;
	private BattleSkillPage bsp;
	private NpcBattlePage nbp;
	private int ctn = 0;


	private boolean keyUp = false;
	private boolean keyDown = false;
	private boolean keyLeft = false;
	private boolean keyRight = false; 
	private boolean playerMove = false;
	private boolean sprint = false;

	private Toolkit tk = Toolkit.getDefaultToolkit();

	private Image img = new ImageIcon("images/userF.PNG").getImage();
	private Image gym = new ImageIcon("images/gym.PNG").getImage();
	private Image vill = new ImageIcon("images/main.PNG").getImage();
	private Image lab = new ImageIcon("images/lab.png").getImage();
	private Image center = new ImageIcon("images/Center.png").getImage();
	private Image huntfield = new ImageIcon("images/HuntFieldPage.png").getImage();
	private Image h_fire = new ImageIcon("images/fireField.png").getImage();
	private Image h_water = new ImageIcon("images/waterField.png").getImage();
	private Image h_jungle = new ImageIcon("images/jungleField.png").getImage();
	private Image mainPage = new ImageIcon("images/mainPage.gif").getImage();
	private Image pvp = new ImageIcon("images/Pvp.png").getImage();//SM_�߰�//���� �� pvp����

	//���� �̹��� �̸��� �ٷ� rpg.png�Դϴ�. �̹����� �ҷ��ɴϴ�
	private Image buffimg;// ������۸��� �Դϴ�.
	private Graphics gc;

	private Thread th;

	private int x, y; // �ɸ����� ���� ��ǥ�� ���� ����
	private int pWidth = 31, pHeight = 32;

	private int cnt; //���� ������ ī���� �ϱ� ���� ����
	private int moveStatus; //�ɸ��Ͱ� ��� �ٶ󺸴��� ������ ���� ����
	private int num = 99;
	private boolean onOff;

	int escCtn=0;//SM_�߰�

	public Map(MainFrame mf) {

		System.out.println("�� Ŭ���� ����...");

		this.mf = mf;
		this.m = this;
		this.ump = new UserMenuPage(mf, m);
		
		this.bp = new BattlePage(mf, m);	//BattlePage �߰�
		this.nbp = new NpcBattlePage(mf, m);

		this.marketView=new MarketView(mf,m);//SM_�߰�

		onOff = true;

		this.setVisible(true);
		this.setSize(1024,768);
		this.setBounds(0,0,1024,768);
		init();
		start();

		Dimension screen = tk.getScreenSize();

		int xpos = (int)(screen.getWidth() / 2 - getWidth() / 2);
		int ypos = (int)(screen.getHeight() / 2 - getHeight() / 2);
		setLocation(xpos, ypos);

		mf.add(this);

	}

	public void init(){
		x = 500;
		y = 600;

		moveStatus = 2;
		//�ɸ��Ͱ� �����Ҷ� �ٶ󺸴� ������ �Ʒ����Դϴ�.
		// 0 : ����, 1 : ������, 2 : �Ʒ���, 3 : ����

	}

	public void start(){ // �⺻���� ���ó��
		System.out.println("��ŸƮ");
		mf.addKeyListener(this);
		th = new Thread(this);
		th.start();
	}

	public void run(){ // ������ �޼ҵ�, ���� ����
		while(true){
			try{
				keyProcess();
				repaint();
				collision();
				Thread.sleep(20);
				cnt++;

				if(!m.isVisible()) {
					while(this.isVisible() == false) {
						th.wait();
					}
				}


			}catch(Exception e){
				return;
			}
		}

	}

	public void paint(Graphics g) { //������۸��� ����մϴ�.
		buffimg = createImage(1024, 768);
		gc = buffimg.getGraphics();
		update(g);
	}

	public void update(Graphics g) {
		//���� ���۸��� �̿��� ���ۿ� �׷������� �����ɴϴ�.
		DrawImg();
		g.drawImage(buffimg, 0, 0, this);
	}

	public void DrawImg() {
		gc.setFont(new Font("Default", Font.BOLD, 20));
		gc.drawString(Integer.toString(cnt), 50, 50);
		gc.drawString(Integer.toString((playerMove)?1:0),200, 50);

		switch(num) {
		case 0 : gc.drawImage(vill, 0, 0, 1024, 768, this); break;
		case 1 : gc.drawImage(center, 0, 0, 1024, 768, this); break; 
		case 2 : gc.drawImage(pvp, 0, 0, 1024, 768, this); break;//SM_�߰�
		case 3 : gc.drawImage(lab, 0, 0, 1024, 768, this); break;
		case 4 : gc.drawImage(gym, 0, 0, 1024, 768, this); break;
		case 5 : gc.drawImage(huntfield, 0, 0, 1024, 768, this); break;
		case 6 : gc.drawImage(h_fire, 0, 0, 1024, 768, this); break;
		case 7 : gc.drawImage(h_water, 0, 0, 1024, 768, this); break;
		case 8 : gc.drawImage(h_jungle, 0, 0, 1024, 768, this); break;
		case 99 : gc.drawImage(mainPage, 0, 0, 904, 648, this);
		gc.drawString("������ �����Ϸ��� EnterŰ�� ��������!", 280, 680);break;


		}

		//���� �ܼ��� ���ѷ��� ���뿩�ο� �ɸ��� ���� üũ�� ����
		//������ ���鼭 �׽�Ʈ�� �뵵�� ���̴� �ؽ�Ʈ ǥ���Դϴ�.

		MoveImage(img, x, y, pWidth, pHeight);
		//�ɸ��͸� �ɾ�� ����� ���� �߰��� ���� �޼ҵ� �Դϴ�.
	}

	public void MoveImage(Image img, int x, int y, int width, int height) {
		//�ɸ��� �̹���, �ɸ��� ��ġ, �ɸ��� ũ�⸦ �޽��ϴ�. 
		//���� ���� �̿��ؼ� ���� �̹���Ĩ�¿��� �ɸ��͸� �߶�
		//ǥ���ϵ��� ����ϴ� �޼ҵ� �Դϴ�.

		gc.setClip(x  , y, width, height);
		//���� ��ǥ���� �ɸ����� ũ�� ��ŭ �̹����� �߶� �׸��ϴ�.

		if( playerMove ){ // �ɸ����� ������ ���θ� �Ǵ��մϴ�.
			if ( cnt / 10 % 4 == 0 ){ gc.drawImage(img,
					x - ( width * 0 ), y - ( height * moveStatus ), this);

			}else  if(cnt/10%4 == 1){ gc.drawImage(img,
					x - ( width * 1 ), y - ( height * moveStatus ), this);

			}else  if(cnt/10%4 == 2){  gc.drawImage(img,
					x - ( width * 2 ), y - ( height * moveStatus ), this);

			}else  if(cnt/10%4 == 3){ gc.drawImage(img,
					x - ( width * 1 ), y - ( height * moveStatus ), this);
			}
			//�ɸ����� ���⿡ ���� �ɾ�� ����� ���ϴ� 
			//�ɸ��� �̹����� �ð����� �̿��� ���������� �׸��ϴ�.

		}else {gc.drawImage(img, x - ( width * 1 ), 
				y - ( height * moveStatus ), this); 
		//�ɸ��Ͱ� �������� ������ ������ �ɸ��͸� �׸��ϴ�.
		}
	}

	public void keyProcess(){
		//���⼭�� �ܼ� �ɸ��Ͱ� �̵��ϴ� ��ǥ ����
		//�ɸ����� ������ ���ι� ������ üũ �մϴ�.
		playerMove = false;

		if (keyUp && y > -10 && keyDown == false){
			playerMove = true;
			y -= movementSP;
			moveStatus = 0;
		}

		if (keyDown && y < 690){
			y += movementSP;
			moveStatus = 2;
			playerMove = true;
		}

		if (keyLeft && x > -20 && keyDown == false && keyUp == false){
			x -= movementSP;

			moveStatus = 3;
			playerMove = true;
		}

		if (keyRight && x < 950 && keyDown == false && keyUp == false){
			x += movementSP;
			moveStatus = 1;
			playerMove = true;

		}

		if (sprint) {
			movementSP = 6;
		}
	}

	public void keyPressed(KeyEvent e) {

		if(escCtn==0&&e.getKeyCode() == 27) { //SM_�߰�//�������� Market���� ���� �߻�
			System.out.println("esc ���� = �����޴�");

			m.setVisible(false);
			mf.add(ump);
			ump.setVisible(true);
			run();
		}


		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT :
			keyLeft = true;
			break;
		case KeyEvent.VK_RIGHT :
			keyRight = true;
			break;
		case KeyEvent.VK_UP :
			keyUp = true;
			break;
		case KeyEvent.VK_DOWN :
			keyDown = true;
			break;
		case KeyEvent.VK_D : 
			System.out.println("x : " + x + " y : " + y + " num : " + num);
			break;
		case KeyEvent.VK_ENTER :
			if(num == 99) {
				num = 0;
				break;
			}
		case KeyEvent.VK_SHIFT :
			sprint = true;
			break;
		}

		//--------------------------------------
		//ü����
		if( num == 4 && (x > 390 && x<440) && (y>670)) {
			num =0;
			x= 488;
			y = 150;
		}
		//����_ü������
		if( num ==0 &&(x >480 && x <510) && (y<148)) {

			num =4;
			x = 430;
			y=670;
		}
		//--------------------------------------
		//������
		if( num == 3 && (x > 500 && x<550) && (y>670)) {
			num =0;
			x= 180;
			y = 140;
		}
		//����_��������
		if( num ==0 &&(x > 170 && x < 200) && (y<140)){
			num =3;
			x = 525;
			y=670;
		}
		//--------------------------------------
		//����
		if( num == 1 && (x > 450 && x<500) && (y>670)) {
			num =0;
			x= 765;
			y = 610;
		}
		//����_������
		if( num == 0 && (x > 750 && x< 780) && (y<610 && y>550)) {
			num =1;
			x= 475;
			y = 670;	
		}
		//���Ϳ��� pvp���Ƿ�
		if(num == 1 && (x>415 &&x<520)&&(y<10)) {
			num=2;
			x=480;
			y=650;
		}
		//���Ϳ��� ���� �̿��ϱ�
		if(num == 1 && (x>190&&x<250)&&(y>350&&y<375)){
			escCtn=1;
			System.out.println("���� �̿�");
			//this.market = new Market(mf,m);
			m.setVisible(false);
			mf.add(marketView);
			marketView.setVisible(true);
			run();

			x=230;
			y=400;

			//ChangePanel.changePanel(mf, this, new Market(mf,m));
		}
		//pvp���ǿ��� ���ͷ�
		if(num==2) {
			if((x>450&&x<500)&&(y>670)){
				num=1;
				x=480;
				y=30;
			}
		}       


		//--------------------------------------
		//�����
		if(num == 5 && (x > 460 && x < 560) && (y < 0)) {
			num = 0;
			x = 490;
			y = 670;
		}
		//����_�������
		if(num == 0 && (x > 450 && x < 520) && (y > 670)) {
			num = 5;
			x = 510;
			y = 0;
		}
		//�����_��
		if(num == 5 && (x < 0) && (y > 260 & y < 410)) {
			num = 6;
			x = 510;
			y = 0;
		}
		//�һ���� ����
		if(num == 6 && (x > 460 && x < 560) && (y < 0)) {
			num = 5;
			x = 10;
			y = 335;
		}
		//�����_��
		if(num == 5 && (x > 979) && (y > 260 & y < 410)) {
			num = 7;
			x = 510;
			y = 0;
		}
		//������� ����
		if(num == 7 && (x > 400 && x < 510) && (y < 0)) {
			num = 5;
			x = 979;
			y = 335;
		}
		//�����_Ǯ
		if(num == 5 && (x > 460 && x < 560) && (y > 680)) {
			num = 8;
			x = 510;
			y = 0;
		}
		//Ǯ����� ����
		if(num == 8 && (x > 450 && x < 580) && (y < 0)) {
			num = 5;
			x = 510;
			y = 670;
		}
		//--------------------------------------
	}


	//---------------------------�̵��Ұ� ������Ʈ ����----------------------------------
	void collision()
	{
		Rectangle rect = new Rectangle(x, y, pWidth, pHeight);//�÷��̾� ũ��
		//���� ������Ʈ------------------------------------------------
		if(num == 0) {
			Rectangle building1 = new Rectangle(300, 430, 90, 135);//�ǹ�ũ��
			if(rect.intersects(building1)){canMove();}//�浹�˻�
			Rectangle buildingLABGYM = new Rectangle(60, -12, 580, 145);//������,ü����
			if(rect.intersects(buildingLABGYM)){canMove();}
			Rectangle buildingyard = new Rectangle(220, 140, 260, 70);
			if(rect.intersects(buildingyard)){canMove();}
			Rectangle buildingyard2 = new Rectangle(530, 140, 320, 70);
			if(rect.intersects(buildingyard2)){canMove();}
			Rectangle buildingyard3 = new Rectangle(630, 200, 200, 80);
			if(rect.intersects(buildingyard3)){canMove();}
			Rectangle buildingcenter = new Rectangle(630, 350, 300, 250);
			if(rect.intersects(buildingcenter)){canMove();}
			Rectangle buildingcenterfen1 = new Rectangle(810, 600, 120, 40);
			if(rect.intersects(buildingcenterfen1)){canMove();}
			Rectangle buildingcenterfen2 = new Rectangle(560, 600, 200, 40);
			if(rect.intersects(buildingcenterfen2)){canMove();}
			Rectangle buildingcenterfen3 = new Rectangle(560, 460, 90, 150);
			if(rect.intersects(buildingcenterfen3)){canMove();}
			Rectangle bunsu = new Rectangle(470, 310, 60, 60);
			if(rect.intersects(bunsu)){canMove();}
			Rectangle gapan = new Rectangle(120, 350, 60, 60);
			if(rect.intersects(gapan)){canMove();}
			Rectangle lamp = new Rectangle(495, 390, 20, 60);
			if(rect.intersects(lamp)){canMove();}
			Rectangle post = new Rectangle(440, 495, 10, 20);
			if(rect.intersects(post)){canMove();}
			Rectangle forest1 = new Rectangle(0, 0, 50, 768);
			if(rect.intersects(forest1)){canMove();}
			Rectangle forest2 = new Rectangle(150, 690, 300, 30);
			if(rect.intersects(forest2)){canMove();}
			Rectangle forest3 = new Rectangle(560, 690, 500, 30);
			if(rect.intersects(forest3)){canMove();}
			Rectangle lake = new Rectangle(100, 470, 160, 120);
			if(rect.intersects(lake)){canMove();}
			Rectangle lake2 = new Rectangle(60, 580, 110, 40);
			if(rect.intersects(lake2)){canMove();}
			Rectangle lake3 = new Rectangle(140, 640, 1, 40);
			if(rect.intersects(lake3)){canMove();}
			Rectangle pump = new Rectangle(550, 270, 20, 20);
			if(rect.intersects(pump)){canMove();}
			Rectangle flower = new Rectangle(900, 183, 100, 25);
			if(rect.intersects(flower)){canMove();}
		}
		//--------------------------------------------------------
		Rectangle house = new Rectangle(860, 130, 30, 30);
		if(rect.intersects(house)){
			int result = JOptionPane.showConfirmDialog(null, "���� �����Ͻðڽ��ϱ�?", "���� Ȯ��", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				System.exit(0);
			}else {
				y += 10;
				keyUp = false;
			}

			

		}//�浹�˻�

		
	
		
		//�� ����� ��ǥ�� 1
		if(num == 7 && (x > 520 && x < 600) && (y > 150 && y < 215)){
			x = 520;
			y = 220;
					
			System.out.println("��Ʋ������");
			m.setVisible(false);
			mf.add(bp);
			bp.setVisible(true);
			run();
		}
		
		/*//keypresse�� �߰�
		if(num == 7 && keyEvent ) {
			for(int i = 0; i < 10; i++) {
				int xs = new Random().nextInt(275) + 1;
				int ys = new Random().nextInt(475) + 1;
				int xe = xs + 100;
				int ye = ys + 100;
				if(x > xs && x < xe && y > ys && y < ye) {
			}
				System.out.println(ys);
				System.out.println(xs);
					
				}
			}*/
		
		
		
		//ü���� ��Ʋ ù��°
		int ctn2 = 0;
		if(num == 4 && (ctn2 == ctn - 1)&& (x > 24 && x < 75) && (y > 200 && y < 240)){
			x = 50;
			y = 245;
			ctn += 1 ;
					
			System.out.println("Npc��Ʋ������");
			m.setVisible(false);
			mf.add(nbp);
			nbp.setVisible(true);
			run();
		}
		
		//ü���� ��Ʋ �ι�°
		if(num == 4 &&(ctn == 0)&& (x > 20 && x < 76) && (y > 580 && y < 615)){
			x = 80;
			y = 615;
			ctn += 1;
					
			System.out.println("Npc��Ʋ������");
			m.setVisible(false);
			mf.add(nbp);
			nbp.setVisible(true);
			run();
		}
		
		//����� ��Ʋ �߰��ؾ� ��



	}

	public void canMove() {
		if(moveStatus == 0) {
			y += movementSP;
		}else if(moveStatus == 2) {
			y -= movementSP;
		}else if(moveStatus == 3) {
			x += movementSP;
		}else if(moveStatus == 1) {
			x -= movementSP;
		}
	}

	//SM_�߰�
	//escCtn �ʵ� ������ �߰� Market���� escCtn���� �����ϱ� ���� ���   
	public int getEscCtn() {
		return escCtn;
	}

	public void setEscCtn(int escCtn) {
		this.escCtn = escCtn;
	}


	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT :
			keyLeft = false;
			break;
		case KeyEvent.VK_RIGHT :
			keyRight = false;
			break;
		case KeyEvent.VK_UP :
			keyUp = false;
			break;
		case KeyEvent.VK_DOWN :
			keyDown = false;
			break;
		case KeyEvent.VK_SHIFT :
			sprint = false;
			movementSP = 3;
			break;

		}
	}

	public void keyTyped(KeyEvent e) {}




}