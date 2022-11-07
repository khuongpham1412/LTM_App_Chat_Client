/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Layout;

import Models.ResponseModel.GetAllMessageResponse;
import Models.ResponseModel.MessItemResponse;
import Utils.Constants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JScrollPane;

/**
 *
 * @author Asus
 */
public class PanelRight extends javax.swing.JPanel {

    private ArrayList<GetAllMessageResponse> messages = new ArrayList<>();
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
        if(Constants.currentPosition != null){
            lbName.setText(Constants.currentPosition.getUsername());
        }
    }
    
    public void updateUsername(){
        if(Constants.currentPosition != null){
            lbName.setText(Constants.currentPosition.getUsername());
        }
        updateStatusOnline();
    }
    
    public void updateAccountOnline(ArrayList<String> accOnline){
        if(Constants.accOnline != null && Constants.currentPosition != null){
            for(String acc : Constants.accOnline){
                if(Constants.currentPosition.getAccountId().equals(acc)){
                    lbStatus.setText("Online");
                    imageAvatar1.setGradientColor1(new java.awt.Color(102, 255, 102));
                    imageAvatar1.setGradientColor2(new java.awt.Color(42, 199, 80));
                    break;
                }
            }
        }
    }
    
    public void updateStatusOnline(){
        lbStatus.setText("Ofline");
        imageAvatar1.setGradientColor1(new java.awt.Color(255, 255, 255, 0));
        imageAvatar1.setGradientColor2(new java.awt.Color(255, 255, 255, 0));
        if(Constants.accOnline != null){
            for(String acc : Constants.accOnline){
            if(Constants.currentPosition.getAccountId().equals(acc)){
                lbStatus.setText("Online");
                imageAvatar1.setGradientColor1(new java.awt.Color(102, 255, 102));
                imageAvatar1.setGradientColor2(new java.awt.Color(42, 199, 80));
                break;
            }
        }
        }
    }
    
    public void setListMessage(ArrayList<GetAllMessageResponse> messages){
        this.messages = messages;
        listMess1.reset();
        if(!this.messages.isEmpty() && this.messages.get(0).getMessage().getType().equals("PRIVATE")){
            btnPermission.setText("Block");
        }else{
            btnPermission.setText("Leave Group");
        }
        
        for(GetAllMessageResponse item : this.messages){
            listMess1.addItem(item);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        
        getLastMessage();
    }
    
    public void updateStatusMessage(GetAllMessageResponse message){
        listMess1.update(message, this.messages.size()-1);
    }
    
    public void sendNewMessage(GetAllMessageResponse mess){
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listMess1 = new Models.JListModel.JlistConversation<>();
        jPanel3 = new javax.swing.JPanel();
        imageAvatar1 = new Helper.ImageAvatar();
        lbName = new javax.swing.JLabel();
        btnPermission = new javax.swing.JButton();
        lbStatus = new javax.swing.JLabel();

        setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setOpaque(false);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setOpaque(false);

        jScrollPane1.setViewportView(listMess1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setOpaque(false);

        imageAvatar1.setGradientColor1(new java.awt.Color(102, 255, 102));
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/Icon/8.png"))); // NOI18N

        lbName.setBackground(new java.awt.Color(255, 255, 255));
        lbName.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbName.setText("Username");

        btnPermission.setText("Permission");

        lbStatus.setText("Status");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPermission)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnPermission, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbStatus)))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textInput1, javax.swing.GroupLayout.DEFAULT_SIZE, 1136, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JButton btnPermission;
    private Helper.ImageAvatar imageAvatar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbStatus;
    private Models.JListModel.JlistConversation<String> listMess1;
    private Components.TextInput textInput1;
    // End of variables declaration//GEN-END:variables
}
