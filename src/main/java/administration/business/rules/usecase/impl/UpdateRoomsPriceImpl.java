package administration.business.rules.usecase.impl;

import administration.business.entity.Room;
import administration.business.rules.usecase.UpdateRoomsPrice;
import administration.repositories.RoomRepository;

import java.util.List;

public class UpdateRoomsPriceImpl implements UpdateRoomsPrice {
    RoomRepository roomRepository;

    public UpdateRoomsPriceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void execute(double groundFloorPrice) {
        List<Room> allRooms = this.roomRepository.getAll();
        allRooms.forEach(room -> room.setRoomPrice(groundFloorPrice));
        this.roomRepository.setRooms(allRooms);
    }
}
