package com.edu.order.dao;

import java.util.List;	

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.member.dao.MemberDAOImpl;
import com.edu.order.dto.CartDTO;
import com.edu.order.dto.OrderDTO;
import com.edu.product.dto.ProductDTO;

@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {
	
	@Autowired
	private SqlSession sqlSession;

	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	private	static final String Namespace = "com.edu.order";
	
	
	
	//장바구니 상품 리스트 보여주기
	@Override
	public List<CartDTO> cartList(String userID) throws Exception {
		
		
		logger.info("MemberDAOImpl 장바구니 리스트 보여주기");
		
		return sqlSession.selectList(Namespace + ".cartList", userID);
	}


	//장바구니 담기
	@Override
	public int addCart(CartDTO cartDTO) throws Exception {
		
		System.out.println("OrderDAO 장바구니 담기");
		
		return sqlSession.insert(Namespace + ".addCart", cartDTO);
	}

	//해당상품이 장바구니에 이미 들어있는지 확인한다.
	@Override
	public int checkcart(CartDTO cartDTO) throws Exception {
		System.out.println("OrderDAO 해당상품이 장바구니에 이미 들어있는지 확인한다.");
		return sqlSession.selectOne(Namespace + ".checkcart", cartDTO);
	}

	//수량 변경
	@Override
	public int countUpdate(CartDTO cartDTO) throws Exception {
		System.out.println("OrderDAO 수량변경");
		return sqlSession.update(Namespace + ".countUpdate", cartDTO);
	}

	//카트넘버에 해당하는 정보 하나가져오기
	@Override
	public CartDTO bills(int cart_num) throws Exception {
		System.out.println("OrderDAO 카트넘버에 해당하는 정보 가져오기");
		return sqlSession.selectOne(Namespace + ".bills", cart_num);
	}

	//카트넘버들을 삭제한다.
	@Override
	public int cartdelete(int cart_num) throws Exception {
		System.out.println("OrderDAO 카트넘버들을 삭제한다.");
		
		return sqlSession.delete(Namespace + ".cartdelete", cart_num);
	}

	
	//장바구니 번호에 해당하는 장바구니 정보 가져오기
	@Override
	public CartDTO cartNumList(int cartNum) throws Exception {
		System.out.println("OrderDAOImpl의 cartNumList().... cartNum: " + cartNum);
		
		return sqlSession.selectOne(Namespace + ".cartNumList", cartNum);
	}
	
	//주문완료 영역 
	
	@Override
	public long getMaxOrderNum() throws Exception {
		System.out.println("OrderDAOImpl의 getMaxOrderNum()....");
		
		return sqlSession.selectOne(Namespace + ".getMaxOrderNum");
	}
	
	@Override
	public int addOrder(OrderDTO orderDTO) throws Exception {
		System.out.println("OrderDAOImpl의 addOrder()....");
		
		return sqlSession.insert(Namespace + ".addOrder", orderDTO);
		
	}
	
	@Override
	public int addOrderProduct(OrderDTO orderDTO) throws Exception {
		System.out.println("OrderDAOImpl의 addOrderProduct()....");
		
		return sqlSession.insert(Namespace + ".addOrderProduct", orderDTO);
	}
	
	@Override
	public int orderDateCount(String date) throws Exception {
		System.out.println("OrderDAOImpl의 orderDateCount()....");
		
		return sqlSession.selectOne(Namespace + ".orderDateCount", date);
	}


	@Override
	public List<OrderDTO> getOrderDetailById(String userID) throws Exception {
		System.out.println("OrderDAOImpl의 getOrderDetailById()....");
		
		return sqlSession.selectList(Namespace + ".getOrderDetailById", userID);
	}
	
	@Override
	public OrderDTO getOrderDetailDTO(OrderDTO orderDTO) throws Exception {
		System.out.println("OrderDAOImpl의 getOrderDetailDTO()....");
		
		return sqlSession.selectOne(Namespace + ".getOrderDetailDTO", orderDTO);
	}
	
	@Override
	public List<OrderDTO> getOrderDetail(long order_num) throws Exception {
		System.out.println("OrderDAOImpl의 getOrderDetail()....");
		
		
		return sqlSession.selectList(Namespace + ".getOrderDTODetail", order_num);
	}
	
	@Override
	public OrderDTO getOrderDTO(long order_num) throws Exception {
		System.out.println("OrderDAOImpl의 getOrderDTO()....");
		
		return sqlSession.selectOne(Namespace + ".getOrderDTO", order_num);
	}

	
	
	
}
