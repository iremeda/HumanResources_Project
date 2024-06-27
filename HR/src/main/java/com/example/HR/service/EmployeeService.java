package com.example.HR.service;

import com.example.HR.model.Employee;
import com.example.HR.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired //Spring bu alanı otomatik olarak inject eder.
    private EmployeeRepository employeeRepository;

    //CRUD'un C harfi(create)
    public Employee hireEmployee(Employee employee){
        //Yeni bir çalışanı veritabanına kaydeder.
        return employeeRepository.save(employee);
    }

    //CRUD'un R harfi (read)
    public List<Employee> getAllEmployee(){
        //Tüm çalışanları veritabanından "findAll" metodu ile getirir.
        return employeeRepository.findAll();
    }
    public Optional<Employee> getEmployeeById(Long id){
        // Belirtilen kimlikteki çalışanı veritabanından getirir.
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails){
        //Belirtilen kimlikte çalışanı güncelleyen metot.

        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Çalışan bulunamadı"));
        //orElse yani if döngüsünün else kısmı gibi düşünebiliriz.Aradığımız çalışan bulunamadığında böyle bir metin gösteriyoruz.

    employee.setName(employeeDetails.getName());
    employee.setPosition(employeeDetails.getPosition());

    return employeeRepository.save(employee);
    }

    public void fireEmployee(Long id){
        //Belirtilen kimlikteki çalışanı silen metotur.

        Employee employee= employeeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Employee not found!"));

        //Silme işlemini yapan "delete" metodunu JPA repository içerisinden çağırırak kullandık.
        employeeRepository.delete(employee);
    }

}
