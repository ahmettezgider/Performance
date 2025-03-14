package de.performance.tdexpressions;

import java.util.UUID;

public class Id {

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public static String getUniqueId() {
        return getUniqueId(0);
    }

    public static String getUniqueId(int n) {
        String nums = System.currentTimeMillis()+"";
        if (n>0)
         return nums.substring(5,n+5);
        else
            return nums;
    }

}
