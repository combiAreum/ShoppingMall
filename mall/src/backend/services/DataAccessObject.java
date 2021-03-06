package backend.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import backend.bean.MemberBean;

class DataAccessObject extends backend.dao.DataAccessObject {

	DataAccessObject(int fileIndex, String filePath) {
		super(fileIndex, filePath);
	}
	
	// memberId를 전달받아서 Member.txt 접근 같은 레코드가 있는 지 파악
	// 같은 Id가 있다면 false 리턴  없다면 true리턴
	boolean duplicateCheck(MemberBean member) {
		boolean check = true;
		File file = new File(this.filePath);
		try {
			//bReader = new BufferedReader(new FileReader(new File(this.filePath)));
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);
			
			String line;
			while((line = bReader.readLine()) != null) {
				String[] record = line.split(",");
								
				if(record[0].equals(member.getMemberId())) {
					check = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { bReader.close();} catch (Exception e) {e.printStackTrace();}
		}
		
		return check;
	}
	
	boolean registrationMember(MemberBean member) {
		boolean isCheck = false;
		File file = new File(this.filePath);
		try {
			fWriter = new FileWriter(file, true);  // true : 이어쓰기
			bWriter = new BufferedWriter(fWriter);
			String record = member.getMemberId() +"," +
							member.getMemberName() + "," +
							member.getMemberPassword() + "," +
							member.getMemberAge() + "," +
							member.getMemberType() + "\n";
			
			bWriter.write(record);
			bWriter.flush();
			isCheck = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try { bWriter.close();} catch (Exception e) {e.printStackTrace();}
		}
		return isCheck;
	}
	
	MemberBean searchMemberInfo(MemberBean member) {
		MemberBean newMember = null;
		File file = new File(this.filePath);
		try {
			//bReader = new BufferedReader(new FileReader(new File(this.filePath)));
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);
			
			String line;

			while((line = bReader.readLine()) != null) {
				String[] record = line.split(",");
				if(record[0].equals(member.getMemberId())) {
					newMember = new MemberBean();
					newMember.setMemberId(record[0]);
					newMember.setMemberName(record[1]);
					newMember.setMemberPassword(record[2]);
					newMember.setMemberAge(Integer.parseInt(record[3]));
					newMember.setMemberType(record[4]);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { bReader.close();} catch (Exception e) {e.printStackTrace();}
		}
		return newMember;
	}

	ArrayList<MemberBean> searchMembersInfo(MemberBean member) {
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		File file = new File(this.filePath);
		try {
			//bReader = new BufferedReader(new FileReader(new File(this.filePath)));
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);
			
			String line;
			
			while((line = bReader.readLine()) != null) {
				String[] record = line.split(",");
				if(record[4].equals(member.getMemberType())) {
					MemberBean newMember = new MemberBean();
					newMember.setMemberId(record[0]);
					newMember.setMemberName(record[1]);
					newMember.setMemberPassword(record[2]);
					newMember.setMemberAge(Integer.parseInt(record[3]));
					newMember.setMemberType(record[4]);
					list.add(newMember);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { bReader.close();} catch (Exception e) {e.printStackTrace();}
		}
		return list;
	}

	
	boolean isAccess(MemberBean member) {
		File file = new File(this.filePath);
		String line;
		String[] record;
		boolean check = false;
		
		try {
			//bReader = new BufferedReader(new FileReader(new File(this.filePath)));
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);

			while((line = bReader.readLine()) != null) {
				//line --> split
				record = line.split(",");
				
				//아이디 비교
				if(record[0].equals(member.getMemberId())) {
					//패스워드 비교	
					if(record[2].equals(member.getMemberPassword())){
						check = true;
					}
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { bReader.close();} catch (Exception e) {e.printStackTrace();}
		}
		return check;
	}
	
	
	
	
	
	
	
	
	
	void fileRead() {
		File file = new File(this.filePath);
		try {
			//bReader = new BufferedReader(new FileReader(new File(this.filePath)));
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);
			
			String record;

			while((record = bReader.readLine()) != null) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { bReader.close();} catch (Exception e) {e.printStackTrace();}
		}
	}

	void fileWriter() {
		File file = new File(this.filePath);
		try {
			fWriter = new FileWriter(file);
			bWriter = new BufferedWriter(fWriter);
			String record;


		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try { bWriter.close();} catch (Exception e) {e.printStackTrace();}
		}
	}
}
