package service;

import administration.gateway.RoomDao;
import administration.business.entity.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {


    private final RoomDao dao;

    public RoomServiceImpl(RoomDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Room> getAllRooms() {
        return dao.getAll();
    }

    @Override
    public void setPrice(double groundFloorPrice) {

        List<Room> roomsWithNewPrice = getAllRooms();
        getAllRooms().forEach(room -> room.setRoomPrice(groundFloorPrice));
        dao.setRooms(roomsWithNewPrice);
    }
}
