/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import static GUI.App.socket;
import Models.Account;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Constants {
    public static Socket socket;
    public static ObjectOutputStream oos;
    public static ObjectInputStream ois;
    public static BufferedReader is;
    public static BufferedWriter os;
    public static int id;
    public static int id_received;
    public static ArrayList<Account> accounts;
    public static Account infomation;
}
