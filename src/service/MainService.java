package service;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Controller;
import dao.MainDao;
import util.ScanUtil;
import util.View;

public class MainService {
	public static Map<String, Object> loginAlba;
	public static Map<String, Object> loginCom;
	public static Map<String, Object> loginMan;

	private MainService(){}
	private static MainService instance;
	public static MainService getInstance(){
		if(instance == null){
			instance = new MainService();
		}
		return instance;
	}	
	public static void main(){
		new MainService().service();
	}
	
	private MyMenu mymenu = MyMenu.getInstance();
	private ServiceCenter sc = ServiceCenter.getInstance();
	public int service() {
		int view = View.MAIN;
		
		while(true){
			switch(view){
			case View.MAIN: view = serviceList(); break;
			case View.MYMENU: view = mymenu.myMenu(); break;
			case View.RESUMELS: view = resumeLs(); break;
			case View.HIRELS: view = hireLs(); break;
			case View.QNAALBA: view = sc.qnaAlba(); break;
			case View.QNACOM: view = sc.qnaCom(); break;
			case View.GUIDE: view = sc.guide(); break;
			case View.NOTICE: view = sc.notice(); break;
			case View.HOME: view = new Controller().start(); break;
			}
		}
	}
	

	public int serviceList(){		
		System.out.println(loginAlba);
		System.out.println("1. 마이메뉴\t2. 이력서게시판\t3. 채용게시판");
		System.out.println("4. 개인 Q&A\t5. 기업 Q&A\t6. 이용가이드");
		System.out.println("7. 공지사항\t8. 로그아웃\t0. 종료");
		System.out.print(">");
		int input = ScanUtil.nextInt();
		
		switch(input){
		case 1:	return View.MYMENU;
		case 2: return View.RESUMELS;
		case 3: return View.HIRELS;
		case 4: return View.QNAALBA;
		case 5: return View.QNACOM;
		case 6: return View.GUIDE;
		case 7: return View.NOTICE;
		case 8: 
			loginAlba = null;
			loginCom = null;
			loginMan = null;
			return View.HOME;
		case 0: 
			System.out.println("종료합니다.");
			System.exit(0);
		default :
			System.out.println("잘못 입력하였습니다.");
			break;
		}
		return View.MAIN;
	}

	//21-02-23 수정부분
	//----------------------이력서게시판----------------------------
	private MainDao mainDao = MainDao.getInstance();
	
	private int resumeLs() {
		resumels();
		System.out.println("1.내 이력서 보기\t2.이력서 올리기\t0.뒤로가기");
		System.out.print(">");
		int input = ScanUtil.nextInt();
		
		switch(input){
		case 1: myresumels(); break;
		case 2: inresumels(); break;
		case 0: return View.MAIN;
		default:
			System.out.println("잘못 입력하였습니다.");
			break;
		}
		return resumeLs();
	}
	
