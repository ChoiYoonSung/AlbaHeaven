package service;

import java.util.Map;

import util.ScanUtil;
import util.View;

public class MainService {
	public static Map<String, Object> loginCom;
	public static Map<String, Object> loginAlba;

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
	
	ServiceCenter sc;
	MyMenu mm;
	
	public int service() {
		int view = View.MAIN;
		
		while(true){
			switch(view){
			case View.MAIN: view = serviceList(); break;
			case View.MYMENU: view = mm.myMenu(); break;
			case View.RESUMELS: view = resumeLs(); break;
			case View.HIRELS: view = hireLs(); break;
			case View.QNAALBA: view = sc.qnaAlba(); break;
			case View.QNACOM: view = sc.qnaCom(); break;
			case View.GUIDE: view = sc.guide(); break;
			case View.NOTICE: view = sc.notice(); break;
			}
		}
	}
	

	public int serviceList(){

		System.out.println("1. 마이메뉴\t2. 이력서게시판\t3. 채용게시판");
		System.out.println("4. 개인 Q&A\t5. 기업 Q&A\t6. 이용가이드");
		System.out.println("7. 공지사항\t8. 로그아웃\t0. 종료");
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
			return View.MAIN;
		case 0: 
			System.out.println("종료합니다.");
			System.exit(0);
		default :
			System.out.println("잘못 입력하였습니다.");
			break;
		}
		return View.MAIN;
	}

	private int resumeLs() {
		System.out.println();
		return 0;
	}

	private int hireLs() {
		System.out.println();
		return 0;
	}
	
}
