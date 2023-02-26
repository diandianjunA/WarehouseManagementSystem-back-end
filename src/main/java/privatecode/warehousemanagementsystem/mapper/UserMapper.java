package privatecode.warehousemanagementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import privatecode.warehousemanagementsystem.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 17305
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-01-31 12:15:06
* @Entity warehousemanagementsystem.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




