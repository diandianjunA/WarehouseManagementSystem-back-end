package privatecode.warehousemanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import privatecode.warehousemanagementsystem.common.QueryPageParamForGoodsType;
import privatecode.warehousemanagementsystem.common.Result;
import privatecode.warehousemanagementsystem.entity.GoodsType;
import privatecode.warehousemanagementsystem.service.GoodsTypeService;

import java.util.List;

@RestController
@RequestMapping("/goodsType")
@CrossOrigin
public class GoodsTypeController {
    @Autowired
    GoodsTypeService goodsTypeService;

    @GetMapping("/list")
    public Result<List<GoodsType>> list(){
        try {
            List<GoodsType> list = goodsTypeService.list();
            return Result.success(list);
        } catch (Exception e) {
            return Result.fail("获取失败");
        }
    }

    @PostMapping("/save")
    public Result<Integer> save(@RequestBody GoodsType goodsType){
        if(goodsTypeService.save(goodsType)){
            return Result.success(0);
        }else{
            return Result.fail("添加失败");
        }
    }

    @PostMapping("/mod")
    public Result<Integer> mod(@RequestBody GoodsType goodsType){
        if(goodsTypeService.updateById(goodsType)){
            return Result.success(0);
        }else{
            return Result.fail("修改失败");
        }
    }

    @PostMapping("/saveOrMod")
    public Result<Integer> saveOrMod(@RequestBody GoodsType goodsType){
        System.out.println(goodsType.getId());
        if(goodsTypeService.saveOrUpdate(goodsType)){
            return Result.success(0);
        }else{
            return Result.fail("添加或修改失败");
        }
    }

    @GetMapping("/delete")
    public Result<Integer> delete(Integer id){
        if(goodsTypeService.removeById(id)){
            return Result.success(0);
        }else{
            return Result.fail("删除失败");
        }
    }

    @PostMapping("/listPage")
    public Result<PageInfo<GoodsType>> listPage(@RequestBody QueryPageParamForGoodsType queryPageParam){
        try {
            PageHelper.startPage(queryPageParam.getPageNum(),queryPageParam.getPageSize());
            LambdaQueryWrapper<GoodsType> goodsTypeLambdaQueryWrapper = new LambdaQueryWrapper<>();
            if(queryPageParam.getId()!=null){
                goodsTypeLambdaQueryWrapper.eq(GoodsType::getId,queryPageParam.getId());
            }
            if(queryPageParam.getName()!=null){
                goodsTypeLambdaQueryWrapper.like(GoodsType::getName,queryPageParam.getName());
            }
            if(queryPageParam.getRemark()!=null){
                goodsTypeLambdaQueryWrapper.like(GoodsType::getRemark,queryPageParam.getRemark());
            }
            List<GoodsType> list = goodsTypeService.list(goodsTypeLambdaQueryWrapper);
            PageInfo<GoodsType> goodsTypePageInfo = new PageInfo<>(list, queryPageParam.getNavSize());
            return Result.success(goodsTypePageInfo);
        } catch (Exception e) {
            return Result.fail("获取失败");
        }
    }
}
