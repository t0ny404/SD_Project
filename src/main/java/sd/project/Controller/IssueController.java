package sd.project.Controller;

import sd.project.Service.DTO.IssueDTO;
import sd.project.Service.DTO.ResponseDTO;
import sd.project.Service.IssueService;
import sd.project.Service.Utils.Severity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(path="/issue")
public class IssueController {

    private final Logger logger = LogManager.getLogger();
    @Autowired
    private IssueService issueService;


    @GetMapping("all")
    @ResponseBody
    public List<IssueDTO> getFoods() {
        return issueService.getAllIssues();
    }

    @GetMapping("{sId}")
    @ResponseBody
    public List<IssueDTO> getIssuesBySeriesId(@PathVariable Integer sId) {
        logger.info("Issues by series {}", sId);
        return issueService.getIssuesBySeriesId(sId);
    }

    @PostMapping("add")
    public ResponseEntity addIssue(@RequestBody IssueDTO issueDTO) {
        try {
            issueService.add(issueDTO);
        } catch(Exception e) {
            logger.error("Issue {} could not be added because {}", issueDTO.getTitle(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage(), Severity.FAILURE));
        }
        logger.info("Issue {} added", issueDTO.getTitle());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO("Food added!", Severity.SUCCESS));
    }

    @DeleteMapping("remove")
    public ResponseEntity removeIssue(@RequestBody IssueDTO issueDTO) {
        try {
            issueService.remove(issueDTO);
        } catch(Exception e) {
            logger.error("Issue {} could not be removed because {}", issueDTO.getTitle(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage(), Severity.FAILURE));
        }
        logger.info("Issue {} removed", issueDTO.getTitle());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO("Issue removed!", Severity.SUCCESS));
    }
}
