package com.devspace.reservation.domain;

import com.devspace.persistence.domain.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class Room extends Entity {

    private String roomNumber;

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;
    private RoomType roomType;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        if (!super.equals(o)) return false;

        Room room = (Room) o;

        if (roomNumber != null ? !roomNumber.equals(room.roomNumber) : room.roomNumber != null) return false;
        if (roomStatus != room.roomStatus) return false;
        if (roomType != null ? !roomType.equals(room.roomType) : room.roomType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (roomNumber != null ? roomNumber.hashCode() : 0);
        result = 31 * result + (roomStatus != null ? roomStatus.hashCode() : 0);
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        return result;
    }
}
