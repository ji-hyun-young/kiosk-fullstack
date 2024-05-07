package com.project.hanaro.kiosk.members.exception;

import com.project.hanaro.kiosk.common.exception.ValueInvalidException;

public class MemberInvalidException extends ValueInvalidException {
    public MemberInvalidException() {
       super("Duplicated Member Value");
    }
}
