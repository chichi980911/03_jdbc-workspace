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
				break;
			case 3:
				break;
			case 4:
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
	
	//=========요청 응답
	
	public void displaylist(ArrayList<Product> list) {
		System.out.println("조회된 리스트 입니다.");
		
		for(int i =0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
}
