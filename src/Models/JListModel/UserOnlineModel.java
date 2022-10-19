/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.JListModel;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class UserOnlineModel implements Serializable{
    private String userId;
    private String roomId;
    private String avater;
    private String username;
    
    public UserOnlineModel(){
        
    }

    public UserOnlineModel(String userId, String roomId, String avater, String username) {
        this.userId = userId;
        this.roomId = roomId;
        this.avater = avater;
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
