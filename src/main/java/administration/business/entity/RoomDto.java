package administration.business.entity;

public class RoomDto {
    public final int floor;
    public final int roomNumber;
    public final double price;

    public RoomDto(int floor, int roomNumber, double price) {
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.price = price;
    }
}
