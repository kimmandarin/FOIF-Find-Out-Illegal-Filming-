package com.example.foif.algorithm;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class OpenCVTest {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public void doTes(String str){
        VideoCapture videoCapture = new VideoCapture();
        videoCapture.open(str);
        Imgcodecs imgcodecs = new Imgcodecs();
        double length = videoCapture.get(Videoio.CAP_PROP_FRAME_COUNT);
        double width = videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH);
        double height = videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT);
        double fps = videoCapture.get(Videoio.CAP_PROP_FPS);
        int count = 0;

        while(videoCapture.isOpened()){
            Mat mat = new Mat();
            videoCapture.read(mat);
            if((int)videoCapture.get(1) % (int)fps == 0){
                imgcodecs.imwrite("C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\originaltest"+count+".jpg", mat);
                count += 1;
            }
        }
        videoCapture.release();
    }
}
