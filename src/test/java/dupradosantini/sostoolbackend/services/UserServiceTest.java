package dupradosantini.sostoolbackend.services;

import dupradosantini.sostoolbackend.domain.AppUser;
import dupradosantini.sostoolbackend.repositories.UserRepository;
import dupradosantini.sostoolbackend.repositories.WorkspaceMemberRepository;
import dupradosantini.sostoolbackend.services.interfaces.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    WorkspaceMemberRepository workspaceMemberRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(userRepository);
        MockitoAnnotations.openMocks(workspaceMemberRepository);
        userService = new UserServiceImpl(
                userRepository, workspaceMemberRepository);
    }

    @Test
    void userFindAllTest() {

        List<AppUser> workspaceList = new ArrayList<>();

        when(userRepository.findAll()).thenReturn(workspaceList);

        List<AppUser> listReturned = userService.findAllUsers();

        Assertions.assertNotNull(listReturned);
        Assertions.assertNotEquals(listReturned, false);

    }

}