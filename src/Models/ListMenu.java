/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Components.MessItem;
import Utils.Constants;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

/**
 *
 * @author Asus
 */
public class ListMenu<E extends Object> extends JList<E>{
    
    private final DefaultListModel model;
    private int selectedIndex = -1;
    
    public ListMenu(){
        model = new DefaultListModel();
        setModel(model);
        setOpaque(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                if(SwingUtilities.isLeftMouseButton(e)){
                    int index = locationToIndex(e.getPoint());
                    Object obj = model.getElementAt(index);
                    if(obj instanceof MessItemModel){
                        MessItemModel messItemModel = (MessItemModel) obj;
                        if(messItemModel.getMenuType() == MessItemModel.MenuType.MENU){
                            System.out.println("index: "+index);
                            MessItemModel item = (MessItemModel) model.getElementAt(index);
                            System.out.println("ID: "+item.getId()+" USERNAME: "+item.getUsername());
                            //KHI CLICK VÀO CÂN GOI SU KIÊN REQUEST_GET_ALL_MESSAGE_BY_USER_ID
                            Constants.id_received = item.getId();
                            selectedIndex = index;
                        }
                    }else{
                        selectedIndex = index;
                    }

                    repaint();
                }
            }
        });
        
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                MessItemModel data;
                if(value instanceof MessItemModel){
                    data = (MessItemModel)value;
                }else{
                    data = new MessItemModel(0,"",value + "",MessItemModel.MenuType.EMPTY);
                }
//                if(isSelected){
//                    System.out.println("INDEX: "+ index);
//                }

                MessItem item = new MessItem(data);
                item.setSelected(selectedIndex == index);
                return item;
            }
            
        };
    }
    
    public void addItem(MessItemModel data){
        model.addElement(data);
    }
    
}
