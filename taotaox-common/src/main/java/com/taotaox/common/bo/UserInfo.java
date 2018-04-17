package com.taotaox.common.bo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -81009171669227758L;
	private Integer userid;
	private String loginid;
	private String lastname;
	private String firstname;
	private String nickname;
	private String phone;
	private String defaultRole;
}
