package com.kiran.repository;

import com.kiran.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "student-api", path="student-api")
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findBySection(String section);

}



//http://localhost:8080/student-api?page=0&size=3&sort=section

//http://localhost:8080/student-api?page=0&size=3&sort=rollNO

//http://localhost:8080/student-api/search/findBySection?section=A

//