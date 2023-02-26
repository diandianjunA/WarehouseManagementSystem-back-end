package privatecode.warehousemanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import privatecode.warehousemanagementsystem.common.QueryPageParam;
import privatecode.warehousemanagementsystem.common.Result;
import privatecode.warehousemanagementsystem.entity.User;
import privatecode.warehousemanagementsystem.service.UserService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result<List<User>> list(){
        try {
            List<User> list = userService.list();
            return Result.success(list);
        } catch (Exception e) {
            return Result.fail("获取失败");
        }
    }

    @PostMapping("/save")
    public Result<Integer> save(@RequestBody User user){
        if(userService.save(user)){
            return Result.success(0);
        }else{
            return Result.fail("添加失败");
        }
    }

    @PostMapping("/mod")
    public Result<Integer> mod(@RequestBody User user){
        if(userService.updateById(user)){
            return Result.success(0);
        }else{
            return Result.fail("修改失败");
        }
    }

    @PostMapping("/saveOrMod")
    public Result<Integer> saveOrMod(@RequestBody User user){
        if(userService.saveOrUpdate(user)){
            return Result.success(0);
        }else{
            return Result.fail("添加或修改失败");
        }
    }

    @GetMapping("/delete")
    public Result<Integer> delete(Integer id){
        if(userService.removeById(id)){
            return Result.success(0);
        }else{
            return Result.fail("删除失败");
        }
    }

    @PostMapping("/listPage")
    public Result<PageInfo<User>> listPage(@RequestBody QueryPageParam queryPageParam){
        try {
            PageHelper.startPage(queryPageParam.getPageNum(),queryPageParam.getPageSize());
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            if(queryPageParam.getName()!=null){
                userLambdaQueryWrapper.like(User::getName,queryPageParam.getName());
            }
            if(queryPageParam.getAge()!=null){
                userLambdaQueryWrapper.like(User::getAge,queryPageParam.getAge());
            }
            if(queryPageParam.getId()!=null){
                userLambdaQueryWrapper.eq(User::getId,queryPageParam.getId());
            }
            if(queryPageParam.getAccount()!=null){
                userLambdaQueryWrapper.like(User::getAccount,queryPageParam.getAccount());
            }
            if(queryPageParam.getSex()!=null){
                userLambdaQueryWrapper.like(User::getSex,queryPageParam.getSex());
            }
            if(queryPageParam.getPhone()!=null){
                userLambdaQueryWrapper.like(User::getPhone,queryPageParam.getPhone());
            }
            if(queryPageParam.getRoleId()!=null){
                userLambdaQueryWrapper.like(User::getRoleId,queryPageParam.getRoleId());
            }
            if(queryPageParam.getValid()!=null){
                userLambdaQueryWrapper.like(User::getValid,queryPageParam.getValid());
            }
            List<User> list = userService.list(userLambdaQueryWrapper);
            PageInfo<User> userPageInfo = new PageInfo<>(list, queryPageParam.getNavSize());
            return Result.success(userPageInfo);
        } catch (Exception e) {
            return Result.fail("获取失败");
        }
    }

    @GetMapping("/findByAccount")
    public Result<Integer> findByAccount(@RequestParam String account){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getAccount,account);
        List<User> list = userService.list(userLambdaQueryWrapper);
        if(list.isEmpty()){
            return Result.success(1);
        }else {
            return Result.success(0);
        }
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody User user){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getAccount,user.getAccount());
        userLambdaQueryWrapper.eq(User::getPassword,user.getPassword());
        List<User> list = userService.list(userLambdaQueryWrapper);
        if(list.isEmpty()){
            return Result.fail("账号或密码输入错误");
        }else {
            User user1 = list.get(0);
            if(user1.getValid()==0){
                return Result.fail("该用户已禁用");
            }else {
                if(user1.getRoleId()==2){
                    return Result.fail("该用户权限不够");
                }else {
                    return Result.success(user1);
                }
            }
        }
    }

    @GetMapping("/getIdByName")
    public Result<Integer> getIdByName(@RequestParam(value = "userName") String userName){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getName,userName);
        List<User> list = userService.list(userLambdaQueryWrapper);
        if(list.isEmpty()){
            return Result.success(null);
        }else{
            return Result.success(list.get(0).getId());
        }
    }
}
