package com.tpe.controller;


import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//15
@Controller// clienttan gelen requestler karsilanacak
@RequestMapping("/students")//istekleri eslestircek. /students ile gelen tum requestleri karsila
//class level:classtaki tüm metodlar için geçerli
//metod level:sadece o metod için geçerli
public class StudentController {
    @Autowired
    private StudentService service;

    //controllerdan mav(veri+view dosyasının ismi) veya String olarak view name i dönebiliriz. ModelAndView objesi donduren method
    //16
    @GetMapping("/hi")//http://localhost:8080/SpringMvc/students/hi + GET
    public ModelAndView sayHi(){
        ModelAndView mav=new ModelAndView();//ModelAndView ozel bir class
        mav.addObject("message","Hi; ");//hi.jsp deki objeleri mappledik
        mav.addObject("messagebody","I am a Student Management System");
        mav.setViewName("hi");//modeli olusturduk birde viewname lazim onu yaptik
        return mav;
    }
    //view resolver:hi.jsp dosyasını views klasöründe bulur
    // ve mav içindeki datayı bind eder(uygun yerlere yerlestircek).


    //18 add student icin
    //@RequestMapping("/students/hi")
    @GetMapping("/new") //http://localhost:8080/SpringMvc/students/new + GET
    public String sendStudentForm(@ModelAttribute("student") Student student){//form icinde submit var bilgileri kaydetmek icin. @ModelAttribute view ile controller arasinda datanin aktarilmasini saglar
        //String olarak viewname gondercez
        //@ModelAttribute student dememizin nedeni studentForm icinde modelAttribute="student"

        return "studentForm";
    }
    //@ModelAttribute studentFormdaki "student" modelının controllera aktarılmasını sağlar.


    //28 save/create student: response olarak tum studentlari gosterelim
    //19 http://localhost:8080/SpringMvc/students/saveStudent + post ile gelen requesti karsilamam gerekiyorbu istedi karsilamak
    @PostMapping("/saveStudent")
    public String createStudent(@ModelAttribute Student student){
       //31
        service.saveStudent(student);

        //30
       return "redirect:/students";//takrar bu linke git     //http://localhost:8080/SpringMvc/students/
    }
    //20 db ile ilgili islemler icin repoya git


    //29tum studentlari listeleme
    //http://localhost:8080/SpringMvc/students/ + get
    @GetMapping
    public ModelAndView getStudent(){
        List<Student> allStudent= service.getAllStudent();

        ModelAndView mav= new ModelAndView();
        mav.addObject("studentList", allStudent);
        mav.setViewName("students");
        return mav;
    }

    //32



}
