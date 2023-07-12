package com.kh.controller;

import com.kh.model.vo.PhoneNoteMenu;
import com.kh.service.PhoneService;
import com.kh.view.PhoneMenu;

public class PhoneController {
//	private PhoneMenu  pnm = new PhoneMenu 전역 사용 x
	
	public void insertPhone(String userName,String age,String address,String Phone ) {
		PhoneNoteMenu pnm = new PhoneNoteMenu(userName,Integer.parseInt(age),address,Phone);
		
		int result = new PhoneService().insertService(pnm);
		
		if(result > 0) {
			new PhoneMenu().displaySuccess("\n전화번호 추가가 완료되었습니다.");
		}else {
			new PhoneMenu().displayFail("\n전화번호 추가가 실패되었습니다.");
		}
		
	}
	
	public void selectPhone() {
		new PhoneService().selectPhone();
		
		
		
	}

}
