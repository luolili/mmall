package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CartMapper;
import com.mmall.dao.OrderItemMapper;
import com.mmall.dao.OrderMapper;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.*;
import com.mmall.service.IOrderService;
import com.mmall.util.BigDecimalUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;


    public ServerResponse createOrder(Integer userId, Integer shippingId) {
        //从购物车里面获取信息
        List<Cart> cartList = cartMapper.selectCheckedCartByUserId(userId);
        ServerResponse<List<OrderItem>> response = getOrderItem(userId, cartList);
        if (!response.isSuccess()) {
            return response;
        }
        List<OrderItem> orderItemList = response.getData();
        if (CollectionUtils.isEmpty(orderItemList)) {
            return ServerResponse.createByErrorMessage("cart is empty");
        }
        BigDecimal payment = getOrderTotalPrice(orderItemList);
        // 生成订单
        Order order = assembleOrder(userId, shippingId, payment);

        if (order == null) {
            return ServerResponse.createByErrorMessage("生成订单错误");
        }

        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderNo(order.getOrderNo());
        }

        //mybatis 批量插入
        return null;
    }

    private Order assembleOrder(Integer userId, Integer shippingId, BigDecimal payment) {
        Order order = new Order();
        long orderNo = generateOrderNo();
        order.setStatus(Const.OrderStatusEnum.NO_PAY.getCode());
        order.setPostage(0);
        order.setPaymentType(Const.PaymentTypeEnum.ONLINE_PAY.getCode());

        order.setPayment(payment);
        order.setUserId(userId);
        order.setShippingId(shippingId);
        int count = orderMapper.insert(order);
        if (count > 0) {
            return order;
        }
        return null;
    }

    //并发的时候有问题
    private long generateOrderNo() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis + new Random().nextInt(100);
        //return currentTimeMillis + currentTimeMillis % 9;
    }

    private BigDecimal getOrderTotalPrice(List<OrderItem> orderItemList) {
        BigDecimal result = new BigDecimal("0");
        for (OrderItem orderItem : orderItemList) {
            result = BigDecimalUtil.add(result.doubleValue(), orderItem.getTotalPrice().doubleValue())
        }
        return result;
    }

    public ServerResponse<List<OrderItem>> getOrderItem(Integer userId, List<Cart> cartList) {
        List<OrderItem> orderItemList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(cartList)) {
            return ServerResponse.createByErrorMessage("购物车为空");
        }
        for (Cart cart : cartList) {
            OrderItem orderItem = new OrderItem();
            Product product = productMapper.selectByPrimaryKey(cart.getProductId());

            if (product.getStock() != Const.ProductStatusEnum.ON_SALE.getCode()) {
                return ServerResponse.createByErrorMessage("产品：" + product.getName() + "不在售卖状态");
            }
            //校验库存
            if (cart.getQuantity() > product.getStock()) {
                return ServerResponse.createByErrorMessage("产品：" + product.getName() + "库存不足");
            }
            orderItem.setUserId(userId);
            orderItem.setProductId(product.getId());
            orderItem.setProductName(product.getName());
            orderItem.setProductImage(product.getMainImage());
            BigDecimal price = product.getPrice();
            orderItem.setCurrentUnitPrice(price);
            orderItem.setQuantity(cart.getQuantity());
            orderItem.setTotalPrice(BigDecimalUtil.multiply(
                    price.doubleValue(), cart.getQuantity()));
            orderItemList.add(orderItem);
            return ServerResponse.createBySuccess(orderItemList);
        }


    }


    // ----------pay
    @Override
    public ServerResponse pay(Long orderNo, Integer userId, String path) {
        Map<String, String> resultMap = Maps.newHashMap();
        Order order = orderMapper.selectByUserIdOrderNo(userId, orderNo);

        if (order == null) {
            return ServerResponse.createByErrorMessage("该用户没有该订单");
        }
        resultMap.put("orderNo", String.valueOf(order.getOrderNo()));

        //-1 订单系统的唯一订单号, 64个字符以内
        String outTradeNo = order.getOrderNo().toString();
        // -2 订单 标题
        String subject = new StringBuilder()
                .append("mmall ,订单号：")
                .append(outTradeNo).toString();
        // -3 订单总价
        String totalAmount = order.getPayment().toString();
        //-4 不可打折的金额
        String undiscountableAmount = "0";

        //-5 商家的id: 卖家支付宝id， 若他为空，默认为支付宝的商家的pid
        String sellerId = "";
        //-6 订单描述
        String body = new StringBuilder()
                .append(outTradeNo + " 总价：")
                .append(totalAmount).toString();
        //-7 商家操作员id
        String operatorId = "test_operator_id";
        String storeId = "test_store_id";//门店id
        //-8 支付timeout时间
        String timeoutExpress = "120m";

        // 商品的明细
        List<GoodsDetail> orderDetailList = new ArrayList<>();
        List<OrderItem> orderItemList = orderItemMapper.getByUserIdOrderNo(userId, orderNo);

        for (OrderItem orderItem : orderItemList) {

            GoodsDetail goodsDetail = GoodsDetail.newInstance(orderItem.getProductId(),
                    BigDecimalUtil.multiply(orderItem.getCurrentUnitPrice().doubleValue(), 100),
                    orderItem.getQuantity());

            orderDetailList.add(goodsDetail);
        }

        return null;
    }
}
