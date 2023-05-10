package administration.business.entity;

import administration.business.entity.dto.RoomDto;

import java.util.Objects;

public class Room {
    int roomNumber;
    RoomPrice price;

    public Room(int roomNumber, RoomPrice price) {
        this.roomNumber = roomNumber;
        this.price = price;
    }

    public void setRoomPrice(double price) {
        this.price.updateRoomPrice(price);
    }

    public RoomDto toDto() {
        return new RoomDto(this.roomNumber, this.price.toDto());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber
                && price.equals(room.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, price);
    }
}
