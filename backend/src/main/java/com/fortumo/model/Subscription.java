package com.fortumo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="subscription")
public class Subscription implements Serializable{

	private static final long serialVersionUID = 979400105345783355L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String name;
	
	@Column
    @NotNull
	private BigDecimal price;
    
    @NotNull
    private String provider;

	public Subscription() {
		super();
	}

	public Subscription(String name, BigDecimal price, String provider) {
		this.name = name;
		this.price = price;
		this.provider = provider;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

}
