package sd.project;

import sd.project.Model.Publisher;
import sd.project.Model.Series;
import sd.project.Model.Utils.Rating;
import sd.project.Model.Writer;
import sd.project.Repository.PublisherRepository;
import sd.project.Repository.SeriesRepository;
import sd.project.Repository.WriterRepository;
import sd.project.Service.DTO.SeriesDTO;
import sd.project.Service.SeriesService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.*;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.Silent.class)
public class SeriesServiceTest {

    @Mock
    private SeriesRepository seriesRepository;
    @Mock
    private PublisherRepository publisherRepository;
    @Mock
    private WriterRepository writerRepository;

    @InjectMocks
    private final SeriesService seriesService = new SeriesService();

    private Series series;
    private SeriesDTO seriesDTO;

    @Captor
    ArgumentCaptor<Series> seriesC;


    @Before
    public void setup() {
        seriesDTO = new SeriesDTO();
        seriesDTO.setTitle("aa");
        seriesDTO.setPublisher(1);
        seriesDTO.setWriter("Stan Lee");
        seriesDTO.setRating(Rating.TEEN.name());
        seriesDTO.setId(1);

        Publisher publisher = new Publisher();
        publisher.setId(1);
        Writer writer = new Writer();
        writer.setName("Stan Lee");

        series = new Series();
        series.setId(1);
        series.setTitle("aa");
        series.setOngoing(true);
        series.setRating(Rating.TEEN);
        series.setPublisher(publisher);
        series.setWriter(writer);
    }

    @Test
    public void successfulAdd() {
        Publisher publisher = new Publisher();
        publisher.setId(1);
        Writer writer = new Writer();
        writer.setName("Stan Lee");

        Mockito.doReturn(publisher).when(publisherRepository).findById(1);
        Mockito.doReturn(Optional.of(writer)).when(writerRepository).findByName("Stan Lee");

        seriesService.add(seriesDTO);

        Mockito.verify(writerRepository, Mockito.never()).save(Mockito.any());
        Mockito.verify(seriesRepository).save(seriesC.capture());
        assertEquals(series.getTitle(), seriesC.getValue().getTitle());
        assertNull(seriesC.getValue().getRating());
    }

    @Test
    public void successfulGetSeriesByName() {
        List<Series> seriesList = new ArrayList<>(Collections.singletonList(series));
        List<SeriesDTO> seriesDTOList = new ArrayList<>((Collections.singletonList(seriesDTO)));

        Mockito.doReturn(seriesList).when(seriesRepository).findByTitleStartingWith("a");

        assertEquals(seriesDTOList, seriesService.getSeriesByName("a"));

    }

    @Test
    public void unsuccessfulGetRestaurantsByName() {
        Mockito.doReturn(new ArrayList<>()).when(seriesRepository).findByTitleStartingWith("c");

        assertTrue(seriesService.getSeriesByName("c").isEmpty());
    }

    @Test
    public void successfulGetAllSeries() {
        Publisher publisher = new Publisher();
        publisher.setId(1);
        Writer writer = new Writer();
        writer.setName("Stan Lee");

        Series series1 = new Series();
        series1.setId(2);
        series1.setTitle("bb");
        series1.setOngoing(true);
        series1.setRating(Rating.EVERYONE);
        series1.setPublisher(publisher);
        series1.setWriter(writer);

        SeriesDTO seriesDTO1 = new SeriesDTO();
        seriesDTO1.setTitle("bb");
        seriesDTO1.setRating(Rating.EVERYONE.name());
        seriesDTO1.setPublisher(1);
        seriesDTO1.setWriter("Stan Lee");
        seriesDTO1.setId(2);

        List<Series> seriesList = new ArrayList<>(Arrays.asList(series, series1));

        List<SeriesDTO> seriesDTOList = new ArrayList<>(Arrays.asList(seriesDTO, seriesDTO1));

        Mockito.doReturn(seriesList).when(seriesRepository).findAll();

        assertEquals(seriesDTOList, seriesService.getAllSeries());
    }
}

