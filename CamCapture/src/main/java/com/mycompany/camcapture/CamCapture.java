/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.camcapture;
import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 *
 * @author Laniel
 */
public class CamCapture {

    public static void main(String[] args) throws IOException{
        Webcam webcam = Webcam.getDefault();
        
        for(Dimension supportedSize: webcam.getViewSizes()){
            System.out.println("supportedSize.toString()");
        }
        
        webcam.open();
        ImageIO.write(webcam.getImage(), "JPG", new File("firstCapture.jpg"));
        webcam.close();
    }
}
