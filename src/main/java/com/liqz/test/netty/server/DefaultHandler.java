package com.liqz.test.netty.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liqz.test.netty.common.IMessageHandler;
import com.liqz.test.netty.common.MessageInput;

import io.netty.channel.ChannelHandlerContext;

public class DefaultHandler implements IMessageHandler<MessageInput> {

	private final static Logger LOG = LoggerFactory.getLogger(DefaultHandler.class);

	@Override
	public void handle(ChannelHandlerContext ctx, String requesetId, MessageInput input) {
		LOG.error("unrecognized message type {} comes", input.getType());
		ctx.close();
	}

}
