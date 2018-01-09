package com.practice.vo;

import java.io.Serializable;

/**
 * @author Xushd on 2018/1/9 16:41
 */
public class QuestionVO implements Serializable {

    private Long id;

    private String question;

    private String type;

    private String classify;

    public QuestionVO() {
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    @Override
    public String toString() {
        return "QuestionVO{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", type='" + type + '\'' +
                ", classify='" + classify + '\'' +
                '}';
    }
}
