package administration.gateway;

import administration.business.entity.Room;

import java.util.List;

public interface RoomDao {
    List<Room> getAll();

    void setRooms(List<Room> roomsWithNewPrice);
}
