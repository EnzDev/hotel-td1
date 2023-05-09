package model;

import java.util.Objects;

public class Room {
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

    public int getFloor() {
        return floor;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public double getPrice() {
        return price;
    }
}
