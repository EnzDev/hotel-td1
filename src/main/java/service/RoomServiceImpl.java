package service;

import administration.gateway.RoomRepository;
import administration.business.entity.Room;

import java.util.List;

public class RoomServiceImpl implements RoomService {


    private final RoomRepository dao;

    public RoomServiceImpl(RoomRepository dao) {
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
