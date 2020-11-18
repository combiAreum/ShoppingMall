package back.controller;

import back.bean.MemberBean;
import back.services.*;

public class FrontController {
	//필드만들고 한번만 하자 메소드마다 뉴 하지말구
	private Authentication auth;
	private Goods goods;
	private GoodsManagement management;
	private Purchase purchase;
	
	public FrontController() {
		auth = new Authentication();
		goods = new Goods();
		management = new GoodsManagement();
		purchase = new Purchase();
	}
	
	/**아이디 중복체크
	 * @name	public duplicateId
	 * @param	String[] userInfo
	 * @return	String[] idCheck
	 * @serviceCode 1
	 * @reference Authentication
	 **/
	
	public String[] duplicated(String[] userInfo) {
		String[] idCheck = new String[1];
		MemberBean member = new MemberBean();
		
		//클라이언트 요청처리를 위한 데이터 이동: DTO: array --> bean
		member.setMemberId(userInfo[0]);
		auth.backController(1, member);
		
		
		//클라이언트에 응답하기 위해 데이터 이동: DTO: bran --> array
		idCheck[0] = member.isDuplicateCheck() ? "1" : "0";
		
		return idCheck;
	}

	/** 회원가입 
	 * @name	public joinMember
	 * @param	String[] userInfo
	 * @return	String[] memberInfo
	 * @serviceCode 2
	 * @reference Authentication
	 **/
	
	public String[] joinMember(String[] userInfo) {
		String[] memberInfo = new String[5];
		
		MemberBean member = new MemberBean();
		
		//클라이언트 요청 처리를 위한 데이터 이동: DTO: array --> bean
		member.setMemberId( userInfo[0] );
		member.setMemberName(userInfo[1]);
		member.setMemberPW(userInfo[2]);
		member.setMemberAge( Integer.parseInt( userInfo[3] ) );
		member.setMemberType(userInfo[4]);
		
		auth.backController(2, member);
		//클라이언트에 응답하기 위해 데이터 이동: DTO: bean --> array
		memberInfo[0] = member.getMemberId();
		memberInfo[1] = member.getMemberName();
		memberInfo[2] = member.getMemberPW();
		memberInfo[3] = member.getMemberAge() + "";
		memberInfo[4] = member.getMemberType();
		
		return memberInfo;
	}
	
	
	


}














