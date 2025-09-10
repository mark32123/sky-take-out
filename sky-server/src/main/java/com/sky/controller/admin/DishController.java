package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品：{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);

        // 清理所有以dish_开头的缓存数据
        String s = "dish" + dishDTO.getCategoryId();
        cleanCache(s);
        return Result.success();
    }

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result page(DishPageQueryDTO dishPageQueryDTO){
        log.info("分页查询：{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 删除菜品
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("批量删除菜品")
    public Result deleteById(@RequestParam List< Long> ids){

        log.info("删除菜品：{}", ids);
        dishService.delete(ids);

        // 清理所有以dish_开头的缓存数据
        cleanCache("dish_*");
        return Result.success();
    }

    /**
     * 根据id查询菜品和对应的口味
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品和对应的口味")
    public Result<DishVO> getById(@PathVariable Long id){

        log.info("查询菜品id：{}", id);
        DishVO dishVO =dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /*
    * 修改菜品
     */

    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品：{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);


        // 清理所有以dish_开头的缓存数据
        cleanCache("dish_*");
        return Result.success();
    }

    /**
     * 菜品起售停售
     * @param status
     * @param id
     */
    @PostMapping("/status/{status}")
    @ApiOperation("菜品起售停售")
    public Result<String> startOrStop(@PathVariable Integer status, Long id){
        log.info("菜品起售停售：{}", id);
        dishService.startOrStop(status, id);
        // 清理所有以dish_开头的缓存数据
        cleanCache("dish_*");
        return Result.success();
    }


    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId){
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

    /**
     * 清理缓存数据
     * @param pattern
     */
    public void cleanCache(String pattern){
        redisTemplate.delete(redisTemplate.keys(pattern));
    }
}
