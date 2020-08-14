package com.wisely.ch9_4;

import static java.lang.System.getProperty;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.file.Files;
import org.springframework.integration.dsl.mail.Mail;
import org.springframework.integration.feed.inbound.FeedEntryMessageSource;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.scheduling.PollerMetadata;

import com.rometools.rome.feed.synd.SyndEntry;

@SpringBootApplication
public class Ch94Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch94Application.class, args);
    }

    @Value("https://spring.io/blog.atom") // 1通过@value注解自动获得https://spring.io/blog.atom的资源
    Resource resource;

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() { // 2使用Fluent API和Pollers配置默认的轮询方式
        return Pollers.fixedRate(500).get();
    }

    @Bean
    public FeedEntryMessageSource feedMessageSource() throws IOException { // 3FeedEntryMessageSource实际为feed：inbound-channel-adapter，此处即构造feed的入站通道适配器作为数据输入
        FeedEntryMessageSource messageSource = new FeedEntryMessageSource(resource.getURL(), "news");
        return messageSource;
    }

    @Bean
    public IntegrationFlow myFlow() throws IOException {
        return IntegrationFlows.from(feedMessageSource()) // 4流程从from方法开始
                               .<SyndEntry, String> route(payload -> payload.getCategories().get(0).getName(), // 5通过路由方法route来选择路由，消息体（payload）的类型为SyndEntry，作为判断条件的类型为String，判断的值是通过payload获得的分类（Categroy）
                                                          mapping -> mapping.channelMapping("releases", "releasesChannel") // 6通过不同分类的值转向不同的消息通道，若分类为releases，则转向releasesChannel；若分类为engineering，则转向engineeringChannel；若分类为news，则转向newsChannel
                                                                            .channelMapping("engineering", "engineeringChannel")
                                                                            .channelMapping("news", "newsChannel"))
                               .get(); // 7通过get方法获得IntegrationFlow实体，配置为Spring的Bean
    }

    @Bean
    public IntegrationFlow releasesFlow() {
        return IntegrationFlows.from(MessageChannels.queue("releasesChannel", 10)) // 1从消息通道releasesChannel开始获取数据
                               .<SyndEntry, String> transform(payload -> "《" + payload.getTitle() + "》 " + payload.getLink() + getProperty("line.separator")) // 2使用transform方法进行数据转换。payload类型为SyndEntry，将其转换为字符串类型，并自定义数据的格式
                               .handle(Files.outboundAdapter(new File("e:/springblog")) // 3用handle方法处理file的出站适配器。Files类是由Spring Integration Java DSL提供的Fluent API用来构造文件输出的适配器
                                            .fileExistsMode(FileExistsMode.APPEND) // 4
                                            .charset("UTF-8") // 5
                                            .fileNameGenerator(message -> "releases.txt") // 6
                                            .get())
                               .get();
    }

    @Bean
    public IntegrationFlow engineeringFlow() {
        return IntegrationFlows.from(MessageChannels.queue("engineeringChannel", 10))
                               .<SyndEntry, String> transform(payload -> "《" + payload.getTitle() + "》 " + payload.getLink() + getProperty("line.separator"))
                               .handle(Files.outboundAdapter(new File("e:/springblog"))
                                            .fileExistsMode(FileExistsMode.APPEND)
                                            .charset("UTF-8")
                                            .fileNameGenerator(message -> "engineering.txt")
                                            .get())
                               .get();
    }

    @Bean
    public IntegrationFlow newsFlow() {
        return IntegrationFlows.from(MessageChannels.queue("newsChannel", 10))
                               .<SyndEntry, String> transform(payload -> "《" + payload.getTitle() + "》 " + payload.getLink() + getProperty("line.separator"))
                               .enrichHeaders( // 1通过enricherHeader来增加消息头的信息
                                       Mail.headers() // 2邮件发送的相关信息通过Spring Integration Java DSL提供的Mail的headers方法来构造
                                           .subject("来自Spring的新闻")
                                           .to("wisely-man@126.com")
                                           .from("wisely-man@126.com")) 
                               .handle(Mail.outboundAdapter("smtp.126.com") // 使用handle方法来定义邮件发送的出站适配器，使用Spring Integration Java DSL提供的Mail.outboundAdapter来构造，这里使用wisely-man@126.com邮箱向自己发送邮件
                                           .port(25)
                                           .protocol("smtp")
                                           .credentials("wisely-man@126.com", "******") 
                                           .javaMailProperties(p -> p.put("mail.debug", "false")), e -> e.id("smtpOut"))
                               .get();
    }

}
