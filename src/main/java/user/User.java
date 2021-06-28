package user;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class User {

    private final String firstName;
    private final String lastName;
    private final String nickName;

}
