package StudentManage;

import java.util.Scanner;

public class StudentView {
    /*
    后面会反复键盘录入，没必要执行一个new一次scanner
    所以将Scanner对象放到成员位置
     */
    Scanner sc = new Scanner(System.in);

    /*
    老数组，长度50，代表班级最多放50人
    后面每个功能都需要使用数组，所以可将数组放到成员位置
     */
    Student[] students = new Student[50];

    /*
    定义一个count，记录数组中有多少个对象。
    因为数组不能直接遍历，没有存对象的位置遍历出来是null，
    调用get方法是空指针，所以用count记录存储对象的个数，
    直接遍历count次
    而且，后面会反复用到count（添加、删除）所以提到成员变量位置
     */
    int count = 0;

    /*
    新数组，一会删除元素的时候需要将删除后剩下的元素复制到新数组中
    因为数组定长，不能直接在原来的数组基础上随意改变长度
    由于一次删一个，所以新数组长度为老数组-1
    后面可能会反复使用新数组，所以定义到成员位置
     */
    Student[] newStudents = new Student[students.length-1];

    //start方法用于展示页面以及调用对应的功能
    public void start() {
        while(true) {
            System.out.println("-----------学生管理系统-----------");
            System.out.println("1 添加学生");
            System.out.println("2 修改学生");
            System.out.println("3 删除学生");
            System.out.println("4 查看学生");
            System.out.println("5 退   出");
            System.out.print("请选择（1-5）：");

            int num = sc.nextInt();
            System.out.println("-------------------------------");
            switch (num){
                case 1:
                    addstudent();
                    break;
                case 2:
                    updatastudent();
                    break;
                case 3:
                    deletesyudent();
                    break;
                case 4:
                    findallstudent();
                    break;
                case 5:
                    System.out.println("是否退出？按0退出，按9取消");
                    int key = sc.nextInt();
                    if(key==0) {
                        return;
                    } else if (key==9) {
                        break;
                    }
            }
        }
    }

    private void findallstudent() {
        System.out.println("学号"+"/t"+"姓名"+"/t"+"性别"+"/t"+"年龄");
        //排序(冒泡):防止删除完之后再重新添加删除的学号，所发生的乱序情况
        for (int j = 0; j < count; j++) {
            for (int i = 0; i < count-j; i++) {
                if(students[i].getId()>students[i+1].getId()) {
                    Student temp = students[i];
                    students[i] = students[i+1];
                    students[i+1] = temp;
                }
            }
        }
        if(count==0){
            System.out.println("还没有添加学生！");
        }
        else {
            for (int i = 0; i < count; i++) {
                System.out.println(students[i].getId()+"/t"+students[i].getName()+"/t"+students[i].getSex()+"/t"+students[i].getSex());
            }
        }
    }

    private void deletesyudent() {
        /*
        Tips：工具类：System   静态方法：arrayCopy
              参数1：原数组
              参数2：从原数组的哪个索引开始复制
              参数3：目标数组
              参数4：从目标数组的哪个位置开始粘贴
              参数5：复制多少个
         */
        //1.输入要删除的学生学号
        System.out.println("请输入要删除的学生学号：");
        int id = sc.nextInt();
        //2.根据id找索引
        int removeIndex = ArrayUtils.findIndexByid(students,id,count);
        System.arraycopy(students,0,newStudents,0,removeIndex);
        System.arraycopy(students,removeIndex+1,newStudents,removeIndex,count-removeIndex);
        students = newStudents;
        count--;
        System.out.println("删除成功！");
    }

    private void updatastudent() {
        //1.录入要修改的学生学号
        System.out.println("请输入要修改的学生学号：");
        int id = sc.nextInt();
        /*2.注意：学生id并不是数组索引
        所以应该根据id查找对应的索引
        */
        int updataIndex = ArrayUtils.findIndexByid(students,id,count);
        System.out.println("请输入学生姓名：");
        String name = sc.next();
        System.out.println("请输入学生性别");
        String sex = sc.next();
        System.out.println("请输入学生年龄");
        int age = sc.nextInt();
        Student student = new Student(id,sex,name,age);
        students[updataIndex] = student;
        System.out.println("修改成功！");
    }

    private void addstudent() {
        //1.键盘录入学生信息
        System.out.println("请输入学生学号：");
        int id = sc.nextInt();
        System.out.println("请输入学生姓名");
        String name = sc.next();
        System.out.println("请输入学生年龄");
        int age = sc.nextInt();
        System.out.println("请输入学生性别");
        String sex = sc.next();
        //2.将学生信息封装到Student对象中
        Student student = new Student(id,sex,name,age);
        //3.将封装好的Student对象放到students数组中
        students[count] = student;
        count++;//count为索引值
        System.out.println("添加成功！");
    }
}
