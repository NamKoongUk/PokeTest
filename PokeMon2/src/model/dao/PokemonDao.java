package model.dao;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import model.vo.Pokemon;

public class PokemonDao {
	private List<Pokemon> pList = new ArrayList<Pokemon>();
	private Image img = new ImageIcon(" ").getImage();

	public PokemonDao() {

		pList.add(new Pokemon("������",104,0,false,img,1));   //�̸�,���ϸ�ѹ�,�Ӽ�,��ȭ����,�̹���,���
		pList.add(new Pokemon("���̸�",4,1,false,img,2));
		pList.add(new Pokemon("���α�",7,2,true,img,1));
		pList.add(new Pokemon("��Ϻα�",8,2,false,img,0));
		
		
		
	}

	public List<Pokemon> getpList() {
		return pList;
	}

	public void setpList(List<Pokemon> pList) {
		this.pList = pList;
	}

}
