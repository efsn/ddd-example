package cn.example.ddd.core.domain.vo

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId

fun LocalDateTime.toOffset(): OffsetDateTime = atOffset(OffsetDateTime.now().offset)

fun OffsetDateTime.toLocal(): LocalDateTime = toZonedDateTime().withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()
