package com.appzoneltd.lastmile.microservices.SizesConfiguration.DTO;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class SizesConfigDto {

	private Long sizeId;

	@NotEmpty(message = "sizesConfig.sizename.NotEmpty")
	@Size(min=1,max=32,message="sizesConfig.sizename.size")
	@Pattern(regexp = "^$|^[a-zA-Z0-9\u0621-\u064A\u0660-\u0669][a-zA-Z0-9\u0621-\u064A\u0660-\u0669 -]*[a-zA-Z0-9\u0621-\u064A\u0660-\u0669]+", message = "sizesConfig.sizename.format")
	private String sizeName;

	@NotNull(message = "sizesConfig.width.NotNull")
	private BigDecimal width;

	@NotNull(message = "sizesConfig.height.NotNull")
	private BigDecimal height;

	@NotNull(message = "sizesConfig.length.NotNull")
	private BigDecimal length;

	@NotNull(message = "sizesConfig.correspondence.NotNull")
	private BigDecimal correspondence;
	
	private String status;

	private Long version;
	
	public Long getSizeId() {
		return sizeId;
	}

	public String getSizeName() {
		return sizeName;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public BigDecimal getCorrespondence() {
		return correspondence;
	}

	public void setSizeId(Long sizeId) {
		this.sizeId = sizeId;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public void setCorrespondence(BigDecimal correspondence) {
		this.correspondence = correspondence;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
