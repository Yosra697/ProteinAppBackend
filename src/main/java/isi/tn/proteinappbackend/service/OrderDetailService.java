package isi.tn.proteinappbackend.service;

import isi.tn.proteinappbackend.configuration.JwtRequestFilter;
import isi.tn.proteinappbackend.entity.*;
import isi.tn.proteinappbackend.repository.CartRepositoy;
import isi.tn.proteinappbackend.repository.OrderDetailRepository;
import isi.tn.proteinappbackend.repository.ProductRepository;
import isi.tn.proteinappbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService {

    private static final String ORDER_PLACED = "Placed";

    @Autowired
    private OrderDetailRepository orderDetailDao;

    @Autowired
    private ProductRepository productDao;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private CartRepositoy cartDao;

    public List<OrderDetail> getAllOrderDetails(){
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetailDao.findAll().forEach(e -> orderDetails.add(e));

        return orderDetails;
    }

    public List<OrderDetail> getOrderDetails() {
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(currentUser).get();

        return orderDetailDao.findByUser(user);
    }

    public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout) {
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

        for(OrderProductQuantity o: productQuantityList) {
            Product product = productDao.findById(o.getProductId()).get();

            String currentUser = JwtRequestFilter.CURRENT_USER;
            User user= userDao.findById(currentUser).get();

            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACED,
                    product.getProductActualPrice()*o.getQuantity(),
                    product,
                    user);

            if(!isSingleProductCheckout) {
                List<Cart> carts= cartDao.findByUser(user);
                carts.stream().forEach(x -> cartDao.deleteById(x.getCartId()));

            }
            orderDetailDao.save(orderDetail);
        }
    }



}
