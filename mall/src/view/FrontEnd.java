package view;

import java.util.Scanner;

public class FrontEnd {

	//생성자 호출
	public FrontEnd() {
		this.viewControl(this.makeTitle());
				
	}
	
	
	private void viewControl(String title) {
		while(true) {
			
			this.screenview(title);
			this.screenview(this.makeSubTitle());
			this.screenview(this.makeMenu());
			
			if(this.clientInput().equals("0")) {
				break;
			}
		}
		this.screenview("종료됨.");
	}
	

	private String makeTitle() {
		
		StringBuffer sb= new StringBuffer();
		sb.append("ᕦ(ò_óˇ)ᕤ ________________________________\n\n");
		sb.append("Second Project :: Shopping Mall\n");
		sb.append("   Designed by AREUM\n\n");
		sb.append("_____________________________ᕦ(ò_óˇ)ᕤ\n");
		sb.append("\n\n");
		return sb.toString();
	}
	
	private String makeSubTitle() {
		
		StringBuffer sb= new StringBuffer();
		sb.append("[ JOIN :: STEP 1 ]\n");
		
		return sb.toString();
	}
	
	private String makeMenu() {
		StringBuffer sb= new StringBuffer();
		sb.append("------------------------------------\n");
		sb.append("1. 로그인         2. 회원가입        0.종료\n");
		sb.append("----------------------------Select :  ");
		return sb.toString();
	}
	
	
	private String clientInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}
	
	private void screenview(String box) {
		System.out.print(box);
	}
	
}
