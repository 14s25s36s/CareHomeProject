package com.careHome.dao;

import com.careHome.pojo.Account;
import com.careHome.pojo.LiveInfo;
import com.careHome.pojo.UserInfo;

import java.util.List;

public interface LoginDao {
    /**
     * 查询账号信息
     *
     * @param userAccount
     * @return
     */
    public List<Account> selectOnAccountInfo(String userAccount);

    /**
     * 查询用户名是否存在
     *
     * @param registeruseraccount
     * @return
     */
    public int selectCheckExist(String registeruseraccount);

    /**
     * 向数据库插入注册的用户账户
     *
     * @param useraccount
     * @param password
     * @return
     */
    public int addUserAccount(String useraccount, String password);

    public int addUserAccountByAdministrator(String useraccount, String permissions);

    public int addAidtoUserInfo(int aid);

    public List<UserInfo> selectMyUserInfo(Integer aid);

    public int updateMyInfo(String uid, String uname, String usex, String uage, String uaddress);

    public int addFamilyInfo(String lname, String lage, String lsex, Integer uid, String careuid, String lstate);

    public List<LiveInfo> selectMyFamilyInfo(int uid, int start, int limit);

    public int selectCountFamily(int uid);

    public List<LiveInfo> selectOneMyFamilyInfo(String lname, Integer uid, int start, int limit);

    public int selectCountFamily(String lname, Integer uid);

    public List<LiveInfo> selectOneMyFamilyInfo(Integer uid);

    public int updatePassword(Integer aid, String password);

    public List<Account> selectOnAccountInfoByUserAccount(String useraccount);
}
