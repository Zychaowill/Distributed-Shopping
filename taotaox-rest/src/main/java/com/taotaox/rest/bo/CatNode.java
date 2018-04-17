package com.taotaox.rest.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CatNode implements Serializable {

	private static final long serialVersionUID = 1349026004936623138L;
	@JsonProperty("u")
	private String url;
	@JsonProperty("n")
	private String name;
	@JsonProperty("i")
	private List<?> item;
}
