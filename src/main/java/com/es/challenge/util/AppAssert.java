package com.es.challenge.util;


import com.es.challenge.exception.AppRuntimeException;
import com.es.challenge.exception.ErrorCode;
import com.es.challenge.exception.ParamInvalidException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author fjt
 * @date 2023-02-16
 */
public class AppAssert {

    public static void greaterOrEqual(Integer num, Integer refer) {
        if (num == null || refer == null || num < refer) {
            throw new ParamInvalidException();
        }
    }

    public static void greaterOrEqualLong(Long num, Long refer, String errorMessage) {
        if (num == null || refer == null || num < refer) {
            throw new ParamInvalidException();
        }
    }

    public static void greaterOrEqual(Integer num, Integer refer, String errorMessage) {
        if (num == null || refer == null || num < refer) {
            throw new ParamInvalidException(errorMessage);
        }
    }

    public static void greaterOrEqual(BigDecimal num, BigDecimal refer, String errorMessage) {
        if (num == null || refer == null || num.compareTo(refer) < 0) {
            throw new ParamInvalidException(errorMessage);
        }
    }

    public static void greater(BigDecimal num, BigDecimal refer, String errorMessage) {
        if (num == null || refer == null || num.compareTo(refer) <= 0) {
            throw new ParamInvalidException(errorMessage);
        }
    }

    public static void lesser(BigDecimal num, BigDecimal refer, String errorMessage) {
        if (num == null || refer == null || num.compareTo(refer) >= 0) {
            throw new ParamInvalidException(errorMessage);
        }
    }

    public static void notEmpty(String param, String errorMessage) {
        if (param == null || param.length() == 0) {
            throw new ParamInvalidException(errorMessage);
        }
    }

    public static void isTrue(Boolean flag, String errorMessage) {
        if (flag == null || !flag) {
            throw new ParamInvalidException(errorMessage);
        }
    }

    public static void notNull(Object object, String errorMessage) {
        if (object == null) {
            throw new ParamInvalidException(errorMessage);
        }
    }

    public static void notNull(List object, String errorMessage) {
        if (object == null || object.size() == 0) {
            throw new ParamInvalidException(errorMessage);
        }
    }

    public static void isBigDecimal(String str, String errorMessage) {
        try {
            new BigDecimal(str);
        } catch (NumberFormatException e) {
            throw new ParamInvalidException(errorMessage);
        }
    }

    public static void scale(BigDecimal number, int scale, String message) {
        BigDecimal newNumber = new BigDecimal(number.toString()).setScale(scale, RoundingMode.UP);
        if (newNumber.compareTo(number) != 0) {
            throw new ParamInvalidException(message);
        }
    }

    public static void greaterOrEqual(BigDecimal num, BigDecimal refer, ErrorCode errorCode) {
        if (num == null || refer == null || num.compareTo(refer) < 0) {
            throw new AppRuntimeException(errorCode);
        }
    }
}
