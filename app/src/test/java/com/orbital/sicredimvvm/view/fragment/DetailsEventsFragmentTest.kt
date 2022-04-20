package com.orbital.sicredimvvm.view.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.orbital.core.utils.FormatUtils
import com.orbital.core.utils.Result
import com.orbital.sicredimvvm.repository.CheckInRepository
import com.orbital.sicredimvvm.viewmodel.DetailsViewModel
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailsEventsFragmentTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel:DetailsViewModel

    @Mock
    private lateinit var resultObserver: Observer<Result<String>>

    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun finish(){
        Dispatchers.resetMain()
    }

    @Test
    fun `verificar se o email é valido`(){
        val emailCorrect = "nome@email.com"
        val emailIncorrect = "aalalla"

        assertTrue(FormatUtils.isValidateEmail(emailCorrect))
    }

    @Test
    fun `verificar se o email é invalido`(){
        val emailCorrect = "nome@email.com"
        val emailIncorrect = "aalalla"

        assertFalse(FormatUtils.isValidateEmail(emailIncorrect))
    }

    @Test
    fun `quando resultlivedata receber sucess`(){

        val resultSucess = MockRepositoryCheckIn(Result.onSucess(""))
        viewModel = DetailsViewModel(resultSucess)
        viewModel.resultLiveData.observeForever(resultObserver)

        viewModel.checkIn("Jão","jao@gmail.com","5")

        verify(resultObserver).onChanged(Result.onSucess(""))
    }

    @Test
    fun `quando resultLiveData receber error`(){
        val msg = "Error API"
        val data = null
        val resultError = MockRepositoryCheckIn(Result.onError(msg, data))
        viewModel = DetailsViewModel(resultError)
        viewModel.resultLiveData.observeForever(resultObserver)

        viewModel.checkIn("Jão","jao@gmail.com","5")

        verify(resultObserver).onChanged(Result.onError(msg, data))
    }
    @Test
    fun `quando resultLiveData receber error network`(){

        val resultErrorNetwork = MockRepositoryCheckIn(Result.onErrorNetwork(null))
        viewModel = DetailsViewModel(resultErrorNetwork)
        viewModel.resultLiveData.observeForever(resultObserver)

        viewModel.checkIn("Jão","jao@gmail.com","5")

        verify(resultObserver).onChanged(Result.onErrorNetwork(null))

    }
}
class MockRepositoryCheckIn(private val result: Result<String>):CheckInRepository{
    override suspend fun checkIn(eventId: String, name: String, email: String): Result<String> {
        return result
    }
}