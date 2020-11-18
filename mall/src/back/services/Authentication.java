package back.services;

import back.bean.MemberBean;

public class Authentication {
	
	public Authentication() {
		
	}
	
	
	public void backController(int serviceCode, MemberBean member) {
		//클라이언트 요청(서비스코드)에 따른 JOB분기
		switch(serviceCode) {
		case 1:
			this.duplicateCheck(member);
			break;
			
		case 2:
			this.regUser(member);
			break;
		}
	}
	
	
	/**아이디 중복체크
	 * @author	combiAreum
	 * @name	private duplicateCheck
	 * @param	MemberBean member
	 * @return	boolean isCheck
	 * */
	private boolean duplicateCheck(MemberBean member) {
		boolean isCheck = false;
		
		//중복체크 코드
		
		isCheck = true;
		member.setDuplicateCheck(isCheck);
		return isCheck;
	}
	
	
	private void regMemberControl(MemberBean member) {
	
	//중복체크
		this.duplicateCheck(member);
	
	//등록
		this.regUser(member);
	
	//정보수집
		this.searchMemberInfo(member);
		
	}
	
	
	/**회원정보 등록
	 * @author	combiAreum
	 * @name	private regUser
	 * @param	MemberBean member
	 * @return	boolean isCheck
	 * */
	private boolean regUser(MemberBean member) {
		
		boolean isCheck = false;
		
		// 빈 데이터를 DB에 저장
		// DB에 저장된 값을 다시 불러와서 빈에 저장
		member.setMemberId("ID적합");
		member.setMemberName("Name적합");
		member.setMemberPW("Password 적합");
		member.setMemberAge(51);
		member.setMemberType("개인");
		return isCheck;
		}
	
	
	/** 회원정보검색
	 * @name	private searchMemberInfo
	 * @param	String memberId
	 * @return	String[] memberInfo
	 **/
	
	private MemberBean searchMemberInfo(MemberBean member) {
		MemberBean memberInfo = new MemberBean();
		//데이터타입 변환 array -->bean
		return memberInfo;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
