package Practice;

public class test {
	public void loop() {
			
		//return - void 잘 모르겠음
		// 반복문 연습
		
		String[] current = new String[11];
		
		current[0] = "main";
		current[1] = "join1";
		current[2] = "join2";
		current[3] = "join3";
		current[4] = "login";
		current[5] = "mypage";
		current[6] = "main1";
		current[7] = "main2";
		current[8] = "main3";
		current[9] = "logout";
		current[10] = "areum";
		
		
		int index = 0;
		while(true) {
			System.out.print(current[index] + "\n");
			index += 1;
			if(index == current.length-1) {
			
				break;
			}
		}
		
		index = 0;
		for(;true;) {
			System.out.print(current[index] + "\n");
			index += 1;
			if(index == current.length-1) {
			
				break;
			}
		}
		
		index = 0;
		while(index<current.length-1) {
			System.out.print(current[index] + "\n");
			index += 1;
		}
		
		
		for(index=0; index<current.length-1; index++) {
			System.out.print(current[index] + "\n");
		}
	
	}
}
