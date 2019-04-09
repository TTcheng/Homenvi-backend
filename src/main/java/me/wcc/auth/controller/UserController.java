package me.wcc.auth.controller;

import io.swagger.annotations.Api;
import me.wcc.auth.entity.User;
import me.wcc.auth.service.UserService;
import me.wcc.base.controller.BaseController;
import me.wcc.base.domain.Page;
import me.wcc.base.infra.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 用户管理 API
 *
 * @author chuncheng.wang@hand-china.com
 * @date 2019-04-08 18:35:24
 */
@RestController("userController")
@Api(tags = "用户管理")
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    // todo 接口权限控制

    /**
     * 列表
     */
    @ApiOperation(value = "用户列表")
    @GetMapping
    public ResponseEntity<Page<User>> list(User user, @ApiIgnore @SortDefault(value = User.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<User> list = userService.pageAndSort(pageRequest, user);
        return Results.success(list);
    }


    /**
     * 详细
     */
    @ApiOperation(value = "用户明细")
    @RequestMapping("/{id}")
    public ResponseEntity<User> detail(@PathVariable Long id) {
        User user = userService.selectByPrimaryKey(id);
        return Results.success(user);
    }

    /**
     * 创建
     */
    @ApiOperation(value = "用户创建")
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        userService.insertSelective(user);
        return Results.success(user);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改用户")
    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        userService.updateByPrimaryKeySelective(user);
        return Results.success(user);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public ResponseEntity<User> remove(@PathVariable Long id) {
        userService.deleteByPrimaryKey(id);
        return Results.success();
    }

}
