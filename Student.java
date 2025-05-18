package StudentManage;

public class Student {
    private int id;//学号
    private String name;//姓名
    private int age;//年龄
    private String sex;//性别

    public Student() {
    }

    public Student(int id, String sex, String name, int age) {
        this.id = id;
        this.sex = sex;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

