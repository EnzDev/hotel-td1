import dao.RoomDaoTest;
import model.Room;
import org.junit.jupiter.api.Test;
import service.RoomService;
import service.RoomServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomServiceTest {

    @Test
    void should_return_empty_rooms() {
        // Given
        RoomService service = new RoomServiceImpl(new RoomDaoTest(new ArrayList<>()));

        // When
        List<Room> allRooms = service.getAllRooms();

        // Then
        assertTrue(allRooms.isEmpty());
    }

    @Test
    void should_return_some_rooms() {
        // Given
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(0, 0, 0));
        RoomService service = new RoomServiceImpl(new RoomDaoTest(rooms));

        // When
        List<Room> allRooms = service.getAllRooms();

        // Then
        assertEquals(allRooms.size(), 1);
    }
}
