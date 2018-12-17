package com.mengzhidu.dream.nuff.serialize.kryo;

import com.esotericsoftware.kryo.Kryo;
import de.javakaffee.kryoserializers.KryoReflectionFactorySupport;

/**
 * Created by xinxing on 2018/12/16
 */
public class KryoHolder {

    private static ThreadLocal<Kryo> kryoThreadLocal = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new KryoReflectionFactorySupport();
            return kryo;
        }
    };

    public static Kryo get() {
        return kryoThreadLocal.get();
    }
}
