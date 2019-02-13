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
		//���� ���޹޾� ���� ��ü ����
		user = new User(name,new Date(),0);
	}
	
	public void firstSelectPokemon(User user) { 
		//ù ���ϸ� �����ϴ� �޼ҵ�
	}
	
	public static int getUserGold() {
		//userDao�� �������մ� User��ü���� gold�� ���Ϲ���
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
		SimpleDateFormat sdf = new SimpleDateFormat("hh�� mm��");
		
		long diff = user.getuDate().getTime() - new Date().getTime();
		
		
		String time = sdf.format(diff);
		return time;
		
	}
	public static String viewCreateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E���� hh�� mm��");
		String time = sdf.format(user.getuDate());
		return time;
	}
}
