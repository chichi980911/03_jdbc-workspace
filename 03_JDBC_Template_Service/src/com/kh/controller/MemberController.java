package src.com.kh.controller;

import java.util.ArrayList;

import src.com.kh.model.dao.MemberDao;
import src.com.kh.model.vo.Member;
import src.com.kh.view.MemberMenu;

//Controller : view를 통해서 사용자가 요청한 기능에 대해서 처리하는 담당
//				해당 메서드로 전달된 데이터 [가공처리 한 후 ] Dao로 전달하면서 호출
//				Dao로 부터 반환받은 결과에 따라 성공인지 실패인지 판단 후 응답화면 결정(view 메서드 호출)
/**
 * @author user1
 *
 */
public class MemberController {
	//private MemberMenu mm = new MemberMenu();

	/**
	 * 사용자의 회원 추가 요청을 처리해주는 메서드
	 * 
	 * @param userId ~ @param hobby : 사용자가 입력했던 정보들이 담겨있는 매개변수
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String email,
			String phone, String address, String hobby) {
		
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby);
		int result = new MemberDao().insertMember(m);

		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원추가 되었습니다.");
		}else {
			new MemberMenu().displayFail("회원추가 실패");
		}

		 
	}

	/**
	 * 사용자의 회원 전체 조회 요청 처리 메서드
	 */
	public void selectList() {
		ArrayList<Member> list = new MemberDao().selectList();
		
		//조회 결과가 있는지 없는지 판단 후 사용자가 보게 될 응답화면 지정
		if(list.isEmpty()) {
			new MemberMenu().displayNodata("조회된 결과가 없습니다.");
		}else {
			new MemberMenu().displayMemberList(list);
		}
		
		
	}
	
	/**
	 * 사용자의 아이디로 회원 검색 요청을 처리해주는 메서드
	 * @param userId : 사용자가 입력한 검색하고자 하는 회원 아이디값
	 */
	public void selectByUserId(String userId) {
		Member m = new MemberDao().selectByUserId(userId);
		
		if(m == null) {
			new MemberMenu().displayNodata(userId + "에 해당하는 검색 결과가 없습니다.");
		}else {
			new MemberMenu().displayMember(m);
		}
		
	}
	
	public void selectByUserName(String userName) {
		
		
		
	}
	
	/**
	 * 회원 탈퇴 요청 처리해준 메서드
	 * @param deleteId 탈퇴 시키고자 하는 회원아이디
	 */
	public void deleteMember(String deleteId) {
		new MemberDao().deleteMember(deleteId);
		
		
 //내가 작성
		
	}
	
//	public void deleteMember2(String deleteId) {
//		new MemberDao().deleteMember2(deleteId);
//	}
//	
	/**이름으로 키워드 검색 요청시 처리해주는 메서드
	 * @param keyword
	 */
	public void selectByUserName2(String keyword) {
		
		ArrayList<Member> list = new MemberDao().selectByUserName2(keyword);
		
		if(list.isEmpty()) {
			new MemberMenu().displayNodata(keyword + "에 해당하는 데이터가 없습니다.");
		}else {
			new MemberMenu().displayMemberList(list);
		}
		
	}
	/**정보 수정할 아이디 입력 받기 + 변경할 메뉴 입력받기
	 * @param userId
	 * @param userPwd
	 * @param email
	 * @param phone
	 * @param address
	 */
	public void updateMember(String userId,String userPwd, String email,String phone ,String Address) {
		Member m = new Member(); //기본생성자
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(Address);
		
		int result = new MemberDao().updateMember(m);
		
		if(result > 0 ) {
			new MemberMenu().displaySuccess("수저성공");
		}else {
			new MemberMenu().displayFail("실패");
		}
	
	
}
}