	//이력서 게시판 전체 출력
	private void resumels() {
		List<Map<String, Object>> resumeBoardList = mainDao.resumeBoardList();
		
		System.out.println("======================================");
		System.out.println("번호\t제목\t\t작성자\t작성일");
		System.out.println("--------------------------------------");
		/*if(resumeBoardList.get(2).size() > 15){
			String title = 
		}*/
		for(Map<String, Object> board : resumeBoardList){
			System.out.println(board.get("RL_NO") + "\t" 
							 + ((String) board.get("RL_TITLE")).substring(1,6) + "...\t"
							 + board.get("ALBA_NAME") + "\t"
							 + board.get("RL_DATE"));
		}
		System.out.println("======================================");
	}
	
	
	//이력서 게시판 개인 내용 확인
	private void myresumels() {
		
			System.out.println("내 이력서 게시판 보기");
			
			Map<String, Object> param = new HashMap<>();
			param.put("ALBA_ID", loginAlba.get("ALBA_ID"));
			
			List<Map<String, Object>> resumeList = mainDao.selectResumeLs(param);
			List<Map<String, Object>> careerList = mainDao.careerLs(param);
			
			System.out.println("이력서리스트\t: " + resumeList.get(0).get("RL_NO"));
			System.out.println("제목\t\t: " + resumeList.get(0).get("RL_TITLE"));
			System.out.println("작성일자\t\t: " + resumeList.get(0).get("RL_DATE"));
			
			System.out.println("아이디\t\t: " + resumeList.get(0).get("ALBA_ID"));
			System.out.println("이름\t\t: " + resumeList.get(0).get("ALBA_NAME"));
			System.out.println("생년월일\t\t: " + resumeList.get(0).get("ALBA_BIR"));
			System.out.println("성별\t\t: " + resumeList.get(0).get("ALBA_SEXDSTN"));
			System.out.println("전화번호\t\t: " + resumeList.get(0).get("ALBA_TEL"));
			System.out.println("이메일\t\t: " + resumeList.get(0).get("ALBA_MAIL"));
			System.out.println("주소\t\t: " + resumeList.get(0).get("ALBA_ADD"));
			
			System.out.println("학력사항\t\t: " + resumeList.get(0).get("RESUME_EDU1"));
			System.out.println("학력사항2\t\t: " + resumeList.get(0).get("RESUME_EDU2"));
			System.out.println("경력사항\t\t: " + resumeList.get(0).get("RESUME_CR1"));
			System.out.println("자기소개서\t\t: " + resumeList.get(0).get("RESUME_INTRCN"));
			System.out.println("희망근무지\t\t: " + resumeList.get(0).get("RESUME_ADD"));
			System.out.println("희망업종\t\t: " + resumeList.get(0).get("RESUME_SECTOR"));
			System.out.println("희망형태\t\t: " + resumeList.get(0).get("RESUME_TYPE"));
			System.out.println("희망근무기간\t: " + resumeList.get(0).get("RESUME_DATE"));
			System.out.println("이력서공개기간\t: " + resumeList.get(0).get("RESUME_OPENTERM"));
			System.out.println("희망근무요일\t: " + resumeList.get(0).get("RESUME_DAY"));
			
			for (int i = 0; i < careerList.size(); i++) {
				System.out.println("경력회사명\t\t: " + careerList.get(i).get("CR_COM_NAME"));
				System.out.println("경력근무기간\t: " + careerList.get(i).get("CR_COM_TERM"));
				System.out.println("경력담당업무\t: " + careerList.get(i).get("CR_COM_TASK"));
			}
			
			
			
			
//		if (((BigDecimal)(loginAlba.get("AUTH"))).intValue() ==1) {
			System.out.println("1.수정\t2.삭제\t0.뒤로가기");
			System.out.print(">");
			int input = ScanUtil.nextInt();
			switch (input) {
			case 1:
				updateresumels();
				break;// 수정
			case 2:
				deleteresumels();
				break;// 삭제
			case 0:
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				myresumels();
				break;
			}
		}

//	}
	
	private void updateresumels() {
		System.out.println("수정할 제목");
		String title = ScanUtil.nextLine();
		
		Map<String, Object> param = new HashMap<>();
		param.put("ALBA_ID", loginAlba.get("ALBA_ID"));
		param.put("RL_TITLE", title);
		
		int result = mainDao.updateResumeLs(param);
		if(0<result){
			System.out.println("이력서게시판에 수정 되었습니다.");
		}else {
			System.out.println("이력서게시판에 수정을 실패하였습니다.");
		}
	}
	
	private void deleteresumels() {
		System.out.println("이력서를 게시판에서 삭제하시겠습니까? (Y/N)");
		String input = ScanUtil.nextLine();
		
		switch(input){
		case "Y":
		case "y":
			Map<String, Object> param = new HashMap<>();
			param.put("ALBA_ID",loginAlba.get("ALBA_ID"));
			
			int result = mainDao.deleteResumeLs(param);
			if(0<result){
				System.out.println("삭제를 완료하였습니다.");				
			}else System.out.println("삭제를 실패하였습니다.");
			break;
		case "N":
		case "n":
			System.out.println("삭제를 취소하였습니다.");
			myresumels();
			break;
		default:
			System.out.println("잘못된 입력입니다.");
			myresumels();
			break;
		}
	}
	
	//이력서 게시판에 올리기
	private void inresumels() {
		System.out.println("이력서 올리기");
		System.out.println("이력서 제목");
		String title = ScanUtil.nextLine();
		
		
		Map<String, Object> param = new HashMap<>();
		param.put("ALBA_ID", loginAlba.get("ALBA_ID"));
		param.put("RL_TITLE", title);
		
		int result = mainDao.insertResumeLs(param);
		
		if(0<result){
			System.out.println("이력서게시판에 업로드 되었습니다.");
		}else {
			System.out.println("이력서게시판에 업로드를 실패하였습니다.");
		}
	}
	
	//----------------------채용공고게시판----------------------------
	private int hireLs() {
		hiresls();
		System.out.println("1.내 채용공고 보기\t2.채용공고 올리기\t0.뒤로가기");
		System.out.print(">");
		int input = ScanUtil.nextInt();

		switch(input){
		case 1: myhirels(); break;
		case 2: inhirels(); break;
		case 0: return View.MAIN;
		default:
			System.out.println("잘못입력하였습니다");
			break;
		}
		return hireLs();
	}
	
	//채용공고 간략 게시판 출력
	private void hiresls() {
		// TODO Auto-generated method stub
		
	}
	
	//회원 채용공고 보기/수정/삭제
	private void myhirels() {
		System.out.println("내 채용공고 보기");
		
		
		
	}

	//회원 채용공고 게시판 올리기
	private void inhirels() {
		System.out.println("채용공고 올리기");
		
		
		
	}
	
}
