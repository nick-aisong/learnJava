package com.wisely.ch9_1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisely.ch9_1.domain.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {

	// 这里只需一个根据用户名查出用户的方法
	SysUser findByUsername(String username);

}
