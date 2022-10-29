/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.JListModel;

import Models.Account;

/**
 *
 * @author Asus
 */
public class CreateGroupModel {
    private Account account;
    private boolean selected;
    
    
    public CreateGroupModel(){
    }

    public CreateGroupModel(Account account, boolean selected) {
        this.account = account;
        this.selected = selected;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    
}
