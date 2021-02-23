package service;

public class MyMenu {

	private MyMenu(){}//생성자
	private static MyMenu instance;//변수생성
	public static MyMenu getInstance(){
		if(instance == null){
			instance = new MyMenu();
		}
		return instance;
	}
	public int myMenu() {
		// TODO Auto-generated method stub
		return 0;
	}

}
