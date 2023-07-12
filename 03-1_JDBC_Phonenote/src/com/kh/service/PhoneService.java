package com.kh.service;

import java.sql.Connection;

import com.kh.JDBCtemplate.JDBCTemplate;
import com.kh.model.PhoneDao.PhoneDao;
import com.kh.model.vo.PhoneNoteMenu;

public class PhoneService {
	
	public int insertService(PhoneNoteMenu pnm) {
		
		Connection conn = JDBCTemplate.getConnection();
		//dao로 넘김
		//new PhoneDao().insertService(conn,pnm)
		
		//dao받아와서 변수생성 후 담기
		int result = new PhoneDao().insertService(conn,pnm);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
		
	}

}
