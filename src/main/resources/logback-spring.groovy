import ch.qos.logback.classic.AsyncAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.LevelFilter
import ch.qos.logback.classic.filter.ThresholdFilter
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.hook.DelayingShutdownHook
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import kotlin.text.Charsets

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO

scan("180 seconds")
def LOG_PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] [%X{uniqueTrackingNumber}] %-5level %logger{64} - %msg%n"

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "${LOG_PATTERN}"
        charset = Charsets.UTF_8
    }
    filter(LevelFilter) {
        level = DEBUG
//        onMatch = "ACCEPT"
//        onMismatch = "DENY"
    }
}

appender("FILE", RollingFileAppender) {
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "logs/all.%d{yyyy-MM-dd}.%i.log"
        maxHistory = 30
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = "64MB"
        }
    }
    encoder(PatternLayoutEncoder) {
        pattern = "${LOG_PATTERN}"
        charset = Charsets.UTF_8
    }
    filter(ThresholdFilter) {
        level = INFO
    }
}

appender("ASYNC", AsyncAppender) {
    discardingThreshold = 0
    queueSize = 256
    appenderRef("FILE")
}

addShutdownHook { DelayingShutdownHook }
root(INFO, ["CONSOLE", "ASYNC"])