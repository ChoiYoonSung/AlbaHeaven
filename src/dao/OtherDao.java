package dao;

import util.JDBCUtil;

public class OtherDao {
	private OtherDao(){}
	private static OtherDao instance;
	public static OtherDao getInstance(){
		if(instance == null)
			instance = new OtherDao();
		return instance;
	}
	
	private JDBCUtil jdbc = JDBCUtil.getInstance();
	
}
