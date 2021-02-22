package service;

import java.util.Map;

import controller.Controller;
import dao.UserDao;
import util.ScanUtil;
import util.View;

public class LoginService {
	private LoginService(){}//생성자
	private static LoginService instance;//변수생성
	public static LoginService getInstance(){
		if(instance == null){
			instance = new LoginService();
		}
		return instance;
}
	private UserDao userDao = UserDao.getInstance();
	
	public int login() {
		System.out.println("로그인 하시겠습니까?");
		System.out.println("1.개인회원 \t 2.기업회원 \t 3. 관리자");
		System.out.println("번호를 입력해주세요>");
		int input =ScanUtil.nextInt();
		
		switch(input){
		case 1: loginalba(); 
			break;
		case 2: logincompany();
			break;
		case 3: loginmanager();
			break;
		default:
			System.out.println("다시 입력해주세요");
			break;
		}
		return View.MAIN;
	}
	private void loginalba() {
		System.out.print("아이디>");
		String albaId = ScanUtil.nextLine();
		System.out.print("비밀번호>");
		String password = ScanUtil.nextLine();
		
		Map<String, Object> user = userDao.selectUser(albaId, password);//[18.selectUser메서드 만들어주고]
		
		if(user == null){
			System.out.println("아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
		}else{
			System.out.println("로그인 성공");
			Controller.loginAlba = user;//[20.loginUser변수 만들고  BOARD_LIST로 리턴]
			
		}
		
	}

	public void logincompany () {//[17.LOGIN도 JOIN과 동일하게]
		System.out.print("아이디>");
		String comId = ScanUtil.nextLine();
		System.out.print("비밀번호>");
		String password = ScanUtil.nextLine();
		
		Map<String, Object> user = userDao.selectCom(comId, password);//[18.selectUser메서드 만들어주고]
		
		if(user == null){
			System.out.println("아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
		}else{
			System.out.println("로그인 성공");
			Controller.loginCom = user;//[20.loginUser변수 만들고  BOARD_LIST로 리턴]
		}
	}
	private void loginmanager() {
	
		
	}
}
