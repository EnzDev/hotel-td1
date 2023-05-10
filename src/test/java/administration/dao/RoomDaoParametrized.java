package administration.dao;

import administration.business.entity.Room;
import administration.gateway.RoomDao;

import java.util.List;

public class RoomDaoParametrized implements RoomDao {
    private final List<Room> rooms;

    public RoomDaoParametrized(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public List<Room> getAll() {
        return this.rooms;
    }

    @Override
    public void setRooms(List<Room> roomsWithNewPrice) { }
}
