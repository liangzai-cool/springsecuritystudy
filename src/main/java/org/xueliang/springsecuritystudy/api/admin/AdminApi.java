package org.xueliang.springsecuritystudy.api.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xueliang.springsecuritystudy.api.BaseApi;
import org.xueliang.springsecuritystudy.model.JSONResponse;

/**
 * 普通管理员接口
 * @author XueLiang
 * 2017年3月2日 下午11:01:45
 */
@RestController
@RequestMapping("/api/admin/")
public class AdminApi extends BaseApi {

    @RequestMapping({"", "/"})
    public String visiteAdmin() {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.addMsg("say", "this comes from admin api");
        return jsonResponse.toString();
    }
}
