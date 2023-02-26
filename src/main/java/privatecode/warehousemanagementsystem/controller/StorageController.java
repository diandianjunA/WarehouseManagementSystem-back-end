package privatecode.warehousemanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import privatecode.warehousemanagementsystem.common.QueryPageParamForStorage;
import privatecode.warehousemanagementsystem.common.Result;
import privatecode.warehousemanagementsystem.entity.Storage;
import privatecode.warehousemanagementsystem.service.StorageService;

import java.util.List;

@RestController
@RequestMapping("/storage")
@CrossOrigin
public class StorageController {
    @Autowired
    StorageService storageService;

    @GetMapping("/list")
    public Result<List<Storage>> list(){
        try {
            List<Storage> list = storageService.list();
            return Result.success(list);
        } catch (Exception e) {
            return Result.fail("获取失败");
        }
    }

    @PostMapping("/save")
    public Result<Integer> save(@RequestBody Storage storage){
        if(storageService.save(storage)){
            return Result.success(0);
        }else{
            return Result.fail("添加失败");
        }
    }

    @PostMapping("/mod")
    public Result<Integer> mod(@RequestBody Storage storage){
        if(storageService.updateById(storage)){
            return Result.success(0);
        }else{
            return Result.fail("修改失败");
        }
    }

    @PostMapping("/saveOrMod")
    public Result<Integer> saveOrMod(@RequestBody Storage storage){
        System.out.println(storage.getId());
        if(storageService.saveOrUpdate(storage)){
            return Result.success(0);
        }else{
            return Result.fail("添加或修改失败");
        }
    }

    @GetMapping("/delete")
    public Result<Integer> delete(Integer id){
        if(storageService.removeById(id)){
            return Result.success(0);
        }else{
            return Result.fail("删除失败");
        }
    }

    @PostMapping("/listPage")
    public Result<PageInfo<Storage>> listPage(@RequestBody QueryPageParamForStorage queryPageParam){
        try {
            PageHelper.startPage(queryPageParam.getPageNum(),queryPageParam.getPageSize());
            LambdaQueryWrapper<Storage> storageLambdaQueryWrapper = new LambdaQueryWrapper<>();
            if(queryPageParam.getId()!=null){
                storageLambdaQueryWrapper.eq(Storage::getId,queryPageParam.getId());
            }
            if(queryPageParam.getName()!=null){
                storageLambdaQueryWrapper.like(Storage::getName,queryPageParam.getName());
            }
            if(queryPageParam.getRemark()!=null){
                storageLambdaQueryWrapper.like(Storage::getRemark,queryPageParam.getRemark());
            }
            List<Storage> list = storageService.list(storageLambdaQueryWrapper);
            PageInfo<Storage> storagePageInfo = new PageInfo<>(list, queryPageParam.getNavSize());
            return Result.success(storagePageInfo);
        } catch (Exception e) {
            return Result.fail("获取失败");
        }
    }
}
