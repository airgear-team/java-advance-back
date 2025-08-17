package org.example.dto;

public class QuestionDTO {
    private Long id;
    private String questionText;
    private String answer;
    private String topicName;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }
}
