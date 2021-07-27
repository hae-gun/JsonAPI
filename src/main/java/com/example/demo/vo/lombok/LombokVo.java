package com.example.demo.vo.lombok;

import com.sun.istack.NotNull;
import lombok.*;

@Builder
@Getter
public class LombokVo {
    @NotNull
    private long id;
    private String name;
}
