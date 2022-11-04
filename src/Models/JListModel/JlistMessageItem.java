/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.JListModel;

import Common.Common;
import Common.EventJlistSelected;
import Components.MessageItem;
import Components.TextInput;
import Enum.Status;
import Models.RequestModel.DataRequest;
import Models.RequestModel.GetAllMessageRequest;
import Models.ResponseModel.MessItemResponse;
import Utils.Constants;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

/**
 *
 * @author Asus
 */
public class JlistMessageItem<E extends Object> extends JList<E>{
    
    private final DefaultListModel model;
    private int selectedIndex = -1;
    private EventJlistSelected event;
    
    public void addEventJListSelected(EventJlistSelected event){
        this.event = event;
    }
    
    
    public JlistMessageItem(){
        model = new DefaultListModel();
        setModel(model);
        setOpaque(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                if(SwingUtilities.isLeftMouseButton(e)){
                    int index = locationToIndex(e.getPoint());
                    Object obj = model.getElementAt(index);
                    if(obj instanceof MessItemResponse){
                        MessItemResponse messItemModel = (MessItemResponse) obj;
                        if(event != null){
                            MessItemResponse item = (MessItemResponse) model.getElementAt(index);
                            Constants.currentPosition = item;
                            Constants.currentPositionIndex = index;
                            //KHI CLICK VÀO CÂN GOI SU KIÊN REQUEST_GET_ALL_MESSAGE_BY_USER_ID
                            try {
                                DataRequest request = new DataRequest();
                                request.setName("GET_ALL_MESSAGE_BY_ROOM_ID_REQUEST");
                                GetAllMessageRequest data = new GetAllMessageRequest(item.getRoomId(), Constants.infomation.getId(), item.getAccountId(), item.getType());
                                request.setRequest(data);
                                request.setStatus(Status.SUCCESS);

                                Common.write(request);
                            } catch (IOException ex) {
                                Logger.getLogger(TextInput.class.getName()).log(Level.SEVERE, null, ex);
                            }       
                            selectedIndex = index;
                            event.selected(index);
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
                MessItemResponse data;
                if(value instanceof MessItemResponse){
                    data = (MessItemResponse)value;
                }else{
                    data = new MessItemResponse();
                }
                

                MessageItem item = new MessageItem(data);
                if(isSelected){
                    item.setSelected(selectedIndex == index);
                }
                
                //User id received
//                if(Constants.currentPosition != null && !Constants.currentPosition.getRoomId().equals(data.getRoomId()) && Constants.infomation.getId().equals(data.getAccountId()) && index == 0 && !isSelected){
//                    item.setSelected(true);
//                }
                //User id send
                if(Constants.currentPosition != null && Constants.currentPosition.getRoomId().equals(data.getRoomId()) && !isSelected){
                    Constants.currentPosition = data;
                    Constants.currentPositionIndex = 0;
                    item.setSelected(true);
                }
                return item;
            }
            
        };
    }
    
    public void addItem(MessItemResponse data){
        model.addElement(data);
    }
    
    public void reset(){
        model.removeAllElements();
    }
    
}
