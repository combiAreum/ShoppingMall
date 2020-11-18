package view;

import java.util.Scanner;

import back.controller.FrontController;

public class FrontEnd {
	
	//필드생성
	private FrontController fc;
	
	//생성자 호출
	public FrontEnd() {
		
		//할당
		fc = new FrontController();
		
		this.viewControl( this.makeTitle() );		
	} 
		
	
	
	private void viewControl( String title ) {
		
		int menuCode = 0;
		
		String[][] menu = {{"MAIN", "LogIn", "Join", "Close"},
						   {"LogIn"},
						   {"Join", "Personal", "Company", "Back"}};

		while(true) {
			this.print( title );
			this.print( this.printMenu( true, menu, menuCode ) );

			menuCode = Integer.parseInt( this.clientInput() );

			if(menuCode == 0) {
				break;
			}
			else if(menuCode == 1) {
				this.login(title);
				menuCode = 0;
			}
			else if(menuCode == 2) {
				this.join(title, menu, menuCode);
			}
			
			}

		}	
	
	
	private void login(String title) {
		this.print(title);
		this.print("로그인");
	}
	
	private void join(String title, String[][] menu, int menuCode) {
		
		String[] userInfo = new String[5];
		String[] joinInfo = new String[5];
		boolean idCheck = false;
		boolean menuCheck = true;
		int code;
		
		this.print( title );
		
		while(!idCheck) {
			this.print( this.makeInputItem ( menuCheck, "Join", "USER ID" ) );
			userInfo[0] = this.clientInput();
			
			//서버전송: id 중복여부체크
			idCheck = ( ( fc.duplicated (userInfo) [0]  ) . equals("0") ) ? false : true;
		
			menuCheck = false;
		}
		
		this.print(this.makeInputItem(false, null, "USER NAME"));
		userInfo[1] = this.clientInput();
		
		this.print(this.makeInputItem(false, null, "USER PW"));
		userInfo[2] = this.clientInput();
		
		this.print(this.makeInputItem(false,  null, "USER AGE"));
		userInfo[3] = this.clientInput();
		
		this.print(this.printMenu(false, menu, menuCode));
		//
		code = Integer.parseInt(this.clientInput());
		if(code >= 1 && code <= menu[menuCode].length-2) {
			userInfo[4] = (code==1? "P": "C"); 
		}
		
		//서버전송
		//서버로부터  가입된 회원정보를 리턴받아 화면출력
		joinInfo = fc.joinMember(userInfo);
		
		//사용자로부터 confirm 후 메인화면 복귀
		this.print(title);
		
		this.print(this.makeInputItem(true, "JOIN Successful", "USER ID"));
		this.print(joinInfo[0] + "\n");
		
		
		this.print(this.makeInputItem(false, null, "USER NAME"));
		this.print(joinInfo[1] + "\n");
		this.print(this.makeInputItem(false, null, "USER PASSWORD"));
		this.print(joinInfo[2] + "\n");
		this.print(this.makeInputItem(false, null, "USER AGE"));
		this.print(joinInfo[3] + "\n");
		this.print(this.makeInputItem(false, null, "USER TYPE"));
		this.print(joinInfo[4] + "\n");
		this.print(this.makeResult(true, "PRESS ANY KEY"));
		this.clientInput();
	}
	

	private String makeInputItem(boolean type, String step, String item) {
		StringBuffer sb = new StringBuffer();
			
			sb.append( type ? "[" + step + "]\n" : "");
			sb.append( type ? "-------------------------------------------------------\n": "");
			sb.append("[" + item + "]: ");
			
			return sb.toString();
		}
	
	
	private String makeResult(boolean type, String result) {
		StringBuffer sb = new StringBuffer();
		
			sb.append("-------------------------------------------------------" + result);
			sb.append(type? ":": "");
			
			return sb.toString();
	}
	
	
	private String makeTitle() {
		StringBuffer sb = new StringBuffer();
		sb.append("ᕦ(ò_óˇ)ᕤ ________________________________\n\n");
		sb.append("  Second Project :: Shopping Mall\n");
		sb.append("  Designed by AREUM\n\n");
		sb.append("_____________________________ᕦ(ò_óˇ)ᕤ\n");
		sb.append("\n");
		return sb.toString();
	}
	
	private String printMenu(boolean type, String [][] menu, int menuCode) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(type? ("[" + menu[menuCode][0]+"]\n"): "\n");
		
	
		
		if(menu[0].length > 1) {
			sb.append("-------------------------------------------------------\n");
			for( int index = 1 ; index < menu[menuCode].length-1; index++) {
				
				sb.append("  " +index + ". " + menu[menuCode][index] + ( ( index%2 == 0 ) ? "\n": "   ") );
			}//					  1      .                               
			
			sb.append("  " + "0. " + menu[menuCode][ menu[menuCode].length-1 ] + "\n");
									
			sb.append("------------------------------------------------------- Select : ");
		}
		
		return sb.toString();
		
	}
	
	
	private String clientInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}
	
	private void print(String text) {
		System.out.print(text);
	}
	
	
	
}
