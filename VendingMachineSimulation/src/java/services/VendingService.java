package services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import DTO.Change;
import DTO.ProductItem;

@Service
public class VendingService implements Cloneable {

	private ArrayList<ProductItem> selection, products;
	List<ProductItem> clonedProduct;
	private Change myChange;
	private String message;
	private BigDecimal balance;

	VendingDAO vendingDAO;

	@Inject
	public VendingService(VendingDAO vendingDAO) {
		// super();
		// this.vendingDAO = vendingDAO;
		products = vendingDAO.getProducts();
		balance = new BigDecimal("0.00");
		selection = new ArrayList<>();
		myChange = null;
		message = null;
	}

	public ArrayList<ProductItem> getProducts() {
		return products;
	}

	public ArrayList<ProductItem> productsSelected(int id) {
		ProductItem select = new ProductItem(vendingDAO.getProductForID(id));
		// select.setQuantity(select.getQuantity() - 1);

		products = vendingDAO.getProducts();

		if (selection.isEmpty()) {
			clonedProduct = products.stream().map(ProductItem::new).collect(Collectors.toList());
		}
		selection.add(select);
		return (ArrayList<ProductItem>) tempModification(select, clonedProduct);
	}

	private List<ProductItem> tempModification(ProductItem select, List<ProductItem> tempSelection2) {

		Iterator<ProductItem> it = tempSelection2.iterator();
		while (it.hasNext()) {
			ProductItem ele = it.next();
			if (select.getId() == ele.getId()) {
				ele.setQuantity(ele.getQuantity() - 1);
			}

		}
		return tempSelection2;

	}

	public void makePurchase() {
		if (!selection.isEmpty() || selection != null) {
			BigDecimal totalPrice = new BigDecimal("0.00");
//			Predicate<ProductItem> predicate = p-> p.getQuantity() <= 0;
//			selection.removeIf(predicate);
			for (ProductItem productItem : selection) {
				totalPrice = totalPrice.add(productItem.getPrice());
				if (productItem.getQuantity() <= 0) {
					message = productItem.getName() + " is Sold Out";
					return;
				}

			}
			
			if (totalPrice.compareTo(balance) > 0) {
				message = "Please Deposit: $" + totalPrice.subtract(balance);
			} else {
				BigDecimal newBalance = balance.subtract(totalPrice);
				vendingDAO.setSelect(products);
				setProducts(vendingDAO.getProducts());
				Change change = new Change(newBalance);
				myChange = change;
				init();
				message="Collect change";
			}
		}
	}
	public void addMoney(int id) {
		balance = balance.add(new BigDecimal(id));
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public VendingDAO getVendingDAO() {
		return vendingDAO;
	}

	public void setVendingDAO(VendingDAO vendingDAO) {
		this.vendingDAO = vendingDAO;
	}

	public List<ProductItem> getSelection() {
		return selection;
	}

	public void setSelection(ArrayList<ProductItem> selection) {
		this.selection = selection;
	}

	public Change getMyChange() {
		return myChange;
	}

	public void setMyChange(Change myChange) {
		this.myChange = myChange;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void setProducts(ArrayList<ProductItem> products) {
		this.products = products;
	}

	public void returnChange() {
		
		if (!selection.isEmpty()) {
			vendingDAO.setSelect(selection);
			setProducts(vendingDAO.getProducts());
		}
		balance = new BigDecimal("0.00");
		Change change = new Change(balance);
		myChange = change;
		init();
	}

	private void init() {

		selection = new ArrayList<>();
		message = "Welcome!";
	}

}
