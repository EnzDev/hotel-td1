package administration.business.entity.dto;

public class RoomDto {
    public final int roomNumber;
    public final double price;

    public RoomDto(int roomNumber, double price) {
        this.roomNumber = roomNumber;
        this.price = price;
    }
}
