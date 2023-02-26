package privatecode.warehousemanagementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import privatecode.warehousemanagementsystem.entity.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 17305
* @description 针对表【storage】的数据库操作Mapper
* @createDate 2023-02-24 20:04:46
* @Entity privatecode.warehousemanagementsystem.entity.Storage
*/
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {

}




