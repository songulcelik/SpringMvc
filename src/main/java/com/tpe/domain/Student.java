package com.tpe.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
//14 burdan objeye karsilik tablo olusturmak icin.bura bitince //15 controller studentcontroller
@Entity
@Table(name = "t_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotBlank,@NotEmpty,@NotNull javax.validation kutuphanesinden geliyor. bunlarla mesaj gonderebiliyoruz kullaniciya
    //hazirlarken en basta validation yapiyor. @column tablo olustururken yapiyor
    @NotBlank(message = "Please provide firstname")//" " blank, ""empty ve null olamaz
    private String firstName;
    @NotEmpty(message = "Please provide lastname")//"" empty ve null olamaz
    private String lastName;
    @NotNull(message = "Please provide grade")//grade null olmasın
    private Integer grade;

    private LocalDateTime createDate=LocalDateTime.now();

    //getter-setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }//bunu silmiyoruz yeri gelince aciklicak

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    //toString
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", grade=" + grade +
                ", createDate=" + createDate +
                '}';
    }
}
