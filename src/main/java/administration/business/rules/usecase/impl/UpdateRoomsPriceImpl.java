package administration.business.rules.usecase.impl;

import administration.business.entity.Room;
import administration.business.rules.usecase.UpdateRoomsPrice;
import administration.gateway.RoomDao;

import java.util.List;

public class UpdateRoomsPriceImpl implements UpdateRoomsPrice {
    RoomDao roomDao;

    public UpdateRoomsPriceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public void execute(double groundFloorPrice) {
        List<Room> allRooms = this.roomDao.getAll();
        allRooms.forEach(room -> room.setRoomPrice(groundFloorPrice));
        this.roomDao.setRooms(allRooms);
    }
}
