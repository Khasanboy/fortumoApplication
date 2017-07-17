package com.fortumo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="billing")
public class Billing implements Serializable{

	private static final long serialVersionUID = -6882394239673689489L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private Date paidDate;
	
	@NotNull
	private Date endDate;
	
	@NotNull
	private BigDecimal total;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="customerId")
	private Customer customer;

	public Billing() {
		super();
	}

	public Billing(Date paidDate, Date endDate, BigDecimal total, Customer customer) {
		super();
		this.paidDate = paidDate;
		this.endDate = endDate;
		this.total = total;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
		

}
