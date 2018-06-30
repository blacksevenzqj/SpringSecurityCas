package ass.management.admin.common.exception;

import ass.management.admin.common.exception.base.BusinessException;

/**
 * 手机号码不合法
 */
public class IllegalMobileException extends BusinessException {

    public IllegalMobileException(String message) {
        super(message);
    }

}
