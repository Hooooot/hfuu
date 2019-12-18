package com.hfuu.web.others.utils;

import java.util.Arrays;
import java.util.Calendar;

/**
 * @author :whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 * @Description :判断学期等相关方法
 * @date :2019/11/18 14:43
 */
public class TermUtils {
    /**
     * 获取当前时间的学期
     *
     * @return 获取的学期，格式如：191    代表19年第一学期
     */
    static public String getCurrentTerm() {
        // 数组需要有序
        int[] firstTermMonth = {1, 2, 9, 10, 11, 12};
        int[] secondTermMonth = {3, 4, 5, 6, 7};
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        String term = null;
        // Arrays.binarySearch 只对有序数组有效
        if (Arrays.binarySearch(firstTermMonth, month) > 0) {
            term = Integer.toString(year).substring(2) + "1";
        } else if (Arrays.binarySearch(secondTermMonth, month) > 0) {
            term = Integer.toString(year - 1).substring(2) + "2";
        }
        return term;
    }
}
