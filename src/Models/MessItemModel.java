/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Asus
 */
public class MessItemModel {
    private int id;
    private String username;
    private String message;
    private String icon;
    private MenuType menuType;

    public static enum MenuType {
        TITLE, MENU, EMPTY
    }
    
    public MessItemModel(){
        
    }

    public MessItemModel(int id, String username, String message, MenuType menuType) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.menuType = menuType;
    }

    public MessItemModel(int id, String username, String message, String icon, MenuType menuType) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.icon = icon;
        this.menuType = menuType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
    }
    
    public Icon toIcon(){
        return new ImageIcon(getClass().getResource("./Icon/" + icon + ".png"));
    }
}
