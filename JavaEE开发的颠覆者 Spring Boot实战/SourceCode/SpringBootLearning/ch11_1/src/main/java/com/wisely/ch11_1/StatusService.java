package com.wisely.ch11_1;

import org.springframework.stereotype.Service;

// 此类无任何特别，仅为改变status的值
@Service
public class StatusService {

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
