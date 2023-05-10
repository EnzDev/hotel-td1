package administration.business.entity;

import administration.business.entity.dto.RoomFloorDto;

import java.util.List;
import java.util.stream.Collectors;

/** Root Entity **/
public class Hotel {
    List<Floor> floors;

    public Hotel(List<Floor> floors) {
        this.floors = floors;
    }

    public void setHotelGroundFloorPrice(double groundFloorPrice) {
        floors.forEach(floor -> floor.setGroundFloorPrice(groundFloorPrice));
    }

    public List<RoomFloorDto> toDto() {
        return floors.stream()
                .map(Floor::toDto)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
