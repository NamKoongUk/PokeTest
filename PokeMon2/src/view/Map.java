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
	private MarketView marketView;//SM_추가
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
	private Image pvp = new ImageIcon("images/Pvp.png").getImage();//SM_추가//유저 간 pvp대기실

	//위에 이미지 이름이 바로 rpg.png입니다. 이미지를 불러옵니다
	private Image buffimg;// 더블버퍼링용 입니다.
	private Graphics gc;

	private Thread th;

	private int x, y; // 케릭터의 현재 좌표를 받을 변수
	private int pWidth = 31, pHeight = 32;

	private int cnt; //무한 루프를 카운터 하기 위한 변수
	private int moveStatus; //케릭터가 어디를 바라보는지 방향을 받을 변수
	private int num = 99;
	private boolean onOff;

	int escCtn=0;//SM_추가

	public Map(MainFrame mf) {

		System.out.println("맵 클래스 실행...");

		this.mf = mf;
		this.m = this;
		this.ump = new UserMenuPage(mf, m);
		
		this.bp = new BattlePage(mf, m);	//BattlePage 추가
		this.nbp = new NpcBattlePage(mf, m);

		this.marketView=new MarketView(mf,m);//SM_추가

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
		//케릭터가 시작할때 바라보는 방향은 아래쪽입니다.
		// 0 : 위쪽, 1 : 오른쪽, 2 : 아래쪽, 3 : 왼쪽

	}

	public void start(){ // 기본적인 명령처리
		System.out.println("스타트");
		mf.addKeyListener(this);
		th = new Thread(this);
		th.start();
	}

	public void run(){ // 스레드 메소드, 무한 루프
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

	public void paint(Graphics g) { //더블버퍼링을 사용합니다.
		buffimg = createImage(1024, 768);
		gc = buffimg.getGraphics();
		update(g);
	}

	public void update(Graphics g) {
		//더블 버퍼링을 이용해 버퍼에 그려진것을 가져옵니다.
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
		case 2 : gc.drawImage(pvp, 0, 0, 1024, 768, this); break;//SM_추가
		case 3 : gc.drawImage(lab, 0, 0, 1024, 768, this); break;
		case 4 : gc.drawImage(gym, 0, 0, 1024, 768, this); break;
		case 5 : gc.drawImage(huntfield, 0, 0, 1024, 768, this); break;
		case 6 : gc.drawImage(h_fire, 0, 0, 1024, 768, this); break;
		case 7 : gc.drawImage(h_water, 0, 0, 1024, 768, this); break;
		case 8 : gc.drawImage(h_jungle, 0, 0, 1024, 768, this); break;
		case 99 : gc.drawImage(mainPage, 0, 0, 904, 648, this);
		gc.drawString("게임을 시작하려면 Enter키를 누르세요!", 280, 680);break;


		}

		//위는 단순히 무한루프 적용여부와 케릭터 방향 체크를 위해
		//눈으로 보면서 테스트할 용도로 쓰이는 텍스트 표출입니다.

		MoveImage(img, x, y, pWidth, pHeight);
		//케릭터를 걸어가게 만들기 위해 추가로 만든 메소드 입니다.
	}

	public void MoveImage(Image img, int x, int y, int width, int height) {
		//케릭터 이미지, 케릭터 위치, 케릭터 크기를 받습니다. 
		//받은 값을 이용해서 위의 이미지칩셋에서 케릭터를 잘라내
		//표출하도록 계산하는 메소드 입니다.

		gc.setClip(x  , y, width, height);
		//현재 좌표에서 케릭터의 크기 만큼 이미지를 잘라 그립니다.

		if( playerMove ){ // 케릭터의 움직임 여부를 판단합니다.
			if ( cnt / 10 % 4 == 0 ){ gc.drawImage(img,
					x - ( width * 0 ), y - ( height * moveStatus ), this);

			}else  if(cnt/10%4 == 1){ gc.drawImage(img,
					x - ( width * 1 ), y - ( height * moveStatus ), this);

			}else  if(cnt/10%4 == 2){  gc.drawImage(img,
					x - ( width * 2 ), y - ( height * moveStatus ), this);

			}else  if(cnt/10%4 == 3){ gc.drawImage(img,
					x - ( width * 1 ), y - ( height * moveStatus ), this);
			}
			//케릭터의 방향에 따라 걸어가는 모션을 취하는 
			//케릭터 이미지를 시간차를 이용해 순차적으로 그립니다.

		}else {gc.drawImage(img, x - ( width * 1 ), 
				y - ( height * moveStatus ), this); 
		//케릭터가 움직이지 않으면 정지한 케릭터를 그립니다.
		}
	}

	public void keyProcess(){
		//여기서는 단순 케릭터가 이동하는 좌표 말고도
		//케릭터의 움직임 여부및 방향을 체크 합니다.
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

		if(escCtn==0&&e.getKeyCode() == 27) { //SM_추가//기존꺼는 Market에서 오류 발생
			System.out.println("esc 누름 = 유저메뉴");

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
		//체육관
		if( num == 4 && (x > 390 && x<440) && (y>670)) {
			num =0;
			x= 488;
			y = 150;
		}
		//마을_체육관입
		if( num ==0 &&(x >480 && x <510) && (y<148)) {

			num =4;
			x = 430;
			y=670;
		}
		//--------------------------------------
		//연구소
		if( num == 3 && (x > 500 && x<550) && (y>670)) {
			num =0;
			x= 180;
			y = 140;
		}
		//마을_연구소입
		if( num ==0 &&(x > 170 && x < 200) && (y<140)){
			num =3;
			x = 525;
			y=670;
		}
		//--------------------------------------
		//센터
		if( num == 1 && (x > 450 && x<500) && (y>670)) {
			num =0;
			x= 765;
			y = 610;
		}
		//마을_센터입
		if( num == 0 && (x > 750 && x< 780) && (y<610 && y>550)) {
			num =1;
			x= 475;
			y = 670;	
		}
		//센터에서 pvp대기실로
		if(num == 1 && (x>415 &&x<520)&&(y<10)) {
			num=2;
			x=480;
			y=650;
		}
		//센터에서 상점 이용하기
		if(num == 1 && (x>190&&x<250)&&(y>350&&y<375)){
			escCtn=1;
			System.out.println("상점 이용");
			//this.market = new Market(mf,m);
			m.setVisible(false);
			mf.add(marketView);
			marketView.setVisible(true);
			run();

			x=230;
			y=400;

			//ChangePanel.changePanel(mf, this, new Market(mf,m));
		}
		//pvp대기실에서 센터로
		if(num==2) {
			if((x>450&&x<500)&&(y>670)){
				num=1;
				x=480;
				y=30;
			}
		}       


		//--------------------------------------
		//사냥터
		if(num == 5 && (x > 460 && x < 560) && (y < 0)) {
			num = 0;
			x = 490;
			y = 670;
		}
		//마을_사냥터입
		if(num == 0 && (x > 450 && x < 520) && (y > 670)) {
			num = 5;
			x = 510;
			y = 0;
		}
		//사냥터_불
		if(num == 5 && (x < 0) && (y > 260 & y < 410)) {
			num = 6;
			x = 510;
			y = 0;
		}
		//불사냥터 퇴장
		if(num == 6 && (x > 460 && x < 560) && (y < 0)) {
			num = 5;
			x = 10;
			y = 335;
		}
		//사냥터_물
		if(num == 5 && (x > 979) && (y > 260 & y < 410)) {
			num = 7;
			x = 510;
			y = 0;
		}
		//물사냥터 퇴장
		if(num == 7 && (x > 400 && x < 510) && (y < 0)) {
			num = 5;
			x = 979;
			y = 335;
		}
		//사냥터_풀
		if(num == 5 && (x > 460 && x < 560) && (y > 680)) {
			num = 8;
			x = 510;
			y = 0;
		}
		//풀사냥터 퇴장
		if(num == 8 && (x > 450 && x < 580) && (y < 0)) {
			num = 5;
			x = 510;
			y = 670;
		}
		//--------------------------------------
	}


	//---------------------------이동불가 오브젝트 설정----------------------------------
	void collision()
	{
		Rectangle rect = new Rectangle(x, y, pWidth, pHeight);//플레이어 크기
		//마을 오브젝트------------------------------------------------
		if(num == 0) {
			Rectangle building1 = new Rectangle(300, 430, 90, 135);//건물크기
			if(rect.intersects(building1)){canMove();}//충돌검사
			Rectangle buildingLABGYM = new Rectangle(60, -12, 580, 145);//연구소,체육관
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
			int result = JOptionPane.showConfirmDialog(null, "정말 종료하시겠습니까?", "종료 확인", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				System.exit(0);
			}else {
				y += 10;
				keyUp = false;
			}

			

		}//충돌검사

		
	
		
		//물 사냥터 좌표값 1
		if(num == 7 && (x > 520 && x < 600) && (y > 150 && y < 215)){
			x = 520;
			y = 220;
					
			System.out.println("배틀페이지");
			m.setVisible(false);
			mf.add(bp);
			bp.setVisible(true);
			run();
		}
		
		/*//keypresse를 추가
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
		
		
		
		//체육관 배틀 첫번째
		int ctn2 = 0;
		if(num == 4 && (ctn2 == ctn - 1)&& (x > 24 && x < 75) && (y > 200 && y < 240)){
			x = 50;
			y = 245;
			ctn += 1 ;
					
			System.out.println("Npc배틀페이지");
			m.setVisible(false);
			mf.add(nbp);
			nbp.setVisible(true);
			run();
		}
		
		//체육관 배틀 두번째
		if(num == 4 &&(ctn == 0)&& (x > 20 && x < 76) && (y > 580 && y < 615)){
			x = 80;
			y = 615;
			ctn += 1;
					
			System.out.println("Npc배틀페이지");
			m.setVisible(false);
			mf.add(nbp);
			nbp.setVisible(true);
			run();
		}
		
		//관장님 배틀 추가해야 함



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

	//SM_추가
	//escCtn 필드 때문에 추가 Market에서 escCtn값을 변경하기 위해 사용   
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