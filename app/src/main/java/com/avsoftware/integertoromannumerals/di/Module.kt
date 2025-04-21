package com.avsoftware.integertoromannumerals.di

import com.avsoftware.integertoromannumerals.roman.DecimalToRomanImpl
import com.avsoftware.integertoromannumerals.roman.DecimalToRomanUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class Module {

    @Binds
    internal abstract fun bindDecimalToRomanUseCase(decimalToRomanImpl: DecimalToRomanImpl ): DecimalToRomanUseCase
}