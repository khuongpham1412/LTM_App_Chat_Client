/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Enum.Status;
import Enum.StatusMessage;
import Enum.TypeMessage;
import Models.Account;
import Models.RequestModel.DataRequest;
import Models.ResponseModel.DataResponse;
import Models.Message;
import Models.RequestModel.UpdateStatusMessageRequest;
import Models.ResponseModel.GetAllMessageResponse;
import Models.ResponseModel.MessItemResponse;
import Utils.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class ClientThread implements Runnable{
    App app;
    Login loginForm;
    
    @Override
    public void run() {
        listen();
    }
    
//    public void showFormLogin(Login login){
//        this.loginForm = login;
//        this.loginForm.setVisible(true);
//    }
    
    public void write(Object obj) throws IOException{
        Constants.oos.writeObject(obj);
        Constants.oos.flush();
    }
    
    public void setLoginForm(Login login){
        this.loginForm = login;
    }

    private void listen() {
        System.out.println("START LISTENNING...");
        DataResponse response = new DataResponse();
        try {
            while(true){
                response = (DataResponse) Constants.ois.readObject();
                switch(response.getName()){
                    case "LOGIN_RESPONSE" ->  {
                        if(response.getStatus().equals(Status.SUCCESS)){
                            Account account = (Account) response.getData();
                            Constants.infomation = account;
                            DataRequest request = new DataRequest();
                            request.setName("GET_ALL_ACCOUNTS_MESSAGGETED_REQUEST");
                            request.setRequest(Constants.infomation.getId());
                            request.setStatus(Status.SUCCESS);

                            write(request);
                            loginForm.setVisible(false);
                        }else if(response.getStatus().equals(Status.WARNING)){
                            JOptionPane.showMessageDialog(null, response.getData().toString());
                            return;
                        }else if(response.getStatus().equals(Status.ERROR)){
                            JOptionPane.showMessageDialog(null, response.getData().toString());
                            return;
                        }
                    }
                    case "GET_ALL_ACCOUNTS_MESSAGGETED_RESPONSE" -> {
                        System.out.println("hi 2");
                        if(response.getStatus().equals(Status.SUCCESS)){
                            ArrayList<MessItemResponse> messItems = (ArrayList<MessItemResponse>) response.getData();
                            Constants.messItems = messItems;
                            DataRequest request = new DataRequest();
                            request.setName("GET_ALL_ACCOUNTS_REQUEST");
                            request.setStatus(Status.SUCCESS);

                            write(request);
                        }
                    }
                    case "GET_ALL_ACCOUNTS_RESPONSE" ->  {
                        if(response.getStatus().equals(Status.SUCCESS)){
                            ArrayList<Account> accounts = (ArrayList<Account>) response.getData();
                            Constants.accounts = accounts;
                            app = new App();
                            app.setVisible(true);
                            
                            DataRequest request = new DataRequest();
                            request.setName("GET_ALL_ACCOUNTS_ONLINE_REQUEST");
                            request.setStatus(Status.SUCCESS);
                            write(request);
                            
                        }
                    }
                    case "GET_ALL_ACCOUNTS_ONLINE_RESPONSE" ->  {
                        System.out.println("hi 1");
                        ArrayList<String> accOnline = (ArrayList<String>) response.getData();
                        Constants.accOnline = accOnline;
                        app.updateAccountOnlineLeft(Constants.accOnline);
                        app.updateAccountOnlineRight(Constants.accOnline);
                    }
                    case "CREATE_GROUP_CHAT_RESPONSE" -> {
                        MessItemResponse mess = (MessItemResponse) response.getData();
                        Constants.messItems.add(mess);
                        app.resetPanelLeft();
                        System.out.println("CREATE GROUP SUCCESS!");
                    }
                    case "LEAVE_GROUP_RESPONSE" -> {
                        MessItemResponse data = (MessItemResponse) response.getData();
                        for(MessItemResponse i : Constants.messItems){
                            if(data.getRoomId().equals(i.getRoomId())){
                                Constants.messItems.remove(i);
                                break;
                            }
                        }
                        app.resetPanelLeft();
                        app.setPanelContent();
                        Constants.currentPosition = null;
                        Constants.currentPositionIndex = -1;
                    }
                    case "UPDATE_ACCOUNT_ONLINE_RESPONSE" -> {
                        String userId = (String) response.getData();
                        Constants.accOnline.add(userId);
                        DataRequest request = new DataRequest();
                        request.setName("RESET_PANEL_LEFT_REQUEST");
                        request.setStatus(Status.SUCCESS);
                        write(request);
                    }
                    case "GET_ALL_MESSAGE_BY_ROOM_ID_RESPONSE" -> {
                        ArrayList<GetAllMessageResponse> messages = (ArrayList<GetAllMessageResponse>) response.getData();
//                        if(Constants.currentPosition != null && !messages.isEmpty() && messages.get(0).getMessage().getIdRoom().equals(Constants.currentPosition.getRoomId()) ){
//                            app.setListMessage(messages);
//                        }else if(messages.isEmpty()){
//                            app.setListMessage(messages);
//                        }
                        if(messages != null){
                            app.setListMessage(messages);
                        }
                    }
                    case "SEND_MESSAGE_PRIVATE_RESPONSE" ->  {
                        if(response.getStatus().equals(Status.SUCCESS)){
                            GetAllMessageResponse mess = (GetAllMessageResponse) response.getData();
//                            MessItemResponse messs = new MessItemResponse();
//                            messs.setNewMessage(mess.getMessage().getMessage());
//                            messs.setRoomId(mess.getMessage().getIdRoom());
//                            messs.setStatus(mess.getMessage().getStatus());
//                            messs.setType(mess.getRoomType());
                            
                            //Neu user dang focus vao ai do
                            if(Constants.currentPosition != null){
                                //Cap nhat phia user gui
                                if(mess.getMessage().getIdRoom().equals(Constants.currentPosition.getRoomId())&& mess.getMessage().getUser_send().equals(Constants.infomation.getId())){
//                                    messs.setAccountId(mess.getMessage().getUser_receive());
//                                    messs.setUsername(Constants.currentPosition.getUsername());
//                                    app.updatePositionItemNewMessage(messs, "USER_SEND");
                                    app.sendNewMessage(mess);
                                }
                                //Neu user ben nhan dang focus vao cuoc tro chuyen do
                                if(mess.getMessage().getIdRoom().equals(Constants.currentPosition.getRoomId()) && !mess.getMessage().getUser_send().equals(Constants.infomation.getId())){
//                                    for(MessItemResponse item : Constants.messItems){
//                                        if(item.getRoomId().equals(mess.getMessage().getIdRoom())){
//                                            messs.setAccountId(mess.getMessage().getUser_send());
//                                            messs.setUsername(item.getUsername());
//                                            break;
//                                        }
//                                    }
//                                    app.updatePositionItemNewMessage(messs, "USER_RECEIVED_FOCUS");
                                    app.sendNewMessage(mess);
                                }
                                //Neu user ben nhan khong focus vao cuoc tro chuyen do
                                else if(!mess.getMessage().getIdRoom().equals(Constants.currentPosition.getRoomId()) && !mess.getMessage().getUser_send().equals(Constants.infomation.getId())){
//                                    for(MessItemResponse item : Constants.messItems){
//                                       if(item.getRoomId().equals(mess.getMessage().getIdRoom())){
//                                           messs.setAccountId(mess.getMessage().getUser_send());
//                                           messs.setUsername(item.getUsername());
//                                            break;
//                                        }
//                                    }
//                                    app.updatePositionItemNewMessage(messs, "TEST1");
                                }
                                String name = "";
                                for(Account acc : Constants.accounts){
                                    if(mess.getMessage().getUser_receive().equals(acc.getId())){
                                        name = acc.getUsername();
                                        break;
                                    }
                                }
                                if(Constants.currentPosition.getRoomId().equals("") && Constants.infomation.getId().equals(mess.getMessage().getUser_send())){
                                    Constants.currentPosition.setRoomId(mess.getMessage().getIdRoom());
                                    app.sendNewMessage(mess);
                                    Constants.messItems.add(new MessItemResponse(mess.getMessage().getUser_receive(), mess.getMessage().getIdRoom(), name, mess.getMessage().getMessage(), mess.getMessage().getStatus(), mess.getRoomType()));
                                }
                            }
                            
                            String name = "";
                            for(Account acc : Constants.accounts){
                                if(mess.getMessage().getUser_send().equals(acc.getId())){
                                    name = acc.getUsername();
                                    break;
                                }
                            }
                            //Neu user ben nhan chua focus vao ai hoac dang focus vao 1 nguoi khac
                            if(Constants.currentPosition == null || (Constants.currentPosition != null && !Constants.currentPosition.getRoomId().equals("") && Constants.infomation.getId().equals(mess.getMessage().getUser_receive()))){
                                boolean check = true;
                                for(MessItemResponse i: Constants.messItems){
                                    if(mess.getMessage().getIdRoom().equals(i.getRoomId())){
                                        check = false;
                                        break;
                                    }
                                }
                                if(check){
                                    Constants.messItems.add(new MessItemResponse(mess.getMessage().getUser_send(), mess.getMessage().getIdRoom(), name, mess.getMessage().getMessage(), mess.getMessage().getStatus(), mess.getRoomType()));
                                    app.resetPanelLeft();
                                }
                            }
                            //Neu user ben nhan dang focus vao
                            else if(Constants.currentPosition != null && Constants.currentPosition.getRoomId().equals("") && Constants.currentPosition.getAccountId().equals(mess.getMessage().getUser_send())){
                                Constants.messItems.add(new MessItemResponse(mess.getMessage().getUser_send(), mess.getMessage().getIdRoom(), name, mess.getMessage().getMessage(), mess.getMessage().getStatus(), mess.getRoomType()));
                                app.sendNewMessage(mess);
                                Constants.currentPosition.setRoomId(mess.getMessage().getIdRoom());
//                                app.resetPanelLeft();
                            }
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            for(String item : Constants.accOnline){
                                if(mess.getMessage().getUser_receive().equals(item)){
                                    DataRequest request = new DataRequest();
                                    request.setName("UPDATE_STATUS_MESSAGE_REQUEST");
                                    mess.getMessage().setStatus(StatusMessage.RECEIVED.toString());
                                    request.setRequest(mess);
                                    request.setStatus(Status.SUCCESS);

                                    write(request);
                                }
                            }
                                    
                        }
                    }
                    case "SEND_MESSAGE_GROUP_RESPONSE" ->  {
                        if(response.getStatus().equals(Status.SUCCESS)){
                            GetAllMessageResponse mess = (GetAllMessageResponse) response.getData();
                            if(Constants.currentPosition != null){
                                if(mess.getMessage().getIdRoom().equals(Constants.currentPosition.getRoomId()) && !Constants.infomation.getId().equals(mess.getMessage().getUser_send())){
                                    app.sendNewMessage(mess);
                                }                              
                                else{}                           
                            }
                            if(Constants.infomation.getId().equals(mess.getMessage().getUser_send())){
                                app.sendNewMessage(mess);
                            }
                        }
                    }
                    case "UPDATE_STATUS_MESSAGE_RESPONSE" -> {
                        GetAllMessageResponse mess = (GetAllMessageResponse) response.getData();
                        if(mess.getMessage().getStatus().equals(StatusMessage.RECEIVED.toString())){
                            if(Constants.currentPosition != null){
                                //Neu ben gui gui id phong = id phong dang focus
                                //+ User gui dang focus vao room nay nen ben user gui luon chay ham nay
                                if(mess.getMessage().getIdRoom().equals(Constants.currentPosition.getRoomId())){
                                    app.updateStatusMessage(mess);
                                    if(mess.getMessage().getIdRoom().equals(Constants.currentPosition.getRoomId()) && !mess.getMessage().getUser_send().equals(Constants.infomation.getId())){
                                        try {
                                        Thread.sleep(1500);
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        DataRequest request = new DataRequest();
                                        request.setName("UPDATE_STATUS_MESSAGE_REQUEST");
                                        mess.getMessage().setStatus(StatusMessage.SEEN.toString());
                                        request.setRequest(mess);
                                        request.setStatus(Status.SUCCESS);

                                        write(request);
                                    }
                                }
                                if(Constants.currentPosition.getRoomId().equals("")){
                                    app.updateStatusMessage(mess);
                                    if(Constants.currentPosition.getRoomId().equals("") && !mess.getMessage().getUser_send().equals(Constants.infomation.getId())){
                                        try {
                                        Thread.sleep(1500);
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        DataRequest request = new DataRequest();
                                        request.setName("UPDATE_STATUS_MESSAGE_REQUEST");
                                        mess.getMessage().setStatus(StatusMessage.SEEN.toString());
                                        request.setRequest(mess);
                                        request.setStatus(Status.SUCCESS);

                                        write(request);
                                    }
                                }
                            }  
                        }
                        if(mess.getMessage().getStatus().equals(StatusMessage.SEEN.toString())){
                            app.updateStatusMessage(mess);
                        }
                    }
                    case "RESET_PANEL_LEFT_RESPONSE" -> {
                        if(response.getStatus().equals(Status.SUCCESS)){
                            ArrayList<MessItemResponse> messItems = (ArrayList<MessItemResponse>) response.getData();
                            Constants.messItems = messItems;
                            app.resetPanelLeft();
                        }
                    }
                    default ->  {
                        System.out.println("Option not exists !!!");
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
