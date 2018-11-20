package com.appzoneltd.lastmile.microservices.syncengine.engine.dto;

import lombok.Data;

@Data
public class DriverRatingCountDto {
	private Long oneStar;
	private Long twoStars;
	private Long threeStars;
	private Long fourStars;
	private Long fiveStars;
	public DriverRatingCountDto(Long oneStar, Long twoStars, Long threeStars, Long fourStars, Long fiveStars) {
		this.oneStar = oneStar;
		this.twoStars = twoStars;
		this.threeStars = threeStars;
		this.fourStars = fourStars;
		this.fiveStars = fiveStars;
	}
}
