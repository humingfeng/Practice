package com.practice.dto;

/**
 * @author Xushd on 2018/1/24 14:46
 */
public class SolrQueryDTO {

    private String query;

    private Long typeId;

    private Long classifyId;

    private Long themeId;

    private Long organizeId;

    private Long baseId;

    private Long apply;

    private Integer free;

    private Integer self;

    private Integer ducation;

    private Integer close;

    private Integer soft;

    private Integer sign;

    private Integer pageIndex;

    private Integer pageSize;

    public SolrQueryDTO() {
    }

    public void init(){
        System.out.println(123);
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

    public Integer getDucation() {
        return ducation;
    }

    public void setDucation(Integer ducation) {
        this.ducation = ducation;
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

    @Override
    public String toString() {
        return "SolrQueryDTO{" +
                "query='" + query + '\'' +
                ", typeId=" + typeId +
                ", classifyId=" + classifyId +
                ", themeId=" + themeId +
                ", organizeId=" + organizeId +
                ", baseId=" + baseId +
                ", free=" + free +
                ", self=" + self +
                ", soft=" + soft +
                '}';
    }
}
