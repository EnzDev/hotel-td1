package administration.business.rules.usecase.impl;

import administration.business.entity.Hotel;
import administration.business.rules.usecase.UpdateRoomsPrice;
import administration.repositories.HotelRepository;

public class UpdateRoomsPriceImpl implements UpdateRoomsPrice {
    HotelRepository hotelRepository;

    public UpdateRoomsPriceImpl(HotelRepository roomRepository) {
        this.hotelRepository = roomRepository;
    }

    @Override
    public void execute(double groundFloorPrice) {
        Hotel hotel = this.hotelRepository.getMyHotel();
        hotel.setHotelGroundFloorPrice(groundFloorPrice);
        this.hotelRepository.updateOneHotel(hotel);
    }
}
