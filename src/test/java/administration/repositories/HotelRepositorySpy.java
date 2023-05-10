package administration.repositories;

import administration.business.entity.Hotel;
import administration.business.entity.dto.RoomDto;

public class HotelRepositorySpy extends HotelRepositoryParametrized {
    private Hotel updatedHotel;

    public HotelRepositorySpy(Hotel hotel) {
        super(hotel);
    }

    @Override
    public void updateOneHotel(Hotel hotel) {
        this.updatedHotel = hotel;
    }

    public RoomDto modifiedRoomDtoAtIndex(int index){
        return updatedHotel.toDto().rooms.get(index);
    }

}
