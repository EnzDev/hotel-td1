package administration.business.rules.usecase.impl;

import administration.business.entity.dto.RoomFloorDto;
import administration.business.rules.usecase.GetAllRooms;
import administration.business.rules.usecase.output.HotelPresenter;
import administration.repositories.HotelRepository;

import java.util.List;

public class GetAllRoomsImpl implements GetAllRooms {
    HotelRepository hotelRepository;

    public GetAllRoomsImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void execute(HotelPresenter presenter) {
        List<RoomFloorDto> roomsDto = hotelRepository.getMyHotel()
                .toDto();
        presenter.present(roomsDto);
    }
}
