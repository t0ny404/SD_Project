package sd.project.Service;

import sd.project.Model.Issue;
import sd.project.Repository.IssueRepository;
import sd.project.Repository.SeriesRepository;
import sd.project.Service.DTO.IssueDTO;
import sd.project.Service.Mappers.IssueMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Issue service, handling all the logic for the issues.
 */
@Service
public class IssueService {

    private final Logger logger = LogManager.getLogger();
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private SeriesRepository seriesRepository;

    /**
     * Gets all issues.
     *
     * @return a List of IssueDTO
     */
    public List<IssueDTO> getAllIssues() {
        return issueRepository.findAll()
                .stream().map(i -> new IssueMapper(i).convertToDTO()).collect(Collectors.toList());
    }

    /**
     * Gets issues by the series id.
     *
     * @param sId the series id
     * @return the issues belonging to that series as a List of IssueDTO
     */
    public List<IssueDTO> getIssuesBySeriesId(Integer sId) {
        logger.info("Get issues in series {}", sId);
        return issueRepository.findAllBySeries(seriesRepository.findById(sId))
                .stream().map(i -> new IssueMapper(i).convertToDTO()).collect(Collectors.toList());
    }

    /**
     * Adds to the database the desired issue
     *
     * @param issueDTO the issueDTO representing the desired issue to add
     */
    public void add(IssueDTO issueDTO) {
        Issue issue = new IssueMapper().convertFromDTO(issueDTO);
        issue.setSeries(seriesRepository.findById(issueDTO.getAdmin()));
        issueRepository.save(issue);
        logger.info("Current issue to be added: {}", issue.getTitle());
        logger.info("Series of the current issue: {}", issue.getSeries().getTitle());
    }

    /**
     * Deletes from the database the desired issue
     *
     * @param issueDTO the issueDTO representing the desired issue
     */
    public void remove(IssueDTO issueDTO) {
        Issue issue = new IssueMapper().convertFromDTO(issueDTO);
        issue.setId(issueDTO.getId());
        issueRepository.delete(issue);
        logger.info("Current issue to be deleted: {}", issue.getTitle());
    }
}
