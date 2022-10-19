/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.JListModel;

import Components.ComponentLeftCreateGroup;
import Models.MessItemModel;
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
public class JlistCreateGroupLeft <E extends Object> extends JList<E>{
    private final DefaultListModel model;
    private int selectedIndex = -1;
    
    public JlistCreateGroupLeft(){
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
                        CreateGroupModel createGroupLeftModel = (CreateGroupModel) obj;
                        CreateGroupModel item = (CreateGroupModel) model.getElementAt(index);
                        item.setSelected(!item.isSelected());
                        selectedIndex = index;
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
                CreateGroupModel data;
                if(value instanceof CreateGroupModel){
                    data = (CreateGroupModel)value;
                }else{
                    data = new CreateGroupModel("","Khuong", false);
                }
                
                ComponentLeftCreateGroup item = new ComponentLeftCreateGroup(data);
                item.setSelected(selectedIndex == index);
                if(isSelected){
                    
                }
                
                return item;
            }
            
        };
    }
    
    public void addItem(CreateGroupModel data){
        model.addElement(data);
    }
    
    public CreateGroupModel getCreateGroupLeftModel(){
        if(selectedIndex != -1){
            CreateGroupModel data = (CreateGroupModel) model.getElementAt(selectedIndex);
            selectedIndex = -1;
            return data;
        }
        return null;
    }
    
    public void resetList(){
        model.removeAllElements();
    }
    
}
