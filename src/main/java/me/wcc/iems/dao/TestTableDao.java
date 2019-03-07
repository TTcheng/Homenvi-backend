package me.wcc.iems.dao;

import me.wcc.iems.entity.TestTable;
import org.apache.ibatis.annotations.Mapper;

import io.choerodon.mybatis.common.BaseMapper;

/**
 * @author chuncheng.wang@hand-china.com 19-3-6 下午8:46
 */
@Mapper
public interface TestTableDao extends BaseMapper<TestTable> {
}
