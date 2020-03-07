package com.achan.exam.auth.client.exception;

/**
 * @author Achan
 * @date 2020/3/7
 */
public class ResourceIdUnspecifiedException extends RuntimeException {

    public ResourceIdUnspecifiedException() {
        super("未指定resource-client-id的值");
    }
}
