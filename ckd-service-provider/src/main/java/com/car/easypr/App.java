package com.car.easypr;

import com.car.easypr.core.PlateLocate;
import org.bytedeco.javacpp.opencv_core;

import java.io.File;
import java.net.URL;
import java.util.Vector;

import static org.bytedeco.javacpp.opencv_highgui.imread;

/**
 *
 */
public class App {
    public static void main(String[] args) {

        System.out.println("begin.......................");
//
//        ClassLoader loader = Thread.currentThread().getContextClassLoader();
//        URL url = loader.getResource("");
//
//        String realPath = url.getPath();
//        //去掉路径信息中的协议名"file:"
//        int pos = realPath.indexOf("file:");
//        if (pos > -1)
//            realPath = realPath.substring(pos + 5);
//
//        if("/".equals(File.separator)){//linux
//
//        }else if("\\".equals(File.separator)){//windows
//            realPath = realPath.substring(1);
//        }
//
//
//        System.out.println(realPath);
//        System.out.println(App.class.getResource("/").getFile());

 //       testPlateRecognise();
//       testPlateDetect();
        testPlateLocate();
//        testCharsRecognise();
//        testColorDetect();
//        testProjectedHistogram();
//        testCharsIdentify();
    }


    public static void testPlateLocate() {
        String imgPath = "/tmp/1.jpg";
        System.out.println("start.....");
        opencv_core.Mat src = imread(imgPath);
        System.out.println("read completed");

        PlateLocate plate = new PlateLocate();
        plate.setDebug(true);
        plate.setLifemode(true);

//        Vector<opencv_core.Mat> resultVec = plate.plateLocate(src);
//        int num = resultVec.size();
//        for (int j = 0; j < num; j++) {
//             //showImage("Plate Located " + j, resultVec.get(j));
//        }

        System.out.println(plate.plateLocate(imgPath));

        return;
    }


}
