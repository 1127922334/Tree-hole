package com.luntan.demo.exception;
//RuntimeException：运行时异常，这种异常我们不需要处理，完全由虚拟机接管
// 比如我们常见的NullPointerException
// 我们在写程序时不会进行catch或throw。
public class DIYError extends  RuntimeException{
    private String  message;
    public DIYError(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    public DIYError( String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
