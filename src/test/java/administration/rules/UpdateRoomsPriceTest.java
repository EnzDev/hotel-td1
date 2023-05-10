package administration.rules;

import administration.business.entity.Room;
import administration.business.rules.usecase.GetAllRooms;
import administration.business.rules.usecase.UpdateRoomsPrice;
import administration.business.rules.usecase.impl.GetAllRoomsImpl;
import administration.business.rules.usecase.impl.UpdateRoomsPriceImpl;
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
        RoomDaoSaveSpy roomDaoTest = new RoomDaoSaveSpy(rooms);
        UpdateRoomsPrice updateRoomsPrice = new UpdateRoomsPriceImpl(roomDaoTest);

        // When
        updateRoomsPrice.execute(100);

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

        RoomDaoSaveSpy roomDaoTest = new RoomDaoSaveSpy(rooms);
        UpdateRoomsPrice updateRoomsPrice = new UpdateRoomsPriceImpl(roomDaoTest);

        // When
        updateRoomsPrice.execute(190);

        // Then
        assertEquals(roomDaoTest.modifiedRoomAtIndex(0), new Room(3, 301, 200));
    }
}
