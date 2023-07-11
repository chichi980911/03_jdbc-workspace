package src.com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static src.com.kh.common.JDBCTemplate.*; //.* 사용시 JDBCTemplate에 있는 모든 스태틱 메서드 그냥 호출 가능
import src.com.kh.model.dao.MemberDao;
import src.com.kh.model.vo.Member;

public class MemberService {
	
	public int insertMember(Member m) {
	//1.jdbc driver 등록
	//2.connection 객체 생성
	
	Connection conn = /*JDBCTemplate.*/getConnection();
	int result = new MemberDao().insertMember(conn,m); // pstmt 만들려면 conn이 필요하다
	
	//6)트랜젝션처리
	if(result > 0) {
		/*JDBCTemplate.*/commit(conn);
	}else {
		/*JDBCTemplate.*/rollback(conn);
	}
	//7) 다쓴 자원 반납처리
	/*JDBCTemplate.*/close(conn);
	return result;
	}


	
	public ArrayList<Member> selectList(){
		//1.jdbc driver 등록
		//2.connection 객체 생성
		
		Connection conn = /*JDBCTemplate.*/getConnection();
		ArrayList<Member> list = new MemberDao().selectList(conn);
		
		return list;
	}
}

