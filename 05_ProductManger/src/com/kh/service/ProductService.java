package com.kh.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;

public class ProductService {

	
	public ArrayList<Product> selectList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectList(conn);
		
		
		return list;
		
		
	
	
	}
}
