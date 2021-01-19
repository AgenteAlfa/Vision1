package com.investigacion;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        for (int i = 1; i <= 10; i++)
        try{

            Mat img = Imgcodecs.imread("src/data/" + i + ".jpg",Imgcodecs.IMREAD_ANYCOLOR);
            Ojo mOjo = new Ojo(img);
            mOjo.Ver();

            Mat Analisis = mOjo.getAnalisis();
            Mat Simple = mOjo.getSimple();
            //new Ventana(Analisis.cols() + 10,Analisis.rows() + 10,i);
            Imgcodecs.imwrite("src/data/" + i + "_a.jpg",Analisis);
            //Imgcodecs.imwrite("src/data/" + i + "_s.jpg",Simple);

            Thread.sleep(3000);
        }catch (Exception E)
        {
            System.out.println(E.getMessage());
            break;
        }



        //new Ventana(Analisis.cols() + 10,Analisis.rows() + 10, );
        }
}
