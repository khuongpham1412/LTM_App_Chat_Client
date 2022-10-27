/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Layout;

import Models.Message;
import Models.ResponseModel.MessItemResponse;
import Utils.Constants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Asus
 */
public class PanelRight extends javax.swing.JPanel {

    private ArrayList<Message> messages = new ArrayList<>();
    /**
     * Creates new form PanelRight
     */
    public PanelRight() {
        initComponents();
        setOpaque(false);
        listMess1.setOpaque(false);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.getVerticalScrollBar().setBackground(new Color(98, 132, 255));
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(5, 0));
        
        jlistUserOnline2.setOpaque(false);
        jlistUserOnline2.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        jlistUserOnline2.setVisibleRowCount(10);
        jScrollPane3.setOpaque(false);
        jScrollPane3.getViewport().setOpaque(false);
        jScrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane3.getHorizontalScrollBar().setBackground(new Color(98, 132, 255));
        jScrollPane3.getHorizontalScrollBar().setPreferredSize(new Dimension(5, 0));
        jScrollPane3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

//        init();
    }
    
    public void setListMessage(ArrayList<Message> messages){
        this.messages = messages;
        listMess1.reset();
        for(Message item : this.messages){
            listMess1.addItem(item);
        }
        for(MessItemResponse item : Constants.messItems){
            jlistUserOnline2.addItem(item);
        }
        
        getLastMessage();
    }
    
    public void updateStatusMessage(Message message){
        listMess1.update(message, this.messages.size()-1);
    }
    
//    private void init(){
//        for(Message item : messages){
//            listMess1.addItem(item);
//        }
//        
//        getLastMessage();
//        
//        JScrollBar vertical = jScrollPane1.getVerticalScrollBar();
//        vertical.setValue(9);
//        Point p = jScrollPane1.getViewport().getLocation();
//        jScrollPane1.getViewport().setViewPosition(p);
//    }
    
    public void sendNewMessage(Message mess){
        this.messages.add(mess);
        listMess1.addItem(mess);
        getLastMessage();
    }
    
    private void getLastMessage(){
        int lastIndex = listMess1.getModel().getSize() - 1;
        listMess1.ensureIndexIsVisible(lastIndex);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textInput1 = new Components.TextInput();
        jScrollPane1 = new javax.swing.JScrollPane();
        listMess1 = new Models.JListModel.JlistConversation<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlistUserOnline2 = new Models.JListModel.JlistUserOnline<>();

        setOpaque(false);

        jScrollPane1.setViewportView(listMess1);

        jPanel1.setOpaque(false);

        jScrollPane3.setOpaque(false);

        jScrollPane3.setViewportView(jlistUserOnline2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textInput1, javax.swing.GroupLayout.DEFAULT_SIZE, 1236, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textInput1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        GradientPaint gradient = new GradientPaint(0, 0, Color.decode("#f2fcfe"), 0, getHeight(), Color.decode("#b2fefa"));
        g2.setPaint(gradient);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(g); 
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private Models.JListModel.JlistUserOnline<String> jlistUserOnline2;
    private Models.JListModel.JlistConversation<String> listMess1;
    private Components.TextInput textInput1;
    // End of variables declaration//GEN-END:variables
}
