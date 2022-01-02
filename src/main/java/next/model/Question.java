package next.model;

import java.util.List;

public class Question {
    private long questionId;
    private String writer;
    private String title;
    private String contents;
    private String createdDate;
    private int countOfAnswer;
    private List<Answer> answerList;

    public Question(long questionId, String writer, String title, String contents, String createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getCountOfAnswer() {
        return countOfAnswer;
    }

    public void setCountOfAnswer(int countOfAnswer) {
        this.countOfAnswer = countOfAnswer;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public void update(Question updateQuestion) {
        this.writer = updateQuestion.writer;
        this.title = updateQuestion.title;
        this.contents = updateQuestion.contents;
        this.createdDate = updateQuestion.createdDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Question other = (Question) obj;
        if(questionId != other.questionId)
            return false;
        if(writer == null) {
            if(other.writer != null)
                return false;
        } else if(!writer.equals(other.writer))
            return false;
        if(title == null) {
            if(other.title != null)
                return false;
        } else if(!title.equals(other.title))
            return false;
        if(contents == null) {
            if(other.contents != null)
                return false;
        } else if(!contents.equals(contents))
            return false;
        if(countOfAnswer != other.countOfAnswer)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Question [" +
                "questionId=" + questionId +
                ", writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", countOfAnswer=" + countOfAnswer + '\'' +
                ", answerList=" + answerList +
                "]";
    }
}

