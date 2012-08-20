package com.devspace.reservation.domain;

import com.devspace.persistence.domain.Entity;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class RoomType extends Entity {

    private String roomTypeName;
    private String description;
    private String roomTypeImage1;
    private String roomTypeImage2;
    private String roomTypeImage3;
    private String roomTypeImage4;


    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoomTypeImage1() {
        return roomTypeImage1;
    }

    public void setRoomTypeImage1(String roomTypeImage1) {
        this.roomTypeImage1 = roomTypeImage1;
    }

    public String getRoomTypeImage2() {
        return roomTypeImage2;
    }

    public void setRoomTypeImage2(String roomTypeImage2) {
        this.roomTypeImage2 = roomTypeImage2;
    }

    public String getRoomTypeImage3() {
        return roomTypeImage3;
    }

    public void setRoomTypeImage3(String roomTypeImage3) {
        this.roomTypeImage3 = roomTypeImage3;
    }

    public String getRoomTypeImage4() {
        return roomTypeImage4;
    }

    public void setRoomTypeImage4(String roomTypeImage4) {
        this.roomTypeImage4 = roomTypeImage4;
    }


}
