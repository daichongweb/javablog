package daichongweb.blog.exception;

import daichongweb.blog.util.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局处理异常返回类
 */
@ControllerAdvice
public class CustomerBusinessExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public JsonResult businessExceptionHandler(BusinessException e) {
        return new JsonResult<>(String.valueOf(e.getCode()), e.getMessage());
    }
}
