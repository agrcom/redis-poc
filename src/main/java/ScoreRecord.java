import com.google.common.base.Preconditions;
import user.UserRecord;

public record ScoreRecord(long score, UserRecord user) {
    public ScoreRecord(long score, UserRecord user) {
        this.score = score;
        this.user = Preconditions.checkNotNull(user, "user.User name must be provided");
    }

    public String print(){
        return """
                 user.User %s achieved %s scores!!
                 It is amazing result!
                 Keep playing  
                """.formatted(this.user, this.score);
    }
}
