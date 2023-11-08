package com.aqua.fall23g1.exception;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alibaba.fastjson2.JSONObject;
import com.aqua.fall23g1.constant.Status;
import com.aqua.fall23g1.utils.JSONUtil;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public JSONObject handleMaxSizeException(MaxUploadSizeExceededException exc) {
	  return JSONUtil.resp(Status.FAILED, "File too large!", exc.getMessage());
  }
}
