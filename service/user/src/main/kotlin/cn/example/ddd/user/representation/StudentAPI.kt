package cn.example.ddd.user.representation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/students")
class StudentAPI {

    @GetMapping("/{sid}/courses/{cid}")
    fun getCourse(
        @PathVariable sid: String,
        @PathVariable cid: String
    ): Course = TODO()

    @GetMapping("{sid}/courses/{cid}/examinations/{eid}/submission")
    fun getSubmission(
        @PathVariable sid: String,
        @PathVariable cid: String,
        @PathVariable eid: String
    ): Submission = TODO()

    @GetMapping("{sid}/courses/{cid}/examinations/{eid}/grading")
    fun getExaminationScore(
        @PathVariable sid: String,
        @PathVariable cid: String,
        @PathVariable eid: String
    ): String = TODO()

    @GetMapping("{sid}/courses/{cid}/grading")
    fun getExaminationScore(
        @PathVariable sid: String,
        @PathVariable cid: String
    ): String = TODO()
}

data class Course(
    val cid: String,
    val name: String,
    val teacher: String,
    val students: List<String>,
    val examinations: List<Examination>,
    val score: String,
    val startDatetime: LocalDateTime,
    val endDatetime: LocalDateTime
)
