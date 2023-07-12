package src.com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import src.com.kh.controller.MemberController;
import src.com.kh.model.vo.Member;

//사용자가 보게 될 시각적인 요소(화면) 출력 및 입력
/**
 * @author user1
 *
 */
public class MemberMenu {

	// Scanner 객체 생성 (전역으로 다 쓸 수 있도록)
	private Scanner sc = new Scanner(System.in);

	// MemberController 객체 생성 (전역에서 바로 요청할 수 있게 끔)
	private MemberController mc = new MemberController();

	/**
	 * 사용자가 보게 될 첫화면(메인 화면)
	 */
	public void mainMenu() {

		while (true) {
			System.out.println("\n== 회원관리 프로그램 ==");
			System.out.println("1. 회원추가");
			System.out.println("2. 회원 전체조회");
			System.out.println("3. 회원 아이디로 멤버 정보 검색");
			System.out.println("4. 회원 이름으로 키워드 검색");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.println("9. 이름으로 정보조회");

			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();

			sc.nextLine();

			switch (menu) {
			case 1:
				inputMember();
				break;
			case 2:
				mc.selectList();
				break; // 전체 회원 조회 입력받을 필요가 없기 때문에 바로 controller 로 이동
			case 3:
//				String userId = inputMemberId();
//				mc.selectByUserId(userId);
				mc.selectByUserId(inputMemberId());
				break;
			case 4:
// userid 입력 받아서 삭제
//				String userName = inputMemberName();
//				mc.selectByUserName(userName);
//				mc.selectByUserName(inputMemberName());

// 유저 이름 키워드로 회원 정보 찾아내기
//				String keyword = inputMemberName2();
//				mc.selectByUserName2(keyword);
				mc.selectByUserName2(inputMemberName2());
				break;
			case 5: updateMember();
				break;
			case 6:mc.deleteMember(deleteMemberId());
//				아래 deleteId 사용하지 않고 case3에서 사용한 id 입력받는 메서드 재사용 가능
//				String deleteId = deleteMemberId();
//				mc.deleteMember(deleteId);
//				mc.deleteMember(deleteMemberId());
				
//				String userId = inputMemberId();
//				mc.deleteMember2(userId);
				break;
			case 0:
				System.out.println("이용해 주셔서 감사합니다.");
				return;
			case 9:
				break;
			default:
				System.out.println("메뉴를 잘못입력하셨습니다, 다시 입력해 주세요.");
			}
			System.out.println();
		}
	}

	/**
	 * 회원 추가 창(서브화면) 즉 추가 하고자 하는 회원의 정보를 입력받아서 회원 추가요청 하는 창
	 */
	public void inputMember() {
		System.out.println("\n==== 회원추가 ====");
		// 아이디~취미 까지 입력받기
		System.out.print("아이디 : ");
		String userId = sc.nextLine();

		System.out.print("비밀번호 :");
		String userPwd = sc.nextLine();

		System.out.print("이름 : ");
		String userName = sc.nextLine();

		System.out.print("성별 (M/F) :");
		String gender = sc.nextLine();

		System.out.print("나이 : "); // 이제부터 문자열로 다 받기 => 웹에서는 다 문자로 넘어옴
		String age = sc.nextLine();

		System.out.print("이메일 : ");
		String email = sc.nextLine();

		System.out.print("전화번호(-뺴고 입력) : ");
		String phone = sc.nextLine();

		System.out.print("주소 : ");
		String address = sc.nextLine();

		System.out.print("취미(,로 연이어 작성) : ");
		String hobby = sc.nextLine();

		// 회원 추가 요청 == Controller 메서드 호출
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
	}
	
	

	/**
	 * 사용자에게 회원 id를 입력 받고 그때 입력된 값을 반환시켜주는 메서드
	 * @return 사용자가 입력한 id 값
	 */
	public String inputMemberId() {
		System.out.print("\n회원 아이디 입력 : ");
		return sc.nextLine();
		
	}
	
	/**
	 * 사용자에게 회원 이름을 입력 받고 그 때 입력된 값을 반환 시켜주는 메서드
	 * @return
	 */
	public String inputMemberName() {
		
		System.out.print("\n조회할 회원 이름 입력 : ");
		return sc.nextLine();
	}
	
	/**
	 * 사용자에게 회원 아이디를 입력 받고 그 때 입력된 값을 반환 시켜주는 메서드
	 * @return
	 */
	public String deleteMemberId() {
		System.out.print("\n삭제할 회원 아이디 입력 : ");
		return sc.nextLine();
	}
	
	/**
	 * 사용자에게 검색할 회원명(키워드) 입력받은 후 그 때 입력된 값을 반환시켜주는 메서드
	 * @return 사람이 입력한 회원명(키워드)
	 */
	public String inputMemberName2() {
		System.out.print("\n검색할 회원이름 키워드 입력 : ");
		return sc.nextLine();
	}
	
	public void updateMember() {
		System.out.println("\n === 회원 정보 변경 ===");
		//아이디 = > 바꿀 비번,이메일,전번,주소
//		System.out.print("회원정보를 변경할 아이디 입력 : ");
//		String userId = sc.nextLine();
		
		String userId = inputMemberId();
	
		System.out.print("변경할 암호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("변경할 이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("변경할 전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print("변경할 주소 : ");
		String Address = sc.nextLine();
		
		mc.updateMember(userId,userPwd,email,phone,Address);
	
	}
	
	
	
	// ----------------------------응답화면--------------------------------

	/**
	 * 서비스 요청 처리 후 성공했을 경우 사용자가 보게 될 응답화면
	 * @param message 
	 */
	public void displaySuccess(String message) {
		System.out.println("\n 서비스 요청 성공 : " + message);

	}

	/**
	 * 서비스 요청 처리 후 실패했을 경우 사용자가 보게 될 응답화면
	 * @param message
	 */
	public void displayFail(String message) {
		System.out.println("\n 서비스 요청 실패 : " + message);

	}
	
	
	
	/**
	 * 조회서비스 요청시 조회결과가 없을 경우 사용자가 보게 될 응답화면
	 * @param message
	 */
	public void displayNodata(String message) {
		System.out.println( "\n 조회된 데이터가 없습니다.");
	}
	/**
	 * 조회서비스 요청시 조회결과가 여러행일 경우 사용자가 보게될 응답화면
	 * @param list
	 */
	public void displayMemberList(ArrayList<Member> list) {
		System.out.println("조회된 결과 데이터 입니당.");
		
//		단순 for 문
		for(int i =0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
//		//향상된 for 문 for each 문
//		for(Member m:list) {//m = > list.get(0), m => list.get(1)
//			System.out.println(m);
			
//		}
	}
	/**조회 서이브 요청시 조회결과가 한 행일 경우 사용자가 보게 될 응답화면
	 * @param m
	 */
	public void displayMember(Member m ) {
		System.out.println("\n 조회된 결과 데이터 입니다.");
		System.out.println(m);
		
	}
	
	public void displayMemberdata(String message) {
		MemberMenu list = new MemberMenu();
		System.out.println("\n 성공적으로 회원이 삭제되었습니다.");
		list.displayMemberList(null);
		

		
	}
	
	public void displaydSuccess(String message) {
		
	}
	
	

	
}
