package administration.business.rules;

import java.util.HashMap;
import java.util.Map;

public class RoomPriceDependingOnFloor {
    private static final Map<Integer, Double> FLOOR_RATES = new HashMap<Integer, Double>() {{
        put(0, 1.0);
        put(1, 1.07);
        put(2, 1.22);
        put(3, 1.33);
    }};
    private static final double MAX_ROOM_PRICE = 200;

    public static double getRoomPrice(double basePrice, int floor) {
        Double rate = FLOOR_RATES.get(floor);
        if (rate == null) {
            throw new IllegalStateException("Room has an invalid floor");
        }

        return Math.min(basePrice * rate, MAX_ROOM_PRICE);
    }
}
