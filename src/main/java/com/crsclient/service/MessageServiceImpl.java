package com.crsclient.service;

import com.crsclient.data.MessageDao;
import com.crsclient.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Laud.Ochei
 */

@Service("MessageService")
public class MessageServiceImpl implements MessageService {

        MessageDao messageDao;
	@Autowired
	public void setMessaageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

        @Override
        public Message GetMessage(String msg) {
            return messageDao.GetMessage(msg);
        }
        
        
}