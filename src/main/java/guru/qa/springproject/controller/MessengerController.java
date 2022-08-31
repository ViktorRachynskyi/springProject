package guru.qa.springproject.controller;

import guru.qa.springproject.domain.SignUpInfo;
import guru.qa.springproject.domain.UserInfo;
import guru.qa.springproject.domain.UserMessage;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@RequestMapping("/api")
@RestController
public class MessengerController {

    private static int counterUsers = 0;
    private static int counterMessages = 0;
    private static final UserInfo[] userInfos = new UserInfo[10];
    private static final UserMessage[] userMessages = new UserMessage[10];

    @PostMapping("/signup")
    @ApiOperation("registration")
    public UserInfo signUp(@RequestBody SignUpInfo signUpInfo) {

        UserInfo user = UserInfo.builder()
                .ID(counterUsers)
                .name(signUpInfo.getName())
                .surname(signUpInfo.getSurname())
                .build();

        userInfos[counterUsers++] = user;

        return user;
    }

    @GetMapping("/user")
    @ApiOperation("searching users")
    @ResponseBody
    public List<Integer> getUsers(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "surname", required = false) String surname) {

        if (name != null && surname != null) {
            return Stream.of(userInfos)
                    .filter(Objects::nonNull)
                    .filter(x -> x.getName().equals(name))
                    .filter(x -> x.getSurname().equals(surname))
                    .map(UserInfo::getID).toList();

        } else if (name != null) {
            return Stream.of(userInfos)
                    .filter(Objects::nonNull)
                    .filter(x -> x.getName().equals(name))
                    .map(UserInfo::getID).toList();

        } else if (surname != null) {
            return Stream.of(userInfos)
                    .filter(Objects::nonNull)
                    .filter(x -> x.getSurname().equals(surname))
                    .map(UserInfo::getID).toList();

        } else {
            return Stream.of(userInfos)
                    .filter(Objects::nonNull)
                    .map(UserInfo::getID).toList();
        }
    }

    @PostMapping("/user/message")
    @ApiOperation("sending message")
    public UserMessage sendMessage(@RequestBody UserMessage newMessage) {

        UserMessage message = UserMessage.builder()
                .message(newMessage.getMessage())
                .userFrom(newMessage.getUserFrom())
                .userTo(newMessage.getUserTo())
                .build();

        userMessages[counterMessages++] = message;

        return message;
    }

    @GetMapping("/user/messages/{id}")
    @ApiOperation("get user messages")
    @ResponseBody
    public List<UserMessage> getUserMessages(@PathVariable("id") String id) {

        return Stream.of(userMessages)
                .filter(Objects::nonNull)
                .filter(x -> x.getUserTo() == 2)
                .toList();
    }
}
