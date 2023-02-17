package priv.jesse.mall.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 张鹏飞
 * @company： 软通动力信息技术股份有限公司
 * @Official： www.isoftstone.com
 */
public class TimeUtils {
    //添加当前时间

    public  static String getTimes(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String format1 = format.format(new Date());
        return format1;
    }


    public  static String getHDFSTimes(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String format1 = format.format(new Date());
        return format1;
    }


}
