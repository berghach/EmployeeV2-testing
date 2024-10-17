package service;

import dao.HolidayDAO;
import entities.Holiday;
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

@ExtendWith(MockitoExtension.class)
class HolidayServiceTest {

    @Mock
    HolidayDAO holidayDAO;

    @InjectMocks
    HolidayService holidayService;

    @Nested
    @DisplayName("Get all holiday tests")
    class GetAll{

        @Test
        @DisplayName("this should return a full list of holidays")
        void ShouldReturnFullHolidayList(){
            List<Holiday> expected = new ArrayList<>();
            expected.add(new Holiday());

            when(holidayDAO.getAll()).thenReturn(expected);

            List<Holiday> actual = holidayService.getAll();

            assertNotNull(actual);
            assertArrayEquals(expected.toArray(), actual.toArray());

            verify(holidayDAO, times(1)).getAll();
        }

        @Test
        @DisplayName("this should return an empty list of holidays")
        void ShouldReturnEmptyHolidayList(){
            List<Holiday> expected = Collections.emptyList();

            when(holidayDAO.getAll()).thenReturn(expected);

            List<Holiday> actual = holidayService.getAll();

            assertNotNull(actual);
            assertArrayEquals(expected.toArray(), actual.toArray());

            verify(holidayDAO, times(1)).getAll();
        }
    }

    @Nested
    @DisplayName("Get holiday by id tests")
    class Get{
        @Test
        @DisplayName("this should return the holiday")
        void ShouldReturnTheHoliday(){
            Holiday expected = new Holiday();
            expected.setId((long) 1);

            when(holidayDAO.get(1)).thenReturn(Optional.of(expected));

            Optional<Holiday> actual = holidayService.get(1);

            assertNotNull(actual);
            assertEquals(expected, actual.orElse(null));

            verify(holidayDAO, times(1)).get(1);
        }

        @Test
        @DisplayName("this should return no holiday")
        void ShouldReturnNoHoliday(){
            Holiday expected = new Holiday();

            when(holidayDAO.get(1)).thenReturn(Optional.of(expected));

            Optional<Holiday> actual = holidayService.get(1);

            assertNotNull(actual);
            assertEquals(expected, actual.orElse(null));

            verify(holidayDAO, times(1)).get(1);
        }
    }
    @Nested
    @DisplayName("Save holiday tests")
    class Save {

        @Test
        @DisplayName("this should save the holiday successfully")
        void ShouldSaveHolidaySuccessfully() throws SystemException {
            Holiday holiday = new Holiday();
            holiday.setId((long) 1);

            when(holidayDAO.save(holiday)).thenReturn(true);

            boolean result = holidayService.save(holiday);

            assertTrue(result);
            verify(holidayDAO, times(1)).save(holiday);
        }

        @Test
        @DisplayName("this should fail to save the holiday")
        void ShouldFailToSaveHoliday() throws SystemException {
            Holiday holiday = new Holiday();
            holiday.setId((long) 1);

            when(holidayDAO.save(holiday)).thenReturn(false);

            boolean result = holidayService.save(holiday);

            assertFalse(result);
            verify(holidayDAO, times(1)).save(holiday);
        }
    }
    @Nested
    @DisplayName("Update holiday tests")
    class Update {

        @Test
        @DisplayName("this should update the holiday successfully")
        void ShouldUpdateHolidaySuccessfully() throws SystemException {
            Holiday holiday = new Holiday();
            holiday.setId((long) 1);

            when(holidayDAO.update(holiday)).thenReturn(true);

            boolean result = holidayService.update(holiday);

            assertTrue(result);
            verify(holidayDAO, times(1)).update(holiday);
        }

        @Test
        @DisplayName("this should fail to update the holiday")
        void ShouldFailToUpdateHoliday() throws SystemException {
            Holiday holiday = new Holiday();
            holiday.setId((long) 1);

            when(holidayDAO.update(holiday)).thenReturn(false);

            boolean result = holidayService.update(holiday);

            assertFalse(result);
            verify(holidayDAO, times(1)).update(holiday);
        }
    }
    @Nested
    @DisplayName("Delete holiday tests")
    class Delete {

        @Test
        @DisplayName("this should delete the holiday successfully")
        void ShouldDeleteHolidaySuccessfully() throws SystemException {
            Holiday holiday = new Holiday();
            holiday.setId((long) 1);

            when(holidayDAO.delete(holiday)).thenReturn(true);

            boolean result = holidayService.delete(holiday);

            assertTrue(result);
            verify(holidayDAO, times(1)).delete(holiday);
        }

        @Test
        @DisplayName("this should fail to delete the holiday")
        void ShouldFailToDeleteHoliday() throws SystemException {
            Holiday holiday = new Holiday();
            holiday.setId((long) 1);

            when(holidayDAO.delete(holiday)).thenReturn(false);

            boolean result = holidayService.delete(holiday);

            assertFalse(result);
            verify(holidayDAO, times(1)).delete(holiday);
        }
    }
}