package me.wcc.homenvi.controller;

import me.wcc.base.controller.BaseController;
import me.wcc.base.domain.Page;
import me.wcc.base.infra.utils.Results;
import me.wcc.homenvi.entity.WarningHistory;
import me.wcc.homenvi.service.WarningHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 采集警告 管理 API
 *
 * @author ttchengwang@foxmail.com
 * @date 2019-05-12 10:39:52
 */
@RestController("warningHistoryController.v1")
@RequestMapping("/v1/warninghistorys")
public class WarningHistoryController extends BaseController {

    @Autowired
    private WarningHistoryService warningHistoryService;

    /**
     * 列表
     */
    @ApiOperation(value = "采集警告列表")
    @GetMapping
    public ResponseEntity<Page<WarningHistory>> list(WarningHistory warningHistory, @ApiIgnore @SortDefault(value = WarningHistory.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest){
        Page<WarningHistory> list = warningHistoryService.pageAndSort(pageRequest, warningHistory);
        return Results.success(list);
    }


    /**
     * 详细
     */
    @ApiOperation(value = "采集警告明细")
    @RequestMapping("/{id}")
    public ResponseEntity<WarningHistory> detail(@PathVariable Long id) {
        WarningHistory warningHistory = warningHistoryService.selectByPrimaryKey(id);
        return Results.success(warningHistory);
    }

    /**
     * 创建
     */
    @ApiOperation(value = "创建采集警告")
    @PostMapping
    public ResponseEntity<WarningHistory> create(@RequestBody WarningHistory warningHistory) {
            warningHistoryService.insertSelective(warningHistory);
        return Results.success(warningHistory);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改采集警告")
    @PutMapping
    public ResponseEntity<WarningHistory> update(@RequestBody WarningHistory warningHistory) {
            warningHistoryService.updateByPrimaryKeySelective(warningHistory);
        return Results.success(warningHistory);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除采集警告")
    @DeleteMapping("/{id}")
    public ResponseEntity<WarningHistory> remove(@PathVariable Long id) {
            warningHistoryService.deleteByPrimaryKey(id);
        return Results.success();
    }

}
