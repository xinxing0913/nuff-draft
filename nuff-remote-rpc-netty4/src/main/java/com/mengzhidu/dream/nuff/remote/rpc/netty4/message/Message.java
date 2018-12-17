package com.mengzhidu.dream.nuff.remote.rpc.netty4.message;

import java.util.Arrays;

/**
 * 消息体
 * 我们的消息体共计分为三部分:
 * 1.头信息
 * 2.请求ID
 * 3.长度信息
 * 4.字节流
 *
 */
public class Message {

    private int requestId;

    private int length;

    private byte[] data;

    private boolean sucess;

    public Message() {
    }

    public Message(int requestId, int length, byte[] data) {
        this.requestId = requestId;
        this.length = length;
        this.data = data;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "length=" + length +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
