package ass.management.admin.common.exception;

import ass.management.admin.common.exception.base.BusinessException;

/**
 * 用户已存在
 */
public class UserExistException extends BusinessException {

    public UserExistException(String message) {
        super(message);
    }

}
