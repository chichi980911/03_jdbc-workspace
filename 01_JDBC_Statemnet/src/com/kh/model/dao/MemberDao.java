package src.com.kh.model.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import src.com.kh.model.vo.Member;

//DAO(Data Access Object) : DB에 직접적으로 접근해서 사용자의 요청에 맞는 SQL 문 실행 후 결과 받기
//JDBC작업 하는곳 Controller에서 받아온 데이터를 DB에서 작업 후 결과를 다시 Controller로 다시 리턴

public class MemberDao {
	/*
	 * JDBC 용 객체
	 * Connection : DB의 연결정보를 담고 있는 객체
	 * [Prepared]Statment : 연결된 DB에 SQL 문 전달해서 실행 후 그 결과를 받아내는 객체 *중요*
	 * ResultSet : SELECT문 실행후 조회된 결과물들이 담겨있는 객체
	 *
	 * JDBC 과정 (순서중요)
	 * 1.Driver 등록
	 * 2.Connection 객체 생성
	 * 3.statement 객체 생성
	 * 4.sql문 전달하면서 실행
	 * 5.결과받기
	 * 	>select문인 경우 => ResultSet 객체 (조회된 데이터들이 담겨있음) =>6-1
	 * 	>dml문인 경우=> int (처리된 행 수) => 6-2
	 * 
	 * 6-1 : ResultSet에 담겨있는 데이터들을 하나씩 vo 객체에 옮겨 담기
	 * 6-2 : 트랜젝션 처리
	 * 
	 * 7. 다 사용한 jdbc 객체를 반드시 자원 반납  안하면 DB 락걸림 (CLOSE 생성의 역순)
	 * 
	 * 
	 * */
	/**
	 * 사용자가 입력한 정보들을 추가시켜주는 메서드
	 * @param m : 사용자가 입력한 값들이 담겨있는 Member 객체
	 */
	public int insertMember(Member m) {
		//insert문 => 처리된 행수(int) => 트랜젝션 처리
		
		//필요한 변수들 먼저 셋팅
		int result = 0; //처리된 결과(처리된 행수)를 받아줄 변수
		Connection conn = null; //연결된 db의 연결정보를 담는 객체
		Statement stmt = null; //완성된 sql(insert문은 실제 값을 다 채우고)문을 전달해서 곧바로 실행 후 결과 받는 객체
		
		//실행할 sql문 (완성된 형태로 만들어두기)
		
		//INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL,'XXX','XXX','XXX','X',XX,'XXX','XX','XX','XX',SYSDATE)
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL,"
												+"'"+ m.getUserId() + "', "
												+"'"+ m.getUserPwd() + "', "
												+"'"+ m.getUserName() + "', "
												+"'"+ m.getGender() + "', "
													+ m.getAge() +", "
												+"'"+ m.getEmail() + "', "
												+"'"+ m.getPhone() + "', "
												+"'"+ m.getAddress() + "', "
												+"'"+ m.getHobby() + "', SYSDATE)";
		//System.out.println(sql); //콘솔에 찎어서 쿼리문 맞는지 확인

		//1)jdbc driver 등록 soround 문 + driver추가
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		//2)Connection 객체 생성 == db 연결
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
		
		//3)statement 객체 생성
		stmt = conn.createStatement();
		
		//4.5)sql문 전달하면서 실행 후 결과받기
		result = stmt.executeUpdate(sql);
		
		//6)트랜젝션 처리
		if(result > 0) {//성공시 커밋
			conn.commit();
		}else { //실패시 롤백
			conn.rollback();
		}
		
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//7) 사용한 jdbc용 객체반납
			try {
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	
	}
	
	/**
	 * 사용자가 요청한 회원 전체 조회를 처리해주는 메서드
	 * @return 조회된 결과가 있었으면 조회된 결과들이 담겨 있는 list or 없으면 텅 빈 리스트 리턴
	 */
	public ArrayList<Member> selectList() {
		//select문 (여러행 조회) => Resultset 객체 => ArrayList에 담기
		
		//필요한 변수들 셋팅 
		
		ArrayList<Member> list = new ArrayList<Member>(); // [] 현재상태는 텅 비어 있는상태 
		
		//select문 이기 때문에 connection
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null; // select문 실행시 조회된 결과값들이 최초로 담기는 객체
		
		//실행할 sql문
		String sql = "SELECT * FROM MEMBER";
		
		//1)JDBC 드라이버 등록 및 예외처리
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		//2) Connection 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
		
		//3)statement 생성
			stmt = conn.createStatement();
		
		//4,5) sql 실행 및 결과받기
			rset = stmt.executeQuery(sql);
			
		//6) ResultSet으로 부터 데이터 하나씩 뽑아서 vo 객체에 담기 + list에 vo객체 추가
		
			while(rset.next()) {
				//현재 rset의 커서가 가리키고 있는 한 행의 데이터들을 싹다 뽑아서 Member객체에 담기
				Member m = new Member();
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				//현재 참조하고 있는 행에 대한 모든 컬럼에 대한 테이블을 한 MEMBER 객체에 담음
				
				list.add(m); //리스트에 해당 회원 객체 순서대로 담기
			
			}
			//반복문이 다 끝난 시점에
			//만약에 조회된 데이터가 ㅇ벗다면 LIST가 텅 빈 상태
			//만약에 조회된 데이터가 있었다면 LIST에 뭐라도 담겨 있을것
			
		
			
		} catch (ClassNotFoundException e) { //1
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) { //2
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list; //텅빈 리스트 or 담겨있는 리스트
		
	}
	
	/**
	 * 사용자의 아이디로 회원 검색 요청 해주는 메서드
	 * @param userId - 사용자가 입력한 검색하고자 하는 회원 아이디값
	 * @return 		 - 검색된 결과가 있으면 생성된 멤버객체 or 없으면 null
	 */
	public Member selectByUserId(String userId) {
		//select문 (한행) =>ResultSet객체
		//그럼 굳이 ArrayList 필요 없음 멤버객체 한갬나 있으면 된다.
		
		//필요한 변수 세팅
		Member m = null; //조회결과가 있을 수도 있고 없을 수 도 있으니 null로 초기화
		
		//JDBC객체
		Connection conn =null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = '" +userId + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {//한행이라도 조회됐을때
				//조회가 잘되었을 떄 조회된 컬럼값을 뽑아서 Member 객체의 각필드에 담기
				m = new Member( rset.getInt("userno"), 
								rset.getString("userid"), 
								rset.getString("userpwd"),
								rset.getString("username"),
								rset.getString("gender"),
								rset.getInt("age"),
								rset.getString("email"),
								rset.getString("phone"), 
								rset.getString("address"),
								rset.getString("hobby"),
								rset.getDate("enrolldate"));
				
			}
			//위의 조건문이 다 끝난 시점에 
			//조회된데이터가 없으면 m은 null(초기화값)이다
			//조회된 데이터가 있으면 m 은 생성후 뭐라도 담겨있다.
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		return m;
	}
	
	
	public Member selectByUserName(String userName) {
		//필요한 변수 생성
		Member m = null;
		
		//jdbc 객체 생성
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null; //select 문 실행시 값이 담길 공간
		
		
		//sql 문 작성
		
		String sql = "SELECT * FROM MEMBER WHERE USERNAME = '"+ userName + "'";
		
		
		//1.driver 삽입
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2)Connection 객체 생성 == db 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
		
