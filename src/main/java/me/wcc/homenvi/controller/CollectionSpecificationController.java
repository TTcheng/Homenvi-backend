package me.wcc.homenvi.controller;

import me.wcc.base.controller.BaseController;
import me.wcc.base.domain.Page;
import me.wcc.base.infra.utils.Results;
import me.wcc.homenvi.entity.CollectionSpecification;
import me.wcc.homenvi.service.CollectionSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 采集项参数 管理 API
 *
 * @author ttchengwang@foxmail.com
 * @date 2019-05-11 14:49:53
 */
@RestController("collectionSpecificationController.v1")
@RequestMapping("/v1/collectionspecifications")
public class CollectionSpecificationController extends BaseController {

    @Autowired
    private CollectionSpecificationService collectionSpecificationService;

    /**
     * 列表
     */
    @ApiOperation(value = "采集项参数列表")
    @GetMapping
    public ResponseEntity<Page<CollectionSpecification>> list(CollectionSpecification collectionSpecification, @ApiIgnore @SortDefault(value = CollectionSpecification.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<CollectionSpecification> list = collectionSpecificationService.pageAndSort(pageRequest, collectionSpecification);
        return Results.success(list);
    }


    /**
     * 详细
     */
    @ApiOperation(value = "采集项参数明细")
    @RequestMapping("/{id}")
    public ResponseEntity<CollectionSpecification> detail(@PathVariable Long id) {
        CollectionSpecification collectionSpecification = collectionSpecificationService.selectByPrimaryKey(id);
        return Results.success(collectionSpecification);
    }

    /**
     * 创建
     */
    @ApiOperation(value = "创建采集项参数")
    @PostMapping
    public ResponseEntity<CollectionSpecification> create(@RequestBody CollectionSpecification collectionSpecification) {
        collectionSpecificationService.insertSelective(collectionSpecification);
        return Results.success(collectionSpecification);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改采集项参数")
    @PutMapping
    public ResponseEntity<CollectionSpecification> update(@RequestBody CollectionSpecification collectionSpecification) {
        collectionSpecificationService.updateByPrimaryKeySelective(collectionSpecification);
        return Results.success(collectionSpecification);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除采集项参数")
    @DeleteMapping("/{id}")
    public ResponseEntity<CollectionSpecification> remove(@PathVariable Long id) {
        collectionSpecificationService.deleteByPrimaryKey(id);
        return Results.success();
    }

}
