package com.qrs.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PageVO {
    private Long total;
    private List records;
}
