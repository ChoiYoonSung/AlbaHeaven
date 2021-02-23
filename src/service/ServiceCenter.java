package service;

public class ServiceCenter {

	private ServiceCenter(){}//생성자
	private static ServiceCenter instance;//변수생성
	public static ServiceCenter getInstance(){
		if(instance == null){
			instance = new ServiceCenter();
		}
		return instance;
	}
	
	public int qnaAlba() {
		System.out.println("개인QNA");
		if(String.valueOf(MainService.loginAlba.get("AUTH")).equals('1')){
			System.out.println("개인");
		}
		if(String.valueOf(MainService.loginCom.get("AUTH")).equals('2')){
			System.out.println("기업");
		}
		if(String.valueOf(MainService.loginMan.get("AUTH")).equals('3')){
			System.out.println("관리자");
		}
		return 0;
	}

	public int qnaCom() {
		System.out.println("기업QNA");
		return 0;
	}

	public int guide() {
		System.out.println("가이드");
		// TODO Auto-generated method stub
		return 0;
	}

	public int notice() {
		System.out.println("공지사항");
		// TODO Auto-generated method stub
		return 0;
	}

	

}
