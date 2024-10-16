package service;

import dao.EmployeeDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class EmployeeServiceTest {

    @BeforeEach
    void setUp() {
        EmployeeDAO employeeDAO = mock(EmployeeDAO.class);
        EmployeeService employeeService = new EmployeeService(employeeDAO);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void get() {
    }

    @Test
    void getAll() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}