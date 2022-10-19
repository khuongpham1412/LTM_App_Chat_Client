/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.JListModel;

/**
 *
 * @author Asus
 */
public class CreateGroupModel {
    private String imageAvater;
    private String username;
    private boolean selected;
    
    public CreateGroupModel(){
    }

    public CreateGroupModel(String imageAvater, String username, boolean selected) {
        this.imageAvater = imageAvater;
        this.username = username;
        this.selected = selected;
    }

    public String getImageAvater() {
        return imageAvater;
    }

    public void setImageAvater(String imageAvater) {
        this.imageAvater = imageAvater;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
