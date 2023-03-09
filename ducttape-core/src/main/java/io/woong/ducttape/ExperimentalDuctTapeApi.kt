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
package io.woong.ducttape

/**
 * Marker for experimental DuctTape features. It may changed or removed in the future because
 * this feature is unstable.
 *
 * To use experimental feature, opt in this annotation:
 *
 * ```
 * // When opt in to a class
 * @OptIn(ExperimentalDuctTapeApi::class)
 * class SomeClass
 *
 * // When opt in to a function
 * @OptIn(ExperimentalDuctTapeApi::class)
 * fun someFunction() {/**/}
 * ```
 */
@RequiresOptIn(
    message = "Experimental DuctTape feature. It may changed or remove in the future.",
    level = RequiresOptIn.Level.ERROR
)
@Retention(AnnotationRetention.BINARY)
public annotation class ExperimentalDuctTapeApi
