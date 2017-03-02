package com.car.mp.util;

/**
 * 常量定义
 *
 * @author wangjh7
 * @date 2016-05-15
 */
public interface Constant {

    enum ErrorCode {

        /**
         * 正常返回状态
         */
        SUCCESS(0, "success"),
        /**
         * 异常返回状态
         */
        ERROR(10001, "fail"),
        /**
         * 参数错误
         */
        ERROR_PARAMETER(10101, "参数错误"),
        /**
         * DB错误
         */
        ERROR_DATABASE(10201, "DB错误"),
        /**
         * 权限错误
         */
        ERROR_AUTHORITY(10301, "权限错误"),
        /**
         * 其他、系统、未知错误
         */
        ERROR_OTHER(10999, "系统错误");

        private Integer code;
        private String message;

        private ErrorCode(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    enum WxMsgType {
        // 错误类型
        WX_MSG_TYPE_TEXT("text"),
        WX_MSG_TYPE_LINK("link");

        private String name;

        private WxMsgType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
