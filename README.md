# DDD Example

[![CircleCI](https://circleci.com/gh/efsn/ddd-example/tree/master.svg?style=svg)](https://circleci.com/gh/efsn/ddd-example/tree/master)
[![Drone](https://cloud.drone.io/api/badges/efsn/ddd-example/status.svg)](https://cloud.drone.io/efsn/ddd-example)
[![Build Status](https://img.shields.io/github/workflow/status/efsn/ddd-example/CI)](https://github.com/efsn/ddd-example)
[![GitHub release](https://img.shields.io/github/release/efsn/ddd-example.svg)](https://gitHub.com/efsn/ddd-example/releases)
[![Docker Pulls](https://img.shields.io/docker/pulls/elmi/ddd-example.svg)](https://hub.docker.com/r/elmi/ddd-example)
<!-- [![GitHub stars](https://img.shields.io/github/stars/efsn/ddd-example.svg)](https://github.com/efsn/ddd-example/stargazers) -->
<!-- [![GitHub forks](https://img.shields.io/github/forks/efsn/ddd-example.svg)](https://github.com/efsn/ddd-example/network/members) -->
[![Change log](https://img.shields.io/badge/change%20log-%E2%96%A4-e53994.svg)](https://gitHub.com/efsn/ddd-example/releases)
[![codecov](https://codecov.io/gh/efsn/ddd-example/branch/master/graph/badge.svg)](https://codecov.io/gh/efsn/ddd-example)
[![Open Source Helpers](https://www.codetriage.com/efsn/ddd-example/badges/users.svg)](https://www.codetriage.com/efsn/ddd-example)

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
git config --local user.email 'xxx@domain.com'
```

or config globally:

```
git config --local user.name 'Lastname Firstname'
git config --local user.email 'xxx@domain.com'
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

Pipeline Stage
- Git
- Unit Test
- Code Analysis
- Test Coverage
- Build image
- Upload image
- Deploy
- Image scan `grype`

**Badges**
- Test
- Coverage
