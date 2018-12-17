package com.mengzhidu.dream.nuff.remote.rpc.netty4.message;

/**
 * 基于Netty的消息的常量定义
 * 对于
 */
public class MessageConst {

    // 魔术字
    public static final short MAGIC = (short)0x01FF;
    public static final byte MAGIC_FIRST = (byte)0x01;
    public static final byte MAGIC_SECOND = (byte)0xFF;

    // 报文头的长度
    public static final int HEADER_LENGTH = 5;

}
