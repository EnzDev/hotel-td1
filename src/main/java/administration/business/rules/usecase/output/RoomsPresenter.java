package administration.business.rules.usecase.output;

import administration.business.entity.RoomDto;

import java.util.List;

public interface RoomsPresenter {
    void present(List<RoomDto> rooms);
}
