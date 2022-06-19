package geektime.spring.springbucks.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import geektime.spring.springbucks.exception.RollbackException;
import geektime.spring.springbucks.mapper.OrderMapper;
import geektime.spring.springbucks.pojo.Order;
import geektime.spring.springbucks.pojo.OrderExample;
import geektime.spring.springbucks.service.OrderService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
@CacheConfig(cacheNames = "order_cache")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;


    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public int create(Order order) throws RollbackException {
        return orderMapper.insert(order);
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    @CacheEvict
    public int deleteById(Long id) {
        return orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    @CacheEvict
    public int updateById(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    @Cacheable
    public List<Order> selectByIds(Collection<Long> ids) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andIdIn((List<Long>) ids);
        return orderMapper.selectByExample(orderExample);
    }

    @Override
    @Cacheable
    public Order getById(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> getByParam(Order param) {

        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        if (!StringUtils.isEmpty(param.getCustomer())) {
            criteria.andCustomerEqualTo(param.getCustomer());
        }
        if (!StringUtils.isEmpty(param.getId())) {
            criteria.andIdEqualTo(param.getId());
        }

        return orderMapper.selectByExample(orderExample);
    }

    @Override
    public PageInfo listOrderByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orders = orderMapper.selectByExample(new OrderExample());
        return new PageInfo<>(orders);
    }


}
