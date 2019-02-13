package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.vo.Skill;

public class SkillDao {

	private List<Skill> ps_list;
	
	public SkillDao() {
		ps_list.add(new Skill());
		ps_list.add(new Skill());
		ps_list.add(new Skill());
		ps_list.add(new Skill());
	}
	
}
