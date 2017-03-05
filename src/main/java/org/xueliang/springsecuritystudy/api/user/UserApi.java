package org.xueliang.springsecuritystudy.api.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xueliang.springsecuritystudy.api.BaseApi;
import org.xueliang.springsecuritystudy.model.JSONResponse;

/**
 * 用户接口
 * @author XueLiang
 * 2017年3月2日 下午11:00:56
 */
@RestController
@RequestMapping("/api/user/")
public class UserApi extends BaseApi {

    @RequestMapping({"", "/"})
    public JSONResponse visiteAdmin() {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.addMsg("say", "this comes from user api");
        return jsonResponse;
    }
}
