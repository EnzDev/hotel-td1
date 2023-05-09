package service;

import dao.RoomDao;
import model.Room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {
    private static final double MAX_ROOM_PRICE = 200;
    public static final Map<Integer, Double> FLOOR_RATES = new HashMap<Integer, Double> () {{
        put(0, 1.0);
        put(1, 1.07);
        put(2, 1.22);
        put(3, 1.33);
    }};


    private final RoomDao dao;

    public RoomServiceImpl(RoomDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Room> getAllRooms() {
        return dao.getAll();
    }

    @Override
    public void setPrice(double price) {

        List<Room> roomsWithNewPrice = getAllRooms()
                .stream()
                .map(room -> {
                    // Compute new price
                    double newPrice = getRoomPrice(price, room);

                    return new Room(room.getFloor(), room.getRoomNumber(), newPrice);
                }).collect(Collectors.toList());

        dao.setRooms(roomsWithNewPrice);
    }

    private double getRoomPrice(double basePrice, Room room) {
        Double rate = FLOOR_RATES.get(room.getFloor());
        if (rate == null) {
            throw new IllegalStateException("Room has an invalid floor");
        }

        return Math.min(basePrice * rate, MAX_ROOM_PRICE);
    }
}
