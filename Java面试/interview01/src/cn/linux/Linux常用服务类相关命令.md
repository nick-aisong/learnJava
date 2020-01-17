Linux常用服务类相关命令
========

### 常用基本命令-进程类
➢ service (CentOS6)
- 注册在系统中的标准化程序
- 有方便统一的管理方式(常用的方法)
- service 服务名 start
- service 服务名 stop
- service 服务名 restart
- service 服务名 reload
- service 服务名 status

- 查看服务的方法/etc/init.d服务名

- 通过chkconfig命令设置自启动
  - 查看服务 chkconfig --list | grep xxx
  - chkconfig --level 5 服务名 on

➢ 运行级别runlevel(CentOS6)
开机 -> BIOS -> /boot -> init进程 -> 运行级别 -> 运行级对应的服务

查看默认级别: vi /etc/inittab

Linux系统有7种运行级别(runlevel):常用的是级别3和5
- 运行级别0: 系统停机状态，系统默认运行级别不能设为0，否则不能正常启动
- 运行级别1: 单用户工作状态，root权限，用于系统维护，禁止远程登陆
- 运行级别2: 多用户状态(没有NFS),不支持网络
- 运行级别3: 完全的多用户状态(有NFS),登陆后进入控制台命令行模式
- 运行级别4: 系统未使用，保留
- 运行级别5: X11控制台，登陆后进入图形GUI模式
- 运行级别6: 系统正常关闭并重启，默认运行级别不能设为6,否则不能正常启动

➢ systemctl (CentOS7)
- 注册在系统中的标准化程序
- 有方便统一的管理方式(常用的方法)
  - systemctl start 服务名(xxx.service)
  - systemctl restart 服务名(xxxx.service)
  - systemctl stop 服务名(xxxx.service)
  - systemctl reload 服务名(xxxx service)
  - systemctl status 服务名(xxxx.service)
- 查看服务的方法/usr/lib/systemd/system
- 查看服务的命令
  - systemctl list-unit-files
  - systemctl --type service
- 自启动 systemctl enable service_name
- 不自启动 systemctl disable service_name
