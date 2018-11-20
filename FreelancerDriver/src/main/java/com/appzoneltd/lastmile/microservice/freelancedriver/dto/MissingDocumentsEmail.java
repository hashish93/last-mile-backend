package com.appzoneltd.lastmile.microservice.freelancedriver.dto;

import java.util.List;

import lombok.Data;

@Data
public class MissingDocumentsEmail {

	private Long userId;

	private List<String> missingDocuments;
}
