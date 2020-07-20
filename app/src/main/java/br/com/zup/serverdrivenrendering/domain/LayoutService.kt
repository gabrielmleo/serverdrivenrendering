package br.com.zup.serverdrivenrendering.domain

import br.com.zup.serverdrivenrendering.model.Response

class LayoutService(
    private val repository: Repository
) {
    suspend fun getMainScreenLayoutData(): Response {
      return repository.getMainScreenLayoutData()
    }
}