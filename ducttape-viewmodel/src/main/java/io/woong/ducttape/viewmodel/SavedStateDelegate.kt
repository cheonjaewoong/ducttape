/*
 * Copyright 2023 Jaewoong Cheon
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.woong.ducttape.viewmodel

import androidx.lifecycle.SavedStateHandle
import kotlin.reflect.KProperty

/**
 * Creates a property delegate to read & write nullable value with with [SavedStateHandle].
 *
 * Saved state property delegate is designed for important value of a view model.
 * Important values should remembered after the process is restarted. [SavedStateHandle] is
 * one of the solution for it. This function creates a property delegate instance, and the
 * instance save given value to [SavedStateHandle] when setter is called.
 *
 * To create a property delegate, call this function like this:
 *
 * ```
 * class SomeViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
 *     var property: String? by savedStateHandle.nullable()
 * }
 * ```
 *
 * @param initialValue The initial value of this property delegate.
 */
public fun <T> SavedStateHandle.nullable(initialValue: T? = null): SavedStateNullablePropertyDelegate.Provider<T> {
    return SavedStateNullablePropertyDelegate.Provider(this, initialValue)
}

/**
 * Creates a property delegate to read & write not null value with with [SavedStateHandle].
 *
 * Saved state property delegate is designed for important value of a view model.
 * Important values should remembered after the process is restarted. [SavedStateHandle] is
 * one of the solution for it. This function creates a property delegate instance, and the
 * instance save given value to [SavedStateHandle] when setter is called.
 *
 * To create a property delegate, call this function like this:
 *
 * ```
 * class SomeViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
 *     var property: String by savedStateHandle.notNull("init")
 * }
 * ```
 *
 * @param initialValue The initial value of this property delegate.
 */
public fun <T : Any> SavedStateHandle.notNull(initialValue: T): SavedStateNotNullPropertyDelegate.Provider<T> {
    return SavedStateNotNullPropertyDelegate.Provider(this, initialValue)
}

public class SavedStateNullablePropertyDelegate<T> internal constructor(
    private val savedStateHandle: SavedStateHandle
) {
    public operator fun getValue(self: Any?, property: KProperty<*>): T? {
        return savedStateHandle[property.name]
    }

    public operator fun setValue(self: Any?, property: KProperty<*>, value: T?) {
        savedStateHandle[property.name] = value
    }

    public class Provider<T> internal constructor(
        private val savedStateHandle: SavedStateHandle,
        private val initialValue: T?
    ) {
        public operator fun provideDelegate(
            self: Any?,
            property: KProperty<*>
        ): SavedStateNullablePropertyDelegate<T> {
            return if (initialValue == null) {
                SavedStateNullablePropertyDelegate(savedStateHandle)
            } else {
                val key = property.name
                if (key !in savedStateHandle.keys()) {
                    savedStateHandle[key] = initialValue
                }
                SavedStateNullablePropertyDelegate(savedStateHandle)
            }
        }
    }
}

public class SavedStateNotNullPropertyDelegate<T> internal constructor(
    private val savedStateHandle: SavedStateHandle
) {
    public operator fun getValue(self: Any?, property: KProperty<*>): T {
        // NotNull property delegate always has initial value. So return type is always not-null.
        return savedStateHandle[property.name]!!
    }

    public operator fun setValue(self: Any?, property: KProperty<*>, value: T) {
        savedStateHandle[property.name] = value
    }

    public class Provider<T> internal constructor(
        private val savedStateHandle: SavedStateHandle,
        private val initialValue: T
    ) {
        public operator fun provideDelegate(
            self: Any?,
            property: KProperty<*>
        ): SavedStateNotNullPropertyDelegate<T> {
            val key = property.name
            if (key !in savedStateHandle.keys()) {
                savedStateHandle[key] = initialValue
            }
            return SavedStateNotNullPropertyDelegate(savedStateHandle)
        }
    }
}
