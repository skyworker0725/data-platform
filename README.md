data-platform
===============

## 数据/报表统一配置及展示平台
#### 项目说明
 * 支撑运营相关报表及数据统一配置及展示
 * 支持报表数据、数据源、报表展示方式、报表查询条件自定义

#### 技术说明
 * Spring Framework
 * Spring MVC
 * Mybatis及通用Mapper组件
 * PageHelper物理分页插件
 * Groovy

#### 开发环境
 * Eclipse / Intellij IDEA
 * JDK 1.7及以上
 * git clone工程至本地后用IDE以MAVEN工程方式进行导入即可
 * 因工程为JAVA EE项目，故需要配置本地的JAVA EE容器/服务器，例如TOMCAT
 * 涉及到GET请求中文参数问题，需要在TOMCAT的配置文件server.xml中的Connector节点增加URIEncoding="UTF-8"属性
 
#### 编码规范
 * 若无公司级项目编码规范，按现有项目风格执行
 * 若需在数据库表与JAVA实体映射间做到最小配置化，需要数据库库/表名以下划线进行分隔
 * 映射至JAVA实体时，表名映射为首字母大写、下划线后字母大写的驼峰式实体名称
 * 字段映射为首字母小写、下划线后字母大写的驼峰式字段名称
 * 需要主动标识出主键字段（单一/复合主键）
 * 尽量复用现有通用Mapper及通用Service基础功能

#### 扩展目标
 * 支持表格报表、图形报表统一配置化及展示
 * 支持报表ACL权限控制
