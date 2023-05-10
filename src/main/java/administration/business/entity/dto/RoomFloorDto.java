package administration.business.entity.dto;

public class RoomFloorDto {
    public final int roomNumber;
    public final int floor;
    public final double price;

    public RoomFloorDto(int roomNumber, int floor, double price) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.price = price;
    }
}
