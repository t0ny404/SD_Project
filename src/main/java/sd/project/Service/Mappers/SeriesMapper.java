package sd.project.Service.Mappers;

import sd.project.Model.Series;
import sd.project.Service.DTO.SeriesDTO;

public class SeriesMapper implements Mapper<Series, SeriesDTO> {

    private Series series;

    public SeriesMapper() {}

    public SeriesMapper(Series series) {
        this.series = series;
    }


    public Series convertFromDTO(SeriesDTO seriesDTO) {
        Series series =  new Series();
        series.setTitle(seriesDTO.getTitle());
        series.setOngoing(true);

        return series;
    }

    public SeriesDTO convertToDTO() {
        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setTitle(series.getTitle());
        seriesDTO.setId(series.getId());
        seriesDTO.setCover(series.getCover());
        seriesDTO.setPublisherLogo(series.getPublisher().getPicture());
        seriesDTO.setRating(series.getRating().name());
        seriesDTO.setWriter(series.getWriter().getName());
        return seriesDTO;
    }

    public SeriesDTO convertToDTOWithoutRating() {
        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setTitle(series.getTitle());
        seriesDTO.setId(series.getId());
        seriesDTO.setCover(series.getCover());
        seriesDTO.setPublisherLogo(series.getPublisher().getPicture());
        seriesDTO.setWriter(series.getWriter().getName());
        return seriesDTO;
    }
}
