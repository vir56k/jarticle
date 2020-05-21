package com.zhangyfvir.jarticle.common.entity;

public class ResultException extends RuntimeException {
    private Result result;

    public ResultException(Result result) {
        super(result.toString());
        this.result = result;
    }

    public ResultException(String msg) {
        super(msg);
        this.result = Result.error(500, msg);
    }
}

