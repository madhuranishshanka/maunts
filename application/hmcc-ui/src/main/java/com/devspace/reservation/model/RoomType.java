package com.devspace.reservation.model;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 9/2/12
 * Time: 9:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoomType {

    private Long id;
    private String roomTypeName;
    private String description;
    private String roomTypeImgOne;
    private String roomTypeImgTwo;
    private String roomTypeImgThere;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getRoomTypeImgTwo() {
        return roomTypeImgTwo;
    }

    public void setRoomTypeImgTwo(String roomTypeImgTwo) {
        this.roomTypeImgTwo = roomTypeImgTwo;
    }

    public String getRoomTypeImgThere() {
        return roomTypeImgThere;
    }

    public void setRoomTypeImgThere(String roomTypeImgThere) {
        this.roomTypeImgThere = roomTypeImgThere;
    }
}
