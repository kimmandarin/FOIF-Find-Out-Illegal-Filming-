package com.example.foif.algorithm;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class CompareVideo {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public void compareVideo(int query_number, int compare_number, String originalFilePath, String compareFilePath) {
        System.out.println("함수 시작 Test");
        double corr = 0.47;
        double inter = 0.48;
        double bhatt = 0.52;
        double sim = 0;
        double a = 0;
        int contin_f = 0;

        if (compare_number >= 0 && compare_number < 200) {
            contin_f = 21;
        } else if (compare_number >= 200 && compare_number < 400) {
            contin_f = 50;
        } else if (compare_number >= 400 && compare_number < 600) {
            contin_f = 100;
        } else if (compare_number >= 600 && compare_number < 800) {
            contin_f = 150;
        } else if (compare_number >= 800) {
            contin_f = 200;
        }

        int query_point = 0;
        int compare_point = 0;
        double x = 0;

        if (compare_number < contin_f) {
            System.out.println("Test 1");
            System.out.println("Compare_Number : " + compare_number);
            System.out.println("contin_f : " + contin_f);
            x = 1;
        } else {
            System.out.println("Test 2");
            x = compare_number / 2;
            while (query_point < query_number + 1 && compare_point < compare_number + 1) {
                System.out.println("Test 3");
                Imgcodecs imgcodecs = new Imgcodecs();
                Imgproc imgproc = new Imgproc();
                Mat query = new Mat();
                Mat test = new Mat();
                query = imgcodecs.imread(originalFilePath + (query_point + 1) + ".jpg");
                test = imgcodecs.imread(compareFilePath + ((int)x + compare_point) + ".jpg");
                List<Mat> imgs = new ArrayList<>();
                imgs.add(query);
                imgs.add(test);
                System.out.println("Test 1번 : " + imgs.get(0));
                System.out.println("Test 2번 : " + imgs.get(1));
                List<Mat> hists = new ArrayList<>();
                Mat hist = new Mat();
                for (int i = 0; i < imgs.size(); i++) {
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
                }
                query = hists.get(0);
                test = hists.get(1);

                double ret_C = imgproc.compareHist(query, hist, imgproc.HISTCMP_CORREL);
                double ret_I = imgproc.compareHist(query, hist, imgproc.HISTCMP_INTERSECT);
                Scalar sumDiff = Core.sumElems(query);
                double sumDiffSee = 0;
                for (int j = 0; j < sumDiff.val.length; j++) {
                    sumDiffSee += sumDiff.val[j];
                }
                ret_I = ret_I / sumDiffSee;
                double ret_B = imgproc.compareHist(query, hist, imgproc.HISTCMP_BHATTACHARYYA);
                System.out.println("비교전: query_number = " + query_number + "\nc_n = " + compare_number + " " + contin_f + "\nquery_point = " + query_point +
                        "\ncompare_point = " + compare_point + "\na = " + a + "\nx = " + x);
                if (ret_C >= corr && ret_I >= inter && ret_B <= bhatt) {
                    System.out.println("\n\n" + (sim + 1) + "번 유사. \nquery영상중" + (query_point + 1) + "번째 프레임과 test영상 중 " + (x + compare_point) + "번째 프레임이 유사합니다.");
                    System.out.println("CORREL : " + ret_C);
                    System.out.println("INTERSECT : " + ret_I);
                    System.out.println("BHATTACHARYYA : " + ret_B);

                    sim += 1;
                    query_point += 1;
                    compare_point += 1;
                } else {
                    compare_point += 1;
                }
                hists.clear();

                if ((compare_number / 2) + (a + 1) >= compare_number - (a * contin_f)) {
                    if ((x + compare_point) == compare_number - (contin_f * (a + 1))) {
                        compare_point = 0;
                        x = 1 + (a * contin_f);
                    }
                } else if (x == (compare_number / 2) + (a * contin_f) && compare_point == contin_f) {
                    compare_point = 0;
                    x = 1 + (a * contin_f);
                }

                if (1 - (a + 1) * contin_f >= compare_number / 2) {
                    if (x + compare_point == compare_number / 2) {
                        break;
                    }
                } else if (x == 1 + a * contin_f && compare_point == contin_f) {
                    compare_point = 0;
                    x = compare_number - ((a + 1) * contin_f);
                }
                if (compare_number - (a + 1) * contin_f <= compare_number / 2 + (a + 1) * contin_f) {
                    if ((x + compare_point) == compare_number - (a - 1) * contin_f) {
                        compare_point = 0;
                        x = 1 + a * contin_f;
                    }
                } else if (x == compare_number - (a + 1) * contin_f && compare_point == contin_f) {
                    a += 1;
                    compare_point = 0;
                    x = compare_number / 2 + a * contin_f;
                }
                if (sim >= 10) {
                    System.out.println("\n\n\n*******두 영상은 동일한 영상입니다.*********");
                    break;
                }
                System.out.println("비교후: query_number = " + query_number + "\nc_n = " + compare_number + " " + contin_f + "\nquery_point = " + query_point +
                        "\ncompare_point = " + compare_point + "\na = " + a + "\nx = " + x);
            }
        }
    }
}
