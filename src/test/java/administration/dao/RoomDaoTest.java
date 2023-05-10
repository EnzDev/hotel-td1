package administration.dao;

import administration.business.entity.Room;
import administration.gateway.RoomDao;

import java.util.List;

public class RoomDaoTest implements RoomDao {
    private List<Room> rooms;

    public RoomDaoTest(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public List<Room> getAll() {
        return this.rooms;
    }

    @Override
    public void setRooms(List<Room> roomsWithNewPrice) {
        rooms = roomsWithNewPrice;
    }

    public Room modifiedRoomAtIndex(int index){return rooms.get(index);}
}
