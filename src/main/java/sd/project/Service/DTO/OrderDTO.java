package sd.project.Service.DTO;

import java.util.Objects;

public class OrderDTO {

    private Integer id;
    private Integer cId;
    private String issues;
    private Integer price;
    private String date;
    private String status;


    public OrderDTO(Integer id, String issues, Integer price, String date, String status) {
        this.id = id;
        this.issues = issues;
        this.price = price;
        this.date = date;
        this.status = status;
    }

    public OrderDTO(Integer id, Integer cId, Integer price, String date, String status) {
        this.id = id;
        this.cId = cId;
        this.price = price;
        this.date = date;
        this.status = status;
    }

    public OrderDTO() {}


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIssue() {
        return issues;
    }

    public void setIssue(String issue) {
        this.issues = issue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDTO order = (OrderDTO) o;

        return (Objects.equals(id, order.id) && Objects.equals(status, order.status)
                && Objects.equals(price, order.getPrice()) &&
                Objects.equals(date, order.getDate()));
    }
}
