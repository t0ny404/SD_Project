package sd.project.Service.Mappers;

public interface Mapper<T, DTO> {

    DTO convertToDTO();
    T convertFromDTO(DTO dto);
}
