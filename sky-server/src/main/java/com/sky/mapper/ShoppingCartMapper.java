package com.sky.mapper;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ShoppingCartMapper {
    /**
     * 添加购物车
     *
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updateNumber(ShoppingCart shoppingCart);

    /**
     * 插入购物车数据
     *
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart (name, image, dish_id, setmeal_id, dish_flavor, number, amount, create_time, user_id)" +
            " values (#{name}, #{image}, #{dishId}, #{setmealId}, #{dishFlavor}, #{number}, #{amount}, #{createTime}, #{userId})")
    void insert(ShoppingCart shoppingCart);

}
