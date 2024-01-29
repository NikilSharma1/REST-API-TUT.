package com.nikil.RestDemo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Data {
	private int id;
	private String name;

	public Data() {

	}

	public Data(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name + " " + this.id;
	}

}
