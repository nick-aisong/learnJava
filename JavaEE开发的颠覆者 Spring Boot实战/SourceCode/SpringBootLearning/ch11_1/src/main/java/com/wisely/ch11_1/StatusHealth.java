package com.wisely.ch11_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class StatusHealth implements HealthIndicator {// 1实现HealthIndicator接口并重写health()方法
	@Autowired
	StatusService statusService;

	@Override
	public Health health() {
		String status = statusService.getStatus();
		if (status == null || !status.equals("running")) {
			return Health.down().withDetail("Error", "Not Running").build(); // 2当status的值为非running时构造失败
		}
		return Health.up().build(); // 3其余情况运行成功
	}

}
