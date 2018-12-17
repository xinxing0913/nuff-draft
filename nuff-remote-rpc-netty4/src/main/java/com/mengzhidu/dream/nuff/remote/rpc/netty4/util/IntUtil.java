package com.mengzhidu.dream.nuff.remote.rpc.netty4.util;

/**
 * Created by xinxing on 2018/12/15
 */
public class IntUtil {
    public static int bytes2Int(byte[] bytes){
        int num=bytes[3] & 0xFF;
        num |=((bytes[2] <<8)& 0xFF00);
        num |=((bytes[1] <<16)& 0xFF0000);
        num |=((bytes[0] <<24)& 0xFF0000);
        return num;
     }

    public static byte[] int2ByteArray(int i){
        byte[] result=new byte[4];
        result[0]=(byte)((i >> 24)& 0xFF);
        result[1]=(byte)((i >> 16)& 0xFF);
        result[2]=(byte)((i >> 8)& 0xFF);
        result[3]=(byte)(i & 0xFF);
        return result;
    }
}
