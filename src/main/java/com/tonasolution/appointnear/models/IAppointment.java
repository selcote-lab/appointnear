package com.tonasolution.appointnear.models;

public interface IAppointment {
	
	public String getAt();

	public void setAt(String at);

	public String getDescription();

	public void setDescription(String description);

	public double getPrice();

	public void setPrice(double price);

	public String getType();

	public void setType(String type);
	
	public String toString();
	
	public Advertiser getAdvertiser();

	public void setAdvertiser(Advertiser advertiser);

	public Adress getAdress();

	public void setAdress(Adress adress);

	public Long get_id();
}
