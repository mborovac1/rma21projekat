package ba.etf.rma21.projekat

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.viewmodel.KvizListViewModel
import org.junit.Test
import org.junit.Assert.*
import org.hamcrest.CoreMatchers.`is` as Is
import org.hamcrest.Matchers.*

class KvizListViewModelUnitTest {
    private val kvizListViewModel = KvizListViewModel()

    @Test
    fun getAll() {
        val sviKvizovi = kvizListViewModel.getAll()
        assertEquals(sviKvizovi.size, 9) // pošto imamo 9 kvizova u KvizStaticData
        assertThat(sviKvizovi, hasItem<Kviz>(
                hasProperty("nazivPredmeta", Is("IM"))
        ))
        assertThat(sviKvizovi, not(hasItem<Kviz>(
                hasProperty("nazivPredmeta", Is("OOAD"))
        )))
    }

    @Test
    fun getMojiKvizovi() {
        val mojiKvizovi = kvizListViewModel.getMojiKvizovi()
        assertEquals(mojiKvizovi.size, 1) // pošto je na početku korisnik upisan na 1 kviz
        assertThat(mojiKvizovi, hasItem<Kviz>(
                hasProperty("nazivPredmeta", Is("RMA"))
        ))
    }

    @Test
    fun getFuture() {
        val buduciKvizovi = kvizListViewModel.getFuture()
        assertTrue(buduciKvizovi.isEmpty())
        assertThat(buduciKvizovi, not(hasItem<Kviz>(
                hasProperty("naziv", Is("Kviz 0"))
        )))
    }

    @Test
    fun getDone() {
        val prosliKvizovi = kvizListViewModel.getDone()
        assertFalse(prosliKvizovi.isNotEmpty())
        assertThat(prosliKvizovi, not(hasItem<Kviz>(
                hasProperty("osvojeniBodovi", Is(2F))
        )))
    }

    @Test
    fun getNotTaken() {
        val neuradjeniKvizovi = kvizListViewModel.getNotTaken()
        assertFalse(neuradjeniKvizovi.isEmpty())
        assertEquals(neuradjeniKvizovi.size, 1)
        assertThat(neuradjeniKvizovi, not(hasItem<Kviz>(
                hasProperty("nazivGrupe", Is("IM-Grupa1"))
        )))
    }
}
