package privatecode.warehousemanagementsystem.common;

import lombok.Data;

@Data
public class Result<T> {
    private int code;//状态码
    private String msg;//信息
    private Long total;//总记录数
    T data;//要返回的数据

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.code=200;
        result.msg="成功";
        result.data=data;
        return result;
    }

    public static <T> Result<T> fail(String msg){
        Result<T> result = new Result<>();
        result.code=400;
        result.msg=msg;
        return result;
    }
}
