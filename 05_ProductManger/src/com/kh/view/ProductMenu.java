package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductMenu {

	Scanner sc = new Scanner(System.in);
	ProductController pc = new ProductController();

	public void mainMenu() {

		while (true) {
			System.out.println("===메뉴===");
			System.out.println("1. 전체 조회 하기 ");
			System.out.println("2. 상품 추가 하기 ");
			System.out.println("3. 상품 수정 하기 "); // 상품 id로 조회하고 수정
			System.out.println("4. 상품 삭제 하기 "); // 상품 id 조회하고 삭제
			System.out.println("5. 상품 검색 하기 "); // 상품 이름으로 키워드 검색
			System.out.println("0. 프로그램 종료하기 ");
			
			System.out.print("메뉴를 선택하세요 : ");
			int usercho = sc.nextInt();
			sc.nextLine();

			switch (usercho) {
			case 1:
				pc.selectList();
				break;
			case 2:
				insertp();
				break;
			case 3:
				updatep();
				break;
			case 4:
				deletep();
				break;
			case 5:
				break;
			case 6:
				return;
			default:
				System.out.println("메뉴를 잘못입력하셨습니다, 다시 입력해 주세요.");
				
				
			}
		}
	}
	
	//사용자가 선택한 메뉴 구현
	public void insertp() {
		System.out.print("추가할 상품의 id : ");
		String pid =sc.nextLine();
		
		System.out.print("추가할 상품명 : ");
		String pname =sc.nextLine();
		
		System.out.print("추가할 상품의 가격 : ");
		String pprice =sc.nextLine();
		
		System.out.print("추가할 상품 설명 : ");
		String pde =sc.nextLine();
		
		System.out.print("추가할 상품 재고 : ");
		String pst =sc.nextLine();
		
		pc.insertProduct(pid,pname,pprice,pde,pst);
	}
	
	public void updatep() {
		System.out.print("변경할 상품의 id를 입력하세요 :");
		String pid = sc.nextLine();
		System.out.print("바꿀 상품명 : ");
		String pname = sc.nextLine();
		System.out.print("바꿀 상풍의 가격 : ");
		String pprice = sc.nextLine();
		System.out.print("바꿀 상품의 설명 : ");
		String pdes = sc.nextLine();
		System.out.print("바꿀 상품의 재고 : ");
		String pst = sc.nextLine();
		
		pc.updateProduct(pid,pname,pprice,pdes,pst);
	}
	public void deletep() {
		System.out.println("삭제할 상품의 id를 입력하세요 : ");
		String pid = sc.nextLine();
		pc.deleteProduct(pid);
		
	}
	
	
	
	//=========요청 응답
	
	public void displaylist(ArrayList<Product> list) {
		System.out.println("조회된 리스트 입니다.");
		
		for(int i =0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 " + message);
	}
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 " + message);
	}
	
}
