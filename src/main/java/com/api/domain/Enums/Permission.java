package com.api.domain.Enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    ALLOWED_TO_SEE("allowed:see"),
    ALLOWED_TO_CHANGE("allowed:change"),
    ALLOWED_TO_IMPORT("allowed:import"),
    ALLOWED_TO_ADD_ROLE("allowed:addRole"),
    ALLOWED_TO_SEE_MONEY("allowed:seeMoney");

    private final String permission;
}