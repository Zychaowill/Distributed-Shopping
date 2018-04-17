package com.taotaox.portal.bo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchResult {

	private Long recordCount;
	private List<Item> itemList;
	private long pageCount;
	private long curPage;
}
