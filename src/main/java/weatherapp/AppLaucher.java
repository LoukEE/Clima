package weatherapp;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AppLaucher extends JFrame{
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){ 
            @Override
            public void run(){

                new AppGUI().setVisible(true);
                // System.out.println(WeatherApp.getLocationData(""));
                System.out.println(WeatherApp.getCurrentTime());
                
            }
        });
    }
}
