package geektime.spring.springbucks.service;

import com.github.pagehelper.PageInfo;
import geektime.spring.springbucks.exception.RollbackException;
import geektime.spring.springbucks.pojo.Order;

import java.util.Collection;
import java.util.List;

public interface OrderService {

    int create(Order order) throws RollbackException;

    int deleteById(Long id);

    int updateById(Order order);

    List<Order> selectByIds(Collection<Long> ids);

    Order getById(Long id);

    List<Order> getByParam(Order param);

    PageInfo listOrderByPage(int pageNum, int pageSize);

}
