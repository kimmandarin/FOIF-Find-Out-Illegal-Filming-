package com.example.foif.algorithm;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class CompareVideo {
    public void compareVideo(int query_number, int compare_number){
        double corr = 0.47;
        double inter = 0.48;
        double bhatt = 0.52;
        int contin_f = 0;

        if (compare_number >= 0 && compare_number < 200){
            contin_f = 21;
        }
        else if(compare_number >= 200 && compare_number < 400){
            contin_f = 50;
        }
        else if(compare_number >= 400 && compare_number < 600){
            contin_f = 100;
        }
        else if(compare_number >= 600 && compare_number < 800){
            contin_f = 150;
        }
        else if(compare_number >= 800){
            contin_f = 200;
        }

        int query_point = 0;
        int compare_point = 0;
        int x = 0;

        if(compare_number < contin_f){
            x = 1;
        }
        else {
            x = compare_number / 2;
            while(query_point < query_number + 1 && compare_point < compare_number + 1){
                Imgcodecs imgcodecs = new Imgcodecs();
                Imgproc imgproc = new Imgproc();
                Mat query = new Mat();
                Mat test = new Mat();
                query = imgcodecs.imread("C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\originaltest" + (query_point+1) + ".jpg");
                test = imgcodecs.imread("C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\originaltest" + (x + compare_point) + ".jpg");
                List<Mat> imgs = new ArrayList<>();
                imgs.add(query);
                imgs.add(test);
                int i = 0;
                List<Mat> hists = new ArrayList<>();
                Mat hist = new Mat();
                while(!imgs.isEmpty()){
                    List<Mat> hsv = new ArrayList<>();
                    Mat hsvTemp = new Mat();
                    imgproc.cvtColor(imgs.get(i), hsvTemp, imgproc.COLOR_BGR2HSV);
                    hsv.add(hsvTemp);
                    int hbins = 100, sbins = 256;
                    MatOfInt histSize = new MatOfInt(hbins, sbins);
                    MatOfFloat histRange = new MatOfFloat(0f, 180f, 0f, 256f);
                    imgproc.calcHist(hsv, new MatOfInt(0, 1), new Mat(), hist, histSize, histRange);
                    Core core = new Core();
                    core.normalize(hist, hist, 0f, 1f, core.NORM_MINMAX);
                    hists.add(hist);
                    i++;
                }
                query = hists.get(0);
                test = hists.get(1);

                double ret_C = imgproc.compareHist(query, hist, imgproc.HISTCMP_CORREL);
                double ret_I = imgproc.compareHist(query, hist, imgproc.HISTCMP_INTERSECT);
               // ret_I = ret_I / sum(query);
            }
        }
    }
}
