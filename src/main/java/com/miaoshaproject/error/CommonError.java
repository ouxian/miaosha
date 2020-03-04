package com.miaoshaproject.error;

/**
 * Created by chenhaiyang on 2019/9/13.
 */
public interface CommonError {
    int getErrorCode();

    String getErrMsg();

    CommonError setErrMsg(String errMsg);
}
