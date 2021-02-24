package service;

import java.math.BigDecimal;

import util.ScanUtil;
import util.View;

public class MyMenu {
	private static MyMenu instance;
	public static MyMenu getInstance(){
		if(instance == null){
			instance = new MyMenu();
		}
		return instance;
	}	

	private Albadata albaData = Albadata.getInstance();
	private Comdata comData = Comdata.getInstance();
	
	public int myMenu() {//권한별 메뉴이동
	try{
		if(((BigDecimal) MainService.login.get("AUTH")).intValue()==1){
			albaMenu();
		}
		
	}catch (NullPointerException a) {
		try{
			if(((BigDecimal) MainService.login.get("AUTH")).intValue()==2){
				comMenu();
			}
			
		}catch (NullPointerException b) {
			try{
				if(((BigDecimal) MainService.login.get("AUTH")).intValue()==3){
					System.out.println("관리자는 확인할게 없습니다.");
				}
			
			}catch (NullPointerException c) {
			}
		}
	}
	return View.MAIN;
	}

	

	private void albaMenu() {//알바메뉴
		System.out.println("1.내 정보 보기\t 2.내가 쓴 이력서 보기\t 3.뒤로가기");
		int input = ScanUtil.nextInt();
		switch (input) {
		case 1: albadata();break;
		case 2:break;
		case 3:break;
		default:
			System.out.println("다시 입력해주세요");
			break;
	}
	
}
	
	
	public int albadata() {
		String[] keyname = {"ID","PASSWORD","이름","생일","성별","메일","연락처","주소","권한"};
		String[] key = {"ALBA_ID","ALBA_PASSWORD","ALBA_NAME","ALBA_BIR","ALBA_SEXDSTN","ALBA_MAIL","ALBA_TEL","ALBA_ADD","AUTH"};
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		 for (int i = 0; i < keyname.length; i++) {
			 System.out.println(keyname[i]+" : "+MainService.login.get(key[i]));
		}
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		albaData.Modify();
		return 0;
	}
	
	
	
	
	
	
private void comMenu() {
	System.out.println("1.내 기업 정보 보기\t 2.내가 쓴 공고 보기\t 3.뒤로가기");
	int input = ScanUtil.nextInt();
	switch (input) {
	case 1: comdata();break;
	case 2:break;
	case 3:break;
	default:
		System.out.println("다시 입력해주세요");
		break;
}

	}



private int comdata() {
	String[] keyname = {"ID","PASSWORD","사업자등록번호","회사명","이메일","주소","연락처","대표자명","권한"};
	String[] key = {"COM_ID","COM_PASSWORD","COM_REGNO","COM_NAME","COM_MAIL","COM_ADD","COM_TEL","COM_CEO","AUTH"};
	System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
	 for (int i = 0; i < keyname.length; i++) {
		 System.out.println(keyname[i]+" : "+MainService.login.get(key[i]));
	}
	System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
	comData.Modify();
	return 0;
	
}
}