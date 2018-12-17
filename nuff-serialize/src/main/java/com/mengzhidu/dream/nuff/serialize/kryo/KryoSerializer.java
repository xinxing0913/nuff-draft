package com.mengzhidu.dream.nuff.serialize.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

import java.io.IOException;

/**
 * Created by xinxing on 2018/12/16
 */
public class KryoSerializer {

    private static final byte[] HEADER = new byte[4];

    public static void serialize(Object object, ByteBuf byteBuf) {
        Kryo kryo = KryoHolder.get();
        int startIndex = byteBuf.writerIndex();
        ByteBufOutputStream byteBufOutputStream = new ByteBufOutputStream(byteBuf);
        try {
            byteBufOutputStream.write(HEADER);
            Output output = new Output(1024 * 4, -1);
            output.setOutputStream(byteBufOutputStream);
            kryo.writeClassAndObject(output, object);
            output.flush();
            output.close();
            int endIndex = byteBuf.writerIndex();
            byteBuf.setInt(startIndex, endIndex - startIndex - 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deserialize(ByteBuf byteBuf) {
        if (byteBuf == null) {
            return null;
        }

        Input input = new Input(new ByteBufInputStream(byteBuf));
        Kryo kryo = KryoHolder.get();
        return kryo.readClassAndObject(input);
    }
}
