package com.kwk.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class NoPropertyTest {
    @Test
    public void test1() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        A1 a1 = new A1();
        a1.setAge("aaaa");

        String a1Str = mapper.writeValueAsString(a1);
        A2 a2 = mapper.readValue(a1Str, A2.class);
        System.out.println(a2);
    }

    @Test
    public void test2() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //设置输出时包含属性的风格
//        mapper.setSerializationInclusion(include);
        //序列化时，忽略空的bean(即沒有任何Field)
//        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        //序列化时，忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //make all member fields serializable without further annotations, instead of just public fields (default setting).
//        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);


        A2 a2 = new A2();
        a2.setAge("aaaa");
        a2.setPoint("bbbb");

        String a2Str = mapper.writeValueAsString(a2);
        A1 a1 = mapper.readValue(a2Str, A1.class);
        System.out.println(a1);
    }
}

class A1 {
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "A1{" +
                "age='" + age + '\'' +
                '}';
    }
}

class A2 {
    private String point;
    private String age;

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "A2{" +
                "point='" + point + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
