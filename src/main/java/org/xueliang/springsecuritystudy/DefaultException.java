package org.xueliang.springsecuritystudy;

public class DefaultException extends Exception {

	private static final long serialVersionUID = 6304732905146351972L;
	
	private String errorCode;

	public static enum Error {
		user_not_login("用户未登录"),
		server_internal_error("服务器内部错误"),
		invalid_parameter("无效参数");
	    
		private String message;
		
		Error(String message){
		    this.message = message;
		}
		
		public String getMessage() {
		    return this.message;
		}
	}
	
	public DefaultException(Error error) {
	    this(error.name(), error.message);
    }
	
	public DefaultException(String code, String message) {
	    super(message);
	    this.errorCode = code;
    }
	
	public String getErrorCode() {
        return errorCode;
    }
}
