package service;


import administration.business.entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
    void setPrice(double groundFloorPrice);
}