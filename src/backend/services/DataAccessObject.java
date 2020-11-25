package backend.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import backend.bean.GoodsBean;
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
				// line  --> split
				record = line.split(",");
				// 아이디 비교
				if(record[0].equals(member.getMemberId())) {
					// 패스워드 비교
					if(record[2].equals(member.getMemberPassword())) {
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

	// History Table에 쓰기
	void writeHistory(MemberBean member) {
		File file = new File(this.filePath);
		try {
			fWriter = new FileWriter(file, true);
			bWriter = new BufferedWriter(fWriter);
			String record = member.getMemberId() + "," + member.getAccessTime()+ "," + member.getAccessType()+"\n";
			bWriter.write(record);
			bWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try { bWriter.close();} catch (Exception e) {e.printStackTrace();}
		}
	}

	// History Table 데이터 읽기(로그아웃 전 정보읽기)
	boolean isLogOut(MemberBean member){
		File file = new File(this.filePath);
		int outCheck = 0;
		String[] record = null;

		try {
			//bReader = new BufferedReader(new FileReader(new File(this.filePath)));
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);

			String line;
			while((line = bReader.readLine()) != null) {
				record = line.split(",");
				if(record[0].equals(member.getMemberId())) {					
					outCheck += Integer.parseInt(record[2]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { bReader.close();} catch (Exception e) {e.printStackTrace();}
		}

		return outCheck == 1? true: false;
	}

	// History Table 데이터 읽기(로그인 후 정보읽기)
	ArrayList<MemberBean> readAccessInfo(MemberBean member){
		File file = new File(this.filePath);
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		String[] record = null;

		try {
			//bReader = new BufferedReader(new FileReader(new File(this.filePath)));
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);

			String line;
			while((line = bReader.readLine()) != null) {
				record = line.split(",");
				if(record[0].equals(member.getMemberId())) {
					if(record[2].equals(member.getAccessType()+"")) {
						MemberBean accessData = new MemberBean();
						/* String.substring(beginIndex)
						 * ex >   yyyyMMddHHmmss
						 *        01234567890123  
						 * String.substring(beginIndex, endIndex)
						 * ddHH   --> (6,10)
						 * */
						accessData.setAccessTime(record[1].substring(8));
						list.add(accessData);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { bReader.close();} catch (Exception e) {e.printStackTrace();}
		}

		return list;
	}

	// History Table 데이터 읽기(로그인 후 정보읽기) --> StringTokenizer
	ArrayList<MemberBean> readAccessInfo2(MemberBean member){
		File file = new File(this.filePath);
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		String[] record = null; 
		StringTokenizer tokens = null;

		try {
			//bReader = new BufferedReader(new FileReader(new File(this.filePath)));
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);

			String line;
			while((line = bReader.readLine()) != null) {
				tokens = new StringTokenizer(line, ",");
				record = new String[tokens.countTokens()];
				int index = -1;
				while(tokens.hasMoreTokens()) {
					index++;
					record[index] = tokens.nextToken();
				}
				if(record[0].equals(member.getMemberId())){
					if(record[2].equals(member.getAccessType()+"")) {
						MemberBean accessInfo = new MemberBean();
						accessInfo.setAccessTime(record[1].substring(8));
						list.add(accessInfo);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { bReader.close();} catch (Exception e) {e.printStackTrace();}
		}

		return list;
	}
	
	
	//1
	ArrayList<String> getGoodsCode(GoodsBean word) {
		ArrayList<String> gCode = new ArrayList<String>();
		File file = new File(this.filePath);
		try {
			//bReader = new BufferedReader(new FileReader(new File(this.filePath)));
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);

			String line;

			while((line = bReader.readLine()) != null) {
				String[] record = line.split(",");
				for(String item : record) {
					if(item.equals(word.getGoodsDetails())) {
						gCode.add(record[0]);			
						break;
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { bReader.close();} catch (Exception e) {e.printStackTrace();}
		}	
		
		return gCode;
	}
	//2
	void getSalesInfo(ArrayList<String> gCode, ArrayList<GoodsBean> gList) {
		File file = new File(this.filePath);
		try {
			//bReader = new BufferedReader(new FileReader(new File(this.filePath)));
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);

			String line;
			while((line = bReader.readLine()) != null) {
				String[] record = line.split(",");
				
				for(int index=0; index<gCode.size(); index++) {
					if(gCode.get(index).equals(record[1])) {
						GoodsBean gInfo = new GoodsBean();
						gInfo.setGoodsSaler(record[0]);
						gInfo.setGoodsCode(record[1]);
						gInfo.setGoodsPrice(Integer.parseInt(record[2]));
						gInfo.setGoodsStock(Integer.parseInt(record[3]));
						gList.add(gInfo);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { bReader.close();} catch (Exception e) {e.printStackTrace();}
		}
	}	
	//3
	void getGoodsInfo(ArrayList<GoodsBean> gList) {
		File file = new File(this.filePath);
		try {
			//bReader = new BufferedReader(new FileReader(new File(this.filePath)));
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);

			String line;

			while((line = bReader.readLine()) != null) {
				String[] record = line.split(",");
				for(int index = 0; index<gList.size(); index++) {
					if(gList.get(index).getGoodsCode().equals(record[0])) {
						gList.get(index).setGoodsName(record[1]);
						gList.get(index).setGoodsCategory(record[2]);
						gList.get(index).setGoodsDetails(record[3]);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { bReader.close();} catch (Exception e) {e.printStackTrace();}
		}
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
