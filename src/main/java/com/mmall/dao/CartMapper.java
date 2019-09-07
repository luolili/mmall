package com.mmall.dao;

import com.mmall.pojo.Cart;
import com.mmall.pojo.CartExample;
import com.mmall.pojo.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_cart
     *
     * @mbg.generated Sun Sep 01 18:38:57 CST 2019
     */
    @Delete({
        "delete from mmall_cart",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_cart
     *
     * @mbg.generated Sun Sep 01 18:38:57 CST 2019
     */
    @Insert({
        "insert into mmall_cart (id, user_id, ",
        "product_id, quantity, ",
        "checked, create_time, ",
        "update_time)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{productId,jdbcType=INTEGER}, #{quantity,jdbcType=INTEGER}, ",
        "#{checked,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_cart
     *
     * @mbg.generated Sun Sep 01 18:38:57 CST 2019
     */
    int insertSelective(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_cart
     *
     * @mbg.generated Sun Sep 01 18:38:57 CST 2019
     */
    @Select({
        "select",
        "id, user_id, product_id, quantity, checked, create_time, update_time",
        "from mmall_cart",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mmall.dao.CartMapper.BaseResultMap")
    Cart selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_cart
     *
     * @mbg.generated Sun Sep 01 18:38:57 CST 2019
     */
    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_cart
     *
     * @mbg.generated Sun Sep 01 18:38:57 CST 2019
     */
    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_cart
     *
     * @mbg.generated Sun Sep 01 18:38:57 CST 2019
     */
    int updateByPrimaryKeySelective(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_cart
     *
     * @mbg.generated Sun Sep 01 18:38:57 CST 2019
     */
    @Update({
        "update mmall_cart",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "product_id = #{productId,jdbcType=INTEGER},",
          "quantity = #{quantity,jdbcType=INTEGER},",
          "checked = #{checked,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Cart record);

    Cart selectCartByUserIdProductId(@Param("userId") Integer userId,
                                     @Param("productId") Integer productId);

    List<Cart> selectCartByUserId(@Param("userId") Integer userId);

    int selectCartProductCheckedStatusByUserId(Integer userId);

    int selectCartProductCountByUserId(Integer userId);

    int deleteCartByUserIdProductIds(@Param("userId") Integer userId,
                                     @Param("productIdList") List<String> productIdList);

    int checkedOrUncheckedProduct(@Param("userId") Integer userId,
                                  @Param("checked") Integer checked,
                                  @Param("productId") Integer productId
    );


}