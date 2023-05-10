package administration.business.entity;

import administration.business.entity.dto.RoomFloorDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Root Entity **/
public class Floor {
    private static final Map<Integer, Double> FLOOR_RATES = new HashMap<Integer, Double>() {{
        put(0, 1.0);
        put(1, 1.07);
        put(2, 1.22);
        put(3, 1.33);
    }};

    int floorNumber;
    List<Room> rooms;

    public Floor(int floorNumber, List<Room> rooms) {
        this.floorNumber = floorNumber;
        this.rooms = rooms;
    }

    public void setGroundFloorPrice(double groundFloorPrice) {
        Double rate = FLOOR_RATES.get(this.floorNumber);
        if (rate == null) {
            throw new IllegalStateException("Room has an invalid floor");
        }

        rooms.forEach(room -> room.setRoomPrice(groundFloorPrice * rate));
    }

    public List<RoomFloorDto> toDto() {
        return rooms.stream()
                .map(Room::toDto)
                .map(roomDto -> new RoomFloorDto(roomDto.roomNumber, floorNumber, roomDto.price))
                .collect(Collectors.toList());
    }
}
