package com.udacity.sandwichclub.utils;

import java.util.List;

/**
 * Created by Vladimir Mikhalev 17.02.2018.
 */

public class ListUtils {

    public static <E> String join(List<E> list) {
        if (list == null) return "";

        StringBuilder sb = new StringBuilder();
        String delimiter = ", ";

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

}
