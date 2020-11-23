package backend.services;

import backend.bean.MemberBean;

public class Authentication {

	public Authentication() {
		
	}
	
	public void backController(int serviceCode, MemberBean member) {
		// 클라이언트 요청(serviceCode)에 따른 Job 분기
		switch(serviceCode) {
		case 1:
			this.duplicateCheck(member);
			break;
		case 2:
			this.regUser(member);
			break;
		}
		
	}
	
	/** 아이디 중복체크
	 *  @author 	hoonzzang
	 *  @name		private  duplicateCheck
	 *  @param		MemberBean member
	 *  @return 	boolean isCheck
	 */
	private boolean duplicateCheck(MemberBean member) {
		boolean isCheck = false; // true:: 사용가능   false :: 사용불가능
		
		isCheck = true;
		member.setDuplicateCheck(isCheck);
		
		return isCheck;
	}
	
	private void regMemberControl(MemberBean member) {

		// 중복체크
		this.duplicateCheck(member);
		
		// 등록
		this.regUser(member);
		
		// 정보수집
		this.searchMemberInfo(member);
	}
	
	/** 회원정보 등록
	 *  @author 	hoonzzang
	 *  @name		private regUser
	 *  @param		MemberBean member
	 *  @return		boolean isCheck
	 */
	private boolean regUser(MemberBean member) {
		boolean isCheck = false;
		
		member.setMemberId("ID적합");
		member.setMemberName("Name적합");
		member.setMemberPassword("Password 적합");
		member.setMemberAge(51);
		member.setMemberType("개인");
		return isCheck;
	}
	
	/** 회원정보 검색
	 * @name		private  searchMemberInfo
	 * @param		String 	 memberId
	 * @return 		MemberBean memberInfo
	 */
	private MemberBean searchMemberInfo(MemberBean member) {
		MemberBean memberInfo = new MemberBean();
		// 데이터 타입 변환 array --> bean
		
		return memberInfo;
	}	

}








