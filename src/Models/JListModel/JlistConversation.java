/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.JListModel;

import Components.Conversation;
import Models.ResponseModel.GetAllMessageResponse;
import java.awt.Component;
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
    Conversation item;
    
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
                GetAllMessageResponse data;
                if(value instanceof GetAllMessageResponse){
                    data = (GetAllMessageResponse)value;
                }else{
                    data = new GetAllMessageResponse();
                }
                if(isSelected){
                    System.out.println(index);
                }
                item = new Conversation(data);
                item.hideNotice();
//                item.setSelected(selectedIndex == index);
                return item;
            }
            
        };
    }
    
    public void hideNotice(){
        item.hideNotice();
    }
    
    public void openNotice(){
        item.openNotice();
    }
    
    public void addItem(GetAllMessageResponse data){
        model.addElement(data);
    }
    
    public void reset(){
        model.removeAllElements();
    }
    
    public void update(GetAllMessageResponse message, int index){
        model.setElementAt(message, index);
    }
    
}
