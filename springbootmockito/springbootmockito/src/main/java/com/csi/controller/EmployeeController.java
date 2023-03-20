package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.repo.EmployeeRepo;
import com.csi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    EmployeeRepo employeeRepo;
    @PostMapping("/savedata")
    public ResponseEntity<Employee>saveData(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeServiceImpl.saveData(employee), HttpStatus.CREATED);
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>>getAllData(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @PutMapping("/updatedata/{empId}")
    public ResponseEntity<Employee>updateData(@PathVariable int empId , @RequestBody Employee employee){
        Employee employee1 =employeeRepo.findById(empId).orElseThrow(()-> new RecordNotFoundException("Employee Id Does NOt Exist"));

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpDOB(employee.getEmpDOB());
        return new ResponseEntity<>(employeeServiceImpl.updateData(employee),HttpStatus.CREATED);
    }

    @DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String>deleteDataById(@PathVariable int empId){
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }
}
