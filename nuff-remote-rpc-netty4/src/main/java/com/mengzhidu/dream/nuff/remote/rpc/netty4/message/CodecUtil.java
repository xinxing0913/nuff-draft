package com.mengzhidu.dream.nuff.remote.rpc.netty4.message;

import com.mengzhidu.dream.nuff.remote.rpc.netty4.util.IntUtil;

/**
 * Created by xinxing on 2018/12/14
 */
public class CodecUtil {

    public static byte[]  encode(String body) {
        if (body == null) {
            body = "";
        }
        byte[] bytes = body.getBytes();
        int length = bytes.length;

        byte[] result = new byte[length + 6];
        result[0] = MessageConst.MAGIC_FIRST;
        result[1] = MessageConst.MAGIC_SECOND;

        byte[] lenBytes = IntUtil.int2ByteArray(length);
        result[2] = lenBytes[0];
        result[3] = lenBytes[1];
        result[4] = lenBytes[2];
        result[5] = lenBytes[3];

        int i = 6;
        for (byte b:bytes) {
            result[i] = b;
            i++;
        }
        return result;
    }
}
