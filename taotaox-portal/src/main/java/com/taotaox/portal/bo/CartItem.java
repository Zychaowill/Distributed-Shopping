package com.taotaox.portal.bo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItem implements Serializable {

	private static final long serialVersionUID = -4830990927500101179L;

	private long id;
	private String title;
	private Integer num;
	private long price;
	private String image;
}
