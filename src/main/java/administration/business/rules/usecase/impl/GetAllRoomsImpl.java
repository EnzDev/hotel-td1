package administration.business.rules.usecase.impl;

import administration.business.entity.Room;
import administration.business.entity.dto.RoomDto;
import administration.business.rules.usecase.GetAllRooms;
import administration.business.rules.usecase.output.RoomsPresenter;
import administration.repositories.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllRoomsImpl implements GetAllRooms {
    RoomRepository roomRepository;

    public GetAllRoomsImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void execute(RoomsPresenter presenter) {
        List<RoomDto> rooms = roomRepository.getAll()
                .stream()
                .map(Room::toDto)
                .collect(Collectors.toList());
        presenter.present(rooms);
    }
}
