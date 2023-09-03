package com.online.partnerships.model.repository

/**
 * Contain all the repositories
 */
interface RepositoryFactory {
    fun createProductRepository(): DefaultProductRepository
}