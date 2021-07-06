package com.example.vo.lombok;

import lombok.*;

@Builder
@Getter
public class LombokVo {
    @NonNull
    private long id;
    private String name;
}
