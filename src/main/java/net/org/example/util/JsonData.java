package net.org.example.util;

public class JsonData {

    /**
     * 返回状态码 -1表示失败, 0表示成功
     */
    private Integer code;

    /**
     * json数据
     */
    private Object data;

    /**
     * 信息
     */
    private String msg;

    public JsonData(){}

    //传递数据和状态码
    public JsonData(Integer code, Object data, String msg){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
    //只传递数据
    public JsonData(Object data){
        this.data = data;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
