package Main;

import javax.swing.*;
import java.net.URL;
public class Date {

    //头部图片 URL:定位图片地址  ImageIcon：图片
    public static URL headerUrl= Date.class.getResource("/image/title.jpg");
    public static ImageIcon header = headerUrl == null ? null : new ImageIcon(headerUrl);

    //    public static void main(String[] args) {
//        System.out.println(1);
//         URL resource1 = Date.class.getResource("/statics/title.jpg");
//        System.out.println(resource1);
//    }
//
    public static URL leftUrl = Date.class.getResource("/image/left.png");
    public static ImageIcon left = new ImageIcon(leftUrl);

    public static URL rightUrl = Date.class.getResource("/image/right.png");
    public static ImageIcon right = new ImageIcon(rightUrl);

    public static URL upUrl = Date.class.getResource("/image/up.png");
    public static ImageIcon up = new ImageIcon(upUrl);

    public static URL downUrl = Date.class.getResource("/image/down.png");
    public static ImageIcon down = new ImageIcon(downUrl);
    //身体
    public static URL bodyUrl = Date.class.getResource("/image/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);
    //食物
    public static URL foodUrl = Date.class.getResource("/image/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);

}
