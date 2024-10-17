package service;

import dao.EmployeeDAO;
import entities.Employee;
import jakarta.transaction.SystemException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    EmployeeDAO employeeDAO;

    @InjectMocks
    EmployeeService employeeService;

    @Nested
    @DisplayName("Get all employee tests")
    class GetAll{

        @Test
        @DisplayName("this should return a full list of employees")
        void ShouldReturnFullEmployeeList(){
            List<Employee> expected = new ArrayList<>();
            expected.add(new Employee());

            when(employeeDAO.getAll()).thenReturn(expected);

            List<Employee> actual = employeeService.getAll();

            assertNotNull(actual);
            assertArrayEquals(expected.toArray(), actual.toArray());

            verify(employeeDAO, times(1)).getAll();
        }

        @Test
        @DisplayName("this should return an empty list of employees")
        void ShouldReturnEmptyEmployeeList(){
            List<Employee> expected = Collections.emptyList();

            when(employeeDAO.getAll()).thenReturn(expected);

            List<Employee> actual = employeeService.getAll();

            assertNotNull(actual);
            assertArrayEquals(expected.toArray(), actual.toArray());

            verify(employeeDAO, times(1)).getAll();
        }
    }

    @Nested
    @DisplayName("Get employee by id tests")
    class Get{
        @Test
        @DisplayName("this should return the employee")
        void ShouldReturnTheEmployee(){
            Employee expected = new Employee();
            expected.setId(1);

            when(employeeDAO.get(1)).thenReturn(Optional.of(expected));

            Optional<Employee> actual = employeeService.get(1);

            assertNotNull(actual);
            assertEquals(expected, actual.orElse(null));

            verify(employeeDAO, times(1)).get(1);
        }

        @Test
        @DisplayName("this should return no employee")
        void ShouldReturnNoEmployee(){
            Employee expected = new Employee();

            when(employeeDAO.get(1)).thenReturn(Optional.of(expected));

            Optional<Employee> actual = employeeService.get(1);

            assertNotNull(actual);
            assertEquals(expected, actual.orElse(null));

            verify(employeeDAO, times(1)).get(1);
        }
    }
    @Nested
    @DisplayName("Save employee tests")
    class Save {

        @Test
        @DisplayName("this should save the employee successfully")
        void ShouldSaveEmployeeSuccessfully() throws SystemException {
            Employee employee = new Employee();
            employee.setId(1);

            when(employeeDAO.save(employee)).thenReturn(true);

            boolean result = employeeService.save(employee);

            assertTrue(result);
            verify(employeeDAO, times(1)).save(employee);
        }

        @Test
        @DisplayName("this should fail to save the employee")
        void ShouldFailToSaveEmployee() throws SystemException {
            Employee employee = new Employee();
            employee.setId(1);

            when(employeeDAO.save(employee)).thenReturn(false);

            boolean result = employeeService.save(employee);

            assertFalse(result);
            verify(employeeDAO, times(1)).save(employee);
        }
    }
    @Nested
    @DisplayName("Update employee tests")
    class Update {

        @Test
        @DisplayName("this should update the employee successfully")
        void ShouldUpdateEmployeeSuccessfully() throws SystemException {
            Employee employee = new Employee();
            employee.setId(1);

            when(employeeDAO.update(employee)).thenReturn(true);

            boolean result = employeeService.update(employee);

            assertTrue(result);
            verify(employeeDAO, times(1)).update(employee);
        }

        @Test
        @DisplayName("this should fail to update the employee")
        void ShouldFailToUpdateEmployee() throws SystemException {
            Employee employee = new Employee();
            employee.setId(1);

            when(employeeDAO.update(employee)).thenReturn(false);

            boolean result = employeeService.update(employee);

            assertFalse(result);
            verify(employeeDAO, times(1)).update(employee);
        }
    }
    @Nested
    @DisplayName("Delete employee tests")
    class Delete {

        @Test
        @DisplayName("this should delete the employee successfully")
        void ShouldDeleteEmployeeSuccessfully() throws SystemException {
            Employee employee = new Employee();
            employee.setId(1);

            when(employeeDAO.delete(employee)).thenReturn(true);

            boolean result = employeeService.delete(employee);

            assertTrue(result);
            verify(employeeDAO, times(1)).delete(employee);
        }

        @Test
        @DisplayName("this should fail to delete the employee")
        void ShouldFailToDeleteEmployee() throws SystemException {
            Employee employee = new Employee();
            employee.setId(1);

            when(employeeDAO.delete(employee)).thenReturn(false);

            boolean result = employeeService.delete(employee);

            assertFalse(result);
            verify(employeeDAO, times(1)).delete(employee);
        }
    }
}