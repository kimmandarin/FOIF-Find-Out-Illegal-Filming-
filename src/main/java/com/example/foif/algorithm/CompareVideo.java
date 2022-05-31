package com.example.foif.algorithm;

import com.example.foif.domain.Result;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class CompareVideo {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public void compareVideo(int query_number, int compare_number, String originalFilePath, String compareFilePath, Result result) {
        double corr = 0.47;
        double inter = 0.48;
        double bhatt = 0.52;
        double sim = 0;
        double a = 0;
        int contin_f = 0;
        int index = 0;
        String[] resultStr = new String[10];
        String[] correlStr = new String[10];
        String[] intersectStr = new String[10];
        String[] bhattacharyyaStr = new String[10];
        String[] realResult = new String[40];


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
            x = 1;
        } else {
            x = compare_number / 2;
            while (query_point < query_number + 1 && compare_point < compare_number + 1) {
                Imgcodecs imgcodecs = new Imgcodecs();
                Imgproc imgproc = new Imgproc();
                Mat query = new Mat();
                Mat test = new Mat();
                query = imgcodecs.imread(originalFilePath + (query_point + 1) + ".jpg");
                test = imgcodecs.imread(compareFilePath + ((int)x + compare_point) + ".jpg");
                List<Mat> imgs = new ArrayList<>();
                imgs.add(query);
                imgs.add(test);
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
                System.out.println("비교 전: query_number = " + query_number + "\nc_n = " + compare_number + " " + contin_f + "\nquery_point = " + query_point +
                        "\ncompare_point = " + compare_point + "\na = " + a + "\nx = " + x);
                if (ret_C >= corr && ret_I >= inter && ret_B <= bhatt) {
                    String temp = (sim + 1) + "번 유사. \nquery 영상 중 " + (query_point + 1) + "번째 프레임과 test 영상 중 " + (x + compare_point) + "번째 프레임이 유사합니다.***";
                    resultStr[index] = temp;
                    correlStr[index] = "CORREL : " + ret_C;
                    intersectStr[index] = "INTERSECT : " + ret_I;
                    bhattacharyyaStr[index] = "BHATTACHARYYA : " + ret_B;

                    result.setResult(resultStr);
                    result.setCorrel(correlStr);
                    result.setIntersect(intersectStr);
                    result.setBhattacharyya(bhattacharyyaStr);

                    sim += 1;
                    query_point += 1;
                    compare_point += 1;
                    index += 1;
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
                    result.setCheck("*******두 영상은 동일한 영상입니다.*********");
                    break;
                }
                System.out.println("비교 후: query_number = " + query_number + "\nc_n = " + compare_number + " " + contin_f + "\nquery_point = " + query_point +
                        "\ncompare_point = " + compare_point + "\na = " + a + "\nx = " + x);
            }
        }
        int temp = -1;
        for(int i = 0; i < resultStr.length; i++){
            realResult[++temp] = resultStr[i];
            realResult[++temp] = correlStr[i];
            realResult[++temp] = intersectStr[i];
            realResult[++temp] = bhattacharyyaStr[i];
        }
        result.setRealResult(realResult);
    }
}
