package com.codegnan.app.javawebapp2309.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.codegnan.app.javawebapp2309.dto.ProductDto;
import com.codegnan.app.javawebapp2309.entity.Product;
import com.codegnan.app.javawebapp2309.utils.JpaUtil;

import jakarta.persistence.EntityManagerFactory;
@Component
public class ProductDaoImpl implements ProductDao {
	private EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
	
	@Override
	public boolean save(ProductDto productDto) {
		var isSaved = false;

		var product = new Product();
		product.setName(productDto.name());
		product.setBrand(productDto.brand());
		product.setDescription(productDto.description());
		product.setStatus(productDto.status());
		product.setCreatedAt(productDto.createdAt());
		product.setUpdatedAt(productDto.updatedAt());

		try (var entityManager = entityManagerFactory.createEntityManager()) {
			entityManager.getTransaction().begin();

			entityManager.persist(product);

			entityManager.getTransaction().commit();
			isSaved = true;
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}

		return isSaved;
	}

	@Override
	public ProductDto findById(int productId) {
		ProductDto productDto = null;

		try (var entityManager = entityManagerFactory.createEntityManager()) {
			var product = entityManager.find(Product.class, productId);

			if (product != null) {
				productDto = new ProductDto(
						product.getId(), 
						product.getName(), 
						product.getBrand(),
						product.getDescription(), 
						product.getStatus(), 
						product.getCreatedAt(), 
						product.getUpdatedAt());
			}
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}

		return productDto;
	}

	@Override
	public ProductDto findByName(String productName) {
		ProductDto productDto = null;

		var jpql = "SELECT p FROM Product p WHERE p.name=:proName";

		try (var entityManager = entityManagerFactory.createEntityManager()) {
			var query = entityManager.createQuery(jpql).setParameter("proName", productName);

			var product = (Product) query.getSingleResult();

			productDto = new ProductDto(
					product.getId(), 
					product.getName(), 
					product.getBrand(),
					product.getDescription(), 
					product.getStatus(), 
					product.getCreatedAt(), 
					product.getUpdatedAt());
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}

		return productDto;
	}

	@Override
	public List<ProductDto> findAll() {
		List<ProductDto> productDtosList = new ArrayList<>();

		var jpql = "SELECT p FROM Product p";

		try (var entityManager = entityManagerFactory.createEntityManager()) {
			var query = entityManager.createQuery(jpql);

			List<Product> productsList = query.getResultList();

			for (var product : productsList) {
				var productDto = new ProductDto(
						product.getId(), 
						product.getName(), 
						product.getBrand(),
						product.getDescription(), 
						product.getStatus(), 
						product.getCreatedAt(), 
						product.getUpdatedAt());

				productDtosList.add(productDto);
			}
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}

		return productDtosList;
	}
	
	
	public boolean updateName(int productId, String updatedName) {
		var isUpdated = false;

		try (var entityManager = entityManagerFactory.createEntityManager()) {
			entityManager.getTransaction().begin();

			var product = entityManager.find(Product.class, productId);

			if (product != null) {
				product.setName(updatedName);
				
				entityManager.getTransaction().commit();
				isUpdated = true;
			}
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}

		return isUpdated;
	}
	
	@Override
	public boolean delete(int productId) {
		var isDeleted = false;

		try (var entityManager = entityManagerFactory.createEntityManager()) {
			entityManager.getTransaction().begin();

			var product = entityManager.find(Product.class, productId);

			if (product != null) {
				entityManager.remove(product);
				
				entityManager.getTransaction().commit();
				isDeleted = true;
			}
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}

		return isDeleted;
	}
}