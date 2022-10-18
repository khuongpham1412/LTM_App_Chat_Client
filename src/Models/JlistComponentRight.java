/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Components.Conversation;
import Enum.StatusMessage;
import Enum.TypeMessage;
import java.awt.Component;
import java.util.Date;
import java.util.UUID;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Asus
 */
public class JlistComponentRight <E extends Object> extends JList<E>{
    private final DefaultListModel model;
    private int selectedIndex = 0;
    
    public JlistComponentRight(){
        model = new DefaultListModel();
        setModel(model);
        setOpaque(false);
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Message data;
                if(value instanceof Message){
                    data = (Message)value;
                }else{
                    data = new Message(UUID.randomUUID(),"", TypeMessage.TEXT, new Date(), StatusMessage.RECEIVED, "", 1,2);
                }
                if(isSelected){
                    System.out.println(index);
                }
                Conversation item = new Conversation(data);
//                item.setSelected(selectedIndex == index);
                return item;
            }
            
        };
    }
    
    public void addItem(Message data){
        model.addElement(data);
    }
    
    
}
