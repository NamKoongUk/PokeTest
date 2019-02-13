package model.vo;

public class Skill {

	private int sType;		//스킬타입
	private int sLevel;		//스킬을 습득할 수 있는 레벨
	private int sAtt;		//스킬 공격력
	
	public Skill() {}
	
	public Skill(int sType, int sLevel, int sAtt) {
		this.sType = sType;
		this.sLevel = sLevel;
		this.sAtt = sAtt;
	}

	public int getsType() {
		return sType;
	}

	public int getsLevel() {
		return sLevel;
	}

	public int getsAtt() {
		return sAtt;
	}

	public void setsType(int sType) {
		this.sType = sType;
	}

	public void setsLevel(int sLevel) {
		this.sLevel = sLevel;
	}

	public void setsAtt(int sAtt) {
		this.sAtt = sAtt;
	}
	
}
