package com.orange.minip.Util;

import java.util.Random;

public class GetRandomUtil {

    public static synchronized String genUniqueKey(){

        Random random=new Random();
        Integer number=random.nextInt(90000)+10000;

        return System.currentTimeMillis()+String.valueOf(number);
    }
}
