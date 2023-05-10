package administration.business.rules.usecase.output;

import administration.business.entity.dto.RoomFloorDto;

import java.util.List;

public interface HotelPresenter {
    void present(List<RoomFloorDto> hotel);
}
