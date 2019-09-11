package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.dao.*;
import com.mmall.pojo.*;
import com.mmall.service.IOrderService;
import com.mmall.util.BigDecimalUtil;
import com.mmall.util.DateTimeUtil;
import com.mmall.vo.OrderItemVO;
import com.mmall.vo.OrderProductVO;
import com.mmall.vo.OrderVO;
import com.mmall.vo.ShippingVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.Bidi;
import java.util.*;

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

    @Autowired
    private ShippingMapper shippingMapper;

    @Override
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
        orderItemMapper.batchInsert(orderItemList);
        //减少商品库存
        reduceProductStock(orderItemList);
        clearCart(cartList);
        //返回给前台数据

        return ServerResponse.createBySuccess(assembleOrderVO(order, orderItemList));
    }

    private OrderVO assembleOrderVO(Order order, List<OrderItem> orderItemList) {
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderNo(order.getOrderNo());
        orderVO.setPayment(order.getPayment());
        orderVO.setPaymentType(order.getPaymentType());
        orderVO.setPaymentTypeDesc(
                Const.PaymentTypeEnum.codeOf(order.getPaymentType()).getValue());
        orderVO.setPostage(order.getPostage());
        orderVO.setStatus(order.getStatus());
        orderVO.setStatusDesc(
                Const.OrderStatusEnum.codeOf(order.getStatus()).getValue());
        orderVO.setShippingId(order.getShippingId());
        Shipping shipping = shippingMapper.selectByPrimaryKey(order.getShippingId());
        if (shipping != null) {
            orderVO.setReceiverName(shipping.getReceiverName());
            ShippingVO shippingVO = assambleShippingVO(shipping);
            orderVO.setShippingVO(shippingVO);
        }

        orderVO.setPaymentTime(DateTimeUtil.dateToStr(order.getPaymentTime()));
        orderVO.setCloseTime(DateTimeUtil.dateToStr(order.getCloseTime()));
        orderVO.setCreateTime(DateTimeUtil.dateToStr(order.getCreateTime()));
        orderVO.setSendTime(DateTimeUtil.dateToStr(order.getSendTime()));
        orderVO.setEndTime(DateTimeUtil.dateToStr(order.getEndTime()));
        orderVO.setOrderItemVOList(assembleOrderItemVO(orderItemList));
        return orderVO;
    }

    private List<OrderItemVO> assembleOrderItemVO(List<OrderItem> orderItemList) {
        List<OrderItemVO> orderItemVOList = Lists.newArrayList();
        for (OrderItem orderItem : orderItemList) {
            OrderItemVO orderItemVO = new OrderItemVO();
            orderItemVO.setOrderNo(orderItem.getOrderNo());
            orderItemVO.setProductId(orderItem.getProductId());
            orderItemVO.setProductImage(orderItem.getProductImage());
            orderItemVO.setCurrentUnitPrice(orderItem.getCurrentUnitPrice());
            orderItemVO.setQuantity(orderItem.getQuantity());
            orderItemVO.setTotalPrice(orderItem.getTotalPrice());
            orderItemVO.setCreateTime(DateTimeUtil.dateToStr(orderItem.getCreateTime()));

            orderItemVOList.add(orderItemVO);

        }
        return orderItemVOList;
    }
    private ShippingVO assambleShippingVO(Shipping shipping) {
        ShippingVO vo = new ShippingVO();
        vo.setReceiverName(shipping.getReceiverName());
        vo.setReceiverAddress(shipping.getReceiverAddress());
        vo.setReceiverCity(shipping.getReceiverCity());
        vo.setReceiverProvince(shipping.getReceiverProvince());
        vo.setReceiverZip(shipping.getReceiverZip());
        vo.setReceiverDistrict(shipping.getReceiverDistrict());
        vo.setReceiverPhone(shipping.getReceiverPhone());
        vo.setReceiverMobile(shipping.getReceiverMobile());
        return vo;
    }
    private void clearCart(List<Cart> cartList) {
        for (Cart cart : cartList) {
            cartMapper.deleteByPrimaryKey(cart.getId());
        }
    }

    private void reduceProductStock(List<OrderItem> orderItemList) {
        for (OrderItem orderItem : orderItemList) {
            Product product = productMapper.selectByPrimaryKey(orderItem.getProductId());
            product.setStock(product.getStock() - orderItem.getQuantity());
            productMapper.updateByPrimaryKeySelective(product);
        }

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
            result = BigDecimalUtil.add(result.doubleValue(), orderItem.getTotalPrice().doubleValue());
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
        }
        return ServerResponse.createBySuccess(orderItemList);
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
      /*  List<GoodsDetail> orderDetailList = new ArrayList<>();
        List<OrderItem> orderItemList = orderItemMapper.getByUserIdOrderNo(userId, orderNo);
        for (OrderItem orderItem : orderItemList) {
            GoodsDetail goodsDetail = GoodsDetail.newInstance(orderItem.getProductId(),
                    BigDecimalUtil.multiply(orderItem.getCurrentUnitPrice().doubleValue(), 100),
                    orderItem.getQuantity());

            orderDetailList.add(goodsDetail);
        }*/

        return null;
    }


    @Override
    public ServerResponse<String> cancel(Integer userId, Long orderNo) {
        Order order = orderMapper.selectByUserIdOrderNo(userId, orderNo);

        if (order == null) {
            return ServerResponse.createByErrorMessage("订单不存在");
        }
        if (order.getStatus() != Const.OrderStatusEnum.NO_PAY.getCode()) {
            return ServerResponse.createByErrorMessage("订单已经付款，不能取消");
        }
        Order updatOrder = new Order();
        updatOrder.setId(order.getId());
        updatOrder.setStatus(Const.OrderStatusEnum.CANCELED.getCode());
        int count = orderMapper.updateByPrimaryKeySelective(updatOrder);
        if (count > 0) {
            return ServerResponse.createBySuccess("取消成功");
        }
        return ServerResponse.createByErrorMessage("取消失败");
    }

    //
    @Override
    public ServerResponse getCartProduct(Integer userId) {
        OrderProductVO orderProductVO = new OrderProductVO();
        List<Cart> cartList = cartMapper.selectCheckedCartByUserId(userId);
        ServerResponse<List<OrderItem>> serverResponse = getOrderItem(userId, cartList);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        List<OrderItem> itemList = serverResponse.getData();
        List<OrderItemVO> orderItemVOList = Lists.newArrayList();
        BigDecimal payment = new BigDecimal("0");

        for (OrderItem orderItem : itemList) {
            payment = BigDecimalUtil.add(payment.doubleValue(), orderItem.getTotalPrice().doubleValue());
        }
        orderItemVOList = assembleOrderItemVO(itemList);
        orderProductVO.setProductTotalPrice(payment);
        orderProductVO.setOrderItemVOList(orderItemVOList);
        return ServerResponse.createBySuccess(orderProductVO);
    }
}
