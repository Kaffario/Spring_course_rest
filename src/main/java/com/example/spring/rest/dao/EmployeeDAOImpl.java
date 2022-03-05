package com.example.spring.rest.dao;

import com.example.spring.rest.entity.Employee;
import com.example.spring.rest.exeption_handling.EmployeeIncorect;
import com.example.spring.rest.exeption_handling.NoEmployeeException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override

    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();

        List<Employee> allEmployees = session.createQuery("from Employee", Employee.class).getResultList();


        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {

        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Employee emp = session.get(Employee.class, id);

        if(emp == null) {
            System.out.println("!!!!!!!!!!!!!!!!!!");
            throw new NoEmployeeException("net takogo");
        }
            return emp;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("delete from Employee " + "where id =:empId");
        query.setParameter("empId", id);
        query.executeUpdate();
    }


}
