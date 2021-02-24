package service;

import java.util.HashMap;
import java.util.Map;


import controller.Controller;
import dao.AlbaMenuDao;
import util.ScanUtil;
import util.View;

public class Albadata {
	private Albadata(){}//생성자
	private static Albadata instance;//변수생성
	public static Albadata getInstance(){
		if(instance == null){
			instance = new Albadata();
		}
		return instance;
}
	private AlbaMenuDao albaMenuDao = AlbaMenuDao.getInstance();
	
	public void Modify() {
		System.out.println("1.수정\t2.탈퇴\t3.뒤로가기\t0.로그아웃");
		System.out.print("입력>");
		int input = ScanUtil.nextInt();
		switch (input) {
		case 1: update(); Modify();
		case 2: delete();
		case 3:break;
		case 0:break;
		default:
			System.out.println("다시 입력해주세요");
			break;
	}
		
	}
	private int delete() {
		Map<String, Object> albadata = new HashMap<>();
		System.out.println("정말로 삭제하시겠습니까");
		System.out.print("1.YES\t2.NO");
		int input = ScanUtil.nextInt();
		switch (input) {
		case 1: 
			int result = albaMenuDao.delete();
			if(0<result){
				System.out.println("삭제 성공");
				System.out.println("처음 화면으로 돌아갑니다.");
				return View.HOME;
			}else {
				System.out.println("삭제 실패");
				
			}
		case 2: Modify();
	
		default:
			System.out.println("다시 입력해주세요");
			break;
	}
		return View.MAIN;
	}
	

	
	private void update() {
		Map<String, Object> albadata = new HashMap<>();
		String[] keyname = {"ID","PASSWORD","이름","생일","성별","메일","연락처","주소","권한"};
		String[] key = {"ALBA_ID","ALBA_PASSWORD","ALBA_NAME","ALBA_BIR","ALBA_SEXDSTN","ALBA_MAIL","ALBA_TEL","ALBA_ADD","AUTH"};
				albadata.put(key[0],MainService.login.get(key[0]));
			for (int i = 1; i < keyname.length-1; i++) {
			System.out.println("1."+keyname[i]+"을 변경하시겠습니까?\t 2.다음");
			int input = ScanUtil.nextInt();
			switch (input) {
			case 1: 
				System.out.println(MainService.login.get(key[i])+"\t<-를 대신할 값을 입력하세요>");
				String input2 = ScanUtil.nextLine();
				albadata.put(key[i],input2);
				break;
			case 2:
				albadata.put(key[i], MainService.login.get(key[i]));
				break;
			default:
				System.out.println("다시 입력해주세요");
			break;
		}
	}
			albadata.put(key[8],MainService.login.get(key[8]));
			int result = albaMenuDao.update(albadata);
			if(0<result){//[16.회원가입 여부 확인하고 HOME으로 리턴]
				System.out.println("수정 성공");
			}else {
				System.out.println("수정 실패");
			}
		}
	}


