package com.tpe.repository;

import com.tpe.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository//dbye erisim olan bir class oldugunu belirtiyorum
public class StudentRepositoryImpl implements StudentRepository{

    //21 bir objeye ihtiyacim var kendim olusturmak yerine spring olustursun diye component diyorduk
    //onun yerine @Repository

    //22rootta session factory olusturan bean vardi. o nedenle fiel olusturduk injection yap dedik
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void save(Student student) {
        Session session= sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        session.saveOrUpdate(student);//saveOrUpdate() obje dbde varsa update eder yoksa save eder


        tx.commit();
        session.close();
    }

    //23
    @Override
    public List<Student> getAll() {
        Session session= sessionFactory.openSession();
        Transaction tx=session.beginTransaction();

        List<Student> studentList =session.createQuery("from Student", Student.class).getResultList();

        tx.commit();
        session.close();
        return studentList;
    }

    //25
    @Override
    public Optional<Student> findById(Long id) {
        Session session= sessionFactory.openSession();
        Transaction tx=session.beginTransaction();

        Student student=session.get(Student.class,id);
        Optional<Student> opt= Optional.ofNullable(student);//student null deger alabilir.bos bir optional nesenesi doner

        tx.commit();
        session.close();

        return opt;
    }
    //26 icin studentserviceye git

    //24
    @Override
    public void delete(Long id) {
        Session session= sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        Student student=session.load(Student.class,id);//objeye baska islemler icin gerek olmadigi icin load kullandim
        session.delete(student);

        tx.commit();
        session.close();

    }
}
