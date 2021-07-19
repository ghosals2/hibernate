package demo.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import demo.entities.Product;

public interface ProductRepositories extends CrudRepository<Product, Integer> {
	List<Product> findByName(String name);
	List<Product> findByNameAndDesc(String name,String desc);
	List<Product> findByPriceGreaterThan(double price);
	List<Product> findByDescContains(String desc);
	List<Product> findByPriceBetween(double price1,double price2);
	List<Product> findByDescLike(String desc);
	List<Product> findByIdIn(List<Integer> ids);

}
