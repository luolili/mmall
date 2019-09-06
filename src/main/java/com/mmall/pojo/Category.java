package com.mmall.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Category implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_category.id
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_category.parent_id
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    private Integer parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_category.name
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_category.status
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    private Boolean status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_category.sort_order
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    private Integer sortOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_category.create_time
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_category.update_time
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mmall_category
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_category
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public Category(Integer id, Integer parentId, String name, Boolean status, Integer sortOrder, Date createTime, Date updateTime) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.status = status;
        this.sortOrder = sortOrder;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_category
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public Category() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_category.id
     *
     * @return the value of mmall_category.id
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_category
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public Category withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_category.id
     *
     * @param id the value for mmall_category.id
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_category.parent_id
     *
     * @return the value of mmall_category.parent_id
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_category
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public Category withParentId(Integer parentId) {
        this.setParentId(parentId);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_category.parent_id
     *
     * @param parentId the value for mmall_category.parent_id
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_category.name
     *
     * @return the value of mmall_category.name
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_category
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public Category withName(String name) {
        this.setName(name);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_category.name
     *
     * @param name the value for mmall_category.name
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_category.status
     *
     * @return the value of mmall_category.status
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_category
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public Category withStatus(Boolean status) {
        this.setStatus(status);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_category.status
     *
     * @param status the value for mmall_category.status
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_category.sort_order
     *
     * @return the value of mmall_category.sort_order
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_category
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public Category withSortOrder(Integer sortOrder) {
        this.setSortOrder(sortOrder);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_category.sort_order
     *
     * @param sortOrder the value for mmall_category.sort_order
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_category.create_time
     *
     * @return the value of mmall_category.create_time
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_category
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public Category withCreateTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_category.create_time
     *
     * @param createTime the value for mmall_category.create_time
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_category.update_time
     *
     * @return the value of mmall_category.update_time
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_category
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public Category withUpdateTime(Date updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_category.update_time
     *
     * @param updateTime the value for mmall_category.update_time
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_category
     *
     * @mbg.generated Thu Sep 05 20:20:58 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}