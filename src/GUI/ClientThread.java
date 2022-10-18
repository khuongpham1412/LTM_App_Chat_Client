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
    @Override
    public void run() {
        listen();
    }
    
    public void write(Object obj) throws IOException{
        Constants.oos.writeObject(obj);
        Constants.oos.flush();
    }

    private void listen() {
        System.out.println("START LISTENNING...");
        DataResponse response = new DataResponse();
        try {
            while(true){
                response = (DataResponse) Constants.ois.readObject();
                switch(response.getName()){
                    case "LOGIN_RESPONSE" ->  {
                        Account account = (Account) response.getData();
                        Constants.infomation = account;
                        DataRequest request = new DataRequest();
                        request.setName("GET_ALL_ACCOUNTS_REQUEST");
                        request.setStatus(Status.SUCCESS);
                        
                        write(request);
                    }
                    case "GET_ALL_ACCOUNTS_RESPONSE" ->  {
                        ArrayList<Account> accounts = (ArrayList<Account>) response.getData();
                        app = new App(accounts);
                        app.setVisible(true);
                    }
                    case "SEND_MESSAGE_PRIVATE_RESPONSE" ->  {
                        Message mess = (Message) response.getData();
                        app.test(new Message(UUID.randomUUID(),mess.getMessage(), TypeMessage.TEXT, new Date(), StatusMessage.RECEIVED, "9",mess.getUser_send(),mess.getUser_receive() ));
                        
                        System.out.println("MESSAGE: "+mess.getMessage());
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
