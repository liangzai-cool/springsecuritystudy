package org.xueliang.springsecuritystudy.api.dba;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xueliang.springsecuritystudy.api.BaseApi;
import org.xueliang.springsecuritystudy.model.JSONResponse;

/**
 * 数据库管理员接口
 * @author XueLiang
 * 2017年3月2日 下午11:01:17
 */
@RestController
@RequestMapping("/api/dba/")
public class DBAApi extends BaseApi {

    @RequestMapping({"", "/"})
    public String visiteAdmin() {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.addMsg("say", "this comes from dba api");
        return jsonResponse.toString();
    }
}
