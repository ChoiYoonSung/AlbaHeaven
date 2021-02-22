package dao;

import util.JDBCUtil;

public class MainDao {
	private MainDao() {}// 생성자

	private static MainDao instance;// 변수생성

	public static MainDao getInstance() {
		if (instance == null) {
			instance = new MainDao();
		}
		return instance;
	}
	
	private JDBCUtil jdbc = JDBCUtil.getInstance();
		
	public int mymenu(){
		String sql = " ";
		return 0;
	}
	
	public int resumeList(){
		String sql = " ";
		return 0;
	}
	
	public int hireList(){
		String sql = "";
		return 0;
	}
	
}
