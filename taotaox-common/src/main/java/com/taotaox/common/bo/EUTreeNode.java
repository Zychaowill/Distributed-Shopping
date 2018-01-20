package com.taotaox.common.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by yachao on 18/1/20.
 */
@Getter
@Setter
public class EUTreeNode implements Serializable {
    private static final long serialVersionUID = 4988057146708605881L;

    private Long id;
    private String text;
    private String state;
}
