package view;

import java.util.Scanner;

public class FrontEnd {
	//생성자 호출
	public FrontEnd() {
		this.viewControl(this.makeTitle());		
	}
		
	private void viewControl(String title) {
		
		int menuCode = 0;
		
//		String[][] menu = new String[3][];
//		
//		String[] menu1 = new String[4];
//		menu1[0] = "Main";
//		menu1[1] = "로그인";
//		menu1[2] = "회원가입";
//		menu1[3] = "종료";
//		menu[0] = menu1;
//		
//		String[] menu2 = new String[1];
//		menu2[0] = "로그인";
//		menu[1] = menu2;
//		
//		String[] menu3 = new String[3];
//		menu3[0] = "회원가입";
//		menu3[1] = "개인회원";
//		menu3[2] = "기업회원";
//		menu[2] = menu3;
		
//		위의 것 이렇게 바꿀수 있음!
		String[][] menu = {{"MAIN", "LogIn", "Join", "Close"},
						   {"LogIn"},
						   {"Join", "Personal", "Company", "Back"}};
		
		while(true) {
			this.print(title);
			this.print(this.printMenu(menu, menuCode));

			menuCode = Integer.parseInt(this.clientInput());

			if(menuCode == 0)
				break;
			
			while(true) {
				this.print(title);
				this.print(this.printMenu(menu, menuCode));

				menuCode = Integer.parseInt(this.clientInput());
				
				if(menuCode == 0) 
					break;
			}
					
		}	
		this.print("종료됨.");
	}


	private String makeTitle() {
		StringBuffer sb = new StringBuffer();
		sb.append("ᕦ(ò_óˇ)ᕤ ________________________________\n\n");
		sb.append("Second Project :: Shopping Mall\n");
		sb.append("   Designed by AREUM\n\n");
		sb.append("_____________________________ᕦ(ò_óˇ)ᕤ\n");
		sb.append("\n\n");
		return sb.toString();
	}
	
	private String printMenu(String[][] menu, int menuCode) {
		
		int index = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("[" + menu[menuCode][index] + "]\n");
		
		if(menu[menuCode].length != 1) {
			sb.append("------------------------------------\n");
			for(index=1; index<menu[menuCode].length-1; index++) {

				sb.append(index + "." + menu[menuCode][index]);

				if(index % 2 == 0 ) {
					sb.append("\n");
				}
				else{
					sb.append("   ");
				}
			}
			sb.append( 0 + "." + menu[menuCode][menu[menuCode].length-1] +"\n");
			sb.append("------------------------------ Select :  \n");
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
