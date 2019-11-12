package cn.part02.ch19.adapter3;

import java.util.HashMap;
import java.util.Map;

//代码清单19-16 用户基本信息
public class OuterUserBaseInfo implements IOuterUserBaseInfo {
    /*
    * 用户的基本信息
    */
    public Map getUserBaseInfo() {
        HashMap baseInfoMap = new HashMap();
        baseInfoMap.put("userName", "这个员工叫混世魔王...");
        baseInfoMap.put("mobileNumber", "这个员工电话是...");
        return baseInfoMap;
    }
}