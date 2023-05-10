package administration.business.rules.usecase.impl;

import administration.business.entity.Room;
import administration.business.entity.RoomDto;
import administration.business.rules.usecase.GetAllRooms;
import administration.business.rules.usecase.output.RoomsPresenter;
import administration.gateway.RoomDao;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllRoomsImpl implements GetAllRooms {
    RoomDao roomDao;

    public GetAllRoomsImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public void execute(RoomsPresenter presenter) {
        List<RoomDto> rooms = roomDao.getAll()
                .stream()
                .map(Room::toDto)
                .collect(Collectors.toList());
        presenter.present(rooms);
    }
}
