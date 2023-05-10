package administration.repositories;

import administration.business.entity.Hotel;

public interface HotelRepository {
    Hotel getMyHotel();

    void updateOneHotel(Hotel hotel);
}
