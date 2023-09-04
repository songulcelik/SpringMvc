package com.tpe.repository;

import com.tpe.domain.Student;

import java.util.List;
import java.util.Optional;
//20
public interface StudentRepository {
    void save(Student student);
    List<Student> getAll();
    Optional<Student> findById(Long id);//nullpointer exc almamak icin
                                // dbde idnin bulunamamasi durumunda null deger yerine bos bir optional objesi donduren bir yapi
    void delete(Long id);
//21 StudentRepositoryImpl ye git
}
