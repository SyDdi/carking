package com.car.mp.inteceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 */
public class FrequencyStruct {

    String uniqueKey;
    long start;
    long end;
    int time;
    int limit;
    List<Long> accessPoints = new ArrayList<Long>();

    public void reset(long timeMillis) {

        start = end = timeMillis;
        accessPoints.clear();
        accessPoints.add(timeMillis);
    }

    @Override
    public String toString() {
        return "FrequencyStruct [uniqueKey=" + uniqueKey + ", start=" + start
                + ", end=" + end + ", time=" + time + ", limit=" + limit
                + ", accessPoints=" + accessPoints + "]";
    }
}
