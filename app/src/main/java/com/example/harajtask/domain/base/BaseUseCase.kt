package com.example.harajtask.domain.base


abstract class BaseUseCase {
    abstract class WithOutParams<Result> : BaseUseCase() {

        abstract suspend fun run(): Result
    }
}
