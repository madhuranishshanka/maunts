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
    private String roomTypeImgOne;
    private String roomTypeImgTwo;
    private String roomTypeImgThere;


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

    public String getRoomTypeImgOne() {
        return roomTypeImgOne;
    }

    public void setRoomTypeImgOne(String roomTypeImgOne) {
        this.roomTypeImgOne = roomTypeImgOne;
    }

    public String getRoomTypeImgThere() {
        return roomTypeImgThere;
    }

    public void setRoomTypeImgThere(String roomTypeImgThere) {
        this.roomTypeImgThere = roomTypeImgThere;
    }

    public String getRoomTypeImgTwo() {
        return roomTypeImgTwo;
    }

    public void setRoomTypeImgTwo(String roomTypeImgTwo) {
        this.roomTypeImgTwo = roomTypeImgTwo;
    }
}
