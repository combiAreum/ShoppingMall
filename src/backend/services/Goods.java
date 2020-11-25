package backend.services;

import java.util.ArrayList;

import backend.bean.GoodsBean;

public class Goods {
	private DataAccessObject dao;
	private String fPath;
	
	public Goods() {
		this.fPath = "C:\\Kimareum\\Java\\Project\\mall201125\\src\\data"; 
	}
	//7
	public ArrayList<GoodsBean> backController(int serviceCode, GoodsBean word) {
		ArrayList<GoodsBean> gList = null;
		
		if(serviceCode == 1) {
			gList = this.searchGoodsCtl(word);
		}
		
		// Job을 수행할 매서드 분기 :: IF ~~
		
		return gList;
	}
	//6
	private ArrayList<GoodsBean> searchGoodsCtl(GoodsBean word) {
		ArrayList<GoodsBean> gList = new ArrayList<GoodsBean>();
		ArrayList<String> gCode;
		
		// word --> Goods.txt Search --> goodsCode
		gCode = this.searchGoodsCode(word);
		// goodsCode --> SaleGoods.txt --> hoon,1001,1500,50 --> goodsBean --> gList
		this.getSaleInfo(gCode, true, gList);
		// goodsCode --> Goods.txt --> 새우깡,과자,농심에서 만든 60년 된 과자 --> goodsBean --> gList
		this.getSaleInfo(gCode, false, gList);
		return gList;
	}
	//5
	private ArrayList<String> searchGoodsCode(GoodsBean word) {
		ArrayList<String> gCode = null;
		dao = new DataAccessObject(1, this.fPath);
		gCode = dao.getGoodsCode(word);
		
		System.out.println(gCode.size());
				
		return gCode;
	}
	//4
	private void getSaleInfo(ArrayList<String> gCode, boolean searchType, 
			ArrayList<GoodsBean> gList) {
		int fileIndex = searchType? 2 : 1;
		dao = new DataAccessObject(fileIndex, this.fPath);
		if(searchType) {
			dao.getSalesInfo(gCode, gList);
			System.out.println(gList.size());
		}else {
			dao.getGoodsInfo(gList);
			
		}
		
	}
	
}









