package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import service.MainService;
import util.JDBCUtil;

public class ComMenuDao {
	private ComMenuDao(){}
	private static ComMenuDao instance;
	public static ComMenuDao getInstance(){
		if(instance == null){
			instance = new ComMenuDao();
		}
		return instance;
	}
	private JDBCUtil jdbc = JDBCUtil.getInstance();
	
	
	public int update(Map<String, Object> comdata) {
		String sql = " update COMPANY set COM_ID=?,COM_PASSWORD=?,COM_REGNO=?,COM_NAME=?,COM_MAIL=?,COM_ADD=?,COM_TEL=?,COM_CEO=?,AUTH=? where COM_ID = ? ";
		
		List<Object> p = new ArrayList<>();
		String[] key = {"COM_ID","COM_PASSWORD","COM_REGNO","COM_NAME","COM_MAIL","COM_ADD","COM_TEL","COM_CEO","AUTH"};
		for (int i = 0; i < key.length; i++) {
			p.add(comdata.get(key[i]));
		}
			p.add(MainService.login.get("COM_ID"));
			
		return jdbc.update(sql, p);
	}
	
	public int delete() {
		String sql = " delete from company where COM_ID=? ";
		List<Object> param = new ArrayList<>();
		param.add(MainService.login.get("COM_ID"));
		
		return jdbc.update(sql, param);
		
	}
}
