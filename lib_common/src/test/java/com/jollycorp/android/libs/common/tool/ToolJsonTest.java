package com.lzy.common.tool;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * desc: Json工具类test {@link ToolJson}<br/>
 * time: 2018/8/8 <br/>
 * author yangbincai <br/>
 * since V 1.2 <br/>
 */
public class ToolJsonTest {


    @Test
    public void testNull() {
        // 测试json为null下转换的结果
        String json = null;
        Assert.assertNull(ToolJson.json2Object(json, Person.class));
        Person[] ts = new Person[10];
        Person[] json2Array = ToolJson.json2Array(json, Person.class, ts);
        Assert.assertNotNull(json2Array);
        Assert.assertArrayEquals(json2Array,ts);
        Assert.assertNull(ToolJson.json2List(json, Person.class));
        Assert.assertNull(ToolJson.json2Map(json));

        // 测试对象为null下转换成json的结果
        Person person = null;
        Assert.assertNotNull(ToolJson.object2Json(person));
        Assert.assertEquals("null", ToolJson.object2Json(person));

        Person[] people = null;
        Assert.assertNotNull(ToolJson.array2Json(people));
        Assert.assertEquals("null", ToolJson.array2Json(people));

        List<Person> personList = null;
        Assert.assertNotNull(ToolJson.list2Json(personList));
        Assert.assertEquals("null", ToolJson.list2Json(personList));

        Map<String, Person> map = null;
        Assert.assertNotNull(ToolJson.map2Json(map));
        Assert.assertEquals("null", ToolJson.map2Json(map));
    }

    /**
     * {@link ToolJson#object2Json(Object)} 单元测试
     * {@link ToolJson#json2Object(String, Class)} 单元测试
     */
    @Test
    public void testObject() {
        Person person = new Person();
        person.setId(1);
        person.setName("James陈");
        person.setAge(23);
        person.setGender(1);

        Person child = new Person();
        child.setId(2);
        child.setName("James陈 儿子");
        child.setAge(2);
        child.setGender(1);

        List<Person> children = new ArrayList<>();
        children.add(child);
        person.setChildren(children);

        String personsJson = ToolJson.object2Json(person);
        System.out.println(personsJson);
        //{"age":23,"children":[{"age":2,"children":[],"gender":1,"id":2,"name":"James陈 儿子"}],"gender":1,"id":1,"name":"James陈"}

        Person person2 = ToolJson.json2Object(personsJson, Person.class);
        if (person2 != null) {
            System.out.println(person2.toString());
        }
        //Person{id=1, name='James陈', age=23, gender=1, children=[Person{id=2, name='James陈 儿子', age=2, gender=1, children=[]}]}
    }

    /**
     * {@link ToolJson#array2Json(Object[])} 单元测试
     * {@link ToolJson#json2Array(String, Class, Object[])} 单元测试
     */
    @Test
    public void testArray() {
        Person[] persons = new Person[10];
        Person person;
        Person child;
        for (int i = 0; i < persons.length; i++) {
            person = new Person();
            person.setId(i);
            person.setName("James陈" + i);
            person.setAge(20 + i);
            person.setGender(i > 5 ? 1 : 0);

            child = new Person();
            child.setId(100 + i);
            child.setName("James陈 " + i + "儿子");
            child.setAge(i);
            child.setGender(i < 5 ? 1 : 0);

            List<Person> children = new ArrayList<>(1);
            children.add(child);
            person.setChildren(children);

            persons[i] = person;
        }

        String personsJson = ToolJson.array2Json(persons);
        System.out.println(personsJson);
        //[{"age":20,"children":[{"age":0,"gender":1,"id":100,"name":"James陈 0儿子"}],"gender":0,"id":0,"name":"James陈0"},{"age":21,"children":[{"age":1,"gender":1,"id":101,"name":"James陈 1儿子"}],"gender":0,"id":1,"name":"James陈1"},{"age":22,"children":[{"age":2,"gender":1,"id":102,"name":"James陈 2儿子"}],"gender":0,"id":2,"name":"James陈2"},{"age":23,"children":[{"age":3,"gender":1,"id":103,"name":"James陈 3儿子"}],"gender":0,"id":3,"name":"James陈3"},{"age":24,"children":[{"age":4,"gender":1,"id":104,"name":"James陈 4儿子"}],"gender":0,"id":4,"name":"James陈4"},{"age":25,"children":[{"age":5,"gender":0,"id":105,"name":"James陈 5儿子"}],"gender":0,"id":5,"name":"James陈5"},{"age":26,"children":[{"age":6,"gender":0,"id":106,"name":"James陈 6儿子"}],"gender":1,"id":6,"name":"James陈6"},{"age":27,"children":[{"age":7,"gender":0,"id":107,"name":"James陈 7儿子"}],"gender":1,"id":7,"name":"James陈7"},{"age":28,"children":[{"age":8,"gender":0,"id":108,"name":"James陈 8儿子"}],"gender":1,"id":8,"name":"James陈8"},{"age":29,"children":[{"age":9,"gender":0,"id":109,"name":"James陈 9儿子"}],"gender":1,"id":9,"name":"James陈9"}]
        Person[] persons2 = new Person[]{};
        persons2 = ToolJson.json2Array(personsJson, Person.class, persons2);
        if (persons2 != null) {
            for (Person pe : persons2) {
                System.out.println(pe.toString());
            }
        }
    }

