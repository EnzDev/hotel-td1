package administration.repositories;

import administration.business.entity.Room;

import java.util.List;

public interface RoomRepository {
    List<Room> getAll();

    void setRooms(List<Room> roomsWithNewPrice);
}
