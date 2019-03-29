package me.wcc.auth.domain.repository.impl;

import java.util.List;
import java.util.function.Function;

import io.choerodon.mybatis.common.BaseMapper;
import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.service.BaseServiceImpl;

import me.wcc.auth.domain.repository.BaseRepository;
import me.wcc.base.exception.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * BaseRepository实现类
 *
 * @param <T> DataObject
 */
public class BaseRepositoryImpl<T> extends BaseServiceImpl<T> implements BaseRepository<T> {
    @Autowired
    BaseMapper<T> mapper;

    /**
     * 按照主键更新记录，更新非null字段
     *
     * @param record 更新的记录内容
     * @return 更新记录数量，必定大于1
     * @throws OptimisticLockException <strong>当更新记录数量为0并且记录继承自AuditDomain时</strong>，抛出异常记录不存在或者版本不一致
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(T record) {
        int res = super.updateByPrimaryKeySelective(record);
        this.checkOptimisticLock(res, record);
        this.setObjectVersionNumber(false, record);
        return res;
    }

    /**
     * 按照主键更新记录，更新所有字段
     *
     * @param record 更新的记录内容
     * @return 更新记录数量，必定大于1
     * @throws OptimisticLockException <strong>当更新记录数量为0并且记录继承自AuditDomain时</strong>，抛出异常记录不存在或者版本不一致
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(T record) {
        int res = super.updateByPrimaryKey(record);
        this.checkOptimisticLock(res, record);
        this.setObjectVersionNumber(false, record);
        return res;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateOptional(T record, String... optionals) {
        int res = super.updateOptional(record, optionals);
        this.checkOptimisticLock(res, record);
        this.setObjectVersionNumber(false, record);
        return res;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(T record) {
        return super.delete(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Object key) {
        return super.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(T record) {
        int result = super.insert(record);
        this.setObjectVersionNumber(true, record);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(T record) {
        int result = super.insertSelective(record);
        this.setObjectVersionNumber(true, record);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOptional(T record, String... optionals) {
        int result = super.insertOptional(record, optionals);
        this.setObjectVersionNumber(true, record);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<T> batchInsert(List<T> list) {
        this.batchDml(list, this::insert);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<T> batchInsertSelective(List<T> list) {
        this.batchDml(list, this::insertSelective);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<T> batchUpdateByPrimaryKey(List<T> list) {
        this.batchDml(list, this::updateByPrimaryKey);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<T> batchUpdateByPrimaryKeySelective(List<T> list) {
        this.batchDml(list, this::updateByPrimaryKeySelective);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<T> batchUpdateOptional(List<T> list, String... optionals) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        for (T obj : list) {
            this.updateOptional(obj, optionals);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDelete(List<T> list) {
        return this.batchDml(list, this::delete);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteByPrimaryKey(List<T> list) {
        return this.batchDml(list, this::deleteByPrimaryKey);
    }

    /**
     * 检查乐观锁<br>
     * 检测到更新，删除失败时，抛出OptimisticLockException 异常
     *
     * @param updateCount update,delete 操作返回的值
     * @param record      操作参数
     * @author gaokuo.dai@hand-china.com 2018年6月7日
     */
    private void checkOptimisticLock(int updateCount, Object record) {
        if (updateCount == 0 && record instanceof AuditDomain) {
            throw new OptimisticLockException();
        }
    }

    /**
     * 使用通用插入方法插入数据时，默认objectVersionNumber返回1
     *
     * @param insertFlag insert:true update/delete:false
     * @param record     已经插入成功的数据
     * @author yunxiang.zhou01@hand-china.com 2018-06-08 15:10
     */
    private void setObjectVersionNumber(boolean insertFlag, Object record) {
        if (record instanceof AuditDomain) {
            AuditDomain auditDomain = ((AuditDomain) record);
            if (insertFlag) {
                auditDomain.setObjectVersionNumber(DEFAULT_OBJECT_VERSION_NUMBER);
            } else {
                if (auditDomain.getObjectVersionNumber() != null) {
                    auditDomain.setObjectVersionNumber(auditDomain.getObjectVersionNumber() + 1);
                }
            }
        }
    }

    /**
     * 批量DML操作
     *
     * @param list       待操作对象
     * @param dmlCommand dml命令
     * @return 被操作的数量
     */
    private int batchDml(List<T> list, Function<T, Integer> dmlCommand) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        int dealCount = 0;
        for (T obj : list) {
            dealCount += dmlCommand.apply(obj);
        }
        return dealCount;
    }
}
