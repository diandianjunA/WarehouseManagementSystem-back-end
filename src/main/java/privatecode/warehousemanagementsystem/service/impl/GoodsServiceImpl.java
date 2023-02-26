package privatecode.warehousemanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import privatecode.warehousemanagementsystem.entity.Goods;
import privatecode.warehousemanagementsystem.service.GoodsService;
import privatecode.warehousemanagementsystem.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

/**
* @author 17305
* @description 针对表【goods】的数据库操作Service实现
* @createDate 2023-02-24 22:58:04
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

}




