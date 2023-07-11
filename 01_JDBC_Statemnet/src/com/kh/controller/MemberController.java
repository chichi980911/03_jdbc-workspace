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

	/**
	 * 사용자의 회원 추가 요청을 처리해주는 메서드
	 * 
	 * @param userId ~ @param hobby : 사용자가 입력했던 정보들이 담겨있는 매개변수
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String email,
			String phone, String address, String hobby) {

		// 받은 값들을 데이터를 직접적으로 처리 해주는 DAO로 넘기기
		// 어딘가에 담아서 전달 어딘간 = vo 객체 Member객체

		// 방법 1) 기본생성자로 생성한 후 각 필드에 setter 메서드 통해서 담는 방법 =>매개변수가 몇개 없을때 사용
		// 방법 2) 매개변수 생성자를 통해서 생성과 동시에 담는 방법

		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby);
		// 나이를 반드시 int 형으로 변환해야한다.
		// String => int 형으로 변환하는 방법 parseInt();
		// new 부터 빨간줄 뜨는 경우 => 100%확률로 매개변수 생성자가 없다는 것
		// System.out.println(m);
		int result = new MemberDao().insertMember(m); // 이후 응답화면 멤버 메뉴에서 생성

		 System.out.println(result);

		 //팝업창 뜨는 것처럼 사용자에게 문구 보여주기
		if (result > 0) {// 성공
			new MemberMenu().displaySuccess("성공적으로 회원이 추가되었습니다.");

		} else {// 실패
			new MemberMenu().displayFail("회원 추가 실패했습니다");

		}
	}

	public void selectList() {
		ArrayList<Member> list = new MemberDao().selectList();
		//조회 결과가 있는지 없는지 판단 후 사용자가 보게 될 응답화면 지정
		
		
		if(list.isEmpty()) { //텅 비어있을경우 ==조회된 데이터가 ㅇ벗을 경우
			new MemberMenu().displayNodata("전체 조회결과가 없습니다.");
		}else {//값이 들어있는 경우
			new MemberMenu().displayMemberList(list);
		}
	}
	
	public void selectByUserId(String userId) {
		Member m = new MemberDao().selectByUserId(userId);
		
		if(m == null) {
			System.out.println("검색 결과가 없을 경우"); // 실패는 아니고 조회된 결과가없음
			new MemberMenu().displayNodata(userId + "에 해당하는 검색 결과가 없습니다.");
		}else {
			new MemberMenu().displayMember(m);
		}
		
	}
	
	public void selectByUserName(String userName) {
		Member m = new MemberDao().selectByUserName(userName);
		
		if(m == null) {
			System.out.println("검색 결과가 없을 경우"); //실패는 아니고 조회된 결과가 없음
			new MemberMenu().displayNodata(userName + "에 해당하는 검색 결과가 없습니다.");
		}else {
			new MemberMenu().displayMember(m);
		}
	}
	
	/**
	 * 회원 탈퇴 요청 처리해준 메서드
	 * @param deleteId 탈퇴 시키고자 하는 회원아이디
	 */
	public void deleteMember(String deleteId) {
 //내가 작성
		Member m = new Member();
		m.setUserId(deleteId);
		
		int result = new MemberDao().deleteMember(m);
//		System.out.println(result);
		
		if (result > 0) {// 성공
			new MemberMenu().displayMemberdata("성공적으로 회원이 삭제되었습니다.");

		} else {// 실패
			new MemberMenu().displayNodata("회원 삭제 실패했습니다");
		}
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
		
		if(list.isEmpty()) {//텅빈 리스트일 경우 => 검색결과 없음
			new MemberMenu().displayNodata(keyword + "에 해당하는 검색 결과가 없습니다.");
		}else {//그게 아닐 경우 => 검색결과있음
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
		Member m  = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(Address);
		
		int result = new MemberDao().updateMember(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원 정보가 변경되었습니다.");
		}else {
			new MemberMenu().displayFail("회원정보 변경에 실패 했습니다.");
			
		}
	}
	
	
}
