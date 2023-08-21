package com.tpe.controller;


import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    public String createStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult){

        //validaston hatasi varsa formu goster.
        if (bindingResult.hasErrors()){
            return "studentForm";
        }
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
    //update
    //http://localhost:8080/SpringMvc/students/update?id=1 + get
    @GetMapping("/update")
    public ModelAndView showStudentForm(@RequestParam("id") Long id){
        Student foundStudent= service.findStudentById(id);
        ModelAndView mav= new ModelAndView();
        mav.addObject("student",foundStudent);//idsi 1 olan secilmisse 1 olanin bilgileri gelicek
        mav.setViewName("studentForm");
        return mav;
    }
    //@RequestParam: bir metodun cagrilmasi sirasinda request ile bir query arametreyi almasini saglar
    //studentFormda <form:hidden path="id" /> var bununla idsi varsa update edecek


    //33 delete
    //delete: tum studentlari gosterelim
    //http://localhost:8080/SpringMvc/students/delete/1 +get
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id){
        service.deleteStudent(id);
        return "redirect:/students";
    }
    //@PathVariable request icindeki path prmtresinin degerini metodun parametresi olarak almamizi saglar

    //34
    //exception aldigimizda bunu handle etmedik
    //try-catch in catch gibi calisan bir annotasyon
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleNotFoundException(Exception ex){
        ModelAndView mav= new ModelAndView();
        mav.addObject("message",ex.getMessage());
        mav.setViewName("notFound");//hangi jsp dosyasi

        return mav;
    }


}
