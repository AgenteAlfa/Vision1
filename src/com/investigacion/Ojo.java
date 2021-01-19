package com.investigacion;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.util.Arrays;
import java.util.Random;

public class Ojo {
    /*
    * El ojo ve una seccion del escenario y analiza si hay una diferencia hacia otro lado
    */
    private final Mat Vision;
    private final Mat Simple;
    private final Mat Analisis;
    private int[] Foco;

    public static int TOPE = 50;
    public static final int MIN = 50;
    public static final double SOMBRA = 6;
    public static final int RADIO = 35;
    public static final int SATURACION = 10;

    public Mat getSimple() {
        return Simple;
    }

    public static final int MAX_REPETICION = 100;
    private int iesomo;

    public Ojo(Mat vision)
    {
        Vision = vision;
        Simple = Mat.ones(Vision.rows(),Vision.cols(),Vision.type()).setTo(Scalar.all(255));
        Analisis = Mat.ones(Vision.rows(),Vision.cols(),Vision.type()).setTo(Scalar.all(255));
    }
    public void Ver()
    {
        AnalizarImagen();
        //SimplificarImagen();
    }

    public Mat getAnalisis() {
        return Analisis;
    }

    private void AnalizarImagen()
    {
        for (int i = 0; i < Vision.rows(); i++) {
            for (int j = 0; j <Vision.cols(); j++) {
                // En cada punto de la imagen
                try{
                    double[] punto = Vision.get(i,j);
                    double temp = 0;
                    double r = 0 , g = 0, b = 0;
                    double R = 0 , G = 0 , B = 0;


                    double[][] arr = {Vision.get(i+1,j),Vision.get(i-1,j),
                            Vision.get(i,j+1),Vision.get(i,j-1)};
                    //Arr contiene todos los vecinos
                    for (double[] dif : arr )
                    {
                        //Para todos los vecinos
                        R += punto[0] - dif[0];
                        r += Math.abs(punto[0] - dif[0]);
                        G += punto[1] - dif[1];
                        g += Math.abs(punto[1] - dif[1]);
                        B += punto[2] - dif[2];
                        b += Math.abs(punto[2] - dif[2]);
                    }
                    temp = r + g + b;
                    if (temp > TOPE * 6) Analisis.put(i,j, 0,0,0);
                    else if (temp > TOPE * 4) Analisis.put(i,j, 255,0,0);
                    else if (temp > TOPE * 2) Analisis.put(i,j, 0,255,0);
                    else if (temp > TOPE ) Analisis.put(i,j, 0,0,255);

                }catch (Exception ignored){}

            }
        }
    }

    private void DibujarPunto_Simple(int[] pos , double ...data)
    {
        try{
            Simple.put(pos[0] + 1,pos[1],data);
            Simple.put(pos[0] - 1,pos[1],data);
            Simple.put(pos[0],pos[1] + 1,data);
            Simple.put(pos[0],pos[1] - 1,data);
        }catch (Exception ignored){}


    }

    private void SimplificarImagen()
    {
        for (int i = 0; i < Analisis.rows(); i++) {
            for (int j = 0; j < Analisis.cols(); j++) {

                VerPunto_Analisis(i,j);



            }
        }

    }

    private void VerPunto_Analisis(int ... pos)
    {
        try{
            double[][] puntos = {Analisis.get(pos[0],pos[1]), Analisis.get(pos[0] + 1,pos[1]),Analisis.get(pos[0] - 1,pos[1]),
                                    Analisis.get(pos[0],pos[1] + 1),Analisis.get(pos[0],pos[1] - 1)};
            int R = 0 ,G = 0,B = 0,N = 0,W = 0;

            for (double p[] : puntos) {
                if ( ArrEq(p,0,0,255) ) R++;
                if ( ArrEq(p,0,255,0) ) G++;
                if ( ArrEq(p,255,0,0) ) B++;
                if ( ArrEq(p,0,0,0) ) N++;
                if ( ArrEq(p,255,255,255) ) W++;
            }
            if (W < 1) DibujarPunto_Simple(pos,255,255,255);
            else if (W < 3) DibujarPunto_Simple(pos,0,0,0);
            else DibujarPunto_Simple(pos,255,255,255);



        }catch (Exception ignored){}
    }
    private boolean ArrEq(double[] A , double ... B)
    {
        return A[0] == B[0] && A[1] == B[1] && A[2] == B[2];
    }

}
