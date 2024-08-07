package dupradosantini.sostoolbackend.services;

import dupradosantini.sostoolbackend.domain.AppUser;
import dupradosantini.sostoolbackend.repositories.UserRepository;
import dupradosantini.sostoolbackend.repositories.WorkspaceMemberRepository;
import dupradosantini.sostoolbackend.services.exceptions.ObjectNotFoundException;
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
import java.util.Optional;

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
    void userFindAllWhenNotFoundAnyRegister() {

        List<AppUser> workspaceList = new ArrayList<>();

        when(userRepository.findAll()).thenReturn(workspaceList);

        List<AppUser> listReturned = userService.findAllUsers();

        Assertions.assertNotNull(listReturned);
        Assertions.assertTrue(listReturned.isEmpty());

    }


    @Test
    void userFindByIdWhenNotFoundOnRepository() {

        AppUser user = new AppUser(1, "Rogerio Santos", "rogerio@gmail.com", "senha1234", null);
        int id = 2;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        ObjectNotFoundException exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            userService.findById(id);
        });

        Assertions.assertEquals("User not found", exception.getMessage());
    }

}