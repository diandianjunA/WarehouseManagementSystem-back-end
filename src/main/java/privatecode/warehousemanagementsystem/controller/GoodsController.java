package privatecode.warehousemanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import privatecode.warehousemanagementsystem.common.QueryPageParamForGoods;
import privatecode.warehousemanagementsystem.common.QueryPageParamForGoodsType;
import privatecode.warehousemanagementsystem.common.Result;
import privatecode.warehousemanagementsystem.entity.Goods;
import privatecode.warehousemanagementsystem.entity.GoodsType;
import privatecode.warehousemanagementsystem.service.GoodsService;

import java.util.List;

@RestController
@RequestMapping("/goods")
@CrossOrigin
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @GetMapping("/list")
    public Result<List<Goods>> list(){
        try {
            List<Goods> list = goodsService.list();
            return Result.success(list);
        } catch (Exception e) {
            return Result.fail("获取失败");
        }
    }

    @PostMapping("/save")
    public Result<Integer> save(@RequestBody Goods goods){
        if(goodsService.save(goods)){
            return Result.success(0);
        }else{
            return Result.fail("添加失败");
        }
    }

    @PostMapping("/mod")
    public Result<Integer> mod(@RequestBody Goods goods){
        if(goodsService.updateById(goods)){
            return Result.success(0);
        }else{
            return Result.fail("修改失败");
        }
    }

    @PostMapping("/saveOrMod")
    public Result<Integer> saveOrMod(@RequestBody Goods goods){
        System.out.println(goods.getId());
        if(goodsService.saveOrUpdate(goods)){
            return Result.success(0);
        }else{
            return Result.fail("添加或修改失败");
        }
    }

    @GetMapping("/delete")
    public Result<Integer> delete(Integer id){
        if(goodsService.removeById(id)){
            return Result.success(0);
        }else{
            return Result.fail("删除失败");
        }
    }

    @PostMapping("/listPage")
    public Result<PageInfo<Goods>> listPage(@RequestBody QueryPageParamForGoods queryPageParam){
        try {
            PageHelper.startPage(queryPageParam.getPageNum(),queryPageParam.getPageSize());
            LambdaQueryWrapper<Goods> goodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
            if(queryPageParam.getId()!=null){
                goodsLambdaQueryWrapper.eq(Goods::getId,queryPageParam.getId());
            }
            if(queryPageParam.getName()!=null){
                goodsLambdaQueryWrapper.like(Goods::getName,queryPageParam.getName());
            }
            if(queryPageParam.getRemark()!=null){
                goodsLambdaQueryWrapper.like(Goods::getRemark,queryPageParam.getRemark());
            }
            if(queryPageParam.getCount()!=null){
                goodsLambdaQueryWrapper.eq(Goods::getCount,queryPageParam.getCount());
            }
            if(queryPageParam.getStorageId()!=null){
                goodsLambdaQueryWrapper.eq(Goods::getStorageId,queryPageParam.getStorageId());
            }
            if(queryPageParam.getGoodsTypeId()!=null){
                goodsLambdaQueryWrapper.eq(Goods::getGoodsTypeId,queryPageParam.getGoodsTypeId());
            }
            List<Goods> list = goodsService.list(goodsLambdaQueryWrapper);
            PageInfo<Goods> goodsPageInfo = new PageInfo<>(list, queryPageParam.getNavSize());
            return Result.success(goodsPageInfo);
        } catch (Exception e) {
            return Result.fail("获取失败");
        }
    }

    @GetMapping("/checkStorageId")
    public Result<Integer> checkStorageId(@RequestParam(value = "storageId") Integer storageId){
        LambdaQueryWrapper<Goods> goodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        goodsLambdaQueryWrapper.like(Goods::getStorageId,storageId);
        List<Goods> list = goodsService.list(goodsLambdaQueryWrapper);
        if(list.isEmpty()){
            return Result.success(0);
        }else{
            return Result.success(1);
        }
    }

    @GetMapping("/checkGoodsTypeId")
    public Result<Integer> checkGoodsTypeId(@RequestParam(value = "goodsTypeId") Integer goodsTypeId){
        LambdaQueryWrapper<Goods> goodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        goodsLambdaQueryWrapper.like(Goods::getGoodsTypeId,goodsTypeId);
        List<Goods> list = goodsService.list(goodsLambdaQueryWrapper);
        if(list.isEmpty()){
            return Result.success(0);
        }else{
            return Result.success(1);
        }
    }

    @GetMapping("/addCount")
    public Result<Integer> addCount(@RequestParam(value = "count") Integer count,@RequestParam(value = "id") Integer id){
        Goods goods = goodsService.getById(id);
        goods.setCount(goods.getCount()+count);
        if(goodsService.updateById(goods)){
            return Result.success(0);
        }else {
            return Result.fail("入库失败");
        }
    }
}
