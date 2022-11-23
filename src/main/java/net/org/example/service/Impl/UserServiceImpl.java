package net.org.example.service.Impl;


import net.org.example.dao.UserDao;
import net.org.example.domain.User;
import net.org.example.service.UserService;
import net.org.example.util.CommonUtil;

public class UserServiceImpl implements UserService {

    //数据库层注入, 有点类似与spring boot的注解
    private UserDao userDao = new UserDao();

    /**
     * 用户登录 md5加密
     * @param countName
     * @param pwd
     * @return
     */
    @Override
    public User login(String countName, String pwd) {
//        String md5Pwd = CommonUtil.MD5(pwd);
        return userDao.login(countName,pwd);
    }
    /**
     * 忘记密码 返回真实密码
     * @param countName
     * @param tel
     * @param email
     * @return
     */
    @Override
    public String findTruePwd(String countName, String tel, String email) {
        return userDao.findTruePwd(countName,tel,email);
    }
    /**
     * 注册 正则验证
     * @param user
     * @return
     */
    @Override
    public int register(User user) {
//        //验证昵称格式
//        if (user.getNickName().length() < 2 && user.getNickName().length() >= 5){
//            System.out.println("昵称不符合,不满足长度为[2,5),请重新输入....");
//            return 0;
//        }
//        //验证密码的格式
//        if(!CommonUtil.checkPwd(user.getPwd())){
//            System.out.println("密码格式不正确,请重新输入.....");
//            return 0;
//        }
//        //验证手机号的格式
//        if (!CommonUtil.checkTel(user.getTel())){
//            System.out.println("手机号格式不正确,请重新输入....");
//            return 0;
//        }
//        //验证邮箱的格式
//        if (!CommonUtil.checkEmail(user.getEmail())){
//            System.out.println("邮箱格式不正确,请重新输入...");
//            return 0;
//        }
//        String md5Pwd = CommonUtil.MD5(user.getPwd());
//        user.setPwd(md5Pwd);
        //随机生成账户
        user.setCountName(CommonUtil.createCount());
        user.setType(1);
        user.setYear(18);
        return userDao.register(user);
    }

    /**
     * 返回查询结果,判断生成的账号是否唯一
     * @param countName
     * @return
     */
    @Override
    public boolean findUserByCountName(String countName) {
        return userDao.findUserByCountName(countName);
    }



    /**
     * 查询用户整体信息
     * @param countName
     * @param pwd
     * @return
     */
    @Override
    public User findUser(String countName, String pwd) {
//        String md5Pwd = CommonUtil.MD5(pwd);
        return userDao.findUser(countName,pwd);
    }

    /**
     * 更新密码
     * @param tel
     * @return
     */
    @Override
    public int updatePwdByTel(String pwd, String countName,String email, String tel) {
//        if (!CommonUtil.checkPwd(pwd)){
//            System.out.println("输入密码格式不正确,重新输入....");
//            return 0;
//        }
//        String md5Pwd = CommonUtil.MD5(pwd);
        return userDao.updatePwdByTel(pwd,countName,email,tel);
    }
}
