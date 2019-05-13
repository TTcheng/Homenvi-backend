package me.wcc.homenvi.controller;

import me.wcc.base.controller.BaseController;
import me.wcc.base.domain.Page;
import me.wcc.base.infra.utils.Results;
import me.wcc.homenvi.entity.CollectionLevel;
import me.wcc.homenvi.service.CollectionLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 采集项等级 管理 API
 *
 * @author ttchengwang@foxmail.com
 * @date 2019-05-11 14:49:53
 */
@RestController("collectionLevelController.v1")
@RequestMapping("/v1/collectionlevels")
public class CollectionLevelController extends BaseController {

    @Autowired
    private CollectionLevelService collectionLevelService;

    /**
     * 列表
     */
    @ApiOperation(value = "采集项等级列表")
    @GetMapping
    public ResponseEntity<Page<CollectionLevel>> list(CollectionLevel collectionLevel, @ApiIgnore @SortDefault(value = CollectionLevel.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest){
        Page<CollectionLevel> list = collectionLevelService.pageAndSort(pageRequest, collectionLevel);
        return Results.success(list);
    }


    /**
     * 详细
     */
    @ApiOperation(value = "采集项等级明细")
    @RequestMapping("/{id}")
    public ResponseEntity<CollectionLevel> detail(@PathVariable Long id) {
        CollectionLevel collectionLevel = collectionLevelService.selectByPrimaryKey(id);
        return Results.success(collectionLevel);
    }

    /**
     * 创建
     */
    @ApiOperation(value = "创建采集项等级")
    @PostMapping
    public ResponseEntity<CollectionLevel> create(@RequestBody CollectionLevel collectionLevel) {
            collectionLevelService.insertSelective(collectionLevel);
        return Results.success(collectionLevel);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改采集项等级")
    @PutMapping
    public ResponseEntity<CollectionLevel> update(@RequestBody CollectionLevel collectionLevel) {
            collectionLevelService.updateByPrimaryKeySelective(collectionLevel);
        return Results.success(collectionLevel);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除采集项等级")
    @DeleteMapping("/{id}")
    public ResponseEntity<CollectionLevel> remove(@PathVariable Long id) {
            collectionLevelService.deleteByPrimaryKey(id);
        return Results.success();
    }

}
