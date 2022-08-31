package guru.qa.springproject.controller;

import guru.qa.springproject.domain.SignUpInfo;
import guru.qa.springproject.domain.UserInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping("/api")
@RestController
public class MessengerController {

    private static int counter = 0;
    private static final UserInfo[] userInfos = new UserInfo[10];

    @PostMapping("/signup")
    @ApiOperation("registration")
    public UserInfo signUp(@RequestBody SignUpInfo signUpInfo) {

        UserInfo user = UserInfo.builder()
                .ID(counter)
                .name(signUpInfo.getName())
                .surname(signUpInfo.getSurname())
                .build();

        userInfos[counter++] = user;

        return user;
    }

    @GetMapping("/getUsers")
    @ApiOperation("getting list of users")
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
}
