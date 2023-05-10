package administration.rules;

import administration.business.entity.Floor;
import administration.business.entity.Hotel;
import administration.business.entity.Room;
import administration.business.entity.RoomPrice;
import administration.business.rules.usecase.UpdateRoomsPrice;
import administration.business.rules.usecase.impl.UpdateRoomsPriceImpl;
import administration.repositories.HotelRepositorySpy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateRoomsPriceTest {

    @Test
    void update_base_price_updates_price_floor0to3() {
        // Given
        List<Floor> floors = Arrays.asList(
                new Floor(0, Collections.singletonList(new Room(1, new RoomPrice(50)))),
                new Floor(1, Collections.singletonList(new Room(101, new RoomPrice(50)))),
                new Floor(2, Collections.singletonList(new Room(201, new RoomPrice(50)))),
                new Floor(3, Collections.singletonList(new Room(301, new RoomPrice(50))))
        );
        Hotel hotel = new Hotel(floors);
        HotelRepositorySpy roomDaoTest = new HotelRepositorySpy(hotel);
        UpdateRoomsPrice updateRoomsPrice = new UpdateRoomsPriceImpl(roomDaoTest);

        // When
        updateRoomsPrice.execute(100);

        // Then
        assertEquals(100, roomDaoTest.modifiedRoomAtIndex(0).price);
        assertEquals(107, roomDaoTest.modifiedRoomAtIndex(1).price);
        assertEquals( 122, roomDaoTest.modifiedRoomAtIndex(2).price);
        assertEquals( 133, roomDaoTest.modifiedRoomAtIndex(3).price);
    }

    @Test
    void should_update_price_not_over_200() {
        // Given
        List<Floor> floors = Collections.singletonList(
                new Floor(3, Collections.singletonList(new Room(301, new RoomPrice(50))))
        );

        Hotel hotel = new Hotel(floors);
        HotelRepositorySpy roomDaoTest = new HotelRepositorySpy(hotel);
        UpdateRoomsPrice updateRoomsPrice = new UpdateRoomsPriceImpl(roomDaoTest);

        // When
        updateRoomsPrice.execute(190);

        // Then
        assertEquals(3, roomDaoTest.modifiedRoomAtIndex(0).floor);
        assertEquals(301, roomDaoTest.modifiedRoomAtIndex(0).roomNumber);
        assertEquals(200, roomDaoTest.modifiedRoomAtIndex(0).price);
    }


    @Test
    void should_break_when_invalid_floor() {
        // Given
        List<Floor> floors = Collections.singletonList(
                new Floor(4, Collections.singletonList(new Room(401, new RoomPrice(50))))
        );
        Hotel hotel = new Hotel(floors);
        HotelRepositorySpy roomDaoTest = new HotelRepositorySpy(hotel);
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
