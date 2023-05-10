package administration.entities;

import administration.business.entity.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RoomTest {

    @Test
    void should_rooms_with_same_info_equals(){
        // Given
        Room a = new Room(1, 101, 50);
        Room b = new Room(1, 101, 50);

        // Then
        assertEquals(a, b);
    }

    @Test
    void should_rooms_with_same_info_same_hashcode(){
        // Given
        Room a = new Room(1, 101, 50);
        Room b = new Room(1, 101, 50);

        // Then
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void should_rooms_with_different_floor_different_hashcode_and_not_equals(){
        // Given
        Room a = new Room(1, 101, 50);
        Room b = new Room(2, 101, 50);

        // Then
        assertNotEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, b);
    }
    @Test
    void should_rooms_with_different_room_different_hashcode_and_not_equals(){
        // Given
        Room a = new Room(1, 101, 50);
        Room b = new Room(1, 102, 50);

        // Then
        assertNotEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, b);
    }

    @Test
    void should_rooms_with_different_price_different_hashcode_and_not_equals(){
        // Given
        Room a = new Room(1, 101, 50);
        Room b = new Room(1, 101, 150);

        // Then
        assertNotEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, b);
    }
}
