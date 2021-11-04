package gp.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value = "SELECT DATE_FORMAT(o.orderdate, '%Y.%m.%d') as orderdate, case when o.orderstate = 1 then '배송준비중' end as orderstate, o.orderkey, (SELECT m.material_Title FROM material m WHERE m.material_Key = d.materialkey) as materialname FROM `order` o, orderdetail d WHERE o.memberid = :userId and o.orderkey = d.orderkey order by o.orderdate asc", nativeQuery=true)
    List<Map<String, Object>> findAllByUserId(Long userId);

    List<Order> findByOrderstate(Integer orderstate);
}
