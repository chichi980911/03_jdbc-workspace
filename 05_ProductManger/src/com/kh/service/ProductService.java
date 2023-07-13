package com.kh.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;

import oracle.sql.converter.JdbcCharacterConverters;

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
	public int deleteProduct(String pid) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ProductDao().deleteProduct(conn,pid);
		
		JDBCTemplate.close(conn);
		return result;
	}
	 
}
