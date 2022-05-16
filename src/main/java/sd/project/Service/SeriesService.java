package sd.project.Service;

import sd.project.Model.Publisher;
import sd.project.Model.Series;
import sd.project.Model.Utils.Rating;
import sd.project.Model.Writer;
import sd.project.Repository.PublisherRepository;
import sd.project.Repository.SeriesRepository;
import sd.project.Repository.WriterRepository;
import sd.project.Service.DTO.SeriesDTO;
import sd.project.Service.Mappers.SeriesMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Series service, handling all the logic for the series.
 */
@Service
public class SeriesService {

    private final Logger logger = LogManager.getLogger();
    @Autowired
    private SeriesRepository seriesRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private WriterRepository writerRepository;

    /**
     * Adds to the database the desired series (to the table containing all the series)
     *
     * @param seriesDTO the seriesDTO representing the desired series to add
     */
    public void add(SeriesDTO seriesDTO) {
        Publisher admin = publisherRepository.findById(seriesDTO.getPublisher());

        logger.info("Current publisher: {}", admin.getName());

        Series series = new SeriesMapper().convertFromDTO(seriesDTO);
        series.setPublisher(admin);

        Optional<Writer> writer = writerRepository.findByName(seriesDTO.getWriter());
        if (writer.isEmpty()) {
            Writer nw = new Writer(seriesDTO.getWriter());
            series.setWriter(writerRepository.save(nw));
        }
        else series.setWriter(writer.get());

        seriesRepository.save(series);

        logger.info("New series: {}", series.getTitle());
    }

    /**
     * Gets all the series that are rated.
     *
     * @return all series as a List
     */
    public List<SeriesDTO> getAllSeries() {
        return seriesRepository.findAll().stream().filter(s -> s.getRating() != null)
                .map(s -> new SeriesMapper(s).convertToDTO()).collect(Collectors.toList());
    }

    /**
     * Gets all the series that are not yet rated.
     *
     * @return all series as a List
     */
    public List<SeriesDTO> getAllSeriesNotRated() {
        return seriesRepository.findAll().stream().filter(s -> s.getRating() == null)
                .map(s -> new SeriesMapper(s).convertToDTOWithoutRating()).collect(Collectors.toList());
    }

    /**
     * Gets all the series whose name starts with a given keyword.
     *
     * @param name the keyword to filter series by their name
     * @return the series starting with the given keyword
     */
    public List<SeriesDTO> getSeriesByName(String name) {
        logger.info("Filter by {}", name);
        return seriesRepository.findByTitleStartingWith(name).stream().filter(s -> s.getRating() != null)
                .map(s -> new SeriesMapper(s).convertToDTO()).collect(Collectors.toList());
    }

    /**
     * Gets all the series by their publisher.
     *
     * @param pId the publisher id
     * @return the series as a List of SeriesDTO from the given publisher
     */
    public List<SeriesDTO> getSeriesByPublisher(Integer pId) {
        logger.info("Filter by publisher {}", pId);
        return seriesRepository.findAllByPublisher(publisherRepository.findById(pId))
                .stream().filter(s -> s.getRating() != null)
                .map(s -> new SeriesMapper(s).convertToDTO()).collect(Collectors.toList());
    }

    /**
     * Change the rating of a given series.
     *
     * @param id the id of the series
     * @param category the rating given by the admin
     */
    public void rate(Integer id, String category) {
        logger.info("Rated to {}", category);
        Series series = seriesRepository.findById(id);
        if (category.contains("MATURE"))
            series.setRating(Rating.MATURE);
        else if (category.contains("TEEN"))
            series.setRating(Rating.TEEN);
        else series.setRating(Rating.EVERYONE);
        seriesRepository.save(series);
    }
}
