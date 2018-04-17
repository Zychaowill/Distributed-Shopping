package com.taotaox.rest.bo;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CatResult implements Serializable {

	private static final long serialVersionUID = 3506713532629901046L;

	private List<?> data;
}
