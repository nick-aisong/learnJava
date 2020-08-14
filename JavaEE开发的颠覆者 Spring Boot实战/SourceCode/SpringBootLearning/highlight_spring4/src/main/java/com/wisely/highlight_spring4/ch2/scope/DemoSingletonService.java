package com.wisely.highlight_spring4.ch2.scope;

import org.springframework.stereotype.Service;

@Service // 1默认为Singleton，相当于@Scope（“singleton”）
public class DemoSingletonService {

}
