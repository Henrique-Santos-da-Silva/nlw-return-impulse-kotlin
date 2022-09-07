package server.impulse.com.nextlevelweek.repositories

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import server.impulse.com.nextlevelweek.database.Feedback
import server.impulse.com.nextlevelweek.models.FeedbackModel
import server.impulse.com.nextlevelweek.utils.dbQuery


class FeedbackRepositoryImpl : FeedbackRepository {

    override suspend fun createFeedback(feedback: FeedbackModel) {
        dbQuery {
            Feedback.insert {
                it[type] = feedback.type
                it[comment] = feedback.comment
                it[screenshot] = feedback.screenShot
            }
        }
    }

    override suspend fun getAllFeedbacks(): List<FeedbackModel> {
        val feedbacks: List<FeedbackModel> = dbQuery {
            Feedback.selectAll().map { toFeedbackModel(it) }
        }

        return feedbacks
    }

    private fun toFeedbackModel(row: ResultRow): FeedbackModel {
        return FeedbackModel(
            id = row[Feedback.id].toString(),
            type = row[Feedback.type],
            comment = row[Feedback.comment],
            screenShot = row[Feedback.screenshot]
        )
    }
}