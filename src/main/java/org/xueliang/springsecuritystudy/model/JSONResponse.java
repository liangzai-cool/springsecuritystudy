package org.xueliang.springsecuritystudy.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.xueliang.springsecuritystudy.DefaultException.Error;

public class JSONResponse {

	private int code;
	private Map<String, Object> msg = new HashMap<String, Object>();
	private Map<String, Object> error = new HashMap<String, Object>();
	
	public JSONResponse(int code){
		this.code = code;
	}
	
	public JSONResponse() {  }
	public JSONResponse(Map<String, Object> msg){
		this.msg = msg;
	}
	
	public JSONResponse(int code, Map<String, Object> msg){
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code ;
	}
	public void setCode(int code) {
		this.code = code;
	}

	public Map<String, Object> getMsg() {
		return msg;
	}
	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}
	public Object getMsg(String key){
		return this.msg.get(key);
	}
	public void addMsg(String key, Object content){
		this.msg.put(key, content);
	}
	public void removeMsg(String key){
		this.msg.remove(key);
	}
	
	public Map<String, Object> getError() {
		return error;
	}
	public void setError(Map<String, Object> error) {
		this.error = error;
	}
	public Object getError(String key){
		return this.error.get(key);
	}
    public void addError(String key, Object content){
        this.code = 1;
        this.error.put(key, content);
    }
    public void addError(Error error){
        this.code = 1;
        this.error.put(error.name(), error.getMessage());
    }
	public void removeError(String key){
		this.error.remove(key);
	}
	
	public boolean getIsOK() {
		return error.isEmpty();
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject(this);
		json.put("serverTime", new Date());
		return json.toString();
	}
}
