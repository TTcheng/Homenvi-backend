package me.wcc.homenvi.controller;

import me.wcc.base.controller.BaseController;
import me.wcc.base.domain.Page;
import me.wcc.base.infra.utils.Results;
import me.wcc.homenvi.entity.CollectionWarning;
import me.wcc.homenvi.service.CollectionWarningService;
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
 * @date 2019-05-11 15:20:57
 */
@RestController("collectionWarningController.v1")
@RequestMapping("/v1/collectionwarnings")
public class CollectionWarningController extends BaseController {

    @Autowired
    private CollectionWarningService collectionWarningService;

    /**
     * 列表
     */
    @ApiOperation(value = "采集警告列表")
    @GetMapping
    public ResponseEntity<Page<CollectionWarning>> list(CollectionWarning collectionWarning, @ApiIgnore @SortDefault(value = CollectionWarning.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest){
        Page<CollectionWarning> list = collectionWarningService.pageAndSort(pageRequest, collectionWarning);
        return Results.success(list);
    }


    /**
     * 详细
     */
    @ApiOperation(value = "采集警告明细")
    @RequestMapping("/{id}")
    public ResponseEntity<CollectionWarning> detail(@PathVariable Long id) {
        CollectionWarning collectionWarning = collectionWarningService.selectByPrimaryKey(id);
        return Results.success(collectionWarning);
    }

    /**
     * 创建
     */
    @ApiOperation(value = "创建采集警告")
    @PostMapping
    public ResponseEntity<CollectionWarning> create(@RequestBody CollectionWarning collectionWarning) {
            collectionWarningService.insertSelective(collectionWarning);
        return Results.success(collectionWarning);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改采集警告")
    @PutMapping
    public ResponseEntity<CollectionWarning> update(@RequestBody CollectionWarning collectionWarning) {
            collectionWarningService.updateByPrimaryKeySelective(collectionWarning);
        return Results.success(collectionWarning);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除采集警告")
    @DeleteMapping("/{id}")
    public ResponseEntity<CollectionWarning> remove(@PathVariable Long id) {
            collectionWarningService.deleteByPrimaryKey(id);
        return Results.success();
    }

}
