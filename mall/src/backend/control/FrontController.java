package backend.control;

import backend.bean.MemberBean;
import backend.services.*;

public class FrontController {
	private Authentication auth;	
	private Goods goods;
	private GoodsManagements management;
	private Purchase purchase;
	
	public FrontController() {
		auth = new Authentication();
		goods = new Goods();
		management = new GoodsManagements();
		purchase = new Purchase();
	}
	
	/** 아이디 중복 체크 
	 * @name		public duplicateId
	 * @param 		String[] userInfo
	 * @return  	String[] idCheck
	 * @serviceCode 1
	 * @references  Authentication
	 */
	public String[] duplicateId(String[] userInfo) {
		String[] idCheck = new String[1];  // respons로 활용할 배열
		MemberBean member = new MemberBean();
		
		// 클라이언트 요청 처리를 위한 데이터 이동 : DTO : array --> bean
		member.setMemberId(userInfo[0]);
		auth.backController(1, member);
		
		// 클라이언트에 응답하기 위해 데이터 이동 : DTO : bean --> array
		idCheck[0] = member.isDuplicateCheck()? "1": "0";
		
		return idCheck;
	}
	
	/** 회원가입
	 * @name		public joinMember
	 * @param		String[] userInfo
	 * @return		String[] memberInfo
	 * @serviceCode 2
	 * @references 	Authentication
	 */
	public String[] joinMember(String[] userInfo) {
		String[] memberInfo = new String[5];
		MemberBean member = new MemberBean();
		
		// 클라이언트 요청 처리를 위한 데이터 이동 : DTO : array --> bean
		member.setMemberId(userInfo[0]);
		member.setMemberName(userInfo[1]);
		member.setMemberPassword(userInfo[2]);
		member.setMemberAge(Integer.parseInt(userInfo[3]));
		member.setMemberType(userInfo[4]);
				
		auth.backController(2, member);
		
		// 클라이언트에 응답하기 위해 데이터 이동 : DTO : bean --> array
		memberInfo[0] = member.getMemberId();
		memberInfo[1] = member.getMemberName();
		memberInfo[2] = member.getMemberPassword();
		memberInfo[3] = member.getMemberAge() + "";
		memberInfo[4] = member.getMemberType();
		
		return memberInfo;
	}	
}










