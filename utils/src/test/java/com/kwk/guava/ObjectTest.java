package com.kwk.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import junit.framework.Assert;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class ObjectTest {

    @Test
    public void toStringTest() {
        Person p = new Person(10, "cyw");
        String ret = "Person{age=10, name=cyw}";
        Assert.assertEquals(p.toString(), ret);
    }

    @Test
    public void testEquals() {
        Person p1 = new Person(10, "cyw");
        Person p2 = new Person(10, "cyw");
        Assert.assertEquals(p1, p2);

        Person p3 = new Person(11, "cyw");
        Assert.assertFalse(p1.equals(p3));

        Person p4 = new Person(10, "cy1w");
        Assert.assertFalse(p1.equals(p4));
    }

    @Test
    public void testHash() {
        Person p1 = new Person(10, "cyw");
        Person p2 = new Person(10, "cyw");
        Set<Person> persons = Sets.newHashSet();
        persons.add(p1);
        persons.add(p2);
        Assert.assertEquals(persons.size(), 1);
    }

    @Test
    public void testCompare() {
        Person p1 = new Person(10, "cyw");
        Person p2 = new Person(11, "cyw");
        Person p3 = new Person(10, "cyy");
        List<Person> persons = Lists.newArrayListWithCapacity(3);
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return ComparisonChain.start().compare(o1.getAge(), o2.getAge()).compare(o1.getName(), o2.getName()).result();
            }
        });

        Assert.assertSame(persons.get(0), p1);
        Assert.assertSame(persons.get(1), p3);
        Assert.assertSame(persons.get(2), p2);
    }

    @Test
    public void testDiff() {
        Person p1 = new Person(20, "aaaa");
        Person p2 = new Person(20, "bbbb");
        String ret = MyCompare.start()
                .compare("age", p1.getAge(), p2.getAge())
                .compare("name", p1.getName(), p2.getName())
                .toString();
        System.out.println(ret);
    }
}

class MyCompare {
    private List<String> diff;

    private MyCompare() {
        diff = Lists.newArrayList();
    }

    public static MyCompare start() {
        return new MyCompare();
    }

    public MyCompare compare(String key, Object a, Object b) {
        if (!Objects.equal(a, b)) {
            diff.add(MessageFormat.format("{0}有差异: {1}, {2}", key, a, b));
        }
        return this;
    }

    public boolean isSame() {
        return diff.isEmpty();
    }

    public String toString() {
        return Joiner.on("\r\n").join(diff);
    }
}

class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("age", age).add("name", name).toString();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Person)) {
            return false;
        }

        Person other = (Person) obj;
        return age == other.age && name == other.name;
    }

    public int hashCode() {
        return Objects.hashCode(age, name);
    }

    int getAge() {
        return age;
    }

    String getName() {
        return name;
    }
}


