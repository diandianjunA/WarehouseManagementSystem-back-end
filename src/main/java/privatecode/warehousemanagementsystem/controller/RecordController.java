package privatecode.warehousemanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import privatecode.warehousemanagementsystem.common.QueryPageParamForGoods;
import privatecode.warehousemanagementsystem.common.QueryPageParamForRecord;
import privatecode.warehousemanagementsystem.common.RecordDto;
import privatecode.warehousemanagementsystem.common.Result;
import privatecode.warehousemanagementsystem.entity.*;
import privatecode.warehousemanagementsystem.entity.Record;
import privatecode.warehousemanagementsystem.service.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/record")
@CrossOrigin
public class RecordController {
    @Autowired
    RecordService recordService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsTypeService goodsTypeService;
    @Autowired
    StorageService storageService;
    @Autowired
    UserService userService;

    @GetMapping("/list")
    public Result<List<Record>> list(){
        try {
            List<Record> list = recordService.list();
            return Result.success(list);
        } catch (Exception e) {
            return Result.fail("获取失败");
        }
    }

    @PostMapping("/save")
    public Result<Integer> save(@RequestBody Record record){
        record.setCreateTime(new Date());
        if(recordService.save(record)){
            return Result.success(0);
        }else{
            return Result.fail("添加失败");
        }
    }

    @PostMapping("/mod")
    public Result<Integer> mod(@RequestBody Record record){
        if(recordService.updateById(record)){
            return Result.success(0);
        }else{
            return Result.fail("修改失败");
        }
    }

    @PostMapping("/saveOrMod")
    public Result<Integer> saveOrMod(@RequestBody Record record){
        System.out.println(record.getId());
        if(recordService.saveOrUpdate(record)){
            return Result.success(0);
        }else{
            return Result.fail("添加或修改失败");
        }
    }

    @GetMapping("/delete")
    public Result<Integer> delete(Integer id){
        if(recordService.removeById(id)){
            return Result.success(0);
        }else{
            return Result.fail("删除失败");
        }
    }

    @PostMapping("/listPage")
    public Result<PageInfo<RecordDto>> listPage(@RequestBody QueryPageParamForRecord queryPageParam){
        try {
            PageHelper.startPage(queryPageParam.getPageNum(),queryPageParam.getPageSize());
            LambdaQueryWrapper<Record> recordLambdaQueryWrapper = new LambdaQueryWrapper<>();
            if(queryPageParam.getId()!=null){
                recordLambdaQueryWrapper.eq(Record::getId,queryPageParam.getId());
            }
            if(queryPageParam.getRemark()!=null){
                recordLambdaQueryWrapper.like(Record::getRemark,queryPageParam.getRemark());
            }
            if(queryPageParam.getStorageId()!=null){
                Storage storage = storageService.getById(queryPageParam.getStorageId());
                LambdaQueryWrapper<Goods> goodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
                goodsLambdaQueryWrapper.eq(Goods::getStorageId,storage.getId());
                List<Goods> list = goodsService.list(goodsLambdaQueryWrapper);
                if(list.isEmpty()){
                    return Result.success(new PageInfo<>(new ArrayList<>(),queryPageParam.getNavSize()));
                }else{
                    recordLambdaQueryWrapper.and(recordLambdaQueryWrapper1 -> {
                        for (Goods goods : list) {
                            recordLambdaQueryWrapper1.or().eq(Record::getGoodsId,goods.getId());
                        }
                    });
                }
            }
            if(queryPageParam.getGoodsTypeId()!=null){
                GoodsType goodsType = goodsTypeService.getById(queryPageParam.getGoodsTypeId());
                LambdaQueryWrapper<Goods> goodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
                goodsLambdaQueryWrapper.eq(Goods::getGoodsTypeId,goodsType.getId());
                List<Goods> list = goodsService.list(goodsLambdaQueryWrapper);
                if(list.isEmpty()){
                    return Result.success(new PageInfo<>(new ArrayList<>(),queryPageParam.getNavSize()));
                }else{
                    recordLambdaQueryWrapper.and(recordLambdaQueryWrapper1 -> {
                        for (Goods goods : list) {
                            recordLambdaQueryWrapper1.or().eq(Record::getGoodsId,goods.getId());
                        }
                    });
                }
            }
            List<Record> list = recordService.list(recordLambdaQueryWrapper);
            ArrayList<RecordDto> resList = new ArrayList<>();
            for (Record record:list){
                RecordDto recordDto = new RecordDto();
                recordDto.setId(record.getId());
                recordDto.setGoodsId(record.getGoodsId());
                recordDto.setAdminId(record.getAdminId());
                recordDto.setUserId(record.getUserId());
                recordDto.setCount(record.getCount());
                recordDto.setCreateTime(record.getCreateTime());
                recordDto.setRemark(record.getRemark());
                Goods goods = goodsService.getById(record.getGoodsId());
                Storage storage = storageService.getById(goods.getStorageId());
                GoodsType goodsType = goodsTypeService.getById(goods.getGoodsTypeId());
                User user = userService.getById(record.getUserId());
                User admin = userService.getById(record.getAdminId());
                recordDto.setStorageName(storage.getName());
                recordDto.setGoodsTypeName(goodsType.getName());
                recordDto.setUserName(user.getName());
                recordDto.setAdminName(admin.getName());
                resList.add(recordDto);
            }
            PageInfo<RecordDto> goodsPageInfo = new PageInfo<>(resList, queryPageParam.getNavSize());
            return Result.success(goodsPageInfo);
        } catch (Exception e) {
            return Result.fail("获取失败");
        }
    }
}
