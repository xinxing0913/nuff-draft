package com.mengzhidu.dream.nuff.serialize.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCRequest;

/**
 * Created by xinxing on 2018/12/16
 */
public class RPCRequestSerializer extends Serializer<RPCRequest> {
    @Override
    public void write(Kryo kryo, Output output, RPCRequest rpcRequest) {
        output.writeInt(rpcRequest.getId());
        output.writeByte(rpcRequest.getMethodName().length());
        output.write(rpcRequest.getMethodName().getBytes());
        kryo.writeClassAndObject(output, rpcRequest.getArgs());
    }

    @Override
    public RPCRequest read(Kryo kryo, Input input, Class<RPCRequest> aClass) {
        RPCRequest rpcRequest = new RPCRequest();
        int id = input.readInt();
        byte methodLength = input.readByte();
        byte[] methodBytes = input.readBytes(methodLength);
        String methodName = new String(methodBytes);
        Object[] args = (Object[]) kryo.readClassAndObject(input);

        rpcRequest.setId(id);
        rpcRequest.setMethodName(methodName);
        rpcRequest.setArgs(args);
        return rpcRequest;
    }
















}
