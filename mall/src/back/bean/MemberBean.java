package back.bean;

public class MemberBean {

	private String MemberId;
	private String MemberName;
	private String MemberPW;
	private String MemberType;
	private int MemberAge;
	private boolean duplicateCheck;
	

	public boolean isDuplicateCheck() {
		return duplicateCheck;
	}
	public void setDuplicateCheck(boolean duplicateCheck) {
		this.duplicateCheck = duplicateCheck;
	}
	public String getMemberId() {
		return MemberId;
	}
	public void setMemberId(String memberId) {
		MemberId = memberId;
	}
	public String getMemberName() {  
		return MemberName;
	}
	public void setMemberName(String memberName) {
		MemberName = memberName;
	}
	public String getMemberPW() {
		return MemberPW;
	}
	public void setMemberPW(String memberPW) {
		MemberPW = memberPW;
	}
	public String getMemberType() {
		return MemberType;
	}
	public void setMemberType(String memberType) {
		MemberType = memberType;
	}
	public int getMemberAge() {
		return MemberAge;
	}
	public void setMemberAge(int memberAge) {		
		MemberAge = memberAge;
	}
	
}
