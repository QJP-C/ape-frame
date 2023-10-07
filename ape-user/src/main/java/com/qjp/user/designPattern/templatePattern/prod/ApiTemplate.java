package com.qjp.user.designPattern.templatePattern.prod;

import com.qjp.bean.Result;
import com.qjp.bean.ResultCode;

/**
 * api模板
 */
public class ApiTemplate {

    public void execute(Result result, final Action action) {
        try {
            action.validate();
            action.execute();
            action.after();
            result.setSuccess(true);
            result.setCode(1024);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setCode(ResultCode.ERROR);
        }
    }

}