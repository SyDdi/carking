package com.car.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.car.domain.Message;
import com.car.service.IMessageService;

/**
 * Created by admin on 2016/11/16.
 */
@Service("IMessageService")
public class MessageService extends BaseService<Message> implements IMessageService {
	
}
