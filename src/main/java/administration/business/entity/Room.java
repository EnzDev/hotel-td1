package administration.business.entity;

import administration.business.entity.dto.RoomDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Room {
    private static final Map<Integer, Double> FLOOR_RATES = new HashMap<Integer, Double>() {{
        put(0, 1.0);
        put(1, 1.07);
        put(2, 1.22);
        put(3, 1.33);
    }};
    private static final double MAX_ROOM_PRICE = 200;

    int floor;
    int roomNumber;
    double price;

    public Room(int floor, int roomNumber, double price) {
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return floor == room.floor
                && roomNumber == room.roomNumber
                && Double.compare(room.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(floor, roomNumber, price);
    }

    public void setRoomPrice(double basePrice) {
        Double rate = FLOOR_RATES.get(this.floor);
        if (rate == null) {
            throw new IllegalStateException("Room has an invalid floor");
        }

        this.price = Math.min(basePrice * rate, MAX_ROOM_PRICE);
    }

    public RoomDto toDto() {
        return new RoomDto(this.floor, this.roomNumber, this.price);
    }
}
