package sd.project.Service.Mappers;

import sd.project.Model.Issue;
import sd.project.Service.DTO.IssueDTO;

public class IssueMapper implements Mapper<Issue, IssueDTO> {

    private Issue issue;


    public IssueMapper() {}

    public IssueMapper(Issue issue) {
        this.issue = issue;
    }


    public Issue convertFromDTO(IssueDTO issueDTO) {
        Issue issue = new Issue();
        issue.setTitle(issueDTO.getTitle());
        issue.setPrice(issueDTO.getPrice());
        issue.setLinkPreview(issueDTO.getLink());
        return issue;
    }

    public IssueDTO convertToDTO() {
        return new IssueDTO(issue.getTitle(),
                issue.getPrice(),
                issue.getId(),
                issue.getLinkPreview(),
                issue.getSeries().getTitle(),
                issue.getSeries().getPublisher().getPicture());
    }
}
