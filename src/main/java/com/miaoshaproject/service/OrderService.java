package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.OrderModel;

/**
 * Created by chenhaiyang on 2019/9/27.
 */
public interface OrderService {

    OrderModel createOrder(Integer userId,Integer itemId,Integer amount) throws BusinessException;

}
