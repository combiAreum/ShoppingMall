package backend.control;

import java.util.ArrayList;
import java.util.HashMap;

import backend.bean.GoodsBean;
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
		String[] idCheck = new String[1];  // response로 활용할 배열
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
	
	public String[][] searchMembersInfo(String[] memberType) {
		ArrayList<MemberBean> list;
		// arr --> bean
		MemberBean member = new MemberBean();
		member.setMemberType(memberType[0]);
		list = auth.backController(3, member);
		//list --> arr[][]
		String[][] memberList = new String[list.size()][5];
		for(int index=0; index<list.size(); index++) {
			memberList[index][0] = list.get(index).getMemberId();
			memberList[index][1] = list.get(index).getMemberName();
			memberList[index][2] = list.get(index).getMemberPassword();
			memberList[index][3] = list.get(index).getMemberAge() + "";
			memberList[index][4] = list.get(index).getMemberType().equals("P")? "개인" : "판매자";
		}
		return memberList;
	}
	
	public String[] memberAccess(String[] accessInfo) {
		String[] memberInfo = null;
		MemberBean member = new MemberBean();  // array --> bean
		// 1. Array --> Bean  :: MemberBean
		member.setMemberId(accessInfo[0]);
		member.setMemberPassword(accessInfo[1]);
		// 2. Service Call :: serviceCode = "A"
		member = auth.backController("A", member);
		// 3. bean --> array
		if(member != null) {
			memberInfo = new String[4];
			memberInfo[0] = member.getMemberId();
			memberInfo[1] = member.getMemberName();
			memberInfo[2] = (member.getMemberType().equals("P"))? "Personal" : "Company";
			memberInfo[3] = member.getAccessTime();
		}
		
		return memberInfo;
	}
	
	public String[] accessOut(String[] memberInfo) {
		// array --> bean
		MemberBean member = null;
		
		if(memberInfo != null) {
			member = new MemberBean();
			member.setMemberId(memberInfo[0]);
		}
		
		auth.backController(-1, member);
		
		return null;
	}
	
	//8 상품검색
	public ArrayList<GoodsBean> searchGoods(HashMap<String, String> map){
		ArrayList<GoodsBean> gList;
		GoodsBean goodsInfo = new GoodsBean();;
		//1. map --> GoodsBeans
		goodsInfo.setGoodsDetails(map.get("Word"));
		
		//2. Goods Class Call :: 1, GoodsBeans
		gList = goods.backController(1, goodsInfo);
		
		//3. ArrayList<GoodsBeans> 리턴 받기
		//4. ArrayList<GoodsBeans> FrontEnd로 전달
		
		return gList;
	}
}
















