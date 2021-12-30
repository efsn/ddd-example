package cn.example.ddd.core.repository.converter

import cn.example.ddd.core.domain.vo.AbstractR2dbcConverter
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.r2dbc.postgresql.codec.Json
import org.springframework.data.convert.ReadingConverter
import org.springframework.stereotype.Component

@Component
@ReadingConverter
class JsonNodeReadingConverter(private val objectMapper: ObjectMapper) : AbstractR2dbcConverter<Json, JsonNode> {
    override fun convert(source: Json): JsonNode? = objectMapper.readTree(source.asString())
}
