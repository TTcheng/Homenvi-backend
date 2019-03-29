package me.wcc.base.exception;

import me.wcc.base.infra.constant.BaseConstants;

/**
 * 乐观锁更新异常
 */
public class OptimisticLockException extends RuntimeException {
    private static final long serialVersionUID = -4289111887481382553L;

    public OptimisticLockException() {
        super(BaseConstants.ErrorCode.OPTIMISTIC_LOCK);
    }

}