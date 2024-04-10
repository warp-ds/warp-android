# warp-android
Warp - NMP design system for Android

## Integrate Warp

A guide on how to integrate Warp into your project.

### Gradle

Warp is used together with a brand theme and should be installed for a specific flavor of the code. Currently only Finn and Tori are supported.

```gradle
implementation("com.schibsted.nmp.warp:warp-android:0.0.19")

finnImplementation("com.schibsted.nmp.warp:warp-android-finn:0.0.17")

toriImplementation("com.schibsted.nmp.warp:warp-android-tori:0.0.19")
```



## Apply theme
To start using Warp you must first initialize the theme depending on the selected flavor of the code. There should be a file like this for each flavor and this file should live in the flavor specific code package. This applies only to compose. To use legacy components in xml see [legacy support](#legacy-support).

```kotlin

class WarpNmpTheme : LegacyWarpTheme {
    @Suppress("ComposableNaming")
    @Composable
    override fun invoke(content: @Composable () -> Unit) {
        FinnWarpTheme(content)//Or ToriWarpTheme(content) depending on the selected flavor
    }
}
```
## Use Warp components
Use the composable theme which you initiated earlier to be able to use Warp components. The selected flavor of the code will give it the correct colors and styling.

```kotlin exmaple
@Composable
fun MainScreen() {

    WarpBrandTheme {
      Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
        ) {
          WarpText(
              text = "Hello world",
              Modifier
                  .padding(vertical = dimensions.space1, horizontal = dimensions.space2),
              style = WarpTextStyle.Display,
              color = colors.text.link
          )
            WarpButton(
              onClick = {  },
              style = WarpButtonStyle.Primary,
              text = "Submit"
              )
          }
    }
}
```

### Legacy support
All Warp components have xml support to be able to use Warp with legacy layouts. Use Warp components as any other custom View components.

To be able to apply the correct styling to legacy components it is required to use Koin dependency injection. You will need to integrate Koin into your project (unless it already is there).

```gradle
implementation("io.insert-koin:koin-androidx-compose:3.5.0")
```

Then start Koin in your Application
```kotlin
class WarpApplication : Application() {
        override fun onCreate() {
            super.onCreate()
            startKoin {
                androidContext(this@WarpApplication)
                modules(warpAppModule)
            }
        }
}
```
Next, create a Theme class (one for each flavor) which implemets the LegacyWarpTheme. This code should live in the flavor specific packages.
```kotlin

class WarpNmpTheme : LegacyWarpTheme {
    @Suppress("ComposableNaming")
    @Composable
    override fun invoke(content: @Composable () -> Unit) {
        FinnWarpTheme(content)//Or ToriWarpTheme(content) depending on the selected flavor
    }
}
```
Then create a theme function which will apply the theme to the components. This code should be in the main folder of the module and accessible no matter the flavor.

```kotlin
@Composable
fun NMPTheme(content: @Composable () -> Unit) {
    val theme = WarpNmpTheme()
    theme {
        content()
    }
}
```
Create an instance of the theme class within a koin module
```kotlin
val warpAppModule = module {
    single<LegacyWarpTheme> { WarpBrandTheme() }
}
```
Now the warp components will show correct colors and styling.

To use compose with this setup follow this example:

```kotlin exmaple
@Composable
fun MainScreen() {

    NmpTheme {
      WarpButton(
              onClick = {  },
              style = WarpButtonStyle.Primary,
              text = "Submit"
              )
    }
}
```

Xml implementation example

```xml example
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
  <com.schibsted.nmp.warp.components.WarpButtonView
      android:id="@+id/warp_button"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      app:layout_constraintStart_toStartOf="parent"
      app:layout_constraintTop_toTopOf="parent"
      app:warpButtonStyle="primary"
      app:buttonText="Send"
      app:loading="false"
      app:enabled="true"/>

  <com.schibsted.nmp.warp.components.WarpTextFieldView
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      app:layout_constraintStart_toStartOf="parent"
      app:layout_constraintTop_toBottomOf="@id/warp_button"
      app:label="Label"
      app:optionalLabel="(optional)"
      app:placeholderText="Placeholder text inside the textField"
      app:helpText="Help text under the textfield"
      app:textFieldEnabled="true"
      app:readOnly="false"
      app:leadingIcon="@drawable/leading_icon"
      app:leadingIconContentDescr="Leading icon description"
      app:trailingIcon="@drawable/trailing_icon"
      app:trailingIconContentDescr="Trailing icon description"
      app:isError="false"/>

</androidx.constraintlayout.widget.ConstraintLayout>
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