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
                    case "GET_ALL_MESSAGE_BY_ROOM_ID_RESPONSE" -> {
                        ArrayList<Message> messages = (ArrayList<Message>) response.getData();
                        app.setListMessage(messages);
                    }
                    case "SEND_MESSAGE_PRIVATE_RESPONSE" ->  {
                        if(response.getStatus().equals(Status.SUCCESS)){
                            Message mess = (Message) response.getData();
                            if(Constants.currentPosition != null){
                                if(mess.getUser_send().equals(Constants.currentPosition.getAccountId())){
                                    app.sendNewMessage(mess);
                                }
                            }
                            if(Constants.infomation.getId().equals(mess.getUser_send())){
                                app.sendNewMessage(mess);
                            }
                            

                            System.out.println("MESSAGE: "+mess.getMessage());
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
