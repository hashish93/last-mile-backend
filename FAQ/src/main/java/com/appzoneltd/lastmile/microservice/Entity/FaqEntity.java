package com.appzoneltd.lastmile.microservice.Entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by hashish on 4/25/2017.
 */
//@Data
@Entity
@Table(name = "faq", schema = "lastmile_core")
@NamedQueries({@NamedQuery(name = "FaqEntity.countAll", query = "SELECT COUNT(x) FROM FaqEntity x")})
public class FaqEntity{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "question")
    private String question;
    @Column(name = "answer")
    private String answer;

//    @ManyToOne
//    @JoinColumn(name = "faq_language_id", referencedColumnName = "id", nullable = true)
//    private FaqLanguageEntity faqLanguageEntity;
    @Column(name = "language")
    private String language;
    
    @Column(name = "language_id")
    private Long languageId;

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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

//	public FaqLanguageEntity getFaqLanguageEntity() {
//		return faqLanguageEntity;
//	}
//
//	public void setFaqLanguageEntity(FaqLanguageEntity faqLanguageEntity) {
//		this.faqLanguageEntity = faqLanguageEntity;
//	}

}
