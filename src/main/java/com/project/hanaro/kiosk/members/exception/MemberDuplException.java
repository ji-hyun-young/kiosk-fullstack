package com.project.hanaro.kiosk.members.exception;

import com.project.hanaro.kiosk.common.exception.ValueInvalidException;

public class MemberDuplException extends ValueInvalidException {
    public MemberDuplException() {
        super("Duplicated Member Value");
    }
}
