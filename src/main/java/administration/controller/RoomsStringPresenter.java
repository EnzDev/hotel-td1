package administration.controller;

import administration.business.entity.dto.RoomDto;
import administration.business.rules.usecase.output.RoomsPresenter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.stream.Collectors;

public class RoomsStringPresenter implements RoomsPresenter {
    public static final DecimalFormat PRICE_FORMATTER = new DecimalFormat("#.##") {{
        DecimalFormatSymbols pointDecimalSep = DecimalFormatSymbols.getInstance();
        pointDecimalSep.setDecimalSeparator('.');

        setDecimalFormatSymbols(pointDecimalSep);
    }};
    private String buffer;

    @Override
    public void present(List<RoomDto> rooms) {
        buffer = rooms.stream()
                .map(this::toRoomString)
                .collect(Collectors.joining("\n"));
    }

    private String toRoomString(RoomDto room) {
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
