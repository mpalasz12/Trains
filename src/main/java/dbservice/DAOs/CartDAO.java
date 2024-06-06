package dbservice.DAOs;

import dbservice.models.Cart;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAO {
	private final JdbcTemplate jdbcTemplate;

	public CartDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addCart(Cart cart) {
		String sql = "INSERT INTO trains (train_id) VALUES (?)";
		jdbcTemplate.update(sql, cart.getCartID());
	}
}
