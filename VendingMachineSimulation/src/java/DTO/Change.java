package DTO;

import java.math.BigDecimal;

public class Change {

	private int yen500;
	private int yen100;
	private int yen50;
	private int yen10;
	private int yen5;

	public Change(BigDecimal newBalance) {
		yen5 = newBalance.intValue();
		yen500 = yen5 / 500;
		yen5 %= 500;

		yen100 = yen5 / 100;
		yen5 %= 100;

		yen50 = yen5 / 50;
		yen5 %= 50;

		yen10 = yen5 / 10;
		yen5 %= 10;
		
		yen5 = yen5 / 5;
		yen5 %= 5;

	}

	public int getYen500() {
		return yen500;
	}

	public void setYen500(int yen500) {
		this.yen500 = yen500;
	}

	public int getYen100() {
		return yen100;
	}

	public void setYen100(int yen100) {
		this.yen100 = yen100;
	}

	public int getYen50() {
		return yen50;
	}

	public void setYen50(int yen50) {
		this.yen50 = yen50;
	}

	public int getYen10() {
		return yen10;
	}

	public void setYen10(int yen10) {
		this.yen10 = yen10;
	}

	public int getYen5() {
		return yen5;
	}

	public void setYen5(int yen5) {
		this.yen5 = yen5;
	}

}
