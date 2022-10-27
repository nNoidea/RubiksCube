package be.ugent.oplossing.model;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String [] args){
        System.out.println(Color.BLACK);
       //RubiksKubus2 rk=  new RubiksKubus2();
       Kubusje2 k =new Kubusje2(new Point3D(2,-2,2), new String[]{"black", "white", "orange", "black", "blue", "black" } );
       Vlakje[]v= k.getVlakjes();
       for (int i=0; i<6;i++) {
           System.out.println(v[i]);
       }

    }
}
