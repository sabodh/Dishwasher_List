package com.online.partnerships.model.repository

import com.online.partnerships.network.ServiceBuilder
import com.online.partnerships.network.ServiceEndPoints

class DefaultRepositoryFactory : RepositoryFactory {
    override fun createProductRepository(): DefaultProductRepository {
        return DefaultProductRepository(
            ServiceBuilder.buildService(
                ServiceEndPoints::class.java
            )
        )
    }
}