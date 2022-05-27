package com.example.foif.algorithm;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class VideoToImage {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    public void videoToImage(String str, String filePath) {
        VideoCapture videoCapture = new VideoCapture();
        videoCapture.open(str);
        Imgcodecs imgcodecs = new Imgcodecs();
        double length = videoCapture.get(Videoio.CAP_PROP_FRAME_COUNT);
        double width = videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH);
        double height = videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT);
        double fps = videoCapture.get(Videoio.CAP_PROP_FPS);
        double duration = length / fps;
        int minutes = (int) duration / 60;
        int count = 0;

        while (videoCapture.isOpened()) {
            Mat mat = new Mat();
            videoCapture.read(mat);
            if (0 < minutes && minutes < 5) {
                if (((int)videoCapture.get(1)) % (((int)fps)/3) == 0) {
                    imgcodecs.imwrite(filePath + count + ".jpg", mat);
                    count += 1;
                }
            }
            else if(5 <= minutes && minutes < 8) {
                if (((int)videoCapture.get(1)) % (((int) fps) / 2) == 0) {
                    imgcodecs.imwrite("C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\originaltest" + count + ".jpg", mat);
                    count += 1;
                }
            }
            else if(8 <= minutes && minutes < 15){
                if (((int)videoCapture.get(1)) % ((int)fps)  == 0) {
                    imgcodecs.imwrite("C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\originaltest" + count + ".jpg", mat);
                    count += 1;
                }
            }
            else if(15 <= minutes && minutes < 20){
                if (((int)videoCapture.get(1)) % (((int)fps)*2)  == 0) {
                    imgcodecs.imwrite("C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\originaltest" + count + ".jpg", mat);
                    count += 1;
                }
            }
            else{
                if (((int)videoCapture.get(1)) % (((int)fps)*3)  == 0) {
                    imgcodecs.imwrite("C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\originaltest" + count + ".jpg", mat);
                    count += 1;
                }
            }
        }
        System.out.println("Test 1");
        videoCapture.release();
        System.out.println("Test 2");
        System.exit(0);
        return;
    }
}
