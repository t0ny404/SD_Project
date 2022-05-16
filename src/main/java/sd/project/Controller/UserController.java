package sd.project.Controller;

import sd.project.Service.DTO.RegisterDTO;
import sd.project.Service.DTO.ResponseDTO;
import sd.project.Service.DTO.UserDTO;
import sd.project.Service.UserService;
import sd.project.Service.Utils.Severity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private final Logger logger = LogManager.getLogger();
    @Autowired
    private UserService userService;


    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO) {
        try {
            logger.info("user to register: {}", registerDTO.getUsername());
            userService.register(registerDTO);
        } catch(Exception e) {
            logger.error("Could not register user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage(), Severity.FAILURE));
        }
        logger.info("User registered");
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO("User registered!", Severity.SUCCESS));
    }

    @GetMapping("current")
    @ResponseBody
    public UserDTO getUser(@AuthenticationPrincipal User user) {
        return userService.getCurrentUser(user);
    }
}
