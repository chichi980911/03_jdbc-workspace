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

}
