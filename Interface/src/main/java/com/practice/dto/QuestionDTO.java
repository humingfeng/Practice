package com.practice.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd  2018/1/8 21:39
 */
public class QuestionDTO implements Serializable {

    private Long id;

    private Long typeId;

    private String question;

    private String answerText;

    private String options;

    private Integer classify;

    private List<QuestionItemDTO> list;


    public QuestionDTO() {
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public List<QuestionItemDTO> getList() {
        return list;
    }

    public void setList(List<QuestionItemDTO> list) {
        this.list = list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }


}
