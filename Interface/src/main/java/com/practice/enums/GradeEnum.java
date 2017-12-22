package com.practice.enums;

/**
 * Created by Xushd on 2017/7/20.
 */
public enum GradeEnum {

    GRADE_1(1,"一年级"),
    GRADE_2(2,"二年级"),
    GRADE_3(3,"三年级"),
    GRADE_4(4,"四年级"),
    GRADE_5(5,"五年级"),
    GRADE_6(6,"六年级");

    private int key;
    private String name;

    GradeEnum(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getKey() {
        return key;
    }

    public static String getName(int index) {
        for (GradeEnum grade : GradeEnum.values()) {
            if (grade.getKey() == index) {
                return grade.name;
            }
        }
        return null;
    }
}
