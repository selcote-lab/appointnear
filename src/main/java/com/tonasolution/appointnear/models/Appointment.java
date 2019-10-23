package com.tonasolution.appointnear.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="Appointment")
@Table(name = "appointment")
public class Appointment implements IAppointment{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;
	
	@Column(name = "at")
	private String at;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "type")
	private String type;
	
	@OneToOne(fetch = FetchType.LAZY)
	@Column(name = "advertiser_id")
	private Advertiser advertiser;
	
	@OneToOne(fetch = FetchType.LAZY)
	@Column(name = "adress_id")
	private Adress adress;
	
	public String getAt() {
		return at;
	}

	public void setAt(String at) {
		this.at = at;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Appointment [price=" + price + ", type=" + type + "]";
	}

	public Advertiser getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(Advertiser advertiser) {
		this.advertiser = advertiser;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public Long get_id() {
		return _id;
	}
}
