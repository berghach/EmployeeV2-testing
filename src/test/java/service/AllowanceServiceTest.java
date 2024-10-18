package service;

import dao.AllowanceDAO;
import entities.Allowance;
import entities.Employee;
import jakarta.transaction.SystemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AllowanceServiceTest {
    @Mock
    AllowanceDAO allowanceDAO;
    
    @InjectMocks
    AllowanceService allowanceService;

    @Nested
    @DisplayName("Get all allowance tests")
    class GetAll{

        @Test
        @DisplayName("this should return a full list of allowances")
        void ShouldReturnFullAllowanceList(){
            List<Allowance> expected = new ArrayList<>();
            expected.add(new Allowance());

            when(allowanceDAO.getAll()).thenReturn(expected);

            List<Allowance> actual = allowanceService.getAll();

            assertNotNull(actual);
            assertArrayEquals(expected.toArray(), actual.toArray());

            verify(allowanceDAO, times(1)).getAll();
        }

        @Test
        @DisplayName("this should return an empty list of allowances")
        void ShouldReturnEmptyAllowanceList(){
            List<Allowance> expected = Collections.emptyList();

            when(allowanceDAO.getAll()).thenReturn(expected);

            List<Allowance> actual = allowanceService.getAll();

            assertNotNull(actual);
            assertArrayEquals(expected.toArray(), actual.toArray());

            verify(allowanceDAO, times(1)).getAll();
        }
    }

    @Nested
    @DisplayName("Get allowance by id tests")
    class Get{
        @Test
        @DisplayName("this should return the allowance")
        void ShouldReturnTheAllowance(){
            Allowance expected = new Allowance();
            expected.setId((long) 1);

            when(allowanceDAO.get(1)).thenReturn(Optional.of(expected));

            Optional<Allowance> actual = allowanceService.get(1);

            assertNotNull(actual);
            assertEquals(expected, actual.orElse(null));

            verify(allowanceDAO, times(1)).get(1);
        }

        @Test
        @DisplayName("this should return no allowance")
        void ShouldReturnNoAllowance(){
            Allowance expected = new Allowance();

            when(allowanceDAO.get(1)).thenReturn(Optional.of(expected));

            Optional<Allowance> actual = allowanceService.get(1);

            assertNotNull(actual);
            assertEquals(expected, actual.orElse(null));

            verify(allowanceDAO, times(1)).get(1);
        }
    }
    @Nested
    @DisplayName("Save allowance tests")
    class Save {

        @Test
        @DisplayName("this should save the allowance successfully")
        void ShouldSaveAllowanceSuccessfully() throws SystemException {
            Allowance allowance = new Allowance();
            allowance.setId((long) 1);

            when(allowanceDAO.save(allowance)).thenReturn(true);

            boolean result = allowanceService.save(allowance);

            assertTrue(result);
            verify(allowanceDAO, times(1)).save(allowance);
        }

        @Test
        @DisplayName("this should fail to save the allowance")
        void ShouldFailToSaveAllowance() throws SystemException {
            Allowance allowance = new Allowance();
            allowance.setId((long) 1);

            when(allowanceDAO.save(allowance)).thenReturn(false);

            boolean result = allowanceService.save(allowance);

            assertFalse(result);
            verify(allowanceDAO, times(1)).save(allowance);
        }
    }
    @Nested
    @DisplayName("Update allowance tests")
    class Update {

        @Test
        @DisplayName("this should update the allowance successfully")
        void ShouldUpdateAllowanceSuccessfully() throws SystemException {
            Allowance allowance = new Allowance();
            allowance.setId((long) 1);

            when(allowanceDAO.update(allowance)).thenReturn(true);

            boolean result = allowanceService.update(allowance);

            assertTrue(result);
            verify(allowanceDAO, times(1)).update(allowance);
        }

        @Test
        @DisplayName("this should fail to update the allowance")
        void ShouldFailToUpdateAllowance() throws SystemException {
            Allowance allowance = new Allowance();
            allowance.setId((long) 1);

            when(allowanceDAO.update(allowance)).thenReturn(false);

            boolean result = allowanceService.update(allowance);

            assertFalse(result);
            verify(allowanceDAO, times(1)).update(allowance);
        }
    }
    @Nested
    @DisplayName("Delete allowance tests")
    class Delete {

        @Test
        @DisplayName("this should delete the allowance successfully")
        void ShouldDeleteAllowanceSuccessfully() throws SystemException {
            Allowance allowance = new Allowance();
            allowance.setId((long) 1);

            when(allowanceDAO.delete(allowance)).thenReturn(true);

            boolean result = allowanceService.delete(allowance);

            assertTrue(result);
            verify(allowanceDAO, times(1)).delete(allowance);
        }

        @Test
        @DisplayName("this should fail to delete the allowance")
        void ShouldFailToDeleteAllowance() throws SystemException {
            Allowance allowance = new Allowance();
            allowance.setId((long) 1);

            when(allowanceDAO.delete(allowance)).thenReturn(false);

            boolean result = allowanceService.delete(allowance);

            assertFalse(result);
            verify(allowanceDAO, times(1)).delete(allowance);
        }
    }
    public static class FakeEmployee extends Employee{
        public FakeEmployee() {
        }

        @Override
        public Double getSalary() {
            return super.getSalary();
        }

        @Override
        public void setSalary(Double salary) {
            super.setSalary(salary);
        }

        @Override
        public Integer getKids() {
            return super.getKids();
        }

        @Override
        public void setKids(Integer kids) {
            super.setKids(kids);
        }
    }
    @Nested
    @DisplayName("Calculate Allowance Amount tests")
    class CalculateAllowanceAmount{
        @Nested
        @DisplayName("Calculate Allowance Amount for Valid Requirements tests")
        class CalculateForValidRequirements{
            @Test
            @DisplayName("Case 1 salary <= 6000 && kids > 3")
            void Case1(){
                Employee fakeEmployeeValidRequirements = new Employee();
                fakeEmployeeValidRequirements.setSalary(5500.0);
                fakeEmployeeValidRequirements.setKids(5);

                double result = allowanceService.calculateAllowanceAmount(fakeEmployeeValidRequirements);
                assertEquals(1200.0, result);
            }
            @Test
            @DisplayName("Case 2 salary <= 6000 && kids <= 3")
            void Case2(){
                Employee fakeEmployeeValidRequirements = new Employee();
                fakeEmployeeValidRequirements.setSalary(5500.0);
                fakeEmployeeValidRequirements.setKids(2);

                double result = allowanceService.calculateAllowanceAmount(fakeEmployeeValidRequirements);
                assertEquals(600.0, result);
            }
            @Test
            @DisplayName("Case 3 salary >= 8000 && kids > 3")
            void Case3(){
                Employee fakeEmployeeValidRequirements = new Employee();
                fakeEmployeeValidRequirements.setSalary(9000.0);
                fakeEmployeeValidRequirements.setKids(5);

                double result = allowanceService.calculateAllowanceAmount(fakeEmployeeValidRequirements);
                assertEquals(820.0, result);
            }
            @Test
            @DisplayName("Case 4 salary >= 8000 && kids <= 3")
            void Case4(){
                Employee fakeEmployeeValidRequirements = new Employee();
                fakeEmployeeValidRequirements.setSalary(9000.0);
                fakeEmployeeValidRequirements.setKids(2);

                double result = allowanceService.calculateAllowanceAmount(fakeEmployeeValidRequirements);
                assertEquals(400.0, result);
            }
        }
        @Nested
        @DisplayName("Calculate Allowance Amount for Invalid Requirements tests")
        class CalculateForInvalidRequirements{
            @Test
            @DisplayName("Case 1 salary > 6000 && salary < 8000 && kids = 0")
            void Case1(){
                Employee fakeEmployeeValidRequirements = new Employee();
                fakeEmployeeValidRequirements.setSalary(7000.0);
                fakeEmployeeValidRequirements.setKids(0);

                double result = allowanceService.calculateAllowanceAmount(fakeEmployeeValidRequirements);
                assertEquals(0.0, result);
            }
            @Test
            @DisplayName("Case 1 salary > 6000 && salary < 8000 && kids != 0")
            void Case2(){
                Employee fakeEmployeeValidRequirements = new Employee();
                fakeEmployeeValidRequirements.setSalary(7000.0);
                fakeEmployeeValidRequirements.setKids(6);

                double result = allowanceService.calculateAllowanceAmount(fakeEmployeeValidRequirements);
                assertEquals(0.0, result);
            }
            @Test
            @DisplayName("Case 3 !(salary > 6000 && salary < 8000) && kids = 0")
            void Case3(){
                Employee fakeEmployeeValidRequirements = new Employee();
                fakeEmployeeValidRequirements.setSalary(9000.0);
                fakeEmployeeValidRequirements.setKids(0);

                double result = allowanceService.calculateAllowanceAmount(fakeEmployeeValidRequirements);
                assertEquals(0.0, result);
            }

        }
    }
}