# hilt-clean-architecture-sample



- `Hilt`는 `Android` 애플리케이션에서 의존성 주입(Dependency Injection, DI)을 단순화하기 위해 구글이 만든 라이브러리
- Dagger 위에 구축

---

## **간소화된 DI 구성**

- Hilt는 Dagger의 복잡한 컴포넌트나 서브컴포넌트 구성을 대신해, 미리 정의된 컴포넌트(`SingletonComponent`, `ActivityComponent` 등)를 제공

## **Android 통합**

- Android의 `Application`, `Activity`, `Fragment`, `Service` 등 다양한 컴포넌트에 쉽게 DI를 적용할 수 있도록 `@HiltAndroidApp`과 `@AndroidEntryPoint`같은 애노테이션을 제공

## **자동화된 라이프사이클 관리**

- Hilt는 Android Component의 lifecycle에 맞춰 의존성의 범위(scope)를 자동 관리

## **모듈(Module)과 프로바이더**

- `@Module`과 `@InstallIn` annotation을 사용해 의존성을 제공하는 코드를 작성할 수 있음
- Hilt는 이를 통해 필요한 인스턴스를 생성

---

## *안드로이드에 적용해보자*

- gradle version 관리
    
    ```kotlin
    [versions]
    hilt = "2.48.1"
    hilt-compose = "1.2.0"
    
    [libraries]
    hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hilt" }
    hilt-compiler = { group = "com.google.dagger", name = "hilt-android-compiler", version.ref = "hilt" }
    hilt-compose = { group = "androidx.hilt", name = "hilt-navigation-compose", version.ref = "hilt-compose" }
    
    [plugins]
    hilt-android = { id = "com.google.dagger.hilt.android", version.ref = "hilt" }
    ```
    
- gradle 설정
    
    ```kotlin
    plugins {
    ...
       alias(libs.plugins.hilt.android)
       id("kotlin-kapt")
    }
    
    dependencies {
        implementation(libs.hilt.android)
        kapt(libs.hilt.compiler)
        implementation(libs.hilt.compose)
    }
    ```
    

- 모듈, 프로바이더 정의
    - `datasourceModule` , `serviceModule` , `usecaseModeul` , `networkModule` , `repositoryModule`
    - 아래 코드 처럼 모듈별로 설정해주었음
    - **`@Module`:** DI 설정을 위한 모듈 클래스임을 나타냄
    - **`@InstallIn(SingletonComponent::class)` :** 모듈을 애플리케이션 전체에 걸친 싱글톤 범위에 설치함을 의미합
    - **`@Provides` :** 모듈 내에서 의존성 객체를 생성하여 제공하는 함수를 지정
    - **`@Singleton`:** 제공된 의존성이 한 번만 생성되어 재사용됨을 보장
    
    ```kotlin
    @Module
    @InstallIn(SingletonComponent::class)
    object RepositoryModule {
        @Provides
        @Singleton
        fun provideNoticeRepository(noticeDataSource: NoticeDataSource): NoticeRepository {
            return NoticeRepositoryImpl(noticeDataSource = noticeDataSource)
        }
    }
    ```
    
- **Application 클래스에 Hilt 적용**
    
    ```kotlin
    @HiltAndroidApp
    class HiltApp : Application()
    ```
    
- **Android 컴포넌트에 주입**
    
    ```kotlin
    @AndroidEntryPoint
    class MainActivity : ComponentActivity() {
    		....
    }
    ```
    

위처럼 작성하면 끝!

---

- compose에서 viewModel을 주입하는 방법
- 아래처럼  `hiltViewModel` 키워드를 사용해도 되고 아니면 여러가지 방법이 있으니, 상황에 맞게 찾아서 쓰자

```kotlin
@HiltViewModel
class MainViewModel @Inject internal constructor(
    private val getNoticesUseCase: GetNoticesUseCase
) : ViewModel() {
		...
}
```

```kotlin
@Composable
fun NoticeList(viewModel: MainViewModel = hiltViewModel()) {
		...
}
```
