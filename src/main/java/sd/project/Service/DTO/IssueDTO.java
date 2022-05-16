package sd.project.Service.DTO;

public class IssueDTO {

    private String title;
    private Integer price;
    private Integer admin;
    private Integer id;
    private Integer quantity;
    private String link;
    private String series;
    private String publisher;


    public IssueDTO(String title, Integer price, Integer id, String link, String series, String publisher) {
        this.title = title;
        this.price = price;
        this.link = link;
        this.id = id;
        this.series = series;
        this.publisher = publisher;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer id) {
        this.admin = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IssueDTO) {
            IssueDTO issueDTO = (IssueDTO) obj;
            return issueDTO.getId().equals(id);
        }
        return false;
    }
}
