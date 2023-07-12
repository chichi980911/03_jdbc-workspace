package com.kh.view;

import java.util.Scanner;

import com.kh.controller.PhoneController;
import com.kh.model.vo.PhoneNoteMenu;



public class PhoneMenu {
	
	//사용자가 보게될 자료 입력
	private Scanner sc = new Scanner(System.in);
	
	
	//컨트롤러로 넘길 객체 생성 
	private PhoneController mc = new PhoneController();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("===============");
			System.out.println("전화번호부 v1.0");
			System.out.println("---------------");
			System.out.println("1. 전화번호등록");
			System.out.println("2. 전화번호검색");
			System.out.println("3. 전화번호 모두보기");
			System.out.println("4. 종료");
			System.out.println("===============");
			System.out.print(">>메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				inputPhone();
				break;
			case 2:
				
				mc.selectPhone();
				break;
			case 3:
				break;
			case 4 :
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("메뉴를 잘못입력하셨습니다, 다시 입력해 주세요.");
			}
			
		}
	}
	
	//==============사용자에게 입력 받을 곳===========
		public void inputPhone() {
			System.out.println("\n메뉴 > 1");
			System.out.println("====전화번호등록====");
			
			System.out.print("이름 : ");
			String userName = sc.nextLine();
			
			System.out.print("나이 : ");
			String age = sc.nextLine();
			
			
			System.out.print("주소 : ");
			String address = sc.nextLine();
			
			System.out.print("전화번호 : ");
			String phone = sc.nextLine();
			
			mc.insertPhone(userName,age,address,phone);
	}
		
		
		
		
		//==================사용자 요청 응답============
		
		public void displayFail(String message) {
			System.out.println("서비스 요청 실패" + message);
			
		}
		public void displaySuccess(String message) {
			System.out.println("서비스 요청 성공 "+ message);
			
		}
		
		
}
