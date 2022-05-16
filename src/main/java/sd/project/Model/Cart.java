package sd.project.Model;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "cart")
public class Cart {

    private Integer id;
    private Issue issue;
    private Order order;
    private Integer quantity;


    public Cart() {}

    public Cart(Issue issue, Order order, Integer quantity) {
        this.issue = issue;
        this.order = order;
        this.quantity = quantity;
    }


    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue()
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "issue", referencedColumnName = "id")
    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @ManyToOne
    @JoinColumn(name = "orderr", referencedColumnName = "id")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order orderByOrder) {
        this.order = orderByOrder;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
