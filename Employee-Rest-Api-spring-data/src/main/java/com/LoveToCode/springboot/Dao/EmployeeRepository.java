package com.LoveToCode.springboot.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LoveToCode.springboot.entity.Employee;

// @RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
