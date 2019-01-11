package services;

import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import DTO.ProductItem;

public class VendingDAO {

	HashMap<Integer, ProductItem> item;
	
	public VendingDAO() {
		item = new HashMap<>();
        item.put(1, new ProductItem(1,"Snickers",new BigDecimal("100"),1));
        item.put(2, new ProductItem(2,"M & Ms",new BigDecimal("150"),9));
        item.put(3, new ProductItem(3,"Pringles",new BigDecimal("210"),2));
        item.put(4, new ProductItem(4,"Reese's",new BigDecimal("185"),5));
        item.put(5, new ProductItem(5,"Pretzels",new BigDecimal("125"),4));
        item.put(6, new ProductItem(6,"Twinkies",new BigDecimal("195"),0));
        item.put(7, new ProductItem(7,"Doritos",new BigDecimal("175"),3));
        item.put(8, new ProductItem(8,"Almond Joy",new BigDecimal("185"),11));
        item.put(9, new ProductItem(9,"Trident",new BigDecimal("195"),1));
	
	
	}
	public ArrayList<ProductItem> getProducts() {
		return new ArrayList<>(item.values());
	}
	public ProductItem getProductForID(int id) {
		return item.get(id);
	}
	public  void setSelect(ArrayList<ProductItem> selection) {
		for (ProductItem selectedItem : selection) {
			item.put(selectedItem.getId(), selectedItem);
		}
	}
	
}
