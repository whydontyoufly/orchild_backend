package com.orchid.tools;

import com.orchid.tools.annotation.Log;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by ljg on 2018/5/3.
 */
public class BaseDto {

    public List<Map<String,String>> getFieldValueList(){
        List<Map<String,String>> fieldList = new ArrayList<Map<String, String>>();
        Field[] fields = this.getClass().getDeclaredFields();
        Object object = this;
        try {
            fieldToValue(fieldList,fields,object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldList;
    }

    private void fieldToValue(List<Map<String ,String>> fieldList,Field[] fields,Object object) throws IllegalAccessException {
        for(Field field : fields){
            field.setAccessible(true);
            Object value = field.get(object);
            if(value==null){
                continue;
            }

            Log fieldLog = field.getAnnotation(Log.class);
            if(fieldLog==null){
                continue;
            }
            Map<String,String> fieldMap = new HashMap<String, String>();
            fieldMap.put("code",field.getName());
            fieldMap.put("name",fieldLog.title());

            //根据类型将字段值转化为字符串
            if(field.getType().equals(Double.class)){
                fieldMap.put("value",Double.toString((Double) value));
            }else if(field.getType().equals(Date.class)){
                fieldMap.put("value",DateUtil.format((Date) value));
            }else if(fieldLog.type().equals("object")){
                object=value;
                Field[] newFields = value.getClass().getDeclaredFields();
                fieldToValue(fieldList,newFields,object);
            }else {
                fieldMap.put("value",String.valueOf(value));
            }
            fieldList.add(fieldMap);
        }
    }
}


