package com.appzoneltd.lastmile.microservice.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by hashish on 4/25/2017.
 */
//@Data
public class FaqDto implements Serializable {

    private Long id;
    @NotBlank(message = "faq.question.required")
    private String question;
    @NotBlank(message = "faq.answer.required")
    private String answer;
//    @NotNull(message = "faq.language.required")
//    private Long languageId;
    
    @NotNull(message = "faq.language.required")
    private String language;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
//	public Long getLanguageId() {
//		return languageId;
//	}
//	public void setLanguageId(Long languageId) {
//		this.languageId = languageId;
//	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
    
    
}
