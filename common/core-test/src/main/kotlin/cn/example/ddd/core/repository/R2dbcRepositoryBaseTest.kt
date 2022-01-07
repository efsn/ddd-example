/*
* Copyright (C) 2022 Bug.S.C Chen (efsn.chan@gmail.com).
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      https://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package cn.example.ddd.core.repository

import cn.example.ddd.core.configuration.R2dbcRepositoryTestConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.EnableTransactionManagement

@ActiveProfiles("test")
@SpringBootTest(classes = [R2dbcRepositoryTestApplication::class])
abstract class R2dbcRepositoryBaseTest

@Import(R2dbcRepositoryTestConfiguration::class)
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = ["cn.example.ddd.**.repository"])
class R2dbcRepositoryTestApplication
