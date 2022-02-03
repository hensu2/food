package restaurant;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public List<RestaurantVo> restaurantList(RestaurantVo vo) {
		return sqlSession.selectList("restaurant.restaurantList", vo);
	}
	public int restaurantCount(RestaurantVo vo) {
		return sqlSession.selectOne("restaurant.restaurantCount",vo);
	}
	public int restaurantQnaCount(RestaurantQnaVo vo) {
		return sqlSession.selectOne("restaurant.restaurantQnaCount",vo);
	}
	public List<RestaurantQnaVo> restaurantQnaList(RestaurantQnaVo vo) {
		return sqlSession.selectList("restaurant.restaurantQnaList", vo);
	}
	public int restaurantDelete(String r_no) {
		return sqlSession.delete("restaurant.restaurantDelete",r_no);
	}
	public int requestDelete(String rqna_no) {
		return sqlSession.delete("restaurant.requestDelete",rqna_no);
	}
	public int restinsert(RestaurantVo vo) {
		int r = -1;
		try {
			r = sqlSession.insert("restaurant.insert", vo);
		}catch (Exception e) {
			r = 0;
			System.out.println(e.getMessage());
		}
		return r;
	}
	public RestaurantQnaVo selectonno_selecte(int rqna_no) {
		return sqlSession.selectOne("restaurant.no_select",rqna_no);
	}
	public RestaurantVo selectone(int rqna_no) {
		return sqlSession.selectOne("restaurant.shopDetail",rqna_no);
	}
	public int update(RestaurantVo vo) {
		return sqlSession.update("restaurant.update", vo);
	}
	
}
