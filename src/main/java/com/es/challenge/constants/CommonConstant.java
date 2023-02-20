package com.es.challenge.constants;

import java.text.SimpleDateFormat;


/**
 * @author fjt
 * @date 2023-02-16
 */
public interface CommonConstant {
    String REQUEST_DATE_FORMAT = "MM/dd/yyyy";
    String RESPONSE_DATE_FORMAT = "MM/dd/yyyy";
    SimpleDateFormat formatter = new SimpleDateFormat(REQUEST_DATE_FORMAT);
    SimpleDateFormat displayFormatter = new SimpleDateFormat(RESPONSE_DATE_FORMAT);
}
