package server.impulse.com.nextlevelweek.services.sendmail

import server.impulse.com.nextlevelweek.models.FeedbackModel
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class SendMailImpl : SendMail {
    private val user = "6a3a6d0d1f5d7e"
    private val password = "94408ff4b9e3a5"
    private val host = "smtp.mailtrap.io"

    private val mailTrapProperties: Properties = Properties().apply {
        put("mail.smtp.auth", "true")
        put("mail.smtp.starttls.enable", "true");
        put("mail.smtp.host", host);
        put("mail.smtp.port", "2525");
    }

    private val session: Session = Session.getInstance(mailTrapProperties, object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(user, password)
        }
    })

    override suspend fun sendMail(feedbackModel: FeedbackModel) {
        val mailMessageContent: String = """
            <div style="font-family: sans-serif; font-size: 16px;">
            <p>Tipo do feedback: ${feedbackModel.type}</p>
            <p>Comentário: ${feedbackModel.comment}</p>
            <img src="data:image/png;base64,${feedbackModel.screenShotBase64}" />
            </div>
        """.trimIndent()

        try {
            val message: MimeMessage = MimeMessage(session).apply {
                setFrom(InternetAddress("Equipe Feedget <oi@feedget.com>"))
                setRecipients(Message.RecipientType.TO, InternetAddress.parse("henriquesantos122@uni9.edu.br"))
                subject = "Novo Feedback"
                setContent(mailMessageContent, "text/html")
            }

            Transport.send(message)

        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }
}