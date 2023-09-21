# warp-android
Warp - NMP design system for Android

## Integrate Warp

A guide on how to integrate Warp into your project.

### Gradle

Warp is used together with a brand theme and should be installed for a specific flavor of the code. Currently only Finn and Tori are supported.

```gradle
implementation("com.schibsted.nmp.warp:warp-android:0.0.1")

finnImplementation("com.schibsted.nmp.warp:warp-android-finn:0.0.1")

toriImplementation("com.schibsted.nmp.warp:warp-android-tori:0.0.1")
```



## Apply theme
To start using Warp you must first initialize the theme depending on the selected flavor of the code.

```kotlin
const val FINN = "finn"
const val TORI = "tori"

@Composable
fun BrandTheme(flavor: String, content: @Composable () -> Unit) {
    when (flavor) {
        FINN -> FinnWarpTheme(content)
        TORI -> ToriWarpTheme(content)
    }
}
```

## License

=======

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.