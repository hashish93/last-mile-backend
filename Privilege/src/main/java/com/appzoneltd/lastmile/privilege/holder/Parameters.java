package com.appzoneltd.lastmile.privilege.holder;

public class Parameters {

	private Integer id;
	private boolean value;
	private String name;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Parameters [id=" + id + ", value=" + value + "]";
	}
	
	
	
	
}
