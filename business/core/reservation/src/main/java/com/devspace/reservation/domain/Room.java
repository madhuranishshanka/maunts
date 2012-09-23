package com.devspace.reservation.domain;

import com.devspace.persistence.domain.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class Room extends Entity {

    private String number;
    @Enumerated(EnumType.STRING)
    private RoomStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    private RoomType type;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "room")
    private Set<Reservation> activeReservations = new HashSet<Reservation>();

    public Room() {
    }

    public Room(String number, RoomStatus status, RoomType type) {
        this.number = number;
        this.status = status;
        this.type = type;
    }

    public void reserve(Reservation reservation) {
        activeReservations.add(reservation);
    }

    public Set<Reservation> getActiveReservations() {
        return activeReservations;
    }

    public String getNumber() {
        return number;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public RoomType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Room room = (Room) o;

        if (number != null ? !number.equals(room.number) : room.number != null) return false;
        if (status != room.status) return false;
        if (type != null ? !type.equals(room.type) : room.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
