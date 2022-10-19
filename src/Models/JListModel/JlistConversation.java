/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.JListModel;

import Components.Conversation;
import Enum.StatusMessage;
import Enum.TypeMessage;
import Models.Message;
import java.awt.Component;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Asus
 * @param <E>
 */
public class JlistConversation<E extends Object> extends JList<E>{
    
    private final DefaultListModel model;
    private int selectedIndex = -1;
    
    public JlistConversation(){
        model = new DefaultListModel();
        setModel(model);
        setOpaque(false);
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e){
//                if(SwingUtilities.isLeftMouseButton(e)){
//                    int index = locationToIndex(e.getPoint());
//                    Object obj = model.getElementAt(index);
//                    if(obj instanceof MessItemModel){
//                        MessItemModel messItemModel = (MessItemModel) obj;
//                        if(messItemModel.getMenuType() == MessItemModel.MenuType.MENU){
//                            System.out.println("index: "+index);
//                            MessItemModel item = (MessItemModel) model.getElementAt(index);
//                            System.out.println("ID: "+item.getId()+" USERNAME: "+item.getUsername());
//                            Constants.currentPositionMessageItemId = item.getId();
//                            //KHI CLICK VÀO CÂN GOI SU KIÊN REQUEST_GET_ALL_MESSAGE_BY_USER_ID
//                            Constants.id_received = item.getId();
//                            selectedIndex = index;
//                        }
//                    }else{
//                        selectedIndex = index;
//                    }
//
//                    repaint();
//                }
//            }
//        });
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
                    data = new Message(UUID.randomUUID().toString(),"", TypeMessage.TEXT.toString(), new Date(), StatusMessage.RECEIVED.toString(), "", "1","2");
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
    
    public void reset(){
        model.removeAllElements();
    }
    
}
