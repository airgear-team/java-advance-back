package org.example.dto;


public class QuestionShortDTO {
    private Long id;
    private String questionText;

    public QuestionShortDTO(Long id, String questionText) {
        this.id = id;
        this.questionText = questionText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}