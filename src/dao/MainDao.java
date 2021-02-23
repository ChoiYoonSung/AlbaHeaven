package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	//이력서 게시판 전체 출력
	public List<Map<String, Object>> resumeBoardList() {
		
		String sql = " SELECT A.RL_NO, A.RL_TITLE, B.ALBA_NAME, A.RL_DATE"
				   + " FROM RESUME_LIST A, ALBA B, RESUME C"
				   + " WHERE A.RESUME_NO = C.RESUME_NO AND B.ALBA_ID = C.ALBA_ID";
		return jdbc.selectList(sql);
	}
	
	//이력서게시판 선택 출력 +완
	public List<Map<String, Object>> selectResumeLs(Map<String, Object> param){
				
		String sql = " SELECT "
				   + "     B.RL_NO, B.RL_TITLE, B.RL_DATE,"
				   + "     A.ALBA_ID, A.ALBA_NAME, A.ALBA_BIR, A.ALBA_SEXDSTN, A.ALBA_TEL, A.ALBA_ADD, A.ALBA_MAIL,"
				   + "     C.RESUME_EDU1, C.RESUME_EDU2, C.RESUME_CR1, C.RESUME_INTRCN, C.RESUME_ADD, C.RESUME_SECTOR,"
				   + "     C.RESUME_TYPE, C.RESUME_DATE, C.RESUME_OPENTERM, C.RESUME_DAY"
				   + " FROM ALBA A, RESUME_LIST B, RESUME C  "
				   + " WHERE B.RESUME_NO = C.RESUME_NO"
				   + " AND   A.ALBA_ID = C.ALBA_ID"
				   + " AND   A.ALBA_ID = ?";
		
		List<Object> p = new ArrayList<>();
		p.add(param.get("ALBA_ID"));
		
		return jdbc.selectList(sql, p);
	}
	public List<Map<String,Object>> careerLs(Map<String, Object>param){
		String sql = " SELECT A.CR_COM_NAME, A.CR_COM_TERM, A.CR_COM_TASK"
				   + " FROM CAREER A, RESUME B"
				   + " WHERE A.RESUME_NO = B.RESUME_NO"
				   + " AND  B.ALBA_ID = ?";
		
		List<Object> p = new ArrayList<>();
		p.add(param.get("ALBA_ID"));
		
		return jdbc.selectList(sql, p);
	}
	
	
	//이력서게시판 선택 수정 +완
	public int updateResumeLs(Map<String, Object> param) {
		String sql = " UPDATE RESUME_LIST"
				   + " SET RL_TITLE = ?, RL_DATE = SYSDATE"
				   + " WHERE RESUME_NO ="
				   + " (SELECT RESUME_NO"
				   + "  FROM RESUME"
				   + "  WHERE ALBA_ID = ?)";
		
		List<Object> p = new ArrayList<>();
		p.add(param.get("RL_TITLE"));
		p.add(param.get("ALBA_ID"));
		return jdbc.update(sql,p);
	}

	//이력서게시판 선택 삭제 +완
	public int deleteResumeLs(Map<String, Object> param){
		String sql = " DELETE FROM RESUME_LIST"
				   + " WHERE RESUME_NO = "
				   + "  (SELECT RESUME_NO"
				   + "  FROM RESUME"
				   + "  WHERE ALBA_ID = ?)";
		
		List<Object> p = new ArrayList<>();
		p.add(param.get("ALBA_ID"));
		return jdbc.update(sql,p);
	}
	
	//이력서리스트 입력  +완
	public int insertResumeLs(Map<String, Object> param) {
		
		String sql = " INSERT INTO RESUME_LIST"
				   + " VALUES("
				   + " (SELECT RESUME_NO"
				   + " FROM RESUME"
				   + " WHERE ALBA_ID = ?)"
				   + ", ?, SYSDATE, SEQRL.NEXTVAL)";
		
		List<Object> p = new ArrayList<>();
		p.add(param.get("ALBA_ID"));
		p.add(param.get("RL_TITLE"));
		return jdbc.update(sql,p);
		
	}
	
	//채용공고 선택 출력
	public Map<String, Object> selectHireLs(Map<String, Object> param){
		
		String sql = " SELECT *"
				   + " FROM HIRE"
				   + " WHERE HIRE.COM_ID = ?";
		List<Object> p = new ArrayList<>();
		p.add(param.get("COM_ID"));
		return jdbc.selectOne(sql);
		
	}
	//채용공고 입력
	public int insertHireLs() {
		return 0;
	}
	
	
}