    /**
     * {@link ToolJson#list2Json(List)} 单元测试
     * {@link ToolJson#json2List(String, Class)} 单元测试
     */
    @Test
    public void testList() {
        List<Person> persons = new ArrayList<>(10);
        Person person;
        Person child;
        for (int i = 0; i < 10; i++) {
            person = new Person();
            person.setId(i);
            person.setName("James陈" + i);
            person.setAge(20 + i);
            person.setGender(i > 5 ? 1 : 0);

            child = new Person();
            child.setId(100 + i);
            child.setName("James陈 " + i + "儿子");
            child.setAge(i);
            child.setGender(i < 5 ? 1 : 0);

            List<Person> children = new ArrayList<>(1);
            children.add(child);
            person.setChildren(children);

            persons.add(person);
        }

        String personsJson = ToolJson.list2Json(persons);
        System.out.println(personsJson);
        //[{"age":20,"children":[{"age":0,"gender":1,"id":100,"name":"James陈 0儿子"}],"gender":0,"id":0,"name":"James陈0"},{"age":21,"children":[{"age":1,"gender":1,"id":101,"name":"James陈 1儿子"}],"gender":0,"id":1,"name":"James陈1"},{"age":22,"children":[{"age":2,"gender":1,"id":102,"name":"James陈 2儿子"}],"gender":0,"id":2,"name":"James陈2"},{"age":23,"children":[{"age":3,"gender":1,"id":103,"name":"James陈 3儿子"}],"gender":0,"id":3,"name":"James陈3"},{"age":24,"children":[{"age":4,"gender":1,"id":104,"name":"James陈 4儿子"}],"gender":0,"id":4,"name":"James陈4"},{"age":25,"children":[{"age":5,"gender":0,"id":105,"name":"James陈 5儿子"}],"gender":0,"id":5,"name":"James陈5"},{"age":26,"children":[{"age":6,"gender":0,"id":106,"name":"James陈 6儿子"}],"gender":1,"id":6,"name":"James陈6"},{"age":27,"children":[{"age":7,"gender":0,"id":107,"name":"James陈 7儿子"}],"gender":1,"id":7,"name":"James陈7"},{"age":28,"children":[{"age":8,"gender":0,"id":108,"name":"James陈 8儿子"}],"gender":1,"id":8,"name":"James陈8"},{"age":29,"children":[{"age":9,"gender":0,"id":109,"name":"James陈 9儿子"}],"gender":1,"id":9,"name":"James陈9"}]
        List<Person> persons2 = ToolJson.json2List(personsJson, Person.class);
        if (persons2 != null) {
            for (int i = 0; i < persons2.size(); i++) {
                System.out.println(persons2.get(i).toString());
            }
        }
        /*
           Person{id=0, name='James陈0', age=20, gender=0, children=[Person{id=100, name='James陈 0儿子', age=0, gender=1, children=null}]}
           Person{id=1, name='James陈1', age=21, gender=0, children=[Person{id=101, name='James陈 1儿子', age=1, gender=1, children=null}]}
           Person{id=2, name='James陈2', age=22, gender=0, children=[Person{id=102, name='James陈 2儿子', age=2, gender=1, children=null}]}
           Person{id=3, name='James陈3', age=23, gender=0, children=[Person{id=103, name='James陈 3儿子', age=3, gender=1, children=null}]}
           Person{id=4, name='James陈4', age=24, gender=0, children=[Person{id=104, name='James陈 4儿子', age=4, gender=1, children=null}]}
           Person{id=5, name='James陈5', age=25, gender=0, children=[Person{id=105, name='James陈 5儿子', age=5, gender=0, children=null}]}
           Person{id=6, name='James陈6', age=26, gender=1, children=[Person{id=106, name='James陈 6儿子', age=6, gender=0, children=null}]}
           Person{id=7, name='James陈7', age=27, gender=1, children=[Person{id=107, name='James陈 7儿子', age=7, gender=0, children=null}]}
           Person{id=8, name='James陈8', age=28, gender=1, children=[Person{id=108, name='James陈 8儿子', age=8, gender=0, children=null}]}
           Person{id=9, name='James陈9', age=29, gender=1, children=[Person{id=109, name='James陈 9儿子', age=9, gender=0, children=null}]}
        */
    }

    /**
     * {@link ToolJson#map2Json(Map)} 单元测试
     * {@link ToolJson#json2Map(String)} 单元测试
     */
    @Test
    public void testMap() {
        Person person = new Person();
        person.setId(1);
        person.setName("James陈");
        person.setAge(23);
        person.setGender(1);

        Person child = new Person();
        child.setId(2);
        child.setName("James陈 儿子");
        child.setAge(2);
        child.setGender(1);

        List<Person> children = new ArrayList<>();
        children.add(child);
        person.setChildren(children);

        Map<String, Person> map1 = new HashMap<>();
        map1.put("key1", person);
        String personJson = ToolJson.map2Json(map1);
        System.out.println(personJson);
        //{"key1":{"age":23,"children":[{"age":2,"gender":1,"id":2,"name":"James陈 儿子"}],"gender":1,"id":1,"name":"James陈"}}

        Map map2 = ToolJson.json2Map(personJson);
        System.out.println(map2 == null ? "" : map2.get("key1"));
        //{"gender":1,"children":[{"gender":1,"name":"James陈 儿子","id":2,"age":2}],"name":"James陈","id":1,"age":23}
    }


    static class Person {

        /**
         * age : 23
         * children : [{"age":2,"children":[],"gender":1,"id":2,"name":"James陈 儿子"}]
         * gender : 1
         * id : 1
         * name : James陈
         */

        private int age;
        private int gender;
        private int id;
        private String name;
        private List<Person> children;

        private Person() {

        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Person> getChildren() {
            return children;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", gender=" + gender +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", children=" + children +
                    '}';
        }
    }
}