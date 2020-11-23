package view;

import java.util.Scanner;

import backend.control.FrontController;

public class FrontEnd {
	private FrontController fc;
	private Scanner scanner;
	
	public FrontEnd() {
		fc = new FrontController();
		this.viewControl(this.makeTitle());
		scanner.close();
	}
	
	private void viewControl(String title) {
		int menuCode = 0;
		
		String[][] menu = {	{"MAIN", "LogIn", "Join", "Close"},
							{"LOGIN"},
							{"JOIN", "Personal", "Company", "Back"}};
				
		while(true) {
			this.display(title);
			this.display(this.makeMenu(true, menu, menuCode));			
			menuCode = Integer.parseInt(this.userInput());
			if(menuCode == 0) {	
				break;
			} else if(menuCode == 1) {	
				this.login(title);
				menuCode = 0;
			} else if(menuCode == 2) {	
				this.join(title, menu, menuCode);
				menuCode = 0;
			}
			
		}
	}
	
	private void login(String title) {
		this.display(title);
		this.display("로그인");
	}
	
	private void join(String title, String[][] menu, int menuCode) {
		String[] userInfo = new String[5];
		boolean idCheck = false;
		boolean menuCheck = true;
		int code;
		String[] joinInfo;
						
		this.display(title);
		while(!idCheck) {
			this.display(this.makeInputItem(menuCheck, "JOIN", "USER ID"));
			userInfo[0] = this.userInput();
			menuCheck = false;
			// 서버 전송 :: id중복여부 체크
			idCheck = (fc.duplicateId(userInfo)[0].equals("0"))? false : true;
		}
		
		this.display(this.makeInputItem(menuCheck, null, "USER NAME"));
		userInfo[1] = this.userInput();
		this.display(this.makeInputItem(menuCheck, null, "USER PASSWORD"));
		userInfo[2] = this.userInput();
		this.display(this.makeInputItem(menuCheck, null, "USER AGE"));
		userInfo[3] = this.userInput();
		
		this.display(makeMenu(menuCheck, menu, menuCode));
		code = Integer.parseInt(this.userInput());
		if(code >= 1 && code <= menu[menuCode].length-2) {
			userInfo[4] = (code==1? "P": "C");
		}
		
		// 회원가입 서버전송
		// 서버로부터 가입된 회원정보를 리턴받아 화면 출력
		joinInfo = fc.joinMember(userInfo);
				
		// 사용자로 부터 Confirm 후 메인화면 복귀
		menuCheck = true;
		this.display(title);
		this.display(this.makeInputItem(menuCheck, "JOIN Successful", "USER ID"));
		this.display(joinInfo[0] + "\n");
		
		menuCheck = false;
		
		this.display(this.makeInputItem(menuCheck, null, "USER NAME"));
		this.display(joinInfo[1] + "\n");
		this.display(this.makeInputItem(menuCheck, null, "USER PASSWORD"));
		this.display(joinInfo[2] + "\n");
		this.display(this.makeInputItem(menuCheck, null, "USER AGE"));
		this.display(joinInfo[3] + "\n");
		this.display(this.makeInputItem(menuCheck, null, "USER TYPE"));
		this.display(joinInfo[4] + "\n");
		
		this.display(this.makeResult(true, "PRESS ANY KEY"));
		this.userInput();
	}
	
	private String makeInputItem(boolean type, String step, String item) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(type? "     [ " + step + " ]\n": "");
		sb.append(type? "     -------------------------------------------------------\n":"");
		sb.append("      [ " + item + " ] : ");
		
		return sb.toString();
	}
	
	private String makeResult(boolean type, String result) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("     ------------------------------------------- "+ result);
		sb.append(type? " : " : "");
		
		return sb.toString();
	}
	
	private String makeTitle() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n\n\n\n");
		sb.append("ᕦ(ò_óˇ)ᕤ ___________________________________________________________________\n");
		sb.append("   +                                                         +\n");
		sb.append("   +             Second Project :: Shopping Mall             +\n");
		sb.append("   +                                                         +\n");
		sb.append("   +                              Designed by HoonZzang      +\n");
		sb.append("   +                                                         +\n");
		sb.append("   + _______________________________________________________ +\n");
		sb.append("\n");
		
		return sb.toString();
	}
	
	private String makeMenu(boolean type, String[][] menu, int menuCode) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(type?"     " + "[ " + menu[menuCode][0] + " ]\n":"");
		
		if(menu[menuCode].length > 1) {
			sb.append("     -------------------------------------------------------\n");
			for(int index=1; index < menu[menuCode].length-1; index++) {
				sb.append("       " + index + ". " + menu[menuCode][index] + ((index%2==0)? "\n":"       "));
			}
			sb.append("       0. " + menu[menuCode][menu[menuCode].length-1] + "\n");
			sb.append("     ------------------------------------------- Select : ");
		}		 
	   	     
		return sb.toString();
	}
	
	private String userInput() {
		scanner = new Scanner(System.in);
		return scanner.next();
	}
	
	private void display(String basket) {
		System.out.print(basket);
	}
	
}








