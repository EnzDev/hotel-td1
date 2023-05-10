package administration.rules;

import administration.business.entity.Floor;
import administration.business.entity.Hotel;
import administration.business.entity.Room;
import administration.business.entity.RoomPrice;
import administration.business.rules.usecase.GetAllRooms;
import administration.business.rules.usecase.impl.GetAllRoomsImpl;
import administration.controller.HotelStringPresenter;
import administration.repositories.HotelRepository;
import administration.repositories.HotelRepositoryParametrized;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAllRoomsTest {

    private static final HotelRepository EMPTY_HOTEL_DAO = new HotelRepositoryParametrized(new Hotel(new ArrayList<>()));

    private static final HotelRepository ONE_ROOM_HOTEL_DAO = new HotelRepositoryParametrized(new Hotel(Collections.singletonList(
            new Floor(0, Collections.singletonList(new Room( 1, new RoomPrice(50))))
    )));

    private static final List<Floor> FLOORS_SAMPLE = Arrays.asList(
            new Floor(0, Arrays.asList(new Room(1, new RoomPrice(50)), new Room(2, new RoomPrice(50)))),
            new Floor(1, Arrays.asList(new Room(101, new RoomPrice(53.5)), new Room(102, new RoomPrice(53.5)), new Room(103, new RoomPrice(53.5)))),
            new Floor(2, Arrays.asList(new Room(201, new RoomPrice(61)), new Room(202, new RoomPrice(61)))),
            new Floor(3, Collections.singletonList(new Room(301, new RoomPrice(66.5))))
    );
    private static final HotelRepository SAMPLE_ROOM_DAO = new HotelRepositoryParametrized(new Hotel(FLOORS_SAMPLE));


    @Test
    void should_return_empty_rooms() {
        // Given
        GetAllRooms getAllRooms = new GetAllRoomsImpl(EMPTY_HOTEL_DAO);
        HotelStringPresenter presenter = new HotelStringPresenter();

        // When
        getAllRooms.execute(presenter);

        // Then
        assertTrue(presenter.read().isEmpty());
    }

    @Test
    void should_return_some_rooms() {
        // Given
        GetAllRooms getAllRooms = new GetAllRoomsImpl(ONE_ROOM_HOTEL_DAO);
        HotelStringPresenter presenter = new HotelStringPresenter();

        // When
        getAllRooms.execute(presenter);

        // Then
        assertFalse(presenter.read().isEmpty());
    }

    @Test
    void should_return_exact_sample_data() {
        // Given
        GetAllRooms getAllRooms = new GetAllRoomsImpl(SAMPLE_ROOM_DAO);
        HotelStringPresenter presenter = new HotelStringPresenter();

        // When
        getAllRooms.execute(presenter);

        // Then
        assertEquals("floor = 0, num = 1, price = 50\n" +
                        "floor = 0, num = 2, price = 50\n" +
                        "floor = 1, num = 101, price = 53.5\n" +
                        "floor = 1, num = 102, price = 53.5\n" +
                        "floor = 1, num = 103, price = 53.5\n" +
                        "floor = 2, num = 201, price = 61\n" +
                        "floor = 2, num = 202, price = 61\n" +
                        "floor = 3, num = 301, price = 66.5",
                presenter.read()
        );
    }

    @Test
    void room_with_same_info_should_be_equals() {
        // Given
        GetAllRooms getAllRooms = new GetAllRoomsImpl(ONE_ROOM_HOTEL_DAO);
        HotelStringPresenter presenter = new HotelStringPresenter();

        // When
        getAllRooms.execute(presenter);

        // Then
        assertEquals("floor = 0, num = 1, price = 50", presenter.read());
    }
}
