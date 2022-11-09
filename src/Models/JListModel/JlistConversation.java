/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.JListModel;

import Components.Conversation;
import Enum.TypeMessage;
import Models.ResponseModel.GetAllMessageResponse;
import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
                    if(data.getMessage().getType().equals(TypeMessage.FILE.toString())){
                        int option = JOptionPane.showConfirmDialog(null, "Do you want download...");
                        if(option == JOptionPane.YES_OPTION){
                            try {
                                    FileInputStream in = new FileInputStream("D:\\SGU\\JavaSwing\\LTM-AppChatServer\\src\\Assets\\Files\\" + data.getMessage().getMessage());
                                    byte[] fileContentBytes = in.readAllBytes();
                                    FileOutputStream out = new FileOutputStream("C:\\Users\\Asus\\Downloads\\" + data.getMessage().getMessage());
                                    out.write(fileContentBytes);
                                    in.close();
                                    out.close();
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(JlistConversation.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(JlistConversation.class.getName()).log(Level.SEVERE, null, ex);
                                }
                        }
//                        switch (option) {
//                            case 0 -> {
//                                try {
//                                    FileInputStream in = new FileInputStream("D:\\SGU\\JavaSwing\\LTM-AppChatServer\\src\\Assets\\Files\\" + data.getMessage().getMessage());
//                                    byte[] fileContentBytes = in.readAllBytes();
//                                    FileOutputStream out = new FileOutputStream("C:\\Users\\Asus\\Downloads\\" + data.getMessage().getMessage());
//                                    out.write(fileContentBytes);
//                                } catch (FileNotFoundException ex) {
//                                    Logger.getLogger(JlistConversation.class.getName()).log(Level.SEVERE, null, ex);
//                                } catch (IOException ex) {
//                                    Logger.getLogger(JlistConversation.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            }
//                            case 1 -> {
//                            }
//                            case 2 -> {
//                            }
//                            default -> {
//                            }
//                        }
                    }
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
