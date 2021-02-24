package service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.OtherDao;
import util.ScanUtil;
import util.View;

public class ServiceCenter {

	private static final List<Map<String, Object>> QAALBABOARD = null;
	ServiceCenter(){}
	private static ServiceCenter instance;
	public static ServiceCenter getInstance(){
		if(instance == null){
			instance = new ServiceCenter();
		}
		return instance;
	}
	
	private OtherDao otherDao = OtherDao.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date now = new Date();
	
	//알바Q&A 게시글 목록 - 완
	public int qnaAlbaList() {
		List<Map<String, Object>> qnaAlbaList = OtherDao.qnaAlbaList();
		System.out.println("================== 개인회원 Q & A =================");
		System.out.println("번호\t제목\t작성자\t작성일");
		System.out.println("-------------------------------------------------");
		for (Map<String, Object> QAALBABOARD : qnaAlbaList) {
			System.out.println(QAALBABOARD.get("QA_AL_NUM") + "\t"
					+ QAALBABOARD.get("QA_AL_TITLE") + "\t"
					+ QAALBABOARD.get("ALBA_ID") + "\t"
					+ sdf.format(QAALBABOARD.get("QA_AL_DATE")));
		}
		System.out.println("=================================================");
		System.out.println("1.조회\t2.등록\t0.메인화면");
		System.out.print("입력>");

		int input = ScanUtil.nextInt();
		switch (input) {
		case 1:
			//selectAlbaQna();
			break;
		case 2:
			insertAlbaQna();
			break;
		case 0:
			break;
		default:
			System.out.println("번호를 잘못 입력하셨습니다.");
			break;
		}
		return View.MAIN;
	} 

	//알바Q&A 게시판 권한  - 완
		public int qnaAlba() {
			try{
				if (((BigDecimal)MainService.login.get("AUTH")).intValue() == 1) {
					qnaAlbaList();
				} 
			} catch (NullPointerException e) {
				try {if (((BigDecimal)MainService.login.get("AUTH")).intValue() == 3) {
					qnaAlbaList();
					}
				} catch (NullPointerException e1) {System.out.println("권한이 없습니다.");
				}
			}
			return View.MAIN;
		}

		//알바Q&A 게시글 조회 - 작성중
	

	//알바Q&A 게시글 등록 - 완
	private int insertAlbaQna() {
		System.out.print("제목>");
		String qnaTitle = ScanUtil.nextLine();
		System.out.print("내용>");
		String qnaContent = ScanUtil.nextLine();
		
		Map<String, Object> param = new HashMap<>();
		param.put("QA_AL_TITLE", qnaTitle);
		param.put("QA_AL_CONTENT", qnaContent);
		param.put("QA_AL_DATE", sdf.format(now));
		param.put("AL_MAN_CONTENT", null);
		param.put("AL_MAN_DATE", null);
		param.put("ALBA_ID", MainService.login.get("ALBA_ID"));
		param.put("MAN_ID", null);

		int result = OtherDao.insertAlbaQna(param);
		
		if(0<result){
			System.out.println("등록하였습니다.");
		}else {
			System.out.println("등록에 실패하였습니다.");
		}
		return qnaAlbaList();
	}


	//--------------------------------------------------------------------------
	
	//기업Q&A 게시판 목록 - 완
	public int qnaComList() {
		List<Map<String, Object>> qnaComList = OtherDao.qnaComList();
			System.out.println("================== 기업회원 Q & A =================");
			System.out.println("번호\t제목\t작성자\t작성일");
			System.out.println("-------------------------------------------------");
			for (Map<String, Object> QACOMBOARD : qnaComList) {
				System.out.println(QACOMBOARD.get("QA_COM_NUM") + "\t"
						+ QACOMBOARD.get("QA_COM_TITLE") + "\t"
						+ QACOMBOARD.get("QA_COM_ID") + "\t"
						+ sdf.format(QACOMBOARD.get("QA_COM_DATE")));
			}
			System.out.println("=================================================");
			System.out.println("1.조회\t2.등록\t0.메인화면");
			System.out.print("입력>");

			int input = ScanUtil.nextInt();
			switch (input) {
			case 1:
				break;
			case 2:
				insertComQna();
				break;
			case 0:
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		return View.MAIN;
	}

	//기업Q&A 게시판 권한  - 완
		public int qnaCom() {
			try{
				if (((BigDecimal)MainService.login.get("AUTH")).intValue() == 2) {
					qnaComList();
				} 
			} catch (NullPointerException e) {
				try {if (((BigDecimal)MainService.login.get("AUTH")).intValue() == 3) {
					qnaComList();
					}
				} catch (NullPointerException e1) {System.out.println("권한이 없습니다.");
				}
			}
			return View.MAIN;
		}
		
		//기업Q&A 게시글 작성 - 완
		private int insertComQna() {
			System.out.print("제목>");
			String qnaTitle = ScanUtil.nextLine();
			System.out.print("내용>");
			String qnaContent = ScanUtil.nextLine();
			
			Map<String, Object> param = new HashMap<>();
			param.put("QA_COM_TITLE", qnaTitle);
			param.put("QA_COM_CONTENT", qnaContent);
			param.put("QA_COM_DATE", sdf.format(now));
			param.put("MAN_CONTENT", null);
			param.put("CON_MAN_DATE", null);
			param.put("QA_COM_ID", MainService.login.get("COM_ID"));
			param.put("MAN_ID", null);

			int result = OtherDao.insertComQna(param);
			
			if(0<result){
				System.out.println("등록하였습니다.");
			}else {
				System.out.println("등록에 실패하였습니다.");
			}
			return qnaComList();
		}
	
	//--------------------------------------------------------------------------
		
	//가이드 게시판 목록  - 완
	public int guide() {	
		List<Map<String, Object>> guideList = OtherDao.guideList();		
		System.out.println("==================== 이용가이드 ===================");
		System.out.println("번호\t제목\t작성자");
		System.out.println("-------------------------------------------------");
		for (Map<String, Object> GUIDE : guideList) {
			System.out.println(GUIDE.get("GUIDE_NO") + "\t"
					+ GUIDE.get("GUID_TITLE") + "\t"
					+ GUIDE.get("MAN_ID"));
		}
		System.out.println("=================================================");
		System.out.println("1.조회\t2.등록\t0.로그아웃");
		System.out.print("입력>");

		int input = ScanUtil.nextInt();
		switch (input) {
		case 1:
			break;
		case 2:
			break;
		case 0:
			break;
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;
		}
		return View.MAIN;
	}	
	
	// 공지 게시판 목록  - 완
	public int notice() {
		List<Map<String, Object>> noticeList = OtherDao.NoticeList();		
		System.out.println("======================= 공지 ======================");
		System.out.println("번호\t제목\t작성자\t작성일");
		System.out.println("-------------------------------------------------");
		for (Map<String, Object> NOTICE : noticeList) {
			System.out.println(NOTICE.get("NOTICE_NO") + "\t"
					+ NOTICE.get("NOTICE_TITLE") + "\t"
					+ NOTICE.get("MAN_ID") + "\t"
					+ sdf.format(NOTICE.get("NOTICE_DATE")));
		}
		System.out.println("=================================================");
		System.out.println("1.조회\t2.등록\t0.로그아웃");
		System.out.print("입력>");

		int input = ScanUtil.nextInt();
		switch (input) {
		case 1:
			break;
		case 2:
			break;
		case 0:
			break;
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;
		}
		return View.MAIN;
	}


}
