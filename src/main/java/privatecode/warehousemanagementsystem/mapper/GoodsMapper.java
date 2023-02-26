package privatecode.warehousemanagementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import privatecode.warehousemanagementsystem.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 17305
* @description 针对表【goods】的数据库操作Mapper
* @createDate 2023-02-24 22:58:04
* @Entity privatecode.warehousemanagementsystem.entity.Goods
*/
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

}




