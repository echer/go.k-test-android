package com.alanecher.testegok

import com.alanecher.testegok.di.connTimeout
import com.alanecher.testegok.di.readTimeout
import com.alanecher.testegok.repository.ProductsRepository
import com.alanecher.testegok.repository.ProductsRepositoryImpl
import com.alanecher.testegok.repository.domain.Cash
import com.alanecher.testegok.repository.domain.Product
import com.alanecher.testegok.repository.domain.Spotlight
import com.alanecher.testegok.repository.domain.dto.ProductsDTO
import com.alanecher.testegok.repository.remote.BaseResponse
import com.alanecher.testegok.repository.remote.PRODUCTS
import com.alanecher.testegok.repository.remote.ProductsAPI
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.*
import okhttp3.mock.*
import okhttp3.mock.MediaTypes.MEDIATYPE_JSON
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@PowerMockIgnore("javax.net.ssl.*")
@RunWith(PowerMockRunner::class)
@PrepareForTest(ProductsRepository::class)
@ExperimentalCoroutinesApi
class ProductsRepositoryTest {

    lateinit var productsRepository: ProductsRepositoryImpl
    lateinit var api: ProductsAPI

    val products =
        ProductsDTO(
            spotlights = listOf(
                Spotlight(
                    name = "Recarga",
                    bannerURL = "https://s3-sa-east-1.amazonaws.com/digio-exame/recharge_banner.png",
                    description = "Agora ficou mais fácil colocar créditos no seu celular! A digio Store traz a facilidade de fazer recargas... direto pelo seu aplicativo, com toda segurança e praticidade que você procura."
                ),
                Spotlight(
                    name = "Uber",
                    bannerURL = "https://s3-sa-east-1.amazonaws.com/digio-exame/uber_banner.png",
                    description = "Dê um vale presente Uber para amigos e familiares, ou use os vales para adicionar créditos à sua conta. O app Uber conecta você a uma viagem confiável em apenas alguns minutos. Você pode escolher entre as opções econômicas ou Premium para viajar do seu jeito. O pagamento é fácil e sem complicações!"
                )
            ),
            products = listOf(
                Product(
                    name = "XBOX",
                    imageURL = "https://s3-sa-east-1.amazonaws.com/digio-exame/xbox_icon.png",
                    description = "Com o e-Gift Card Xbox você adquire créditos para comprar games, música, filmes, programas de TV e muito mais!",
                ),
                Product(
                    name = "Google Play",
                    imageURL = "https://s3-sa-east-1.amazonaws.com/digio-exame/google_play_icon.png",
                    description = "O e-gift Google Play dá acesso a um mundo de entretenimento. É muito fácil encontrar algo que você goste, seja você um fã de música ou de filmes, livros, revistas, apps ou jogos. Com o e-gift Google Play, você não precisa de cartão de crédito e pode aproveitar o melhor do cinema, suas músicas favoritas e muito mais em smartphones e tablets Android, iOS e também no computador."
                ),
                Product(
                    name = "Level up",
                    imageURL = "https://s3-sa-east-1.amazonaws.com/digio-exame/level_up_icon.png",
                    description = "Com o e-Gift Card Level Up você adquire créditos para jogar diversas opções de games, de MMORPGs a jogos de Tiro e Mobas! Para ver a lista de jogos e a quantia de créditos que pode resgatar em cada um deles, acesse: http://levelupgames.uol.com.br/levelup/seuegiftcard.lhtml"
                )
            ),
            cash = Cash(
                title = "digio Cash",
                bannerURL = "https://s3-sa-east-1.amazonaws.com/digio-exame/cash_banner.png",
                description = "Dinheiro na conta sem complicação. Transfira parte do limite do seu cartão para sua conta."
            )
        )

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

    }

    fun createMockClient(interceptor: MockInterceptor) {
        api = Retrofit.Builder()
            .baseUrl(PRODUCTS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(connTimeout, TimeUnit.SECONDS)
                    .readTimeout(readTimeout, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build()
            )
            .build()
            .create(ProductsAPI::class.java)
        productsRepository = ProductsRepositoryImpl(
            api
        )
    }

    @Test
    fun `test successfull response listproducts`() = coroutinesTestRule.testDispatcher.runBlockingTest {

        //Given
        var mockedJson:String = Gson().toJson(products)
        createMockClient(MockInterceptor().apply {
            rule(get, url eq PRODUCTS) {
                respond(HttpCode.HTTP_200_OK).body(mockedJson,MEDIATYPE_JSON)
            }
        })

        //When
        var response: BaseResponse<ProductsDTO> = productsRepository.listProducts()

        //Then
        assertTrue(response is BaseResponse.Success)
        assertEquals(BaseResponse.Success(products).data, (response as BaseResponse.Success).data)
    }

    @Test
    fun `test error 500 response listproducts`() = coroutinesTestRule.testDispatcher.runBlockingTest {

        //Given
        val internalError = "{}"
        createMockClient(MockInterceptor().apply {
            rule(get, url eq PRODUCTS) {
                respond(HttpCode.HTTP_500_INTERNAL_SERVER_ERROR).body(internalError,MEDIATYPE_JSON)
            }
        })

        var response: BaseResponse<ProductsDTO> = productsRepository.listProducts()

        assertTrue(response is BaseResponse.Error)
        assertEquals(
            BaseResponse.Error(internalError).message,
            (response as BaseResponse.Error).message
        )
    }

    @Test
    fun `test error 404 response listproducts`() = coroutinesTestRule.testDispatcher.runBlockingTest {

        //Given
        val notFound = "The request has not found!"
        createMockClient(MockInterceptor().apply {
            rule(get, url eq PRODUCTS) {
                respond(HttpCode.HTTP_404_NOT_FOUND).body(notFound,MEDIATYPE_JSON)
            }
        })

        var response: BaseResponse<ProductsDTO> = productsRepository.listProducts()

        assertTrue(response is BaseResponse.Error)
        assertEquals(BaseResponse.Error(notFound).message, (response as BaseResponse.Error).message)
    }
}