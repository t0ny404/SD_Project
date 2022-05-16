package sd.project;

import sd.project.Model.Issue;
import sd.project.Model.Publisher;
import sd.project.Model.Series;
import sd.project.Repository.*;
import sd.project.Service.DTO.IssueDTO;
import sd.project.Service.IssueService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.*;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.Silent.class)
public class IssueServiceTest {

    @Mock
    private IssueRepository issueRepository;
    @Mock
    private SeriesRepository seriesRepository;

    @InjectMocks
    private final IssueService issueService = new IssueService();

    @Captor
    ArgumentCaptor<Issue> issueC;

    private Series series;
    private Issue issue1, issue2;

    @Before
    public void setup() {
        Publisher publisher = new Publisher();
        publisher.setId(1);
        publisher.setName("Stan Lee");
        publisher.setPicture("");

        series =  new Series();
        series.setTitle("aa");
        series.setCover("aa");
        series.setOngoing(true);
        series.setId(1);
        series.setPublisher(publisher);

        issue1 = new Issue();
        issue1.setTitle("aa");
        issue1.setPrice(2);
        issue1.setId(1);
        issue1.setSeries(series);
        issue2 = new Issue();
        issue2.setTitle("bb");
        issue2.setPrice(3);
        issue2.setId(2);
        issue2.setSeries(series);
    }

    @Test
    public void successfulGetAllIssues() {
        Mockito.doReturn(Arrays.asList(issue1, issue2)).when(issueRepository).findAll();
        List<IssueDTO> list = new ArrayList<>(Arrays.asList(
                new IssueDTO("aa", 2, 1, null, "aa", ""),
                new IssueDTO("bb", 3, 2, null, "aa", "")));
        assertEquals(list, issueService.getAllIssues());
    }

    @Test
    public void successfulGetFoodsBySeries() {
        List<IssueDTO> issueDTOList =  new ArrayList<>(Arrays.asList(
                new IssueDTO("aa", 2, 1, null, "aa", ""),
                new IssueDTO("bb", 3, 2, null, "aa", "")));

        Mockito.doReturn(series).when(seriesRepository).findById(1);
        Mockito.doReturn(Arrays.asList(issue1, issue2)).when(issueRepository).findAllBySeries(series);

        assertEquals(issueDTOList, issueService.getIssuesBySeriesId(1));
    }

    @Test
    public void successfulAdd() {
        Mockito.doReturn(series).when(seriesRepository).findById(1);

        IssueDTO issueDTO = new IssueDTO("aa", 2, 1, null, "aa", "");
        issueDTO.setAdmin(1);
        issueService.add(issueDTO);

        Mockito.verify(issueRepository).save(issueC.capture());
        assertEquals(issue1.getTitle(), issueC.getValue().getTitle());
    }

    @Test
    public void successfulRemove() {
        issueService.remove(new IssueDTO("aa", 2, 1, null, "aa", ""));

        Mockito.verify(issueRepository).delete(issueC.capture());
        assertEquals(issue1, issueC.getValue());
    }
}
