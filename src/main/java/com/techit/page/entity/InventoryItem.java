package com.techit.page.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "INVENTORY")
public class InventoryItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INV_ID")
	private Integer itemId;

	@Column(name = "INV_ITEM_NAME")
	private String itemName;

	@Column(name = "INV_ITEM_COST")
	private BigDecimal itemCost;

	@Column(name = "INV_ITEM_QNTY")
	private Integer itemQuantity;

	@Column(name = "INV_CRT_DT")
	private Date createDate;

	@Column(name = "INV_UPDT_DT")
	private Date updateDate;

	@PrePersist
	void onCreate() {
		this.setCreateDate(new Timestamp(new Date().getTime()));
		this.setUpdateDate(new Timestamp(new Date().getTime()));
	}

	@PreUpdate
	void onUpdate() {
		this.setUpdateDate(new Timestamp(new Date().getTime()));
	}

}
