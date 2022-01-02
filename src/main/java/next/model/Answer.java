package next.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Answer {
    private long answerId;
    private String writer;
    private String contents;
    private String createdDate;
    private long questionId;

    public Answer(long answerId, String writer, String contents, String createdDate, long questionId) {
        this.answerId = answerId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.questionId = questionId;
//        this.createdDate = LocalDateTime.parse(LocalDateTime.now().toString(),
//                DateTimeFormatter.ofPattern("YYYY-MM-DD HH24:MI"));
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
