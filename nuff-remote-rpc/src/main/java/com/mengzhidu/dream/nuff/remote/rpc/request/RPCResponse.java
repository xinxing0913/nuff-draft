package com.mengzhidu.dream.nuff.remote.rpc.request;

/**
 * Created by xinxing on 2018/12/16
 */
public class RPCResponse {
    private int requestId;

    private Object result;

    private Throwable throwable;

    private boolean success;

    public RPCResponse() {
    }

    public RPCResponse(int requestId, Object result, Throwable throwable, boolean success) {
        this.requestId = requestId;
        this.result = result;
        this.throwable = throwable;
        this.success = success;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "RPCResponse{" +
                "requestId=" + requestId +
                ", result=" + result +
                ", throwable=" + throwable +
                ", success=" + success +
                '}';
    }
}
