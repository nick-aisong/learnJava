package cn.part02.ch19.adapter;

import java.util.Map;

//代码清单19-3 劳动服务公司的人员信息接口
public interface IOuterUser {
    //基本信息，比如名称，性别，手机号码等
    public Map getUserBaseInfo();

    //工作区域信息
    public Map getUserOfficeInfo();

    //用户的家庭信息
    public Map getUserHomeInfo();
}