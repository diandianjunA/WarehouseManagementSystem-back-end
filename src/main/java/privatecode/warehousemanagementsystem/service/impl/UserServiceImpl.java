package privatecode.warehousemanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import privatecode.warehousemanagementsystem.entity.User;
import privatecode.warehousemanagementsystem.service.UserService;
import privatecode.warehousemanagementsystem.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 17305
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-01-31 12:15:06
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




