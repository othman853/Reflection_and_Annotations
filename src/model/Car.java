package model;

import annotation.FieldName;
import annotation.Id;

public class Car implements Entity{
	@Id
	@FieldName("id_car")
	private int id;
	private String name;
	private String brand;
	private String color;
	
	public Car(int id, String name, String brand, String color) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.color = color;
	}
}