package com.yf666.mygithubutils;

import java.util.ArrayList;
import java.util.List;

/**
 * list集合工具类
 * Created by toothwind on 2017/9/13.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */
public class ListUtil {

    /**
     * 剔除list中的重复元素
     * 不用判断list是否为空
     * @param list
     * @param <T> 需要重写 eqauls和hashCode
     */
    public static <T> void removeMulitiChilds(List<T> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        List<T> tempList = new ArrayList<>();
        for (T t : list) {
            if (!tempList.contains(t)) {
                tempList.add(t);
            }
        }
        list.clear();
        list.addAll(tempList);
    }

    /**
     * 判断一个集合是否为空
     * @param list
     * @return
     */
    public static boolean isEmpty(List list){
        return list == null || list.size() == 0;
    }

}
