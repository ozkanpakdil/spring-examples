/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import com.logitech.gaming.LogiLED;

/**
 *
 * @author ozkan
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LogiLED.LogiLedInit();
        LogiLED.LogiLedSetLighting(100, 0, 0);
        LogiLED.LogiLedSaveCurrentLighting();
        LogiLED.LogiLedShutdown();
        System.out.println("no exception so far...........");
    }
    
}
