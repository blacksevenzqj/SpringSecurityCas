package ass.management.security.common.exception;

import ass.management.security.common.exception.base.BusinessException;

/**
 * 用户未存在
 */
public class UserNotExistException extends BusinessException {

    public UserNotExistException(String message) {
        super(message);
    }

}
