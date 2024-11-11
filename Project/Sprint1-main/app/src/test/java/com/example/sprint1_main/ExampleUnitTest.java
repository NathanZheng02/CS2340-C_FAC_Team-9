package com.example.sprint1_main;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.DateModel;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.LodgingModel;
import com.example.sprint1_main.model.ReservationModel;
import com.example.sprint1_main.model.TimeModel;
import com.example.sprint1_main.model.UserDatabaseModel;
import com.example.sprint1_main.model.UserModel;

import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void additionIsCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void userModelAccess() {
        //First User Test with getters
        UserModel newUser = new UserModel("UserName@email.com", "123-456-7890",
                                    "UserName", 18, "goodUserName", "goodPassword");

        assertEquals(newUser.getName(), "UserName");
        assertEquals(newUser.getEmail(), "UserName@email.com");

        //Second User Test with setters
        UserModel jonDoe = new UserModel("johnDoe@email.com", "123-456-7890",
                                    "John Doe", 25, "JohnDoe14", "secretAccountPass14!");
        jonDoe.setName("Jon Doe");
        jonDoe.setEmail("jonDoe@gmail.com");
        jonDoe.setUsername("JonDoe12");

        assertEquals(jonDoe.getName(), "Jon Doe");
        assertEquals(jonDoe.getEmail(), "jonDoe@gmail.com");
        assertEquals(jonDoe.getUsername(), "JonDoe12");
    }

    @Test
    public void correctDestinationName() {
        DestinationModel newDestination = new DestinationModel("Cornwall", new DateModel(5,5,2005), new DateModel(5,5,2006));

        assertEquals("Cornwall", newDestination.getDestinationName());
        System.out.println("Test correctDestinationName passed");
    }

    @Test
    public void correctEstimatedDays() {
        DestinationModel newDestination = new DestinationModel("Cornwall", new DateModel(5,5,2005), new DateModel(5,5,2006));

        assertEquals(365, newDestination.getEstimatedDays());
        System.out.println("Test correctEstimatedDays passed");
    }

    @Test
    public void correctManagerSingletonUser() {
        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        UserModel newUser = new UserModel("email", "number", "name", 8,
        "username", "password");

        manager.setCurrentUser(newUser);
        assertEquals("username", manager.getCurrentUser().getUsername());

        ApplicationManagerModel manager2 = ApplicationManagerModel.getInstance();
        assertEquals("username", manager2.getCurrentUser().getUsername());

    }

    @Test
    public void correctManagerSingletonDestination() {
        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        DestinationModel newDestination = new DestinationModel("Cornwall", new DateModel(5,5,2005), new DateModel(5,5,2006));


        manager.setCurrentDestination(newDestination);
        assertEquals("Cornwall", manager.getCurrentDestination().getDestinationName());

        ApplicationManagerModel manager2 = ApplicationManagerModel.getInstance();
        assertEquals("Cornwall", manager2.getCurrentDestination().getDestinationName());

    }

    @Test
    public void destinationModelDateTester() {
        DestinationModel dm = new DestinationModel("Paris", new DateModel(1, 1, 2024), new DateModel(2, 2, 2024));
        int September = 9;
        int October = 10;
        int November = 11;
        int December = 12;
        int invalidDate = 30;
        //Testing amount of days in a month
        assertEquals(dm.getDaysInMonth(September), 30);
        assertEquals(dm.getDaysInMonth(October), 31);
        assertEquals(dm.getDaysInMonth(November), 30);
        assertEquals(dm.getDaysInMonth(December), 31);
        assertEquals(dm.getDaysInMonth(invalidDate), 0);
    }

    @Test
    public void destinationModelGetTester() {
        DestinationModel dm = new DestinationModel("Paris", new DateModel(1, 1, 2024), new DateModel(2, 2, 2024));

        assertEquals(dm.getDestinationName(), "Paris");
        assertEquals(dm.getStartDate().getMonth(), 1);
        assertEquals(dm.getEndDate().getMonth(), 2);

        dm.setDestinationName("BeiJing");
        dm.setEndDate(new DateModel(3, 3, 2024));
        assertEquals(dm.getDestinationName(), "BeiJing");
        assertEquals(dm.getEndDate().getMonth(), 3);
    }
    @Test
    public void testSetAndGetUsername() {
        UserModel user = new UserModel("test@example.com", "555-1234", "Jane Doe", 30, "janeDoe", "password");
        user.setUsername("newUsername");
        assertEquals("newUsername", user.getUsername());
    }

    @Test
    public void testLoginStatus() {
        UserModel user = new UserModel("test@example.com", "555-1234", "Jane Doe", 30, "janeDoe", "password");
        user.setLoginStatus(true);
        assertTrue(user.getLoginStatus());
    }

    @Test
    public void testGetDestinationName() {
        DestinationModel destination = new DestinationModel("London", new DateModel(5, 10, 2022), new DateModel(5, 20, 2022));
        assertEquals("London", destination.getDestinationName());
    }

    @Test
    public void testSetEstimatedDays() {
        DestinationModel destination = new DestinationModel("London", new DateModel(5, 10, 2022), new DateModel(5, 20, 2022));

        destination.setEstimatedDays(10);
        assertEquals(10, destination.getEstimatedDays());
    }

    @Test
    public void testGetMonth() {
        DateModel date = new DateModel(12, 25, 2024);
        assertEquals(12, date.getMonth());
    }

    @Test
    public void testGetDay() {
        DateModel date = new DateModel(10, 30, 2027);
        assertEquals(30, date.getDay());
    }

    //Sprint 3 JUnit Tests
    @Test
    public void reservationModelTest() {
        String location = "Paris, France";
        String website = "www.visitparis.com";
        DateModel date = new DateModel(10, 2, 2024);
        TimeModel time = new TimeModel(9, 00);
        ReservationModel model = new ReservationModel(location, website, date, time);
        assertEquals(model.getDate().getMonth(), 10);
        assertEquals(model.getDate().getDay(), 2);
        assertEquals(model.getTime().getHour(), 9);
    }

    @Test
    public void reservationModelTest2() {
        String location = "Tokyo, Japan";
        String website = "www.welcomeTokyo.org";
        DateModel date = new DateModel(11, 10, 2024);
        TimeModel time = new TimeModel(13, 30);
        ReservationModel model = new ReservationModel(location, website, date, time);
        assertEquals(model.getDate().getYear(), 2024);
        assertEquals(model.getTime().getMinute(), 30);
        assertEquals(model.getTime().getHour(), 13);
    }

    @Test
    public void lodgingModelTest() {
        DateModel checkIn = new DateModel(1, 1,2021);
        DateModel checkOut = new DateModel(1, 3,2021);
        int numRooms = 2;
        String roomType = "Double";
        String location = "Paris";
        LodgingModel model = new LodgingModel(checkIn, checkOut, numRooms, roomType, location);
        assertEquals(model.getNumRooms(), 2);
        assertEquals(model.getRoomType(), "Double");
        assertEquals(model.getLocation(), "Paris");
    }

    @Test
    public void lodgingModelTest2() {
        DateModel checkIn = new DateModel(3, 27,2023);
        DateModel checkOut = new DateModel(3, 30,2023);
        int numRooms = 1;
        String roomType = "Double";
        String location = "Tokyo";
        LodgingModel model = new LodgingModel(checkIn, checkOut, numRooms, roomType, location);
        assertEquals(model.getCheckInTime().getMonth(), model.getCheckOutTime().getMonth());
        assertEquals(model.getCheckInTime().getDay() + 3, model.getCheckOutTime().getDay());
    }
}