package service;

import dao.EmployeeChangeDAO;
import entities.EmployeeChange;
import jakarta.transaction.SystemException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class EmployeeChangeServiceTest {
    @Mock
    EmployeeChangeDAO employeeChangeDAO;
    @InjectMocks
    EmployeeChangeService employeeChangeService;

    @Nested
    @DisplayName("Get all employeeChange tests")
    class GetAll{

        @Test
        @DisplayName("this should return a full list of employeeChanges")
        void ShouldReturnFullEmployeeChangeList(){
            List<EmployeeChange> expected = new ArrayList<>();
            expected.add(new EmployeeChange());

            when(employeeChangeDAO.getAll()).thenReturn(expected);

            List<EmployeeChange> actual = employeeChangeService.getAll();

            assertNotNull(actual);
            assertArrayEquals(expected.toArray(), actual.toArray());

            verify(employeeChangeDAO, times(1)).getAll();
        }

        @Test
        @DisplayName("this should return an empty list of employeeChanges")
        void ShouldReturnEmptyEmployeeChangeList(){
            List<EmployeeChange> expected = Collections.emptyList();

            when(employeeChangeDAO.getAll()).thenReturn(expected);

            List<EmployeeChange> actual = employeeChangeService.getAll();

            assertNotNull(actual);
            assertArrayEquals(expected.toArray(), actual.toArray());

            verify(employeeChangeDAO, times(1)).getAll();
        }
    }

    @Nested
    @DisplayName("Get employeeChange by id tests")
    class Get{
        @Test
        @DisplayName("this should return the employeeChange")
        void ShouldReturnTheEmployeeChange(){
            EmployeeChange expected = new EmployeeChange();
            expected.setId(1);

            when(employeeChangeDAO.get(1)).thenReturn(Optional.of(expected));

            Optional<EmployeeChange> actual = employeeChangeService.get(1);

            assertNotNull(actual);
            assertEquals(expected, actual.orElse(null));

            verify(employeeChangeDAO, times(1)).get(1);
        }

        @Test
        @DisplayName("this should return no employeeChange")
        void ShouldReturnNoEmployeeChange(){
            EmployeeChange expected = new EmployeeChange();

            when(employeeChangeDAO.get(1)).thenReturn(Optional.of(expected));

            Optional<EmployeeChange> actual = employeeChangeService.get(1);

            assertNotNull(actual);
            assertEquals(expected, actual.orElse(null));

            verify(employeeChangeDAO, times(1)).get(1);
        }
    }
    @Nested
    @DisplayName("Save employeeChange tests")
    class Save {

        @Test
        @DisplayName("this should save the employeeChange successfully")
        void ShouldSaveEmployeeChangeSuccessfully() throws SystemException {
            EmployeeChange employeeChange = new EmployeeChange();
            employeeChange.setId(1);

            when(employeeChangeDAO.save(employeeChange)).thenReturn(true);

            boolean result = employeeChangeService.save(employeeChange);

            assertTrue(result);
            verify(employeeChangeDAO, times(1)).save(employeeChange);
        }

        @Test
        @DisplayName("this should fail to save the employeeChange")
        void ShouldFailToSaveEmployeeChange() throws SystemException {
            EmployeeChange employeeChange = new EmployeeChange();
            employeeChange.setId(1);

            when(employeeChangeDAO.save(employeeChange)).thenReturn(false);

            boolean result = employeeChangeService.save(employeeChange);

            assertFalse(result);
            verify(employeeChangeDAO, times(1)).save(employeeChange);
        }
    }
    @Nested
    @DisplayName("Update employeeChange tests")
    class Update {

        @Test
        @DisplayName("this should update the employeeChange successfully")
        void ShouldUpdateEmployeeChangeSuccessfully() throws SystemException {
            EmployeeChange employeeChange = new EmployeeChange();
            employeeChange.setId(1);

            when(employeeChangeDAO.update(employeeChange)).thenReturn(true);

            boolean result = employeeChangeService.update(employeeChange);

            assertTrue(result);
            verify(employeeChangeDAO, times(1)).update(employeeChange);
        }

        @Test
        @DisplayName("this should fail to update the employeeChange")
        void ShouldFailToUpdateEmployeeChange() throws SystemException {
            EmployeeChange employeeChange = new EmployeeChange();
            employeeChange.setId(1);

            when(employeeChangeDAO.update(employeeChange)).thenReturn(false);

            boolean result = employeeChangeService.update(employeeChange);

            assertFalse(result);
            verify(employeeChangeDAO, times(1)).update(employeeChange);
        }
    }
    @Nested
    @DisplayName("Delete employeeChange tests")
    class Delete {

        @Test
        @DisplayName("this should delete the employeeChange successfully")
        void ShouldDeleteEmployeeChangeSuccessfully() throws SystemException {
            EmployeeChange employeeChange = new EmployeeChange();
            employeeChange.setId(1);

            when(employeeChangeDAO.delete(employeeChange)).thenReturn(true);

            boolean result = employeeChangeService.delete(employeeChange);

            assertTrue(result);
            verify(employeeChangeDAO, times(1)).delete(employeeChange);
        }

        @Test
        @DisplayName("this should fail to delete the employeeChange")
        void ShouldFailToDeleteEmployeeChange() throws SystemException {
            EmployeeChange employeeChange = new EmployeeChange();
            employeeChange.setId(1);

            when(employeeChangeDAO.delete(employeeChange)).thenReturn(false);

            boolean result = employeeChangeService.delete(employeeChange);

            assertFalse(result);
            verify(employeeChangeDAO, times(1)).delete(employeeChange);
        }
    }

//    @Nested
//    @DisplayName("Get Employee Changed Fields tests")
//    class GetEmployeeChangedFields{
//
//    }

}