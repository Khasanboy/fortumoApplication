package model;

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
	
    @NotNull
	private BigDecimal price;
    
    @NotNull
    private String serviceProvider;

    @OneToOne
	private Customer customer;

	public Subscription() {
		super();
	}

	public Subscription(String name, BigDecimal price, String serviceProvider, Customer customer) {
		this.name = name;
		this.price = price;
		this.serviceProvider = serviceProvider;
		this.customer = customer;
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

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
