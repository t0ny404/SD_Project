package sd.project.Controller;

import sd.project.Service.ActiveCart;
import sd.project.Service.DTO.IssueDTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(path="/cart")
public class CartController {

    private final Logger logger = LogManager.getLogger();

    @PutMapping("add")
    public void add(@RequestBody IssueDTO issueDTO) {
        ActiveCart.getCart().add(issueDTO);
        logger.info("Adding issue {}", issueDTO.getTitle());
    }

    @PutMapping("remove")
    public void remove(@RequestBody IssueDTO issueDTO) {
        ActiveCart.getCart().remove(issueDTO);
        logger.info("Removed 1 buc. of food {} from cart", issueDTO.getTitle());
    }

    @GetMapping("all")
    @ResponseBody
    public List<IssueDTO> getAll() {
        return ActiveCart.getCart().getAll();
    }

    @GetMapping("total")
    @ResponseBody
    public Integer getTotal() {
        logger.info("Cart total: {}", ActiveCart.getCart().getTotal());
        return ActiveCart.getCart().getTotal();
    }
}
