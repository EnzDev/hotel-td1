import dao.RoomDao;
import dao.RoomDaoTest;
import administration.business.entity.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import service.RoomService;
import service.RoomServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RoomServiceTest {
    private static final RoomDao EMPTY_ROOM_DAO = new RoomDaoTest(new ArrayList<>());

    private static final RoomDao ONE_ROOM_DAO = new RoomDaoTest(Collections.singletonList(
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
    private static final RoomDao SAMPLE_ROOM_DAO = new RoomDaoTest(ROOMS_SAMPLE);


    @Test
    void should_return_empty_rooms() {
        // Given
        RoomService service = new RoomServiceImpl(EMPTY_ROOM_DAO);

        // When
        List<Room> allRooms = service.getAllRooms();

        // Then
        assertTrue(allRooms.isEmpty());
    }

    @Test
    void should_return_some_rooms() {
        // Given
        RoomService service = new RoomServiceImpl(ONE_ROOM_DAO);

        // When
        List<Room> allRooms = service.getAllRooms();

        // Then
        assertEquals(allRooms.size(), 1);
    }

    @Test
    void update_base_price_updates_price_floor0to3() {
        // Given
        List<Room> rooms = Arrays.asList(
                new Room(0, 1, 50),
                new Room(1, 101, 50),
                new Room(2, 201, 50),
                new Room(3, 301, 50)
        );
        RoomDaoTest roomDaoTest = new RoomDaoTest(rooms);
        RoomService service = new RoomServiceImpl(roomDaoTest);



        // When
        service.setPrice(100);

        // Then
        assertEquals(roomDaoTest.modifiedRoomAtIndex(0).getPrice(),100);
        assertEquals(roomDaoTest.modifiedRoomAtIndex(1).getPrice(), 107);
        assertEquals(roomDaoTest.modifiedRoomAtIndex(2).getPrice(),  122);
        assertEquals(roomDaoTest.modifiedRoomAtIndex(3).getPrice(),  133);
    }

    @Test
    void should_update_price_not_over_200() {
        // Given
        List<Room> rooms = Collections.singletonList(new Room(3, 301, 50));
        RoomService service = new RoomServiceImpl(new RoomDaoTest(rooms));

        // When
        service.setPrice(190);
        List<Room> allRooms = service.getAllRooms();

        // Then
        assertEquals(allRooms.get(0), new Room(3, 301, 200));
    }

    @Test
    void should_break_when_invalid_floor() {
        // Given
        List<Room> rooms = Collections.singletonList(new Room(4, 301, 50));
        RoomService service = new RoomServiceImpl(new RoomDaoTest(rooms));

        // When
        Executable executeSetPrice = () -> service.setPrice(0);

        // Then
        assertThrows(
                IllegalStateException.class,
                executeSetPrice
        );
    }


    @Test
    void should_return_exact_sample_data() {
        // Given
        RoomService service = new RoomServiceImpl(SAMPLE_ROOM_DAO);

        // When
        List<Room> allRooms = service.getAllRooms();

        // Then
        for (int i = 0; i < ROOMS_SAMPLE.size(); i++) {
            assertEquals(ROOMS_SAMPLE.get(i), allRooms.get(i));
        }
    }

    @Test
    void room_with_same_info_should_be_equals() {
        // Given
        RoomService service = new RoomServiceImpl(ONE_ROOM_DAO);

        // When
        List<Room> allRooms = service.getAllRooms();

        // Then
        assertEquals(allRooms.get(0), new Room(0, 1, 50));
    }
}
