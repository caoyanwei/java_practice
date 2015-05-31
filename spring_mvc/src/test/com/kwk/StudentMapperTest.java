package com.kwk;

import com.kwk.dao.StudentEntity;
import com.kwk.dao.StudentMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentMapperTest extends BaseTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void abcTest() {
        StudentEntity studentEntity = studentMapper.getStudent(2);
        System.out.println("aaa");
    }
}
