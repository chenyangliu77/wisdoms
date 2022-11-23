package net.org.example.util;


import net.org.example.service.Impl.UserServiceImpl;
import net.org.example.service.UserService;

import java.security.MessageDigest;
import java.util.Random;

public class CommonUtil {
    /**
     * MD5加密处理， 可以让别人在数据库看不了密码
     * 参考资料: https://baike.baidu.com/item/MD5/212708?fr=aladdin
     * @param data
     * @return
     */
    public static String MD5(String data)  {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception exception) {
        }
        return null;
    }

    /**
     * 随机生成账号, 且不重复, 固定长度为10
     * @return
     */
    public static String createCount(){
        //生成数据库操作对象
        UserService userService = new UserServiceImpl();
        String countName = "";  //生成随机账号
        //随机生成十位数
        Random random = new Random();
        while (true){
            int start = random.nextInt(9) + 1;
            int content = 0;
            countName = countName + start;
            //存储数字的
            for (int i = 1; i < 10; i++){
                content = random.nextInt(10);
                countName = countName + content;
            }
            //判断是否重复
            boolean flag = userService.findUserByCountName(countName);
            if (flag){
                countName = ""; //存在该条数据,则清空继续执行循环
                continue;
            }else {
                break; //如果不存在该条数据就跳出循环
            }
        }
        //返回字符串
        return countName;
    }

    /**
     * 校验手机号格式: 第一位为1；第二位为3，5，7，8，9中的一位，后面取值范围为[0-9],总长度11位
     * @return
     */
    public static boolean checkTel(String tel){
        //正则表达式
        String regex = "[1][35789][0-9]{9}";
        if (tel.matches(regex)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 验证邮箱格式 邮箱的规则：以@qq.com或者以@163.com结尾，首位取值为[1-9]，总长度为：[11-19]
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        if (email == null || email == ""){
            return false;
        }
        //总长度为[11~19]
        int len = email.length();
        if (email.length() < 11 || email.length() > 19){
            return false; //如果长度不是在11~19之间就返回邮箱格式不正确
        }
        String[] element = email.split("@");
        element[1] = "@" + (element[1]);
        int firstElement;
        //第一个元素
        String e = email.substring(0,1);
        if (e.matches("\\d")){
            firstElement = Integer.parseInt(e); //将他转化为整型
            if (firstElement >= 1 && firstElement <= 9){
                if (element[1].equals("@qq.com") || element[1].equals("@163.com")){
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }else{
            return false; //如果不是数字就返回邮箱格式错误
        }
    }

    /**
     * 验证密码格式: 要求至少包含大写字母，小写字母，数字中的两位，且至少包含@#两种特殊符号中的一种，长度要求：[6,10]
     * @param pwd
     * @return
     */
    public static boolean checkPwd(String pwd){
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#])[A-Za-z\\d$@#]{6,10}";
        if (pwd.matches(regex)){
            return true;  //满足条件便可以
        }else {
            return false;
        }
    }
}