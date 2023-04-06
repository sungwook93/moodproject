package com.edu.order.dto;

public class CartDTO {


	private int cart_num; //장바구니 번호
	private String product_code; //상품 코드
	private String userID;//회원 아이디
	private int product_amount; //상품 수량
	private int product_price;	//상품 가격
	private String product_size; //상품 사이즈
	private String product_color;	//상품 색상
	private String product_type; //상품 타입
	private String product_name; //상품 타입
	
	public int getCart_num() {
		return cart_num;
	}
	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getProduct_amount() {
		return product_amount;
	}
	public void setProduct_amount(int product_amount) {
		this.product_amount = product_amount;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public String getProduct_size() {
		return product_size;
	}
	public void setProduct_size(String product_size) {
		this.product_size = product_size;
	}
	public String getProduct_color() {
		return product_color;
	}
	public void setProduct_color(String product_color) {
		this.product_color = product_color;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	@Override
	public String toString() {
		return "CartDTO [cart_num=" + cart_num + ", product_code=" + product_code + ", userID=" + userID
				+ ", product_amount=" + product_amount + ", product_price=" + product_price + ", product_size="
				+ product_size + ", product_color=" + product_color + ", product_type=" + product_type
				+ ", product_name=" + product_name + "]";
	}
	
	
	
}
