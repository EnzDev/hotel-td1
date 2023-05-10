package administration.dao;

import administration.business.entity.Room;

import java.util.List;

public class RoomDaoSaveSpy extends RoomDaoParametrized {
    private List<Room> savedRoom;

    public RoomDaoSaveSpy(List<Room> rooms) {
        super(rooms);
    }

    @Override
    public void setRooms(List<Room> roomsWithNewPrice) {
        savedRoom = roomsWithNewPrice;
    }

    public Room modifiedRoomAtIndex(int index){return savedRoom.get(index);}
}
