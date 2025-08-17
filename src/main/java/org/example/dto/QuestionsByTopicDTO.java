package org.example.dto;

import java.util.List;

public class QuestionsByTopicDTO {
    private String topicName;
    private List<QuestionShortDTO> questions;

    public QuestionsByTopicDTO(String topicName, List<QuestionShortDTO> questions) {
        this.topicName = topicName;
        this.questions = questions;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<QuestionShortDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionShortDTO> questions) {
        this.questions = questions;
    }
}
