package com.appzoneltd.lastmile.microservice.freelancedriver.dto;

public enum MissingDocumentValues {

	is_National_Id_Exist("Personal ID"), is_Vehicleownership_Id_Exist(" Vehicle License ") , is_Criminalrecord_Exist("Criminal Records") , is_Birthcertificate_Exist("Birth Certificate") ;
	

	private final String status;

	private MissingDocumentValues(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
