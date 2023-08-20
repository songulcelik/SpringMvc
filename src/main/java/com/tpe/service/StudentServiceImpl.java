package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//27
@Service//bu classtan bir tane obje uretilip tum uygulama boyunca kullanilsin spring tarafindan
public class StudentServiceImpl implements StudentService{
    //28
    @Autowired
    private StudentRepository repo;

    @Override
    public void saveStudent(Student student) {
        repo.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return repo.getAll();
    }

    @Override
    public Student findStudentById(Long id) {
        Student student=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found by id : "+ id));//optiona istiyordu
        //get kullaninca da kizariklik gecer. gette gelen optional bossa dogrudan NoSuchElementException firlatiyor
        return student;
    }


    @Override
    public void deleteStudent(Long id) {
        //bu idli student yoksa serviceden bu idli ogrenci var mi diye bakalim
        Student student= findStudentById(id);//idli ogrenci yoksa boyle bir id yok dicek.silmeye calismadan bu idye sahi student yoksa exc firlatcak ustteki methoddan
        repo.delete(student.getId());
    }
}
