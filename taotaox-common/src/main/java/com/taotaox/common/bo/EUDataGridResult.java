package com.taotaox.common.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by yachao on 18/1/20.
 */
@Getter
@Setter
public class EUDataGridResult {

    private long total;
    private List<?> rows;
}
