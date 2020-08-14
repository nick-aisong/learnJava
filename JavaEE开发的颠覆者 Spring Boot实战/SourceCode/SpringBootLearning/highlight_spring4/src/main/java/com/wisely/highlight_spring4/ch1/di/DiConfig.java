package com.wisely.highlight_spring4.ch1.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // 1@Configuration声明当前类是一个配置类，在后面1.3.2节的Java配置中有更详细的说明
@ComponentScan("com.wisely.highlight_spring4.ch1.di") // 2使用@ComponentScan，自动扫描包名下所有使用@Service、@Component、@Repository和@Controller的类，并注册为Bean
public class DiConfig {

}
