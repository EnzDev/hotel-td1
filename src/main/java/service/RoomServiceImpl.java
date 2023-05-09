package service;

import dao.RoomDao;
import model.Room;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    private final RoomDao dao;

    public RoomServiceImpl(RoomDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Room> getAllRooms() {
        return dao.getAll();
    }
}
