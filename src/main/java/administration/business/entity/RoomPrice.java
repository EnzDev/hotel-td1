package administration.business.entity;

import java.util.Objects;

public class RoomPrice {
    private static final double MAX_ROOM_PRICE = 200;

    double price;


    public RoomPrice(double price) {
        this.price = price;
    }

    public void updateRoomPrice(double price) {
        this.price = Math.min(price, MAX_ROOM_PRICE);
    }

    public double toDto() {
        return this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomPrice room = (RoomPrice) o;
        return Double.compare(room.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
