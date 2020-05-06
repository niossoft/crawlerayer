package com.junghuan.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Lotto649NumberRecord {

	/** 開獎日期 */
	private Date date;
	/** 得獎號碼 */
	private List<String> rewardNumberList;
	/** 特別號碼 */
	private String specialNumber;
}
