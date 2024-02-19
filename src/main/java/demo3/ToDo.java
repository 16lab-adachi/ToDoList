package demo3;

import java.time.LocalDateTime;

/**
 * @author ADACHI
 */
public class ToDo {
    private int realId;
    private int userId;
    private String title;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime ddl;

    public ToDo() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDdl() {
        return ddl;
    }

    public void setDdl(LocalDateTime ddl) {
        this.ddl = ddl;
    }

    public int getRealId() {
        return realId;
    }

    public void setRealId(int realId) {
        this.realId = realId;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", ddl=" + ddl +
                '}';
    }
}
