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
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                        }
                    }
                    case "GET_ALL_ACCOUNTS_MESSAGGETED_RESPONSE" -> {
                        if(response.getStatus().equals(Status.SUCCESS)){
                            ArrayList<MessItemResponse> messItems = (ArrayList<MessItemResponse>) response.getData();
                            Constants.messItems = messItems;
                            DataRequest request = new DataRequest();
                            request.setName("GET_ALL_ACCOUNTS_REQUEST");
                            request.setStatus(Status.SUCCESS);

                            write(request);
                        }
                    }
                    case "GET_ALL_ACCOUNTS_ONLINE_RESPONSE" ->  {
                        if(response.getStatus().equals(Status.SUCCESS)){
                            
                        }
                    }
                    case "GET_ALL_ACCOUNTS_RESPONSE" ->  {
                        if(response.getStatus().equals(Status.SUCCESS)){
                            ArrayList<Account> accounts = (ArrayList<Account>) response.getData();
                            Constants.accounts = accounts;
                            app = new App();
                            app.setVisible(true);
                        }
                    }
                    case "CREATE_GROUP_CHAT_RESPONSE" -> {
                        System.out.println("CREATE GROUP SUCCESS!");
                    }
                    case "GET_ALL_MESSAGE_BY_ROOM_ID_RESPONSE" -> {
                        ArrayList<GetAllMessageResponse> messages = (ArrayList<GetAllMessageResponse>) response.getData();
                        if(messages != null){
                            app.setListMessage(messages);
                        }
                    }
                    case "SEND_MESSAGE_PRIVATE_RESPONSE" ->  {
                        if(response.getStatus().equals(Status.SUCCESS)){
                            GetAllMessageResponse mess = (GetAllMessageResponse) response.getData();
                            MessItemResponse messs = new MessItemResponse();
                            messs.setAccountId(mess.getMessage().getUser_send());
                            messs.setNewMessage(mess.getMessage().getMessage());
                            messs.setRoomId(mess.getMessage().getIdRoom());
                            messs.setStatus(mess.getMessage().getStatus());
                            messs.setType(mess.getMessage().getType());
                            
                            //Neu user dang focus vao ai do
                            if(Constants.currentPosition != null){
                                //Neu ben gui gui id phong = id phong dang focus
                                if(mess.getMessage().getIdRoom().equals(Constants.currentPosition.getRoomId())){
                                    app.sendNewMessage(mess);
                                }
                            }
                            
                            //Neu user dang focus vao 1 ai do
//                            if(Constants.currentPosition != null){
//                                //Neu id nguoi gui = id ben nguoi nhan dang focus (nguoi nhan dang o trong cuoc hoi thoai nay)(cap nhat ben phia nguoi nhan)
//                                if(mess.getMessage().getUser_send().equals(Constants.currentPosition.getAccountId())){
//                                    app.sendNewMessage(mess);
//                                    for(MessItemResponse item : Constants.messItems){
//                                        if(item.getRoomId().equals(mess.getMessage().getIdRoom())){
//                                            messs.setUsername(item.getUsername());
//                                            break;
//                                        }
//                                    }
//                                    app.updatePositionItemNewMessage(messs, "TEST3");
//                                }else{
//                                    System.out.println("User khong focus");
//                                }
//                                else if(!mess.getMessage().getUser_send().equals(Constants.currentPosition.getAccountId())){
//                                    for(MessItemResponse item : Constants.messItems){
//                                        if(item.getRoomId().equals(mess.getMessage().getIdRoom())){
//                                            messs.setUsername(item.getUsername());
//                                            break;
//                                        }
//                                    }
//                                    app.updatePositionItemNewMessage(messs, "TEST1");
//                                }                           
//                            }
//                            //neu id nguoi gui = id nguoi gui (cap nhat ben phia nguoi gui)
//                            if(Constants.infomation.getId().equals(mess.getMessage().getUser_send())){
//                                app.sendNewMessage(mess);
//                                messs.setUsername(Constants.currentPosition.getUsername());
//                                app.updatePositionItemNewMessage(messs, "TEST2");
//                            }
            
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            DataRequest request = new DataRequest();
                            request.setName("UPDATE_STATUS_MESSAGE_REQUEST");
                            mess.getMessage().setStatus(StatusMessage.RECEIVED.toString());
                            request.setRequest(mess);
                            request.setStatus(Status.SUCCESS);

                            write(request);
                        }
                    }
                    case "SEND_MESSAGE_GROUP_RESPONSE" ->  {
                        if(response.getStatus().equals(Status.SUCCESS)){
                            GetAllMessageResponse mess = (GetAllMessageResponse) response.getData();
                            if(Constants.currentPosition != null){
                                if(mess.getMessage().getIdRoom().equals(Constants.currentPosition.getRoomId()) && !Constants.infomation.getId().equals(mess.getMessage().getUser_send())){
                                    app.sendNewMessage(mess);
                                    MessItemResponse messs = new MessItemResponse();
                                    messs.setAccountId(mess.getMessage().getUser_send());
                                    messs.setNewMessage(mess.getMessage().getMessage());
                                    messs.setRoomId(mess.getMessage().getIdRoom());
                                    messs.setStatus(mess.getMessage().getStatus());
                                    messs.setType(mess.getMessage().getType());
                                    for(MessItemResponse item : Constants.messItems){
                                        if(item.getRoomId().equals(mess.getMessage().getIdRoom())){
                                            System.out.println("VAO ROIF NHA: "+item.getUsername());
                                            messs.setUsername(item.getUsername());
                                            break;
                                        }
                                    }
                                    app.updatePositionItemNewMessage(messs, "TEST3");
                                }                              
                            else{
                                    MessItemResponse messs = new MessItemResponse();
                                    messs.setAccountId(mess.getMessage().getUser_send());
                                    messs.setNewMessage(mess.getMessage().getMessage());
                                    messs.setRoomId(mess.getMessage().getIdRoom());
                                    messs.setStatus(mess.getMessage().getStatus());
                                    messs.setType(mess.getMessage().getType());
                                    for(MessItemResponse item : Constants.messItems){
                                        if(item.getRoomId().equals(mess.getMessage().getIdRoom())){
                                            messs.setUsername(item.getUsername());
                                            break;
                                        }
                                    }
                                    app.updatePositionItemNewMessage(messs, "TEST1");
                                }                           
                            }
                            if(Constants.infomation.getId().equals(mess.getMessage().getUser_send())){
                                app.sendNewMessage(mess);
                                MessItemResponse messs = new MessItemResponse();
                                    messs.setAccountId(mess.getMessage().getUser_send());
                                    messs.setNewMessage(mess.getMessage().getMessage());
                                    messs.setRoomId(mess.getMessage().getIdRoom());
                                    messs.setStatus(mess.getMessage().getStatus());
                                    messs.setType(mess.getMessage().getType());
                                    messs.setUsername(Constants.currentPosition.getUsername());
                                    app.updatePositionItemNewMessage(messs, "TEST2");
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
