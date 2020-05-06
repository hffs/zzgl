//package com.ruoyi.project.wordAction;
//
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.usermodel.Range;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class WriteDoc {
//    public void testWrite() throws Exception {
//        List<Users> list = new ArrayList<Users>();
//        list.add(new Users("a",10,"男",new Date()));
//        list.add(new Users("b",10,"女",new Date()));
//
//        String templatePath = "D:\\template.doc";
//        InputStream is = new FileInputStream(templatePath);
//        OutputStream os = null;
//        HWPFDocument doc = new HWPFDocument(is);
//        Range range = doc.getRange();
//        for(int i=0;i<list.size();i++){
//            Users user = list.get(i);
//            //把range范围内的${reportDate}替换为当前的日期
//            range.replaceText("${name}", user.getName());
//            range.replaceText("${sex}", user.getSex());
//            range.replaceText("${age}", String.valueOf(user.getAge()));
//            range.replaceText("${date}", user.getBirthday().toString());
//            os = new FileOutputStream(new File("D:\\"+user.getName()+".doc"));
//            //把doc输出到输出流中
//            doc.write(os);
//        }
//        os.close();
//        is.close();
//    }
//    class Users{
//        private String name,sex;
//        private int age;
//        private Date birthday;
//
//        public String getName() {
//            return name;
//        }
//
//        public Date getBirthday() {
//            return birthday;
//        }
//
//        public int getAge() {
//            return age;
//        }
//
//        public String getSex() {
//            return sex;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }
//
//        public void setSex(String sex) {
//            this.sex = sex;
//        }
//
//        public void setBirthday(Date birthday) {
//            this.birthday = birthday;
//        }
//        public Users(){}
//        public Users(String name,int age,String sex,Date birthday){
//            this.age = age;
//            this.name = name;
//            this.sex = sex;
//            this.birthday = birthday;
//        }
//    }
//}
