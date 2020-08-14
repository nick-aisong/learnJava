package com.wisely.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

// 在8.2.1中我们对定义RepositoryFactoryBean做了讲解，这里的代码大可以作为模板代码使用，只需修改和定义实现类相关的代码即可
public class CustomRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>
		extends JpaRepositoryFactoryBean<T, S, ID> {// 1自定义RepositoryFactoryBean，继承JpaRepositoryFactoryBean

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {// 2重写createRepositoryFactory方法，用当前的CustomRepositoryFactory创建实例
		return new CustomRepositoryFactory(entityManager);
	}

	private static class CustomRepositoryFactory extends JpaRepositoryFactory {// 3创建CustomRepositoryFactory，并继承JpaRepositoryFactory

		public CustomRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
		}

		@Override
		@SuppressWarnings({ "unchecked" })
		protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
				RepositoryInformation information, EntityManager entityManager) {// 4重写getTargetRepository方法，获得当前自定义的Repository实现
			return new CustomRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);

		}

		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {// 5重写getRepositoryBaseClass，获得当前自定义的Repository实现的类型
			return CustomRepositoryImpl.class;
		}
	}
}