package backend.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import backend.bean.MemberBean;

public class Authentication {
	private DataAccessObject dao;
	private String fPath;
	
	public Authentication() {
		this.fPath = "C:\\Kimareum\\Java\\Project\\mall201125\\src\\data"; 
	}
	
	public ArrayList<MemberBean> backController(int serviceCode, MemberBean member) {
		ArrayList<MemberBean> list = null;
		// 클라이언트 요청(serviceCode)에 따른 Job 분기
		switch(serviceCode) {
		case 1:
			this.duplicateCheck(member);
			break;
		case 2:
			this.regMemberControl(member);
			break;
		case 3:
			list = this.searchMembersInfo(member);
			break;
		case -1 :
			this.accessOut(member);
			break;
		}
		return list;
	}
	
	/* 매서드 오버로딩 
	 * 	같은 클래스내에서 동일한 이름의 매서드를 사용가능하게 하는 특성
	 *  : 사용되어지는 파라미터변수의 타입이나 갯수가 다르면 사용가능
	 *  : 외부에서 접근시 동일한 이름을 제공함으로써 접근성을 편하게 하는 특징.
	 * */
	public MemberBean backController(String serviceCode, MemberBean member) {
		MemberBean accessInfo = null;
		// 클라이언트 요청(serviceCode)에 따른 Job 분기
		switch(serviceCode) {
		case "A":
			accessInfo = this.accessControl(member);
			break;
		}
		return accessInfo;
	}
	/** 로그아웃 
	 * @name 	accessOut
	 * @param	MemberBean member
	 * 
	 */
	// History Table에서 로그인 상태 여부 확인
	// :: 해당 계정의 액세스 타입을 숫자로 변환해서 누적합  -> 최종값 : 0 또는 1  <-- DAO
	// ::: 0인 경우 --> 로그아웃 상태       1--> 로그인상태
	// 1--> History Table에 로그아웃 기록
	// 0 --> History Table 작업 필요 X
	private void accessOut(MemberBean member) {
		dao = new DataAccessObject(7, this.fPath);
		if(dao.isLogOut(member)) {
			member.setAccessType(-1);
			member.setAccessTime(this.getNow());
			dao.writeHistory(member);
		}
		
	}
	/** 회원유무 확인
	 *  
	 * @param    MemberBean member
	 * @return 	 boolean  :: true - 회원    false - 비회원
	 */
	/** 아이디 중복체크
	 *  @author 	hoonzzang
	 *  @name		private  duplicateCheck
	 *  @param		MemberBean member
	 *  @return 	boolean isCheck
	 */
	private boolean duplicateCheck(MemberBean member) {
		boolean isCheck = false; // true:: 사용가능   false :: 사용불가능
		dao = new DataAccessObject(0, this.fPath);
				
		isCheck = dao.duplicateCheck(member);
		member.setDuplicateCheck(isCheck);
		
		return isCheck;
	}
	
	/* 로그인 제어 메서드 */
	private MemberBean accessControl(MemberBean member) {
		MemberBean accessInfo = null;
		// 1. 회원유무 확인 :: 아이디만 확인
		if(!this.duplicateCheck(member)) {
			// 2. 로그인 정보 일치 확인 :: 아이디와 패스워드
			if(this.isAccess(member)) {
				// Append : History Table에 로그인 남기기 :: id, time, 1
				member.setAccessTime(this.getNow());
				member.setAccessType(1);
				
				// History table 에 로그인 기록 쓰기
				this.writeLogInInfo(member);
				// 3. 로그인 계정의 정보 수집 : 아이디, 이름, 회원타입
				accessInfo = this.searchMemberInfo(member);
				//[ hoon(임훈:Company) AccessTime : 20201124095000]
				accessInfo.setAccessTime(this.searchAccessTime(member));
				
				// 4. 계정의 정보 중  패스워드와 나이는 삭제	
				accessInfo.setMemberPassword(null);
				accessInfo.setMemberAge(0);
			}
		} 		
		return accessInfo;
	}
	
	private String searchAccessTime(MemberBean member) {
		ArrayList<MemberBean> list;
		int accessTime = -1;
		//boolean check = true;
		
		dao = new DataAccessObject(7, this.fPath);
		list = dao.readAccessInfo(member);
		// list에서 최대값을 갖는 데이터를 추출
		
		for(MemberBean data : list) {
			if(accessTime < Integer.parseInt(data.getAccessTime())) { 
				accessTime = Integer.parseInt(data.getAccessTime());
			}
			
//			if(check) {
//				accessTime = Integer.parseInt(data.getAccessTime());
//				check = false;
//			}else {
//				if(accessTime < Integer.parseInt(data.getAccessTime())) {
//					accessTime = Integer.parseInt(data.getAccessTime();
//				}
//			}
		}
		return accessTime+"";
	}
	
	private void writeLogInInfo(MemberBean member) {
		dao = new DataAccessObject(7, this.fPath);
		dao.writeHistory(member);
	}
	
	private String getNow() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return sdf.format(date);
	}

	/** 로그인정보 일치 확인
	 * @name	 isAccess 
	 * @param    MemberBean member
	 * @return 	 boolean  :: true - 로그인 성공   false - 로그인 실패 
	 */
	private boolean isAccess(MemberBean member) {
		dao = new DataAccessObject(0, this.fPath);
		return dao.isAccess(member);
	}
	
	
	private void regMemberControl(MemberBean member) {
		// 중복체크
		if(this.duplicateCheck(member)) {
			// 등록
			if(this.regUser(member)) {
				// 정보수집
				member = this.searchMemberInfo(member);
			}
		}			
	}
	
	/** 회원정보 등록
	 *  @author 	hoonzzang
	 *  @name		private regUser
	 *  @param		MemberBean member
	 *  @return		boolean isCheck
	 */
	private boolean regUser(MemberBean member) {
		dao = new DataAccessObject(0, this.fPath);
		
		// 등록요청 // 등록 여부 리턴
		
		return dao.registrationMember(member);
	}
	
	/** 회원정보 검색
	 * @name		private  searchMemberInfo
	 * @param		String 	 memberId
	 * @return 		MemberBean memberInfo
	 */
	private MemberBean searchMemberInfo(MemberBean member) {
		dao = new DataAccessObject(0, this.fPath);
		return dao.searchMemberInfo(member);
	}	
	
	private ArrayList<MemberBean> searchMembersInfo(MemberBean member) {
		dao = new DataAccessObject(0, this.fPath);
		return dao.searchMembersInfo(member);
	}

}








