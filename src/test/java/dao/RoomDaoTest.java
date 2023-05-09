package dao;

import model.Room;

import java.util.List;

public class RoomDaoTest implements RoomDao {
    private final List<Room> rooms;

    public RoomDaoTest(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public List<Room> getAll() {
        return this.rooms;
    }
}