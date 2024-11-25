package com.example.sprint1_main;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.CheckInSortStrategy;
import com.example.sprint1_main.model.CheckOutSortStrategy;
import com.example.sprint1_main.model.Context;
import com.example.sprint1_main.model.DateModel;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.LodgingModel;
import com.example.sprint1_main.model.ResDateSortStrategy;
import com.example.sprint1_main.model.ResTimeSortStrategy;
import com.example.sprint1_main.model.ReservationModel;
import com.example.sprint1_main.model.Sortable;
import com.example.sprint1_main.model.ReservationModel;
import com.example.sprint1_main.model.TimeModel;
import com.example.sprint1_main.model.TravelDatabaseModel;
import com.example.sprint1_main.model.TravelModel;
import com.example.sprint1_main.model.UserDatabaseModel;
import com.example.sprint1_main.model.UserModel;

import java.util.ArrayList;
import java.util.List;

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


    //Sprint 3 Tests

    public void testCheckInSort() {
        List<LodgingModel> lodgList = new ArrayList<>();

        LodgingModel lodg1 = new LodgingModel(new DateModel(1,1,1), new DateModel(1,1,1), 1, "Single", "loc1");
        LodgingModel lodg2 = new LodgingModel(new DateModel(2,2,2), new DateModel(2,2,2), 1, "Single", "loc2");
        LodgingModel lodg3 = new LodgingModel(new DateModel(3,3,3), new DateModel(3,3,3), 1, "Single", "loc3");

        lodgList.add(lodg1);
        lodgList.add(lodg3);
        lodgList.add(lodg2);

        assertEquals("loc1", lodgList.get(0).getLocation());
        assertEquals("loc3", lodgList.get(1).getLocation());
        assertEquals("loc2", lodgList.get(2).getLocation());

        Context context = new Context();

        context.setStrategy(new CheckInSortStrategy());

        List<Sortable> unsorted = new ArrayList<>();

        for (LodgingModel lodging: lodgList) {
            unsorted.add((Sortable) lodging);
        }

        List<Sortable> sorted = context.executeStrategy(unsorted);

        List<LodgingModel> newLodgings = new ArrayList<>();
        for (Sortable sortItem : sorted) {
            newLodgings.add((LodgingModel) sortItem);
        }

        assertEquals("loc1", newLodgings.get(0).getLocation());
        assertEquals("loc2", newLodgings.get(1).getLocation());
        assertEquals("loc3", newLodgings.get(2).getLocation());

    }


    public void testCheckOutSort() {
        List<LodgingModel> lodgList = new ArrayList<>();

        LodgingModel lodg1 = new LodgingModel(new DateModel(1,1,1), new DateModel(1,1,1), 1, "Single", "loc1");
        LodgingModel lodg2 = new LodgingModel(new DateModel(2,2,2), new DateModel(2,2,2), 1, "Single", "loc2");
        LodgingModel lodg3 = new LodgingModel(new DateModel(3,3,3), new DateModel(3,3,3), 1, "Single", "loc3");

        lodgList.add(lodg1);
        lodgList.add(lodg3);
        lodgList.add(lodg2);

        assertEquals("loc1", lodgList.get(0).getLocation());
        assertEquals("loc3", lodgList.get(1).getLocation());
        assertEquals("loc2", lodgList.get(2).getLocation());

        Context context = new Context();

        context.setStrategy(new CheckOutSortStrategy());

        List<Sortable> unsorted = new ArrayList<>();

        for (LodgingModel lodging: lodgList) {
            unsorted.add((Sortable) lodging);
        }

        List<Sortable> sorted = context.executeStrategy(unsorted);

        List<LodgingModel> newLodgings = new ArrayList<>();
        for (Sortable sortItem : sorted) {
            newLodgings.add((LodgingModel) sortItem);
        }

        assertEquals("loc1", newLodgings.get(0).getLocation());
        assertEquals("loc2", newLodgings.get(1).getLocation());
        assertEquals("loc3", newLodgings.get(2).getLocation());

    }


    public void testResDateSort() {
        List<ReservationModel> resList = new ArrayList<>();

        ReservationModel res1 = new ReservationModel("loc1", "website", new DateModel(1,1,1), new TimeModel(1,1));
        ReservationModel res2 = new ReservationModel();
        ReservationModel res3 = new ReservationModel();

        resList.add(res1);
        resList.add(res3);
        resList.add(res2);

        assertEquals("loc1", resList.get(0).getLocation());
        assertEquals("loc3", resList.get(1).getLocation());
        assertEquals("loc2", resList.get(2).getLocation());

        Context context = new Context();

        context.setStrategy(new ResDateSortStrategy());

        List<Sortable> unsorted = new ArrayList<>();

        for (ReservationModel reservation: resList) {
            unsorted.add((Sortable) reservation);
        }

        List<Sortable> sorted = context.executeStrategy(unsorted);

        List<ReservationModel> newRes = new ArrayList<>();
        for (Sortable sortItem : sorted) {
            newRes.add((ReservationModel) sortItem);
        }

        assertEquals("loc1", newRes.get(0).getLocation());
        assertEquals("loc2", newRes.get(1).getLocation());
        assertEquals("loc3", newRes.get(2).getLocation());

    }


    public void testResTimeSort() {
        List<ReservationModel> resList = new ArrayList<>();

        ReservationModel res1 = new ReservationModel("loc1", "website", new DateModel(1,1,1), new TimeModel(1,1));
        ReservationModel res2 = new ReservationModel();
        ReservationModel res3 = new ReservationModel();

        resList.add(res1);
        resList.add(res3);
        resList.add(res2);

        assertEquals("loc1", resList.get(0).getLocation());
        assertEquals("loc3", resList.get(1).getLocation());
        assertEquals("loc2", resList.get(2).getLocation());

        Context context = new Context();

        context.setStrategy(new ResTimeSortStrategy());

        List<Sortable> unsorted = new ArrayList<>();

        for (ReservationModel reservation: resList) {
            unsorted.add((Sortable) reservation);
        }

        List<Sortable> sorted = context.executeStrategy(unsorted);

        List<ReservationModel> newRes = new ArrayList<>();
        for (Sortable sortItem : sorted) {
            newRes.add((ReservationModel) sortItem);
        }

        assertEquals("loc1", newRes.get(0).getLocation());
        assertEquals("loc2", newRes.get(1).getLocation());
        assertEquals("loc3", newRes.get(2).getLocation());

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
    @Test
    public void getReservationLocationTest() {
        String location = "Milan, Italy";
        String website = "www.visitmilan.com";
        DateModel date = new DateModel(1, 22, 2023);
        TimeModel time = new TimeModel(4, 50);
        ReservationModel model = new ReservationModel(location, website, date, time);

        assertEquals("Milan, Italy", model.getLocation());
    }

    @Test
    public void getReservationWebsiteTest() {
        String location = "New York, USA";
        String website = "www.visitnyc.com";
        DateModel date = new DateModel(11, 15, 2019);
        TimeModel time = new TimeModel(6, 00);
        ReservationModel model = new ReservationModel(location, website, date, time);

        assertEquals("www.visitnyc.com", model.getWebsite());
    }

    @Test
    public void getReservationDateTest() {
        String location = "Vancouver, Canada";
        String website = "www.visitcanucks.com";
        DateModel date = new DateModel(3, 24, 2005);
        TimeModel time = new TimeModel(8, 00);
        ReservationModel model = new ReservationModel(location, website, date, time);

        DateModel resultDate = model.getDate();
        assertEquals(3, resultDate.getMonth());
        assertEquals(24, resultDate.getDay());
        assertEquals(2005, resultDate.getYear());
    }

    @Test
    public void getReservationTimeTest() {
        String location = "London, England";
        String website = "www.visitlondon.com";
        DateModel date = new DateModel(4, 3, 2024);
        TimeModel time = new TimeModel(12, 00);
        ReservationModel model = new ReservationModel(location, website, date, time);

        TimeModel resultTime = model.getTime();
        assertEquals(12, resultTime.getHour());
        assertEquals(00, resultTime.getMinute());
    }

    @Test
    public void travelCalulatorTest() { //tests duration calculator in the TravelModel class
        DateModel start = new DateModel(11, 24, 2024);
        DateModel end = new DateModel(12, 24, 2024);
        UserModel user = new UserModel("testing1", "555-1234", "John Doe", 30, "johnDoe", "123");

        TravelModel travel = new TravelModel(user, start, end);

        assertEquals(30, travel.getDuration());
    }

    public void travelModelTest() {
        UserModel newUser = new UserModel("UserName@email.com", "123-456-7890",
                "UserName", 18, "goodUserName", "goodPassword");
        DateModel startDate = new DateModel(12, 24, 2011);
        DateModel endDate = new DateModel(1, 2, 2012);
        TravelModel travel = new TravelModel(newUser, startDate, endDate);
        assertEquals(travel.getUser().getName(), "UserName");
        assertEquals(travel.getStartDate().getMonth(), 12);
        assertEquals(travel.getEndDate().getMonth(), 1);
    }

    @Test
    public void travelModelTest2() {
        UserModel newUser = new UserModel("UserName@email.com", "123-456-7890",
                "UserName", 18, "goodUserName", "goodPassword");
        DateModel startDate = new DateModel(12, 24, 2011);
        DateModel endDate = new DateModel(1, 2, 2012);
        TravelModel travel = new TravelModel(newUser, startDate, endDate);
        assertEquals(travel.getUser().getPhoneNumber(), "123-456-7890");
        assertEquals(travel.getStartDate().getDay(), 24);
        assertEquals(travel.getEndDate().getDay(), 2);
    }

    @Test
    public void travelModelTest3() {
        UserModel newUser = new UserModel("UserName@email.com", "123-456-7890",
                "UserName", 18, "goodUserName", "goodPassword");
        DateModel startDate = new DateModel(12, 24, 2011);
        DateModel endDate = new DateModel(1, 2, 2012);
        TravelModel travel = new TravelModel(newUser, startDate, endDate);
        assertEquals(travel.getUser().getPassword(), "goodPassword");
        assertEquals(travel.getStartDate().getYear(), 2011);
        assertEquals(travel.getEndDate().getYear(), 2012);
    }

    @Test
    public void travelModelSmallListTest() {
        UserModel user1 = new UserModel("alice@example.com", "555-0001", "Alice", 25, "alice25", "pass1");
        UserModel user2 = new UserModel("bob@example.com", "555-0002", "Bob", 28, "bob28", "pass2");

        TravelModel trip1 = new TravelModel(user1, new DateModel(5, 10, 2024),
                new DateModel(5, 20, 2024));
        TravelModel trip2 = new TravelModel(user2, new DateModel(6, 15, 2024),
                new DateModel(6, 25, 2024));

        List<TravelModel> travelPosts = new ArrayList<>();
        travelPosts.add(trip1);
        travelPosts.add(trip2);

        assertEquals(2, travelPosts.size());
        assertEquals("Alice", travelPosts.get(0).getUser().getName());
        assertEquals(5, travelPosts.get(0).getStartDate().getMonth());
        assertEquals("Bob", travelPosts.get(1).getUser().getName());
        assertEquals(6, travelPosts.get(1).getStartDate().getMonth());
    }

    @Test
    public void travelModelLargerListTest() {
        UserModel user1 = new UserModel("alice@example.com", "555-0001", "Alice", 25, "alice25", "pass1");
        UserModel user2 = new UserModel("bob@example.com", "555-0002", "Bob", 28, "bob28", "pass2");
        UserModel user3 = new UserModel("carol@example.com", "555-0003", "Carol", 30, "carol30", "pass3");
        UserModel user4 = new UserModel("dave@example.com", "555-0004", "Dave", 27, "dave27", "pass4");
        UserModel user5 = new UserModel("eve@example.com", "555-0005", "Eve", 26, "eve26", "pass5");

        TravelModel trip1 = new TravelModel(user1, new DateModel(5, 10, 2024), new DateModel(5, 20, 2024));
        TravelModel trip2 = new TravelModel(user2, new DateModel(6, 15, 2024), new DateModel(6, 25, 2024));
        TravelModel trip3 = new TravelModel(user3, new DateModel(7, 5, 2024), new DateModel(7, 15, 2024));
        TravelModel trip4 = new TravelModel(user4, new DateModel(8, 10, 2024), new DateModel(8, 20, 2024));
        TravelModel trip5 = new TravelModel(user5, new DateModel(9, 25, 2024), new DateModel(10, 5, 2024));

        List<TravelModel> travelPosts = new ArrayList<>();
        travelPosts.add(trip1);
        travelPosts.add(trip2);
        travelPosts.add(trip3);
        travelPosts.add(trip4);
        travelPosts.add(trip5);

        assertEquals(5, travelPosts.size());

        assertEquals(10, travelPosts.get(0).getStartDate().getDay());
        assertEquals(5, travelPosts.get(0).getEndDate().getMonth());

        assertEquals(15, travelPosts.get(1).getStartDate().getDay());
        assertEquals(6, travelPosts.get(1).getEndDate().getMonth());

        assertEquals(5, travelPosts.get(2).getStartDate().getDay());
        assertEquals(7, travelPosts.get(2).getEndDate().getMonth());

        assertEquals(10, travelPosts.get(3).getStartDate().getDay());
        assertEquals(8, travelPosts.get(3).getEndDate().getMonth());

        assertEquals(25, travelPosts.get(4).getStartDate().getDay());
        assertEquals(10, travelPosts.get(4).getEndDate().getMonth());
    }

    @Test
    public void travelModelEmailYearTest() {
        UserModel user = new UserModel("test.email@email.com", "111-222-3333",
                "Test User", 20, "testUser", "securePass123");
        DateModel startDate = new DateModel(3, 15, 2020);
        DateModel endDate = new DateModel(4, 10, 2020);
        TravelModel travel = new TravelModel(user, startDate, endDate);

        assertEquals("test.email@email.com", travel.getUser().getEmail());
        assertEquals(2020, travel.getStartDate().getYear());
        assertEquals(2020, travel.getEndDate().getYear());
    }
    @Test
    public void travelModelUsernameDayTest() {
        UserModel user = new UserModel("example.user@email.com", "999-888-7777",
                "Example User", 25, "exampleUser", "password456");
        DateModel startDate = new DateModel(10, 5, 2023);
        DateModel endDate = new DateModel(10, 15, 2023);
        TravelModel travel = new TravelModel(user, startDate, endDate);

        assertEquals("exampleUser", travel.getUser().getUsername());
        assertEquals(5, travel.getStartDate().getDay());
        assertEquals(15, travel.getEndDate().getDay());
    }

    @Test
    public void travelModelNameMonthTest() {
        UserModel user = new UserModel("user.name@email.com", "444-555-6666",
                "User Name", 30, "userName30", "pass1234");
        DateModel startDate = new DateModel(11, 1, 2025);
        DateModel endDate = new DateModel(12, 15, 2025);
        TravelModel travel = new TravelModel(user, startDate, endDate);

        assertEquals("User Name", travel.getUser().getName());
        assertEquals(11, travel.getStartDate().getMonth());
        assertEquals(12, travel.getEndDate().getMonth());
    }
    @Test
    public void travelModelPhoneDateTest() {
        UserModel user = new UserModel("testuser@email.com", "333-444-5555",
                "Test User", 35, "testUser35", "safePassword");
        DateModel startDate = new DateModel(2, 20, 2022);
        DateModel endDate = new DateModel(3, 1, 2022);
        TravelModel travel = new TravelModel(user, startDate, endDate);

        assertEquals("333-444-5555", travel.getUser().getPhoneNumber());
        assertEquals(2, travel.getStartDate().getMonth());
        assertEquals(20, travel.getStartDate().getDay());
        assertEquals(2022, travel.getStartDate().getYear());
        assertEquals(3, travel.getEndDate().getMonth());
        assertEquals(1, travel.getEndDate().getDay());
        assertEquals(2022, travel.getEndDate().getYear());
    }

    @Test
    public void testOverlappingTravelDates() {
        TravelModel trip1 = new TravelModel(
                new UserModel("user1@example.com", "555-1234", "User One", 30, "user1", "pass1"),
                new DateModel(6, 1, 2024),
                new DateModel(6, 10, 2024)
        );

        TravelModel trip2 = new TravelModel(
                new UserModel("user2@example.com", "555-5678", "User Two", 35, "user2", "pass2"),
                new DateModel(6, 5, 2024),
                new DateModel(6, 15, 2024)
        );

        boolean overlap = trip1.getStartDate().getYear() == trip2.getStartDate().getYear() &&
                trip1.getEndDate().getMonth() >= trip2.getStartDate().getMonth() &&
                trip1.getEndDate().getDay() >= trip2.getStartDate().getDay();

        assertTrue("Trips should overlap", overlap);

        boolean noOverlap = trip1.getEndDate().getMonth() < trip2.getStartDate().getMonth() ||
                (trip1.getEndDate().getMonth() == trip2.getStartDate().getMonth() &&
                        trip1.getEndDate().getDay() < trip2.getStartDate().getDay());

        assertFalse("Trips should not be entirely separate", noOverlap);
    }

    @Test
    public void testMultiThreadedNotesAccess() throws InterruptedException {
        TravelModel travel = new TravelModel(
                new UserModel("user@example.com", "555-1234", "Multi Thread User", 29, "threaduser", "threadpass"),
                new DateModel(8, 1, 2024),
                new DateModel(8, 10, 2024)
        );

        Thread thread1 = new Thread(() -> travel.getNotes().add("Thread 1: Pack sunscreen."));
        Thread thread2 = new Thread(() -> travel.getNotes().add("Thread 2: Check flight tickets."));
        Thread thread3 = new Thread(() -> travel.getNotes().add("Thread 3: Arrange airport pickup."));

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        assertEquals(3, travel.getNotes().size());
        assertTrue(travel.getNotes().contains("Thread 1: Pack sunscreen."));
        assertTrue(travel.getNotes().contains("Thread 2: Check flight tickets."));
        assertTrue(travel.getNotes().contains("Thread 3: Arrange airport pickup."));
    }
}