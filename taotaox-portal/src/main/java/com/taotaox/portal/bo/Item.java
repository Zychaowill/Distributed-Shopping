package com.taotaox.portal.bo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Item implements Serializable {

	private static final long serialVersionUID = -7064882022559310752L;
	
	private String id;
	private String title;
	private String sellPoint;
	private Long price;
	private String image;
	private String categoryName;
	private String itemDesc;
}
