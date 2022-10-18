/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common;

import Utils.Constants;
import java.io.IOException;

/**
 *
 * @author Asus
 */
public final class Common {
    public static void write(Object obj) throws IOException{
        Constants.oos.writeObject(obj);
        Constants.oos.flush();
    }
}
