package com.techit.page.to;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class InventoryItemTO {

	private Integer itemId;

	private String itemName;

	private BigDecimal itemCost;

	private Integer itemQuantity;

	private Date crtDate;

	private Date updtDate;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private BigDecimal totalQntyCost;

	public BigDecimal getTotalQntyCost() {
		return this.itemCost.multiply(new BigDecimal(this.itemQuantity));
	}

	public void setTotalQntyCost() {
		this.totalQntyCost = this.itemCost.multiply(new BigDecimal(this.itemQuantity));
	}

}
