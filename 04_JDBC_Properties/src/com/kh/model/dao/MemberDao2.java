package src.com.kh.model.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;

import src.com.kh.model.vo.Member;



public class MemberDao2 {

	
	/**
	 * 회원추가 메서드
	 * 
	 * @param m
	 * @return result : 처리된 행수
	 */
	public int insertMember(Member m) {
		

		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL,?,?,?,?,?,?,?,?,?,SYSDATE)";

		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			pstmt = conn.prepareStatement(sql); 
			

			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());

			
			result = pstmt.executeUpdate(); 
			
			//여기서 SQLEXCEPTION 에러가 날 수 있기 떄문에 뒤에 커밋과 롤백이 있으면 실행이 안된다
			//문제가 생기면 무조건 롤백을 해야함

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} if (result > 0) {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		
		} else {
			if(conn != null && !conn.isClosed()) {
			conn.rollback();
		}
		}
		finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return result;
	}

	public ArrayList<Member> selectList() {
		
		ArrayList<Member> list = new ArrayList<Member>(); 

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			pstmt = conn.prepareStatement(sql); 
			

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Member(rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),
						rset.getString("username"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("email"), rset.getString("phone"), rset.getString("address"),
						rset.getString("hobby"), rset.getDate("enrolldate")));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 회원의 아이디를 받아 검색해주는 메서드
	 * @param userId 회원이 검색하고자 하는 아이디
	 * @return m : 그 아이디를 가진 회원 
	 */
	public Member selectByUserId(String userId) {
		// SELECT문 => 한행 OR 0행 => ResultSet객체 > Member객체
		Member m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERID = ?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				m = new Member(rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),
						rset.getString("username"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("email"), rset.getString("phone"), rset.getString("address"),
						rset.getString("hobby"), rset.getDate("enrolldate"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return m;
	}
	
	public ArrayList<Member> selectByUserName2(String keyword) {
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		

		
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC"); 
			pstmt = conn.prepareStatement(sql);
			
			
		
			
			pstmt.setString(1, "%"+keyword+"%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Member (rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),
						rset.getString("username"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("email"), rset.getString("phone"), rset.getString("address"),
						rset.getString("hobby"), rset.getDate("enrolldate")));
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}return list;
	}
	public int updateMember(Member m) {
		//update 문 => 처리된 행수(int) =>트랜젝션 처리
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET USERPWD = ?, EMAIL =?, PHONE = ?, ADDRESS = ? WHERE USERID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC"); 
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,m.getUserPwd());
			pstmt.setString(2,m.getEmail());
			pstmt.setString(3,m.getPhone());
			pstmt.setString(4,m.getAddress());
			pstmt.setString(5,m.getUserId());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}return result;
		
	
	}
}
	


