package backend.bean;

public class GoodsBean {
	// 공통
	private String goodsCode;
	// SaleGoods
	private String goodsSaler;
	private int goodsPrice;
	private int goodsStock;
	// Goods
	private String goodsName;
	private String goodsCategory;
	private String goodsDetails;
	
	public String getGoodsSaler() {
		return goodsSaler;
	}
	public void setGoodsSaler(String goodsSaler) {
		this.goodsSaler = goodsSaler;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public int getGoodsStock() {
		return goodsStock;
	}
	public void setGoodsStock(int goodsStock) {
		this.goodsStock = goodsStock;
	}
	public String getGoodsCategory() {
		return goodsCategory;
	}
	public void setGoodsCategory(String goodsCategory) {
		this.goodsCategory = goodsCategory;
	}
	public String getGoodsDetails() {
		return goodsDetails;
	}
	public void setGoodsDetails(String goodsDetails) {
		this.goodsDetails = goodsDetails;
	}
}
