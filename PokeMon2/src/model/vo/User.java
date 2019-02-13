package model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	
	private String uName;
	private Date uDate;
	private int uGold;
	private List<Pokemon> up_list = new ArrayList<Pokemon>(4);
	private List<Item> ui_list = new ArrayList<Item>();
	private List<Pokemon> tp_list = new ArrayList<Pokemon>();
	
	public List<Pokemon> getUp_list() {
		return up_list;
	}

	public void setUp_list(List<Pokemon> up_list) {
		this.up_list = up_list;
	}

	public List<Item> getUi_list() {
		return ui_list;
	}

	public void setUi_list(List<Item> ui_list) {
		this.ui_list = ui_list;
	}

	public List<Pokemon> getTp_list() {
		return tp_list;
	}

	public void setTp_list(List<Pokemon> tp_list) {
		this.tp_list = tp_list;
	}

	public User() {}
	
	public User(String uName, Date uDate, int uGold) {
		this.uName = uName;
		this.uDate = uDate;
		this.uGold = uGold;
	}

	public String getuName() {
		return uName;
	}

	public Date getuDate() {
		return uDate;
	}

	public int getuGold() {
		return uGold;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public void setuDate(Date uDate) {
		this.uDate = uDate;
	}

	public void setuGold(int uGold) {
		this.uGold = uGold;
	}
	
	

}
