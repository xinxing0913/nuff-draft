package com.mengzhidu.dream.nuff.serialize.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCResponse;

/**
 * Created by xinxing on 2018/12/16
 */
public class RPCResponseSerializer extends Serializer<RPCResponse> {

    @Override
    public void write(Kryo kryo, Output output, RPCResponse rpcResponse) {
        output.writeInt(rpcResponse.getRequestId());
        output.writeBoolean(rpcResponse.isSuccess());
        if (rpcResponse.isSuccess()) {
            kryo.writeClassAndObject(output, rpcResponse.getResult());
        } else {
            kryo.writeClassAndObject(output, rpcResponse.getThrowable());
        }
    }

    @Override
    public RPCResponse read(Kryo kryo, Input input, Class<RPCResponse> clazz) {
        RPCResponse rpcResponse = new RPCResponse();
        rpcResponse.setRequestId(input.readInt());
        rpcResponse.setSuccess(input.readBoolean());
        if (rpcResponse.isSuccess()) {
            rpcResponse.setResult(kryo.readClassAndObject(input));
        } else {
            rpcResponse.setThrowable((Throwable) kryo.readClassAndObject(input));
        }
        return rpcResponse;
    }











}
