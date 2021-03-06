/**
 * Copyright 2014-2019  the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webank.oracle.base.pojo.vo;

/**
 * A-BB-CCC A:error level. <br/>
 * 1:system exception <br/>
 * 2:business exception <br/>
 * B:project number <br/>
 * Oracle-Service:05 <br/>
 * C: error code <br/>
 */
public class ConstantCode {

    /* return success */
    public static final RetCode SUCCESS = RetCode.mark(0, "success");

    /* system exception */
    public static final RetCode SYSTEM_EXCEPTION = RetCode.mark(105000, "system exception");

    /*Business exception */
    public static final RetCode NEW_KEY_STORE_FAIL = RetCode.mark(205000, "create keyStore exception");

    public static final RetCode SEND_TRANSACTION_FAIL = RetCode.mark(205001, "send transaction exception");

    public static final RetCode GROUP_ID_NOT_EXIST = RetCode.mark(205002, "group id not exist");

    public static final RetCode DEPLOY_FAILED = RetCode.mark(205004, "deploy failed");

    public static final RetCode DATA_SIGN_ERROR = RetCode.mark(205003, "data sign error");

    /* param exception */
    public static final RetCode PARAM_EXCEPTION = RetCode.mark(405000, "param exception");

}
