package privatecode.warehousemanagementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import privatecode.warehousemanagementsystem.entity.GoodsType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 17305
* @description 针对表【goods_type】的数据库操作Mapper
* @createDate 2023-02-24 22:21:52
* @Entity privatecode.warehousemanagementsystem.entity.GoodsType
*/
@Mapper
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {

}




