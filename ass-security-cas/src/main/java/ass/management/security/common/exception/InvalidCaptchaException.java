package ass.management.security.common.exception;

import ass.management.security.common.exception.base.BusinessException;

/**
 * 无效验证码
 */
public class InvalidCaptchaException extends BusinessException {

    public InvalidCaptchaException(String message) {
        super(message);
    }

}
