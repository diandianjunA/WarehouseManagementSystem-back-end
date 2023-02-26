package privatecode.warehousemanagementsystem.common;

import lombok.Data;
import privatecode.warehousemanagementsystem.entity.Record;

@Data
public class RecordDto extends Record {
    String storageName;
    String goodsTypeName;
    String userName;
    String adminName;
}
