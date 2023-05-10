package administration.repositories;

import administration.business.entity.Hotel;
import administration.business.entity.dto.RoomFloorDto;

public class HotelRepositorySpy extends HotelRepositoryParametrized {
    private Hotel updatedHotel;

    public HotelRepositorySpy(Hotel hotel) {
        super(hotel);
    }

    @Override
    public void updateOneHotel(Hotel hotel) {
        this.updatedHotel = hotel;
    }

    public RoomFloorDto modifiedRoomAtIndex(int index){
        return updatedHotel.toDto().get(index);
    }
}
