package com.taotaox.common.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by yachao on 18/1/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EUDataGridResult {

    private long total;
    private List<?> rows;
}
