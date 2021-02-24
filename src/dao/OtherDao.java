package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class OtherDao {

	private static OtherDao instance;

	public static OtherDao getInstance() {
		if (instance == null)
			instance = new OtherDao();
		return instance;
	}

	private static JDBCUtil jdbc = JDBCUtil.getInstance();

	// 알바 Q&A 리스트 - 완
	public static List<Map<String, Object>> qnaAlbaList() {
		String sql = "SELECT QA_AL_NUM, QA_AL_TITLE, "
				+ "QA_AL_CONTENT,QA_AL_DATE, AL_MAN_CONTENT, "
				+ "AL_MAN_DATE, ALBA_ID, MAN_ID " + "FROM QAALBABOARD "
				+ "ORDER BY QA_AL_NUM DESC";

		return jdbc.selectList(sql);
	}

	// 알바 Q&A 글 조회 - 작성중

	// 알바 Q&A 게시글 등록 - 완
	public static int insertAlbaQna(Map<String, Object> param) {
		String sql = "INSERT INTO QAALBABOARD (QA_AL_NUM, QA_AL_TITLE, QA_AL_CONTENT, "
				+ "QA_AL_DATE, AL_MAN_CONTENT, AL_MAN_DATE, ALBA_ID, MAN_ID) "
				+ "VALUES (QAALBABOARDNO.NEXTVAL, ?, ?, ?, ?, ?,"
				+ " (SELECT DISTINCT ALBA_ID FROM QAALBABOARD WHERE ALBA_ID = ?), ?)";

		List<Object> p = new ArrayList<>();
		p.add(param.get("QA_AL_TITLE"));
		p.add(param.get("QA_AL_CONTENT"));
		p.add(param.get("QA_AL_DATE"));
		p.add(param.get("AL_MAN_CONTENT"));
		p.add(param.get("AL_MAN_DATE"));
		p.add(param.get("ALBA_ID"));
		p.add(param.get("MAN_ID"));
		return jdbc.update(sql, p);
	}

	// 기업 Q&A 리스트 - 완
	public static List<Map<String, Object>> qnaComList() {
		String sql = "SELECT QA_COM_NUM, QA_COM_TITLE, QA_COM_CONTENT, "
				+ "QA_COM_DATE, QA_COM_ID, MAN_ID, QA_COM_CONTENT, QA_COM_DATE "
				+ "FROM QACOMBOARD " + "ORDER BY QA_COM_NUM DESC";

		return jdbc.selectList(sql);
	}

	// 가이드 리스트 - 완
	public static List<Map<String, Object>> guideList() {
		String sql = "SELECT GUIDE_NO, GUIDE_TITLE, GUIDE_CONTENT, MAN_ID "
				+ "FROM GUIDE " + "ORDER BY GUIDE_NO DESC";

		return jdbc.selectList(sql);
	}

	// 공지 리스트 - 완
	public static List<Map<String, Object>> NoticeList() {
		String sql = "SELECT NOTICE_NO, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_DATE, MAN_ID "
				+ "FROM NOTICE " + "ORDER BY NOTICE_NO DESC";

		return jdbc.selectList(sql);
	}

	// 기업 Q&A 게시글 등록 - 완
	public static int insertComQna(Map<String, Object> param) {
		String sql = "INSERT INTO QACOMBOARD (QA_COM_NUM, QA_COM_TITLE, QA_COM_CONTENT, "
				+ "QA_COM_DATE, COM_MAN_CONTENT, COM_MAN_DATE, QA_COM_ID, MAN_ID) "
				+ "VALUES (QACOMBOARDNO.NEXTVAL, ?, ?, ?, ?, ?,"
				+ " (SELECT DISTINCT QA_COM_ID FROM QACOMBOARD WHERE QA_COM_ID = ?), ?)";

		List<Object> p = new ArrayList<>();
		p.add(param.get("QA_COM_TITLE"));
		p.add(param.get("QA_COM_CONTENT"));
		p.add(param.get("QA_COM_DATE"));
		p.add(param.get("AL_MAN_CONTENT"));
		p.add(param.get("AL_MAN_DATE"));
		p.add(param.get("QA_COM_ID"));
		p.add(param.get("MAN_ID"));
		return jdbc.update(sql, p);
	}

}
