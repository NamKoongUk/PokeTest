package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.vo.User;

public class UserManager {
	private static User user;
	
	public UserManager(User user) {
		this.user = user;
	}
	
	public void userItemSet() {
		
	}
	public static void createUser(String name) {
		//값을 전달받아 유저 객체 생성
		user = new User(name,new Date(),0);
	}
	
	public void firstSelectPokemon(User user) { 
		//첫 포켓몬 선택하는 메소드
	}
	
	public static int getUserGold() {
		//userDao가 가지고잇는 User객체값의 gold를 리턴받음
		System.out.println(user.getuGold());
		return user.getuGold();
	}
	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		UserManager.user = user;
	}

	public static void setUserGold(int gold) {
		user.setuGold(gold);
	}
	public static String viewUserName() {
		return user.getuName();
	}
	public static String viewUserTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh시 mm분");
		
		long diff = user.getuDate().getTime() - new Date().getTime();
		
		
		String time = sdf.format(diff);
		return time;
		
	}
	public static String viewCreateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E요일 hh시 mm분");
		String time = sdf.format(user.getuDate());
		return time;
	}
}
