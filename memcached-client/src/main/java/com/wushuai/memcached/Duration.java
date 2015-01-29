package com.wushuai.memcached;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author gaochuanjun
 * @since 15/1/19
 */
public enum Duration {
    ONE_MIN,
    TEN_MIN,
    FIVE_MIN,
    ONE_DAY,
    THIRTY_SEC,;

    private int seconds;

    public int getSeconds() {
        switch (this) {
            case THIRTY_SEC:
                return 30 * 1000;
            case FIVE_MIN:
                return 300 * 1000;
            case ONE_MIN:
                return 60 * 1000;
            case TEN_MIN:
                return 600 * 1000;
            case ONE_DAY:
                return (int) ((DateUtils.ceiling(new Date(), Calendar.DAY_OF_MONTH).getTime() - System.currentTimeMillis()));
        }
        return -1;
    }
}
