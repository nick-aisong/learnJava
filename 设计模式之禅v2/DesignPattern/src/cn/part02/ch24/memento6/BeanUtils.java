package cn.part02.ch24.memento6;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;

//代码清单24-17 BeanUtils工具类
public class BeanUtils {
    //把bean的所有属性及数值放入到Hashmap中
    public static HashMap<String, Object> backupProp(Object bean) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            //获得Bean描述
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            //获得属性描述
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            //遍历所有属性
            for (PropertyDescriptor des : descriptors) {
                //属性名称
                String fieldName = des.getName();
                //读取属性的方法
                Method getter = des.getReadMethod();
                //读取属性值
                Object fieldValue = getter.invoke(bean, new Object[]{});
                if (!fieldName.equalsIgnoreCase("class")) {
                    result.put(fieldName, fieldValue);
                }
            }
        } catch (Exception e) {
            //异常处理
        }
        return result;
    }

    //把HashMap的值返回到bean中
    public static void restoreProp(Object bean, HashMap<String, Object> propMap) {
        try {
            //获得Bean描述
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            //获得属性描述
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            //遍历所有属性
            for (PropertyDescriptor des : descriptors) {
                //属性名称
                String fieldName = des.getName();
                //如果有这个属性
                if (propMap.containsKey(fieldName)) {
                    //写属性的方法
                    Method setter = des.getWriteMethod();
                    setter.invoke(bean, new Object[]{propMap.get(fieldName)});
                }
            }
        } catch (Exception e) {
            //异常处理
            System.out.println("shit");
            e.printStackTrace();
        }
    }
}

//该类大家在项目中会经常用到，可以作为参考使用。类似的功能有很多工具已经提供，
//比如Spring、Apache工具集commons等，大家也可以直接使用