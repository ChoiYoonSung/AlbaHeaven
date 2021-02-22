package service;

import util.View;

public class MainService {
	private MainService(){}
	private static MainService instance;
	public static MainService getInstance(){
		if(instance == null){
			instance = new MainService();
		}
		return instance;
	}
	
	public void main(){
		new MainService().service();
	}
	
	ServiceCenter sc;

	private void service() {
		int view = View.MAIN;
		
		while(true){
			switch(view){
			case View.MAIN: view = serviceList(); break;
			case View.MYMENU: view = myMenu(); break;
			case View.RESUMELS: view = resumeLs(); break;
			case View.HIRELS: view = hireLs(); break;
			case View.QNAALBA: view = sc.qnaAlba(); break;
			case View.QNACOM: view = sc.qnaCom(); break;
			case View.GUIDE: view = sc.guide(); break;
			case View.NOTICE: view = sc.notice(); break;
			}
		}
	}
	

	private int serviceList(){

		System.out.println("1. 마이메뉴\t2. 이력서게시판\t3. 채용게시판");
		System.out.println("4. 개인Q&A\t5. 기업Q&A\t6. 공지사항");
		System.out.println("7. 이용가이드\t8. 로그아웃\t0. 종료");
		int input = 0;
		switch(input){
		case 1:	return View.MYMENU;
		case 2: return View.RESUMELS;
		case 3: return View.HIRELS;
		case 4: return View.QNAALBA;
		case 5: return View.QNACOM;
		case 6: return View.NOTICE;
		case 7: return View.GUIDE;
		case 0: 
			System.out.println("종료합니다.");
			System.exit(0);
		default :
			System.out.println("잘못 입력하였습니다.");
			break;
		}
		return View.MAIN;

	}

	private int myMenu() {
		
		return 0;
	}

	private int resumeLs() {
		return 0;
	}

	private int hireLs() {
		return 0;
	}
	
}
