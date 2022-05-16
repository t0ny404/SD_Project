package sd.project.Service.DTO;


public class UserDTO {

    private Integer id;
    private String name;
    private String type;
    private String picture;


    public UserDTO(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
