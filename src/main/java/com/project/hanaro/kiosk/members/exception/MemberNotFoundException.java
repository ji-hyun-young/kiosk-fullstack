package com.project.hanaro.kiosk.members.exception;

import com.project.hanaro.kiosk.common.exception.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {
    public MemberNotFoundException(Long memberId) {
        super("Could not find Member: " + memberId);
    }
}
