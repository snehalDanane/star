package DTO;

import java.math.BigDecimal;

public class ProductItem{
	
	private String name;
	private int id,quantity;
	private BigDecimal price;
	
	public ProductItem(int id, String name, BigDecimal price,int quantity ) {
		super();
		this.name = name;
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}
	public ProductItem(ProductItem productItem) {
		name = productItem.name;
		id = productItem.id;
		quantity = productItem.quantity;
		price = productItem.price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	
}