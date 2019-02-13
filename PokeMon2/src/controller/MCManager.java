package controller;

import java.util.Date;

import model.dao.ItemDao;
import model.dao.UserDao;
import model.vo.User;

//센터
//체력회복
public class MCManager {
	//마켓
	//구매수량체크->소유금액체크->구매가능여부 체크
	String resultNo = null;
	
	public void useMarket(String itemName, int itemAmount) {
		ItemDao id = new ItemDao(); 
		//->MarketView에서 쓸 것
		//최소수량, 최대수량
		/*User user = new User("이름",new Date(),100000);
		UserDao ud = new UserDao(user);
		UserManager um = new UserManager(user,ud);*/
		int check=0;//구매 하고 싶은 아이템 총량의 수량
		
		ItemManager im = new ItemManager();
		for(int i=0 ; i<id.getItemList().size() ; i++) {
			//아이템 고른 것을 비교하여 아이템리스트의 몇번째 인덱스에 있는 지 확인
			if(id.getItemList().get(i).getiName().equals(itemName)) {
				//구매 하고 싶은 아이템의 총 금액
				check=(id.getItemList().get(i).getiPrice())*itemAmount;
				//유저인벤에 있는 소지금액과 비교 후	
				//소지금액<check : 소지금액이 적어 구매가 불가능 합니다 출력 : MarketView에서 바로 출력함
				if(true/*um.getUserGold()<check*/) {
					//구매 불가
					resultNo="응 못사";
				}else {
					//구매 가능
					//중복 아이템이 있으면 수량을 증가시켜야
					System.out.println("아이템 : "+itemName+", 수량 : "+itemAmount);
					
					
				}
				//소지금액>check : 구매 가능 : 구매하는 아이템과 수량을 전달받아 유저 인벤에 저장
			}
		}
		
	}
	public String getResultNo() {
		return resultNo;
	}
	public void setResultNo(String resultNo) {
		this.resultNo = resultNo;
	}
	public void useCenter() {
		//회복 시켜주기
		
	}

}
