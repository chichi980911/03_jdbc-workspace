package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.vo.Product;
import com.kh.service.ProductService;
import com.kh.view.ProductMenu;



public class ProductController {
	
	public void selectList() {
		ArrayList<Product> list = new ProductService().selectList();
		
		
		if(list.isEmpty()) {
			System.out.println("조회 결과가 없습니다.");
		}else {
			new ProductMenu().displaylist(list);
		}
		
	}
	
	public void insertProduct(String pid, String pname, String pprice, String pde, String pst) {
		Product p = new Product(pid,pname,Integer.parseInt(pprice) ,pde,Integer.parseInt(pst));
		
		int result = new ProductService().insertProduct(p);
		
		if(result > 0) {
			new ProductMenu().displaySuccess("회원 추가 성공");
		}else {
			new ProductMenu().displayFail("회원추가 실패");
		}
		
	}
	
	public void updateProduct(String pid, String pname, String pprice, String pdes, String pst) {
		Product p = new Product(pid,pname,Integer.parseInt(pprice),pdes,Integer.parseInt(pst));
		int result = new ProductService().updateProduct(p);
		
		if(result > 0) {
			new ProductMenu().displaySuccess("회원 정보 수정 성공");
		}else {
			new ProductMenu().displayFail("회원 정보 수정 실패 ");
		}
	}
	public void deleteProduct(String  userid) {
		Product p = new Product(userid);
		 int result = new ProductService().deleteProduct(p);
		 
		 if(result > 0) {
				new ProductMenu().displaySuccess("회원 정보 수정 성공");
			}else {
				new ProductMenu().displayFail("회원 정보 수정 실패 ");
			}
		
	}
	
//	public void keywordProduct(String pname) {
//		Product p = new Product();
//		ArrayList<Product> list = new ProductService().keywordProduct(p);
//		
//		if(list.isEmpty()) {
//			new ProductMenu().displayFail("검색에 실패했습니다");
//		}else {
//			new ProductMenu().displaylist(list);
//		}
//	}
	
	
	public void  keywordProduct(String pname) {
		Product p = new Product(pname);
		ArrayList<Product> list = new ProductService().keywordProduct(p);
		
		if(list.isEmpty()) {
			System.out.println("조회 결과가 없습니다.");
		}else {
			new ProductMenu().displaylist(list);
		}
		
		
	}
	

}
