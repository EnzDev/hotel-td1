package administration.controller;

import administration.business.entity.RoomDto;
import administration.business.rules.usecase.output.RoomsPresenter;

import java.util.List;
import java.util.stream.Collectors;

public class RoomsStringPresenter implements RoomsPresenter {
    private String buffer;

    @Override
    public void present(List<RoomDto> rooms) {
        buffer = rooms.stream()
                .map(this::toRoomString)
                .collect(Collectors.joining("\n"));
    }

    private String toRoomString(RoomDto room) {
        return String.format(
                "etage = %d, num = %d, prix = %f",
                room.floor,
                room.roomNumber,
                room.price
        );
    }

    public String read() {
        return buffer;
    }
}
