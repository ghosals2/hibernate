package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import demo.entities.Product;
import demo.repos.ProductRepositories;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductdataApplicationTests {
	
	@Autowired
	ProductRepositories repo;

	@Test
	void contextLoads() {
	}
	@Test
	void testCreate() {
		Product product=new Product();
		product.setId(2);
		product.setName("p2");
		product.setDesc("Demo Product2");
		product.setPrice(1000);
		
		repo.save(product);
	}
	@Test
	void testRead() {
		Product product=repo.findById(2).get();
		assertNotNull(product);
		assertEquals("p2",product.getName());
	}
	@Test
	void testUpdate() {
		Product product=repo.findById(1).get();
		product.setPrice(1500d);
		repo.save(product);
		
	}
	@Test
	void testDelete() {
		Product product=repo.findById(1).get();
		
		repo.delete(product);
		
	}
	@Test
	void testCount() {
		System.out.println("Total No of rows >>>>>>>>"+repo.count());
		
	}
	@Test
	void testFindByName() {
		List<Product> products=repo.findByName("tv");
		products.forEach(p->System.out.println("price >>>>" +p.getPrice()));
		
		}
	@Test
	void testFindByNameAndDesc() {
		List<Product> products=repo.findByNameAndDesc("washer", "from lg");
		products.forEach(p->System.out.println("price >>>>" +p.getPrice()));
		
		}
	@Test
	void testFindByPriceGreaterThan() {
		List<Product> products=repo.findByPriceGreaterThan(1000d);
		products.forEach(p->System.out.println("product >>>>" +p.getName()));
		
		}
	@Test
	void testFindByDescContains() {
		List<Product> products=repo.findByDescContains("lg");
		products.forEach(p->System.out.println("product >>>>" +p.getName()));
		
		}
	@Test
	void testFindByPriceBetween() {
		List<Product> products=repo.findByPriceBetween(800, 1700);
		products.forEach(p->System.out.println("product >>>>" +p.getName()));
		
		}
	@Test
	void testFindByDesclike() {
		List<Product> products=repo.findByDescLike("%lg%");
		products.forEach(p->System.out.println("product >>>>" +p.getName()));
		
		}
	@Test
	void testFindByIdIn() {
		List<Product> products=repo.findByIdIn(Arrays.asList(1,2,3));
		products.forEach(p->System.out.println("product >>>>" +p.getName()));
		
		}
	@Test
	public void testFindAllpaging() {
		Pageable pageable=PageRequest.of(1, 2);
		Page<Product> results=repo.findAll(pageable);
		results.forEach(p->System.out.println("Page>>>>>>" +p.getName()));
	}
	@Test
	public void testFindAllSorting() {
		Sort sort=Sort.by(Direction.DESC, "name","price");
		/*List<Order> orders=new ArrayList<Order>();
		Order order1=Order.desc("name");
		orders.add(order1);
		Order order2=Order.asc("desc");
		orders.add(order2);
		//Sort sort1=new Sort(orders);
*/		//repo.findAll(Sort.by(Direction.DESC, "name").and(Sort.by(Direction.ASC,"desc"))).forEach(p->System.out.println("sorted>>"+p.getName()));
		repo.findAll(Sort.by(new Sort.Order(Direction.DESC, "name"),new Sort.Order(Direction.ASC,"desc"))).forEach(p->System.out.println("sorted>>"+p.getName()));
		
	}
	@Test
	public void testFindAllpagingAndSorting() {
		Pageable pageable=PageRequest.of(0, 2, Direction.DESC, "price");
		Page<Product> results=repo.findAll(pageable);
		results.forEach(p->System.out.println("Page>>>>>>" +p.getName()));
	}
	


}
