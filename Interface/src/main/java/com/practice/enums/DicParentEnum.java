package com.practice.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Dic parent enum
 * @author Xushd  2017/12/23 16:26
 */
public enum DicParentEnum {

    /**
     * 数据字典类型
     */
    USER_TYPE(1L, "用户类型", "USER_TYPE"),
    ORGANIZE_TYPE(2L, "组织类型", "ORGANIZE_TYPE"),
    ATTENTION_TYPE(46L, "注意事项类型", "ATTENTION_TYPE"),
    QUESTION_TYPE(50L, "题目类型", "QUESTION_TYPE"),
    CLASS_TYPE(57L, "班级", "CLASS_TYPE"),
    NATION_TYPE(78L, "民族", "NATION_TYPE"),
    TEACHER_TYPE(150L, "教师职务", "TEACHER_TYPE"),
    RELATIONS(160L, "关系", "RELATIONS"),
    BASE_TAG(168L, "基地标签", "BASE_TAG"),
    PERIOD(3L, "学段", "PERIOD");

    private Long id;

    private String name;

    private String key;

    DicParentEnum(Long id, String name, String key) {
        this.id = id;
        this.name = name;
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }



    public static String stateOf(Long id) {
        for (DicParentEnum state : values()) {
            if (state.getId().equals(id)) {
                return state.getName();
            }
        }
        return null;
    }

    public static DicParentEnum stateOf(String key) {
        for (DicParentEnum state : values()) {
            if (state.getKey().equals(key)) {
                return state;
            }
        }
        return null;
    }

    public static List<DicParentEnum> getAll() {
        List<DicParentEnum> list = new ArrayList<>();
        for (DicParentEnum state : values()) {
            list.add(state);
        }
        return list;
    }

}
