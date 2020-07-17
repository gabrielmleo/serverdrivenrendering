package br.com.zup.serverdrivenrendering.domain.exception

data class ScreenReaderException(val errorMsg: String = "Ocorreu um erro ao ler o Json"): Throwable()
