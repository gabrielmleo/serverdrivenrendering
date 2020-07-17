package br.com.zup.serverdrivenrendering.domain.util

class Response<T> private constructor(
    private val value: T?,
    private val failure: Throwable?
) {

    val isSuccess: Boolean
        get() = value != null

    val isFailure: Boolean
        get() = failure != null

    companion object {

        fun <T> success(value: T): Response<T> =
            Response(value, null)

        fun <T> failure(failure: Throwable): Response<T> =
            Response(null, failure)
    }

    inline fun <V> flatMap(transform: (value: T) -> Response<V>): Response<V> {
        if (isFailure) return failure(
            exceptionOrNull()!!
        )
        return transform(getOrThrow())
    }

    fun <V> map(transform: (v: T) -> V): Response<V> {
        return if (isSuccess) {
            success(transform(getOrThrow()))
        } else {
            failure(this.failure!!)
        }
    }


    fun exceptionOrNull() = failure

    fun getOrNull() = value

    fun getOrThrow(): T = value ?: error("Value is null")

    fun getOrElse(e: T) = value ?: e

    inline fun onSuccess(action: (value: T) -> Unit): Response<T> = this.apply {
        if (isSuccess) action(getOrThrow())
    }

    fun onFailure(action: (exception: Throwable) -> Unit): Response<T> = this.apply {
        if (isFailure) action(failure!!)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Response<*>) return false
        return value == other.getOrNull() && failure == other.exceptionOrNull()
    }

    override fun hashCode(): Int {
        var result = value?.hashCode() ?: 0
        result = 31 * result + (failure?.hashCode() ?: 0)
        return result
    }

    fun onAny(action: () -> Unit): Response<T> {
        return onSuccess {
            action()
        }.onFailure {
            action()
        }

    }
}