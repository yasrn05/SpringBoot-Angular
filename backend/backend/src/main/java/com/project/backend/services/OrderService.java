package com.project.backend.services;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.backend.dtos.OrderDTO;
import com.project.backend.exceptions.DataNotFoundException;
import com.project.backend.models.Order;
import com.project.backend.models.OrderStatus;
import com.project.backend.models.User;
import com.project.backend.repositories.OrderRepository;
import com.project.backend.repositories.UserRepository;
import com.project.backend.responses.OrderResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderReposistory;
    private final ModelMapper modelMapper;

    @Override
    public OrderResponse createOrder(OrderDTO orderDTO) throws Exception {
        // Check userid có tồn tại hay không
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id: " + orderDTO.getUserId()));
        // Convert orderDTO -> Order
        // Dùng thư viện Model Mapper
        // Tạo luồng ánh xạ riêng để kiểm soát việc ánh xạ
        modelMapper.typeMap(OrderDTO.class, Order.class)
                .addMappings(mapper -> mapper.skip(Order::setId));
        // Cập nhật các trường của đơn hàng từ orderDTO
        Order order = new Order();
        modelMapper.map(orderDTO, order);
        order.setUser(user);
        order.setOrderDate(new Date()); // This time
        order.setStatus(OrderStatus.PENDING);
        // Kiểm tra shipping date phải lớn hơn hôm nay
        Date shippingDate = orderDTO.getShippingDate();
        if (shippingDate == null || shippingDate.before(new Date())) {
            throw new DataNotFoundException("Date must be at least today!");
        }
        order.setActive(true);
        orderReposistory.save(order);
        return null;
    }

    @Override
    public OrderResponse getOrder(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrder'");
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderDTO orderDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrder'");
    }

    @Override
    public void deletedOrder(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletedOrder'");
    }

    @Override
    public List<OrderResponse> getAllOrder(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllOrder'");
    }

}
