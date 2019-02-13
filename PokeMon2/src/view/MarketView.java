package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.MCManager;
import model.dao.ItemDao;

//아이템 구매 하는 상점 클래스

//스크롤만 추가 하면 list가 나오지 않음...
//해결방법을 모르겟음
public class MarketView extends JPanel {
   private MainFrame mf;
   private JPanel marketView;
   
   private JPanel listPanel;
   private Map m;
   private String itemTemp;//아이템 내용들을 번호 매겨서 사용//아직 구현 X
   private String itemNo;//구매한 아이템 해당 번호
   private int itemAmount;//구매한 아이템 수량
   private Image backButtonImage = new ImageIcon("images/back.png").getImage();
   private JButton backButton = new JButton(new ImageIcon(backButtonImage));
   
   private MCManager mc;
   
 //--------------------------수정
   private ItemDao id = new ItemDao();//추가
   
   public MarketView(MainFrame mf, /*Map*/JPanel oldPage) {
      this.mf=mf;
      this.marketView=this;
      this.m=(Map)oldPage;
      //System.out.println("매개변수 생성자");
      
      this.setLayout(null);
      this.setBounds(0, 0, 1024, 768);
      
      listPanel =new JPanel();
      
      
      //--------------------------수정
      String[] itemList = new String[id.getItemList().size()];
      for(int i=0 ; i<itemList.length ; i++) {
    	  itemList[i]=id.getItemList().get(i).getiName();
      }
      
      
      
      
      
      JList list = new JList(itemList);
      list.setBounds(150, 100, 700, 250);
      
      list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      
      JScrollPane scroller = new JScrollPane(list);
      scroller.setPreferredSize(new Dimension(700,100));
      scroller.setLocation(150, 100);
      
      //listPanel.add(scroller);
      //listPanel.setBounds(150, 100, 700, 100);
      //listPanel.setVisible(true);
      
      JLabel selected = new JLabel("선택 항목 : ");
      selected.setBounds(250, 400, 100, 50);
      
      
      JTextField selectedItem = new JTextField(40);
      selectedItem.setLocation(350, 400);
      selectedItem.setSize(100, 50);
      
      
      
      //this.add(listPanel);
      
      //String result = "";
      
      list.addListSelectionListener(new ListSelectionListener() {
         
         @Override
         public void valueChanged(ListSelectionEvent e) {
            selectedItem.setText((String)list.getSelectedValue());
            //result+=selected.getText();
            //list.requestFocus();
         }
      });
      
      JLabel amount = new JLabel("수량 입력 : ");
      amount.setBounds(470, 400, 100, 50);
      
      JTextField selectedAmount = new JTextField(40);
      selectedAmount.setLocation(550, 400);
      selectedAmount.setSize(100, 50);
      amount.add(selectedAmount);
      
      JButton btn = new JButton("구매");
      btn.setBounds(650, 400, 60, 50);
      
      //선택한 결과물
      JLabel sayResult = new JLabel("구매한 내역");
      sayResult.setBounds(250, 500, 100, 50);
      
      
      JTextField resultItem = new JTextField(40);
      resultItem.setLocation(350, 500);
      resultItem.setSize(100, 50);
      resultItem.setEditable(false);
      
      JTextField resultAmount = new JTextField(40);
      resultAmount.setLocation(450, 500);
      resultAmount.setSize(300, 50);
      resultAmount.setEditable(false);

      
      btn.addActionListener(new ActionListener() {
         MCManager mc = new MCManager();
         @Override
         public void actionPerformed(ActionEvent e) {//구매버튼 누름
        	 
        	//구매 누르는 순간 MCManager로 값을 보내서 초기값 지정해줌
        	 //MCManager에서 비교
        	 int value = Integer.parseInt(selectedAmount.getText());
        	 itemAmount=value;
        	 mc.useMarket(selectedItem.getText(),itemAmount);
        	 
            if(value>100) {
            	System.out.println("즐");
            	resultAmount.setText("최대 구매 가능 수량은 100입니다.");
            }else if(value<=0) {
            	
            	resultAmount.setText("응~~ 돌아가~~");
            	
            }else {
            	
            	
            	if(mc.getResultNo()!=null) {
            		System.out.println("소지금액부족");
            		resultAmount.setText("소지금액을 넘어감");
            	}else {
            		resultItem.setText(selectedItem.getText()+"");
            		resultAmount.setText(value+"");
            		
            		mc.useMarket(selectedItem.getText(),itemAmount);
            		
            	}
            	
            }
            
            
         }
      });
      
      
      backButton.setBounds(920, 600, 70, 50);
      settingButton(backButton);
      backButton.setBorderPainted(false);
      backButton.setFocusPainted(false);
      backButton.setContentAreaFilled(false);
      
      backButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            //backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
         }
         @Override
         public void mouseExited(MouseEvent e) {
            //backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         }
         @Override
         public void mousePressed(MouseEvent e) {
            mf.remove(marketView);
            
            m.setVisible(true);
            mf.requestFocus();
            ((Map)m).start();
            m.setEscCtn(0);
            //ChangePanel.changePanel(mf, userMenu, oldPage);
         }
      });
      
      /*mf.addKeyListener(new KeyAdapter() {
    	  public void KeyPressed(KeyEvent e) {
    		  if(e.getKeyCode()==27) {
    			  System.out.println("즐");
    		      	mf.remove(marketView);
    		      	marketView.setVisible(false);
    		      	m.setVisible(true);
    		      }  
    	  }
	});*/
      
      
      this.add(scroller);
      this.add(list);
      this.add(selected);
      
      this.add(selectedItem);
      this.add(selectedAmount);
      
      this.add(amount);
      this.add(sayResult);
      
      this.add(resultItem);
      this.add(resultAmount);
      this.add(btn);
      
      this.add(backButton);
      
      //mf.add(listPanel,"North");
      //mf.add(listPanel);
      
      //mf.add(marketView,"Center");
      
      //mf.add(marketView);
      
      
      
   }
   public void settingButton(JButton jb) {
      jb.setBorderPainted(false);
      jb.setFocusPainted(false);
      jb.setContentAreaFilled(false);
   }
   
}