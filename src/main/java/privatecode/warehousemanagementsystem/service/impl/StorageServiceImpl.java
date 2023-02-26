package privatecode.warehousemanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import privatecode.warehousemanagementsystem.entity.Storage;
import privatecode.warehousemanagementsystem.service.StorageService;
import privatecode.warehousemanagementsystem.mapper.StorageMapper;
import org.springframework.stereotype.Service;

/**
* @author 17305
* @description 针对表【storage】的数据库操作Service实现
* @createDate 2023-02-24 20:04:47
*/
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage>
    implements StorageService{

}




