package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

public class Nerc implements Comparable<Nerc>{

	private int id;
	private String value;
	private List<PowerOutages> poList ;

	public Nerc(int id, String value) {
		setPoList(new ArrayList<PowerOutages>()) ;
		this.id = id;
		this.value = value;
	}
	
	public Nerc() {
		setPoList(new ArrayList<PowerOutages>()) ;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nerc other = (Nerc) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(value);
		return builder.toString();
	}

	public List<PowerOutages> getPoList() {
		return poList;
	}

	public void setPoList(List<PowerOutages> poList) {
		this.poList = poList;
	}

	@Override
	public int compareTo(Nerc arg0) {
		return this.id-arg0.id;
	}
}
