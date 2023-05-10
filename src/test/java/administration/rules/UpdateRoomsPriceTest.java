package administration.rules;

import administration.business.entity.Room;
import administration.business.rules.usecase.UpdateRoomsPrice;
import administration.business.rules.usecase.impl.UpdateRoomsPriceImpl;
import administration.repositories.RoomRepositorySaveSpy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateRoomsPriceTest {

    @Test
    void update_base_price_updates_price_floor0to3() {
        // Given
        List<Room> rooms = Arrays.asList(
                new Room(0, 1, 50),
                new Room(1, 101, 50),
                new Room(2, 201, 50),
                new Room(3, 301, 50)
        );
        RoomRepositorySaveSpy roomDaoTest = new RoomRepositorySaveSpy(rooms);
        UpdateRoomsPrice updateRoomsPrice = new UpdateRoomsPriceImpl(roomDaoTest);

        // When
        updateRoomsPrice.execute(100);

        // Then
        assertEquals(100, roomDaoTest.modifiedRoomDtoAtIndex(0).price);
        assertEquals(107, roomDaoTest.modifiedRoomDtoAtIndex(1).price);
        assertEquals( 122, roomDaoTest.modifiedRoomDtoAtIndex(2).price);
        assertEquals( 133, roomDaoTest.modifiedRoomDtoAtIndex(3).price);
    }

    @Test
    void should_update_price_not_over_200() {
        // Given
        List<Room> rooms = Collections.singletonList(new Room(3, 301, 50));

        RoomRepositorySaveSpy roomDaoTest = new RoomRepositorySaveSpy(rooms);
        UpdateRoomsPrice updateRoomsPrice = new UpdateRoomsPriceImpl(roomDaoTest);

        // When
        updateRoomsPrice.execute(190);

        // Then
        assertEquals(3, roomDaoTest.modifiedRoomDtoAtIndex(0).floor);
        assertEquals(301, roomDaoTest.modifiedRoomDtoAtIndex(0).roomNumber);
        assertEquals(200, roomDaoTest.modifiedRoomDtoAtIndex(0).price);
    }


    @Test
    void should_break_when_invalid_floor() {
        // Given
        List<Room> rooms = Collections.singletonList(new Room(4, 401, 50));

        RoomRepositorySaveSpy roomDaoTest = new RoomRepositorySaveSpy(rooms);
        UpdateRoomsPrice updateRoomsPrice = new UpdateRoomsPriceImpl(roomDaoTest);

        // When
        Executable executeSetPrice = () -> updateRoomsPrice.execute(100);

        // Then
        assertThrows(
                IllegalStateException.class,
                executeSetPrice
        );
    }
}
