package com.practice.enums;

/**
 * @author Xushd  2018/1/20 14:25
 */
public enum SystemParamEnum {

    /**
     * 系统参数
     */
    ICONFONT_CSS(1L,"ICONFONT_CSS");

    private Long id;

    private String name;

    SystemParamEnum(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public static String stateOf(Long id) {
        for (SystemParamEnum state : values()) {
            if (state.getId().equals(id)) {
                return state.getName();
            }
        }
        return null;
    }

    public static SystemParamEnum stateOf(String name) {
        for (SystemParamEnum state : values()) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }

}
