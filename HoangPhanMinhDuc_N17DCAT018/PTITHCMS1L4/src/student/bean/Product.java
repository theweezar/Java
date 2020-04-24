package student.bean;

public class Product {
	private String name;
	private Double untiPrice;
	private Double discount;
	public Product(String name, Double untiPrice, Double discount) {
		this.name = name;
		this.untiPrice = untiPrice;
		this.discount = discount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getUntiPrice() {
		return untiPrice;
	}
	public void setUntiPrice(Double untiPrice) {
		this.untiPrice = untiPrice;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getNewPrice(){
		return untiPrice * (1 - discount);
	}
}
