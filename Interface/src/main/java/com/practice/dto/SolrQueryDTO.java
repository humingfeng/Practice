package com.practice.dto;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author Xushd on 2018/1/24 14:46
 */
public class SolrQueryDTO implements Serializable{

    private String query;

    private Long pid;

    private Long cid;

    private Long aid;

    private Long typeId;

    private Long classifyId;

    private Long themeId;

    private Long organizeId;

    private Long baseId;

    private Long apply;

    private Integer free;

    private Integer self;

    private Integer duration;

    private Integer close;

    private Integer soft;

    private Integer sign;

    private Integer pageIndex;

    private Integer pageSize;

    public SolrQueryDTO() {
    }


    public void init() {
        this.query = "";
        this.typeId = 0L;
        this.classifyId = 0L;
        this.themeId = 0L;
        this.organizeId = 0L;
        this.baseId = 0L;
        this.apply = 0L;
        this.free = 0;
        this.self = 0;
        this.duration = 0;
        this.close = 0;
        this.soft = 0;
        this.sign = 0;
        this.pageIndex = 1;
        this.pageSize = 10;

        this.pid = 0L;
        this.cid = 0L;
        this.aid = 0L;
    }

    public void init(String searchParam, String query) {
        String[] param = searchParam.split("q_t|i|e|o|b|a|f|m|d|c|s|g|p");

        if (StringUtils.isNotBlank(query)) {
            this.query = query;
        } else {
            this.query = "";
        }
        this.typeId = Long.valueOf(param[1]);
        this.classifyId = Long.valueOf(param[2]);
        this.themeId = Long.valueOf(param[3]);
        this.organizeId = Long.valueOf(param[4]);
        this.baseId = Long.valueOf(param[5]);
        this.apply = Long.valueOf(param[6]);
        this.free = Integer.valueOf(param[7]);
        this.self = Integer.valueOf(param[8]);
        this.duration = Integer.valueOf(param[9]);
        this.close = Integer.valueOf(param[10]);
        this.soft = Integer.valueOf(param[11]);
        this.sign = Integer.valueOf(param[12]);
        this.pageIndex = Integer.valueOf(param[13]);

        this.pageSize = 10;

        this.pid = 0L;
        this.cid = 0L;
        this.aid = 0L;

    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public Long getThemeId() {
        return themeId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    public Long getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(Long organizeId) {
        this.organizeId = organizeId;
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    public Integer getSelf() {
        return self;
    }

    public void setSelf(Integer self) {
        this.self = self;
    }

    public Integer getSoft() {
        return soft;
    }

    public void setSoft(Integer soft) {
        this.soft = soft;
    }

    public Long getApply() {
        return apply;
    }

    public void setApply(Long apply) {
        this.apply = apply;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getClose() {
        return close;
    }

    public void setClose(Integer close) {
        this.close = close;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "SolrQueryDTO{" +
                "query='" + query + '\'' +
                ", typeId=" + typeId +
                ", classifyId=" + classifyId +
                ", themeId=" + themeId +
                ", organizeId=" + organizeId +
                ", baseId=" + baseId +
                ", apply=" + apply +
                ", free=" + free +
                ", self=" + self +
                ", duration=" + duration +
                ", close=" + close +
                ", soft=" + soft +
                ", sign=" + sign +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
