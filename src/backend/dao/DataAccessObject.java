package backend.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class DataAccessObject {
	protected String filePath;
	protected String[] fileList = 
		{"Member.txt",		//0
		 "Goods.txt",		//1
		 "SaleGoods.txt", 	//2
		"Recent.txt",		//3
		"ShoppingBasket.txt", //4
			"Purchase.txt",	//5
			"PurchaseDetail.txt",	//6
			"History.txt"};	//7
	protected File file;
	protected FileReader fReader;
	protected FileWriter fWriter;
	protected BufferedReader bReader;
	protected BufferedWriter bWriter;

	protected DataAccessObject(int fileIndex, String filePath) {
		this.filePath = filePath + "\\" + fileList[fileIndex];
	}

}