		//3)Statement 객체 생성
			stmt = conn.createStatement();
		
		//4.5)sql문 전달하면서 실행 후 결과받기
			rset = stmt.executeQuery(sql);
			
		//6ResultSet으로 부터 데이터 하나씩 뽑아서 vo 객체에 담기 + list에 vo객체 추가
			if(rset.next()) {
				m = new Member(rset.getInt("userno"),
								rset.getString("userid"),
								rset.getString("userpwd"),
								rset.getString("username"),
								rset.getString("gender"),
								rset.getInt("age"),
								rset.getString("email"),
								rset.getString("phone"), 
								rset.getString("address"),
								rset.getString("hobby"),
								rset.getDate("enrolldate"));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}
	
	public int deleteMember(Member m) {
		//사용할 변수 생성
		
		
		//jdbd 객체생성
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		
		//sql문 작성
		String sql = "DELETE FROM MEMBER WHERE USERID = '" +m.getUserId()+ "'";
//		System.out.println(sql);
		//1.driver 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		//2.connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
		//3.statement 객체 생성
			stmt = conn.createStatement();
		//4.결과값 받기
			result = stmt.executeUpdate(sql);
		//5. 트랜젝션 처리
			//6)트랜젝션 처리
			if(result > 0) {//성공시 커밋
				conn.commit();
			}else { //실패시 롤백
				conn.rollback();
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
//		
	}
	/**
	 * 사용자의 이름으로 키워드 검색 요청시 처리해주는 메서드
	 * @param keyword
	 * @return
	 */
//	public ArrayList<Member> selectByUserName2(String keyword) {
//		//SELECT 문 수행 (여러행) => ResultSet
//		//ArrayList로 짜야한다.
//		
//		ArrayList<Member> list = new ArrayList<Member>();
//		
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rset = null;
//		
//		//쿼리
//		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%" + keyword + "%'";
//		//드라이버 등록
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		//CONNECTION 객체 생성
//		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
//		
//		stmt = conn.createStatement();
//		
//		rset = stmt.executeQuery(sql);
//		
//		while(rset.next()){
//			list.add(new Member(rset.getInt("userno"),
//								rset.getString("userid"),
//								rset.getString("userpwd"),
//								rset.getString("username"),
//								rset.getString("gender"),
//								rset.getInt("age"),
//								rset.getString("email"),
//								rset.getString("phone"), 
//								rset.getString("address"),
//								rset.getString("hobby"),
//								rset.getDate("enrolldate")
//								)); //(안에는 멤버 객체만 들어올 수 있다.)
//			
//		}
//		
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			try {
//				rset.close();
//				stmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}return list;
		
//		public void deleteMember2(String deleteId) {
//			
//		}
		
		
//	}
	/**
	 * 사용자가 입력한 아이디의 정보 변경 요청 처리해주는 메서드
	 * @param m
	 * @return result : 처리된 행수
	 */
	public int updateMember(Member m) {
		// update문 => 처리된 행수(int) => 트랜젝션 처리(commit,rollback)
		int result = 0;
		
		Connection conn =null;
		Statement stmt = null;
		
		String sql = "UPDATE MEMBER " 
				   +	"SET USERPWD = " + "'" + m.getUserPwd() + "'" 
				   +	", EMAIL = '" + m.getEmail() + "' "
				   +	", phone = '" + m.getPhone() + "' "
				   +	", address = '" + m.getAddress() + "' "
				   + "WHERE USERID = '" + m.getUserId()+ "'";
		System.out.println(sql);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}return result; 
		
	}
	
	
	
}
