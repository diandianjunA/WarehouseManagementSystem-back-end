package privatecode.warehousemanagementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import privatecode.warehousemanagementsystem.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 17305
* @description 针对表【record】的数据库操作Mapper
* @createDate 2023-02-25 14:30:06
* @Entity privatecode.warehousemanagementsystem.entity.Record
*/
@Mapper
public interface RecordMapper extends BaseMapper<Record> {

}




