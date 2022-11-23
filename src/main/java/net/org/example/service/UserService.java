package net.org.example.service;


import net.org.example.domain.User;

/**
 * 用户类的服务层
 */
public interface UserService {
    /**
     * 登录 -> 根据账号和密码进行登录
     * @param countName
     * @param pwd
     * @return
     */
    User login(String countName, String pwd);

    /**
     * 忘记密码: 账号, 手机号, 邮箱验证
     * @param countName
     * @param tel
     * @param email
     * @return
     */
    String findTruePwd(String countName, String tel, String email);

    /**
     * 注册 md5加密
     * @param user
     * @return
     */
    int register(User user);

    /**
     * 查看是否有重复的账号, 保证账号的唯一性
     * @param countName
     * @return
     */
    boolean findUserByCountName(String countName);



    /**
     * 查询用户整体信息
     * @param countName
     * @param pwd
     * @return
     */
    User findUser(String countName, String pwd);

    /**
     * 修改密码
     * @param pwd
     * @param countName
     * @param email
     * @param tel
     * @return
     */
    int updatePwdByTel(String pwd, String countName,String email, String tel);
}
