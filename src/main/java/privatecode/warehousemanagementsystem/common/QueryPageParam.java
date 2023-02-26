package privatecode.warehousemanagementsystem.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import privatecode.warehousemanagementsystem.entity.User;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class QueryPageParam extends User {
    //默认
    private static int PAGE_SIZE=20;
    private static int PAGE_NUM=1;
    private static int NAV_SIZE=5;

    private int pageSize=PAGE_SIZE;
    private int pageNum=PAGE_NUM;
    private int navSize=NAV_SIZE;

}
