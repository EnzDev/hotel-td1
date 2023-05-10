package administration.repositories;

import administration.business.entity.Room;

import java.util.List;

public class RoomRepositoryParametrized implements RoomRepository {
    private final List<Room> rooms;

    public RoomRepositoryParametrized(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public List<Room> getAll() {
        return this.rooms;
    }

    @Override
    public void setRooms(List<Room> roomsWithNewPrice) { }
}
