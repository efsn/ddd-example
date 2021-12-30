package cn.example.ddd.user.representation

import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("courses")
class CourseAPI {

    @PostMapping("{cid}/examinations")
    fun createExamination(
        @PathVariable cid: String,
        @RequestBody request: ExaminationCreateRequest
    ): Unit = TODO()

    @PostMapping("{cid}/examinations/{eid}/grading-request/confirmation")
    fun gradeExamination(
        @PathVariable cid: String,
        @PathVariable eid: String,
        @RequestBody request: ExaminationGradeRequest
    ): Unit = TODO()

    @GetMapping("{cid}/examinations/{eid}")
    fun getExamination(
        @PathVariable cid: String,
        @PathVariable eid: String
    ): Examination = TODO()

    @PostMapping("{cid}/examinations/{eid}/notifications-request/confirmation")
    fun sendExaminationNotification(
        @PathVariable cid: String,
        @PathVariable eid: String,
        @RequestBody request: NotificationSendRequest
    ): Unit = TODO()

    @PostMapping("{cid}/examinations/{eid}/attendance-request/confirmation")
    fun attendanceExamination(
        @PathVariable cid: String,
        @PathVariable eid: String,
        @RequestBody request: AttendanceExaminationRequest
    ): Unit = TODO()

    @PostMapping("{cid}/examinations/{eid}/submission-request/confirmation")
    fun submitExamination(
        @PathVariable cid: String,
        @PathVariable eid: String,
        @RequestBody request: SubmissionRequest
    ): Unit = TODO()

    @PostMapping("{cid}/grading-request/confirmation")
    fun gradleCourse(
        @PathVariable cid: String,
        @RequestBody request: CourseGradeRequest
    ): Unit = TODO()
}

data class NotificationSendRequest(
    val studentId: String,
    val message: String
)

data class AttendanceExaminationRequest(
    val studentId: String,
    val dateTime: LocalDateTime
)

data class SubmissionRequest(
    val studentId: String,
    val answerSheet: Submission
)

data class CourseGradeRequest(
    val studentId: String,
    val score: String
)

data class Examination(
    val courseId: String,
    val teacher: String,
    val paper: String,
    val startDatetime: LocalDateTime,
    val endDatetime: LocalDateTime
)

data class Submission(
    val courseId: String,
    val examinationId: String,
    val answers: List<String>,
    val startDatetime: LocalDateTime,
    val commitDateTime: LocalDateTime
)

data class ExaminationGradeRequest(
    val studentId: String,
    val score: String
)

data class ExaminationCreateRequest(
    val courseId: String,
    val teacher: String,
    val paper: String,
    val startDatetime: LocalDateTime,
    val endDatetime: LocalDateTime
)
