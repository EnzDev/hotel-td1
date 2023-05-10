package administration.rules;

import administration.business.entity.Room;
import administration.business.rules.usecase.GetAllRooms;
import administration.business.rules.usecase.impl.GetAllRoomsImpl;
import administration.business.rules.usecase.output.RoomsPresenter;
import administration.controller.RoomsStringPresenter;
import administration.dao.RoomDaoParametrized;
import administration.dao.RoomDaoSaveSpy;
import administration.gateway.RoomDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import service.RoomService;
import service.RoomServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAllRoomsTest {

    private static final RoomDao EMPTY_ROOM_DAO = new RoomDaoParametrized(new ArrayList<>());

    private static final RoomDao ONE_ROOM_DAO = new RoomDaoParametrized(Collections.singletonList(
            new Room(0, 1, 50)
    ));

    private static final List<Room> ROOMS_SAMPLE = Arrays.asList(
            new Room(0, 1, 50),
            new Room(0, 2, 50),
            new Room(1, 101, 53.5),
            new Room(1, 102, 53.5),
            new Room(1, 103, 53.5),
            new Room(2, 201, 61),
            new Room(2, 202, 61),
            new Room(3, 301, 66.5)
    );
    private static final RoomDao SAMPLE_ROOM_DAO = new RoomDaoParametrized(ROOMS_SAMPLE);


    @Test
    void should_return_empty_rooms() {
        // Given
        GetAllRooms getAllRooms = new GetAllRoomsImpl(EMPTY_ROOM_DAO);
        RoomsStringPresenter presenter = new RoomsStringPresenter();

        // When
        getAllRooms.execute(presenter);

        // Then
        assertTrue(presenter.read().isEmpty());
    }

    @Test
    void should_return_some_rooms() {
        // Given
        GetAllRooms getAllRooms = new GetAllRoomsImpl(ONE_ROOM_DAO);
        RoomsStringPresenter presenter = new RoomsStringPresenter();

        // When
        getAllRooms.execute(presenter);

        // Then
        assertFalse(presenter.read().isEmpty());
    }

    @Test
    void should_return_exact_sample_data() {
        // Given
        GetAllRooms getAllRooms = new GetAllRoomsImpl(SAMPLE_ROOM_DAO);
        RoomsStringPresenter presenter = new RoomsStringPresenter();

        // When
        getAllRooms.execute(presenter);

        // Then
        assertEquals(presenter.read(),
                "floor = 0, num = 1, price = 50\n" +
                        "floor = 0, num = 2, price = 50\n" +
                        "floor = 1, num = 101, price = 53.5\n" +
                        "floor = 1, num = 102, price = 53.5\n" +
                        "floor = 1, num = 103, price = 53.5\n" +
                        "floor = 2, num = 201, price = 61\n" +
                        "floor = 2, num = 202, price = 61\n" +
                        "floor = 3, num = 301, price = 66.5"
        );
    }

    @Test
    void room_with_same_info_should_be_equals() {
        // Given
        GetAllRooms getAllRooms = new GetAllRoomsImpl(ONE_ROOM_DAO);
        RoomsStringPresenter presenter = new RoomsStringPresenter();

        // When
        getAllRooms.execute(presenter);

        // Then
        assertEquals(presenter.read(), "floor = 0, num = 1, price = 50");
    }
}
