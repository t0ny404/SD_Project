package sd.project.Controller;

import sd.project.Service.DTO.SeriesDTO;
import sd.project.Service.SeriesService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path="/series")
public class SeriesController {

    private final Logger logger = LogManager.getLogger();
    @Autowired
    private SeriesService seriesService;


    @GetMapping({"all", "/"})
    @ResponseBody
    public List<SeriesDTO> getSeries() {
        return seriesService.getAllSeries();
    }

    @GetMapping("notRated")
    @ResponseBody
    public List<SeriesDTO> getSeriesNotRated() {
        return seriesService.getAllSeriesNotRated();
    }

    @GetMapping("{name}")
    @ResponseBody
    public List<SeriesDTO> getSeriesByName(@PathVariable Optional<String> name) {
        if (name.isPresent()) {
            logger.info("Filter by keyword {}", name);
            return seriesService.getSeriesByName(name.get());
        } else {
            logger.warn("No filter applied because keyword to filter by is empty!");
            return getSeries();
        }
    }

    @GetMapping("publisher/{pId}")
    @ResponseBody
    public List<SeriesDTO> getSeriesByPublisher(@PathVariable Integer pId) {
        logger.info("Filter by publisher {}", pId);
        return seriesService.getSeriesByPublisher(pId);
    }

    @PostMapping("add")
    public void add(@RequestBody SeriesDTO seriesDTO) {
        logger.info("Add series with name {}", seriesDTO.getTitle());
        seriesService.add(seriesDTO);
    }

    @PutMapping("rate/{id}")
    public void rate(@PathVariable Integer id, @RequestBody String category) {
        logger.info("Rated {}", category);
        seriesService.rate(id, category);
    }
}
