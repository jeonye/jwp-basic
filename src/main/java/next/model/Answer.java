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

    public void update(Answer updateAnswer) {
        this.writer = updateAnswer.writer;
        this.contents = updateAnswer.contents;
        this.createdDate = updateAnswer.createdDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Answer other = (Answer) obj;
        if(answerId != other.answerId)
            return false;
        if(writer == null) {
            if(other.writer != null)
                return false;
        } else if(!writer.equals(other.writer))
            return false;
        if(contents == null) {
            if(other.contents != null)
                return false;
        } else if(!contents.equals(other.contents))
            return false;
        if(questionId != other.questionId)
            return false;
        return true;
    }
}
