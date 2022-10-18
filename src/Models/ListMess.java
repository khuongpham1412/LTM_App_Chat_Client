/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Components.Conversation;
import Components.MessItem;
import Components.MessItem1;
import Enum.StatusMessage;
import Enum.TypeMessage;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.UUID;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Asus
 */
public class ListMess<E extends Object> extends JList<E>{
    
    private final DefaultListModel model;
    private int selectedIndex = 0;
    
    public ListMess(){
        model = new DefaultListModel();
        
        setModel(model);
        setOpaque(false);
        
        
//        int size = list.getModel().getSize();
        
//        if (lastIndex >= 0) {
//           wordList.ensureIndexIsVisible(lastIndex);
//        }
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e){
//                if(SwingUtilities.isLeftMouseButton(e)){
//                    System.out.println(getSelectedIndex());
//                    int index = 0;////                    Object obj = model.getElementAt(index);
////                    if(obj instanceof MessItemModel){
////                        MessItemModel messItemModel = (MessItemModel) obj;

////                            locationToIndex(e.getPoint());
////                    Object obj = model.getElementAt(index);
////                    if(obj instanceof MessItemModel){
////                        MessItemModel messItemModel = (MessItemModel) obj;
////                        if(messItemModel.getMenuType() == MessItemModel.MenuType.MENU){
////                            System.out.println("index: "+index);
////                            selectedIndex = index;
////                        }
////                    }else{
////                        selectedIndex = index;
////                    }
//
//                    repaint();
//                }
//            }
//        });

//        addListSelectionListener(new ListSelectionListener() {
//                    @Override
//                    public void valueChanged(ListSelectionEvent e) {
//                        int index = getSelectedIndex();
//                        Rectangle bounds = getCellBounds(index, index);
//                        Point p = bounds.getLocation();
//                        p.y += bounds.height;
////                        SwingUtilities.convertPointToScreen(p, );
////                        p.x -= selected.getWidth();
////                        selected.setLocation(p);
////                        selected.setVisible(true);
//                    }
//                });
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
