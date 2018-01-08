package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/1/8 22:03
 */
public class QuestionItemDTO implements Serializable {

    private Long id;

    private String optionMark;

    private String text;

    private Integer correct;

    public QuestionItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionMark() {
        return optionMark;
    }

    public void setOptionMark(String optionMark) {
        this.optionMark = optionMark;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }
}
