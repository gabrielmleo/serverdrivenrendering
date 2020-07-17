package br.com.zup.serverdrivenrendering.domain.model

sealed class Response {
    data class Success(val screenInfo: ScreenInfo): Response()
    data class Failure(val throwable: Throwable): Response()
}