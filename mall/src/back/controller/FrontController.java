package back.controller;

public class FrontController {
	
	private String[][] members = {{"a", "아름","1234","P"},
								  {"b", "원빈","1234","P"},
								  {"c", "혜지","1234","P"},
								  {"d", "민성","1234","P"},
								  {"e", "선아","1234","P"}};

	public FrontController() {
		
	}
	
	public String[] duplicated(String[] memberInfo) {
		String[] idCheck = new String[1];
		
		// "1" = 사용가능(초기값)
		idCheck[0] = "1";
		
		//id추출해서 데이터베이스에 있는 id와 비교
		for(int index = 0; index < members.length; index++) {
			if(memberInfo[0].equals(members[index][0])) {
				
				// "0" = 사용불가능(id중복됨)
				idCheck[0] = "0";
				break;
			}
		}
		return idCheck;
	}
}
