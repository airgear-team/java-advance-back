package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String questionText;

    @Column(nullable = false, length = 10000)
    private String answer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    public Question() {}

    public Question(String questionText, String answer, Topic topic) {
        this.questionText = questionText;
        this.answer = answer;
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}

