/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.JListModel;

import Components.ComponentUserOnline;
import Models.ResponseModel.MessItemResponse;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Asus
 */
public class JlistUserOnline<E extends Object> extends JList<E>{
    private final DefaultListModel model;
    private int selectedIndex = -1;
    
    public JlistUserOnline(){
        model = new DefaultListModel();
        setModel(model);
        setOpaque(false);
        this.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e){
//                if(SwingUtilities.isLeftMouseButton(e)){
//                    int index = locationToIndex(e.getPoint());
//                    Object obj = model.getElementAt(index);
//                    if(obj instanceof MessItemModel){
//                        CreateGroupModel createGroupLeftModel = (CreateGroupModel) obj;
//                        CreateGroupModel item = (CreateGroupModel) model.getElementAt(index);
//                        item.setSelected(!item.isSelected());
//                        selectedIndex = index;
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
                MessItemResponse data;
                if(value instanceof MessItemResponse){
                    data = (MessItemResponse)value;
                }else{
                    data = new MessItemResponse();
                }
                if(isSelected){
                    System.out.println(index);
                }
                ComponentUserOnline item = new ComponentUserOnline(data);
//                item.setSelected(selectedIndex == index);
                return item;
            }
            
        };
    }
    
    public void addItem(MessItemResponse data){
        model.addElement(data);
    }
    
    public void resetList(){
        model.removeAllElements();
    }
}
