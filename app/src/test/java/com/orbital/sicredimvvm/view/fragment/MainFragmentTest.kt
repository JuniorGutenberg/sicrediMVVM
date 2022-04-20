package com.orbital.sicredimvvm.view.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.orbital.core.utils.Result
import com.orbital.sicredimvvm.db.model.EventsData
import com.orbital.sicredimvvm.repository.EventsRepository
import com.orbital.sicredimvvm.viewmodel.MainViewModel
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

class MainFragmentTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel:MainViewModel

    @Mock
    private lateinit var eventListDataObserver: Observer<Result<List<EventsData>?>>

    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `quando eventlist receber sucesso set eventlist_value`(){
        val events = mutableListOf(EventsData(0,"1","titulo1",
            2.55,-88.0,10.00,
            "image","descrição",1000))

        val resultSucess = MockRepository(Result.onSucess(events))
        viewModel = MainViewModel(resultSucess)
        viewModel.eventList.observeForever(eventListDataObserver)

        viewModel.getEvents()
        verify(eventListDataObserver).onChanged(Result.onSucess(events))

    }

    @Test
    fun `quando eventlist receber error set mensagem e data null`(){

        val msg = "Error API"
        val data = null
        val events = mutableListOf(EventsData(0,"1","titulo1",
            2.55,-88.0,10.00,
            "image","descrição",1000))

        val resultError = MockRepository(Result.onError(msg, data))
        viewModel = MainViewModel(resultError)
        viewModel.eventList.observeForever(eventListDataObserver)

        viewModel.getEvents()

        verify(eventListDataObserver).onChanged(Result.onError(msg, null))

    }

    @Test
    fun `quando eventilist receber errorNetwork set null`(){
        val events = mutableListOf(EventsData(0,"1","titulo1",
            2.55,-88.0,10.00,
            "image","descrição",1000))

        val resultErrorNetwork = MockRepository(Result.onErrorNetwork(null))
        viewModel = MainViewModel(resultErrorNetwork)

        viewModel.eventList.observeForever(eventListDataObserver)

        viewModel.getEvents()

        verify(eventListDataObserver).onChanged(Result.onErrorNetwork(null))
    }
}
class MockRepository(private val result:Result<MutableList<EventsData>>):EventsRepository{
    override suspend fun getEvents(): Result<MutableList<EventsData>> {
        return result
    }
}