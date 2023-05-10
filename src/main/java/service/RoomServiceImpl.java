package service;

import administration.business.rules.RoomPriceDependingOnFloor;
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

        List<Room> roomsWithNewPrice = getAllRooms()
                .stream()
                .map(room -> {
                    // Compute new price
                    double newPrice = RoomPriceDependingOnFloor.getRoomPrice(groundFloorPrice, room.getFloor());

                    return new Room(room.getFloor(), room.getRoomNumber(), newPrice);
                }).collect(Collectors.toList());

        dao.setRooms(roomsWithNewPrice);
    }
}
