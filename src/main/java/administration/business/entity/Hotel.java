package administration.business.entity;

import administration.business.entity.dto.HotelDto;
import administration.business.entity.dto.RoomDto;

import java.util.List;
import java.util.stream.Collectors;

/** Root Entity **/
public class Hotel {
    List<Room> rooms;

    public Hotel(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setHotelGroundFloorPrice(double groundFloorPrice) {
        rooms.forEach(room -> room.setRoomPrice(groundFloorPrice));
    }

    public HotelDto toDto() {
        List<RoomDto> roomList = rooms.stream()
                .map(Room::toDto)
                .collect(Collectors.toList());
        return new HotelDto(roomList);
    }
}
