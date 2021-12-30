package cn.example.ddd.core.domain.vo

import org.springframework.core.convert.converter.Converter

interface AbstractR2dbcConverter<S, T> : Converter<S, T>
