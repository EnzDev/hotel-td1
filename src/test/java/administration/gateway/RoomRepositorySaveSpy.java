package administration.gateway;

import administration.business.entity.Room;
import administration.business.entity.dto.RoomDto;

import java.util.List;

public class RoomRepositorySaveSpy extends RoomRepositoryParametrized {
    private List<Room> savedRoom;

    public RoomRepositorySaveSpy(List<Room> rooms) {
        super(rooms);
    }

    @Override
    public void setRooms(List<Room> roomsWithNewPrice) {
        savedRoom = roomsWithNewPrice;
    }

    public RoomDto modifiedRoomDtoAtIndex(int index){
        return savedRoom.get(index).toDto();
    }
}
