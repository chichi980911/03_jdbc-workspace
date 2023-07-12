package src.com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import src.com.kh.common.JDBCTemplate;
import src.com.kh.model.vo.Member;

//DAO(Data Access Object) : DB에 직접적으로 접근해서 사용자의 요청에 맞는 SQL 문 실행 후 결과 받기
//JDBC작업 하는곳 Controller에서 받아온 데이터를 DB에서 작업 후 결과를 다시 Controller로 다시 리턴

public class MemberDao {
	//=====================================================

	/*
	 * 기존의 방식 : DAO 클래스를 사용자가 요청할 때마다 실행해야하는 SQL문을 자바소스코드 내에 명시적으로 작성 => 정적코딩발식
	 * 
	 * >문제점 : SQL문을 수정해야 할 경우 자바 소스코드를 수정해야한다 => 수정된 내용을 반영시키고자 한다면 반드시 프로그램을 재기동 해야함
	 * 
	 * >해결방식 : SQL문을 별도로 관리하는 외부파일 (.xml)을 만들어서 실시간으로 파일에 기록된 sql문을 읽어 들여서 실행 => 동적 코딩방식
	 * 			여러줄 쓸 수 있도록 => xml로 하는게 좋다.
	 * */

	
	private Properties prop = new Properties();
	
	public MemberDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	//=====================================================
	/**
	 * 회원추가 메서드
	 * 
	 * @param m
	 * @return result : 처리된 행수
	 */
	public int insertMember(Connection conn, Member m) {
		// insert문 => 처리된 행수 => 트랜젝션 처리

		int result = 0;
		//conn 객체는 이미 서비스에서 생성돼있다
		
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertMember");

		try {
			//3) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql); // 애초에 pstmt 객체 생성시 sql문 담은채로 생성 (지금은 미완성)

			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());

			// 4,5)SQL구문 실행 및 결과 받기
			result = pstmt.executeUpdate(); // 여기서는 sql문 전달하지 않고 그냥 실행해야한다.

			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			//conn은 반납 x  (트랜젝션 처리 서비스가서 해야한다)
		}
		return result;
	}

	
	//
	public ArrayList<Member> selectList( Connection conn) {
		// select문(여러행) = ResultSet 객체
		ArrayList<Member> list = new ArrayList<Member>(); // 텅빈 리스트

		
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectList");

		

		try {	
			
			// 3)PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql); // 애초에 pstmt 객체 생성시 sql문 담은채로 생성 (지금은 미완성)
			// >빈공간을 실제값 (사용자가 입력한 값)으로 채워준 후 실행

			rset = pstmt.executeQuery();
			
			

			while (rset.next()) {
				list.add(new Member(rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),
						rset.getString("username"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("email"), rset.getString("phone"), rset.getString("address"),
						rset.getString("hobby"), rset.getDate("enrolldate")));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		return list;
	}

	/**
	 * 회원의 아이디를 받아 검색해주는 메서드
	 * 
	 * @param userId 회원이 검색하고자 하는 아이디
	 * @return m : 그 아이디를 가진 회원
	 */
	public Member selectByUserId(Connection conn, String userId) {
		// SELECT문 => 한행 OR 0행 => ResultSet객체 > Member객체
		Member m = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectByUserId");

		try {
			

			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				m = new Member(rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),
						rset.getString("username"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("email"), rset.getString("phone"), rset.getString("address"),
						rset.getString("hobby"), rset.getDate("enrolldate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
			}
		return m;
	}

	public ArrayList<Member> selectByUserName2(Connection conn, String keyword) {

		ArrayList<Member> list = new ArrayList<Member>();

		
		PreparedStatement pstmt = null;
		ResultSet rset = null;

//		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%' || ? || '%'"; 해결방법1

		String sql = prop.getProperty("selectByUserName2");

		try {
			
			pstmt = conn.prepareStatement(sql);

//			pstmt.setString(1, keyword); //해결방법 1  //'%관%'

			pstmt.setString(1, "%" + keyword + "%");

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Member(rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),
						rset.getString("username"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("email"), rset.getString("phone"), rset.getString("address"),
						rset.getString("hobby"), rset.getDate("enrolldate")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int updateMember(Connection conn, Member m) {
		// update 문 => 처리된 행수(int) =>트랜젝션 처리
		int result = 0;

		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");

		try {
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());

			result = pstmt.executeUpdate();

		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int deleteMember(Connection conn, Member m) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			

			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		return result;
		
		
		
		
	}

	
}
