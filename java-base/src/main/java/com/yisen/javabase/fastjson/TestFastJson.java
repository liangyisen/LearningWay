package com.yisen.javabase.fastjson;

import com.alibaba.fastjson.*;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextValueFilter;
import com.alibaba.fastjson.serializer.JSONObjectCodec;
import lombok.Data;
import org.junit.Test;

import java.io.Serializable;
import java.util.HashMap;

public class TestFastJson {


    @Test
    public void aVoid() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 2; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("AGE", 10);
            jsonObject.put("FULL NAME", "Doe " + i);
            jsonObject.put("DATE OF BIRTH", "2016/12/12 12:12:12");
            jsonArray.add(jsonObject);
        }
        String jsonOutput = jsonArray.toJSONString();

        System.out.println("jsonArray = " + jsonArray);
    }

    @Test
    public void bVoid() {
        HashMap<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("AGE", 10);
        objectHashMap.put("FULL NAME", "Doe ");
        objectHashMap.put("DATE OF BIRTH", "2016/12/12 12:12:12");
        JSONObject json = new JSONObject(objectHashMap);
        System.out.println("json = " + json.toJSONString());

    }

    @Data
    public static class ValueFilterPO {
        public String name;
    }

    @Test
    public void givenContextFilter_whenJavaObject_thanJsonCorrect() {
        ContextValueFilter valueFilter = (context, object, name, value) -> {
            if (name.equals("DATE OF BIRTH")) {
                return "NOT TO DISCLOSE";
            }
            if (value.equals("John")) {
                return ((String) value).toUpperCase();
            } else {
                return null;
            }
        };
        ValueFilterPO valueFilterPO = new ValueFilterPO();
        valueFilterPO.setName("John");
        String jsonOutput = JSON.toJSONString(valueFilterPO, valueFilter);
        System.out.println("jsonOutput = " + jsonOutput);
    }


    @Test
    public void cVoid(){
        HashMap<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("name", "yise");
        objectHashMap.put("age", 1111);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", 111);
        stringObjectHashMap.put("type", "001");

        objectHashMap.put("info", stringObjectHashMap);
        boolean contains = JSONPath.contains(objectHashMap, "$.info.id");
        Object extract = JSONPath.extract(JSON.toJSONString(objectHashMap), "$.info.id");
        Object read = JSONPath.read(JSON.toJSONString(objectHashMap), "$.info.id");


        System.out.println("read = " + read);
        System.out.println("extract = " + extract);
        System.out.println("contains = " + contains);
    }

}
