package administration.business.entity.dto;

import java.util.List;

public class HotelDto {
    public final List<RoomDto> rooms;

    public HotelDto(List<RoomDto> rooms) {
        this.rooms = rooms;
    }
}
