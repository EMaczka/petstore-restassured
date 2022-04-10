package com.epam;

import com.epam.entities.Order;
import com.epam.entities.OrderStatus;
import com.epam.log.Log;
import com.epam.steps.StoreServiceSteps;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoreServiceTest {
    Order order = createOrder();

    @Test
    public void createOrderTest() {
        Log.info("Order creation test starts...");
        Response responseCreatedOrder = StoreServiceSteps.createOrder(order);
        Assert.assertEquals(responseCreatedOrder.getStatusCode(), 200
                , "Incorrect order status code");
        Assert.assertEquals(responseCreatedOrder.as(Order.class).getId(), order.getId()
                , "Order ID is incorrect");
        Log.info(order + " created successfully!");
    }

    @Test
    public void getOrderByPetIdTest() {
        Log.info("Getting order by pet ID test starts...");
        Response responseCreatedOrder = StoreServiceSteps.createOrder(order);
        Response responseGetOrderByPetId = StoreServiceSteps.getOrderByPetId(order.getPetId());
        Assert.assertEquals(responseGetOrderByPetId.getStatusCode(), 200
                , "Status code is not as expected");
        Assert.assertEquals(responseGetOrderByPetId.as(Order.class).getId(), order.getId(),
                "Order ID is incorrect");
        Log.info(order + " retrieved!");
    }

    @Test
    public void deleteOrderTest() {
        Log.info("Delete order by ID test starts...");
        Response responseCreatedOrder = StoreServiceSteps.createOrder(order);
        StoreServiceSteps.deleteOrderById(order.getId());
        Response responseDeletedOrder = StoreServiceSteps.getOrderByPetId(order.getPetId());
        Assert.assertEquals(responseDeletedOrder.getStatusCode(), 404
                , "Order was not deleted");
        Log.info(order + " deleted successfully");
    }

    private Order createOrder() {
        return new Order()
                .setId(1)
                .setPetId(1)
                .setQuantity(1)
                .setShipmentDate("2022-04-10 14:40")
                .setStatus(OrderStatus.placed.toString())
                .setComplete(true);
    }
}