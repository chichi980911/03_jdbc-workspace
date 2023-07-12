package com.kh.model.PhoneDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kh.JDBCtemplate.JDBCTemplate;
import com.kh.model.vo.PhoneNoteMenu;

public class PhoneDao {
	public int insertService(Connection conn,PhoneNoteMenu pnm) {
		
		//dml문 결과값 처리된 행수 반환 int 형 변수 생성
		int result = 0;
		
		PreparedStatement pstmt =null;
		//sql문
		String sql = "INSERT INTO PHONENOTE VALUES(SEQ_PHONENO.NEXTVAL,?,?,?,?,SYSDATE)" ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pnm.getUserName());
			pstmt.setInt(2, pnm.getAge());
			pstmt.setString(3, pnm.getAddress());
			pstmt.setString(4,pnm.getPhone());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}return result;
	}
	

}
