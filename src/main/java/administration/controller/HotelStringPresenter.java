package administration.controller;

import administration.business.entity.dto.RoomFloorDto;
import administration.business.rules.usecase.output.HotelPresenter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.stream.Collectors;

public class HotelStringPresenter implements HotelPresenter {
    public static final DecimalFormat PRICE_FORMATTER = new DecimalFormat("#.##") {{
        DecimalFormatSymbols pointDecimalSep = DecimalFormatSymbols.getInstance();
        pointDecimalSep.setDecimalSeparator('.');

        setDecimalFormatSymbols(pointDecimalSep);
    }};
    private String buffer;

    @Override
    public void present(List<RoomFloorDto> rooms) {
        buffer = rooms.stream()
                .map(this::toRoomString)
                .collect(Collectors.joining("\n"));
    }

    private String toRoomString(RoomFloorDto room) {
        return String.format(
                "floor = %d, num = %d, price = %s",
                room.floor,
                room.roomNumber,
                PRICE_FORMATTER.format(room.price)
        );
    }

    public String read() {
        return buffer;
    }
}
