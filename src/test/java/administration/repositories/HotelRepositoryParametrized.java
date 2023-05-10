package administration.repositories;

import administration.business.entity.Hotel;

public class HotelRepositoryParametrized implements HotelRepository {
    private final Hotel hotel;

    public HotelRepositoryParametrized(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public Hotel getMyHotel() {
        return this.hotel;
    }

    @Override
    public void updateOneHotel(Hotel hotel) { }
}
