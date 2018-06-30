package ass.management.security.common.exception;

import ass.management.security.common.exception.base.BusinessException;

/**
 * 手机号码不合法
 */
public class IllegalMobileException extends BusinessException {

    public IllegalMobileException(String message) {
        super(message);
    }

}
