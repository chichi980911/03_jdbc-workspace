package src.com.kh.run;

import src.com.kh.view.MemberMenu;

public class Run {

	public static void main(String[] args) {
		
		/*
		 * *MVC패턴
		 * 
		 * -MODEL : 데이터 처리 담당(데이터들을 담기위한 클래스(VO), 데이터들이 보관된 공관과 직접적으로 접근해서 데이터 주고 받기(dao))
		 * -VIEW : 화면을 담당 (지금은 콘솔창 사용자가 보게되는 시각적인 요소, 출력 및 입력)
		 * -CONTROLLER : 사용자의 요청을 처리해주는 담당 (사용자 요청을 처리한 후 그에 해당하는 응답화면 지정)
		 *
		 * 
		 * */

		new MemberMenu().mainMenu(); // 1.MemberMenu 객체 생성및 mainMenu 메서드 호출
	}

}
