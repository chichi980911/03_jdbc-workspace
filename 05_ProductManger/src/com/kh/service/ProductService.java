package com.kh.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;


import com.kh.common.JDBCTemplate;
import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;



public class ProductService {

	
	public ArrayList<Product> selectList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectList(conn);
		
		
		return list;
	}
	public  int insertProduct(Product p) {
		Connection conn = JDBCTemplate.getConnection();
		 int result = new ProductDao().insertProduct(conn,p);
		
		 JDBCTemplate.close(conn);
		 return result;
	}
	public int updateProduct(Product p) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ProductDao().updateProduct(conn,p);
		
		JDBCTemplate.close(conn);
		return result;
		
	}
	public int deleteProduct(Product p) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ProductDao().deleteProduct(conn,p);
		
		JDBCTemplate.close(conn);
		return result;
	}
	
	public ArrayList<Product> keywordProduct(Product p) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list =new ProductDao().keywordProduct(conn,p);
		
		JDBCTemplate.close(conn);
		return list;
	}

	 
}
