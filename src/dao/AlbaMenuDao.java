package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import service.MainService;
import util.JDBCUtil;

public class AlbaMenuDao {
	private AlbaMenuDao(){}
	private static AlbaMenuDao instance;
	public static AlbaMenuDao getInstance(){
		if(instance == null){
			instance = new AlbaMenuDao();
		}
		return instance;
	}
	
	private JDBCUtil jdbc = JDBCUtil.getInstance();

	
	public int update(Map<String, Object> albadata) {
		String sql = " update ALBA set ALBA_ID=?,ALBA_PASSWORD=?,ALBA_NAME=?,ALBA_BIR=?,ALBA_SEXDSTN=?,ALBA_MAIL=?,ALBA_TEL=?,ALBA_ADD=?,AUTH=? where ALBA_ID = ? ";
		
		List<Object> p = new ArrayList<>();
		String[] key = {"ALBA_ID","ALBA_PASSWORD","ALBA_NAME","ALBA_BIR","ALBA_SEXDSTN","ALBA_MAIL","ALBA_TEL","ALBA_ADD","AUTH"};
		for (int i = 0; i < key.length; i++) {
			p.add(albadata.get(key[i]));
		}
			p.add(MainService.login.get("ALBA_ID"));
			
		return jdbc.update(sql, p);
	}	
	
	
	public int delete(){
		String sql = " delete from alba where ALBA_ID=? ";
		List<Object> param = new ArrayList<>();
		param.add(MainService.login.get("ALBA_ID"));
		
		return jdbc.update(sql, param);
		
	}

	}
	
	

