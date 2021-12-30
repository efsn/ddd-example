# DDD Example

![Build Status](https://img.shields.io/github/workflow/status/efsn/ddd-example/CI)
![Docker Pulls](https://img.shields.io/docker/pulls/efsn/ddd-example.svg)
[![GitHub release](https://img.shields.io/github/release/efsn/ddd-example.svg)](https://GitHub.com/efsn/ddd-example/releases/)
![GitHub stars](https://img.shields.io/github/stars/efsn/ddd-example.svg)
![GitHub forks](https://img.shields.io/github/forks/efsn/ddd-example.svg)

A minimal implementation of microservice framework based on `DDD` domain design principal thought.

## Package Structure

```tree
.
├── common               ## 通用模块目录
│   ├── core              # 核心组件
│   ├── core-test         # 核心测试组件
├── devops               ## 基础设施
│   ├── docker            # Docker配置
│   ├── k8s               # K8s配置
├── external             ## 外部模块
├── gradle               ## Gradle配置
├── httpclient           ## API测试工具
├── service              ## 业务模块
├── src                  ## 应用源码目录
├── .editorconfig        # Editorconfig
├── .gitattributes       # Git属性
├── .gitignore           # Git ignore
├── build.gradle.kts     # Gradle build配置
├── Dockerfile           # Dockerfile
├── gradlew              # gradlew cmd
├── gradlew.bat          # gradlew bat cmd
├── LICENSE              # License
├── README.md            # README文档
└── settings.gradle.kts  # gradle settings配置
```

## Develop Convention
- 开发规范
- 提交规范
-

### Git Workflow

> 请使用 `git pull -r --recurse-submodules` 同步代码

#### git-commit convention

##### Config user.name & user.email

config in repo locally:

```
git config --local user.name 'Lastname Firstname'
git config --local user.email 'xxx@thoughtworks.com'
```

or config globally:

```
git config --local user.name 'Lastname Firstname'
git config --local user.email 'xxx@thoughtworks.com'
```

##### Git commit message

```
git message factor
```

***TODO***
- 分支管理
- commit message convention
- 编码风格、校验、扫描、安全
  - ktlint/checkstyle
  -
- secrets -> key vault/configmap
- ~~去掉DTO~~
- GitHub源码泄露安全扫描
- 敏感数据安全
- 日志汇总
- 异常报警
  - 遵循logstash、opentracing等标准日志实践，并对关键数据进行脱敏处理
- 安全扫描
  - SonarQube、FindBugs
  - CI
  - 依赖安全扫描
  - 镜像安全扫描
- 密钥管理（KV）
  - 如Talisman、git-secrets
- 契约测试
- Service Mock UT
- Repo UT
- pg schema usage?
- kustomize
-

### Security

```
<!--  plugin -->
  "project-report"
  "com.diffplug:spotless:6.0.4"
  "com.github.johnrengelman.shadow:6.1.0"
  "org.sonarqube:3.3"
```



