package me.wcc.homenvi.controller;

import io.swagger.annotations.Api;
import me.wcc.base.controller.BaseController;
import me.wcc.base.domain.Page;
import me.wcc.base.infra.utils.Results;
import me.wcc.homenvi.entity.Notification;
import me.wcc.homenvi.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 管理 API
 *
 * @author chuncheng.wang@hand-china.com
 * @date 2019-04-08 17:38:52
 */
@Api(tags = "通知管理")
@RestController("notificationController")
@RequestMapping("/homenvi/notifications")
public class NotificationController extends BaseController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 列表
     */
    @ApiOperation(value = "通知列表")
    @GetMapping
    public ResponseEntity<Page<Notification>> list(Notification notification, @ApiIgnore @SortDefault(value = Notification.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Notification> list = notificationService.pageAndSort(pageRequest, notification);
        return Results.success(list);
    }


    /**
     * 详细
     */
    @ApiOperation(value = "通知明细")
    @GetMapping("/{id}")
    public ResponseEntity<Notification> detail(@PathVariable Long id) {
        Notification notification = notificationService.selectByPrimaryKey(id);
        return Results.success(notification);
    }

    /**
     * 创建
     */
    @ApiOperation(value = "创建通知")
    @PostMapping
    public ResponseEntity<Notification> create(@RequestBody Notification notification) {
        notificationService.insertSelective(notification);
        return Results.success(notification);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改通知")
    @PutMapping
    public ResponseEntity<Notification> update(@RequestBody Notification notification) {
        notificationService.updateByPrimaryKeySelective(notification);
        return Results.success(notification);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除通知")
    @DeleteMapping("/{id}")
    public ResponseEntity<Notification> remove(@PathVariable Long id) {
        notificationService.deleteByPrimaryKey(id);
        return Results.success();
    }

}
