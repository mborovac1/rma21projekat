package ba.etf.rma21.projekat

import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.viewmodel.UpisPredmetViewModel
import org.hamcrest.CoreMatchers.`is` as Is
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.Assert.*

class UpisPredmetViewModelUnitTest {
    val upisPredmetViewModel = UpisPredmetViewModel()

    // svi predmeti
    @Test
    fun getAll() {
        val sviPredmeti = upisPredmetViewModel.getAll()
        assertEquals(sviPredmeti.size, 15) // pošto imamo 15 predmeta u PredmetStaticData
        assertThat(sviPredmeti, hasItem<Predmet>(
            hasProperty("naziv", Is("IM"))
        ))
        assertThat(sviPredmeti, not(hasItem<Predmet>(
            hasProperty("naziv", Is("OOAD"))
        )))
        assertThat(sviPredmeti, not(hasItem<Predmet>(
            hasProperty("godina", Is(6))
        )))
        assertThat(sviPredmeti, not(hasItem<Predmet>(
            hasProperty("godina", Is(0))
        )))
    }

    // upisani predmeti
    @Test
    fun getUpisani() {
        val upisaniPredmeti = upisPredmetViewModel.getUpisani()
        assertEquals(upisaniPredmeti.size, 1) // korisnik je upisan na RMA na početku
        assertThat(upisaniPredmeti, hasItem<Predmet>(
            hasProperty("naziv", Is("RMA"))
        ))
        assertThat(upisaniPredmeti, not(hasItem<Predmet>(
            hasProperty("naziv", Is("AFJ"))
        )))
    }

    // lista predmeta po godinama
    @Test
    fun getNeupisani() {
        val neupisaniPredmeti = upisPredmetViewModel.getNeupisaniNazivi(1)
        assertEquals(neupisaniPredmeti.size, 3) // nije upisan na IM, TP i MLTI sa prve god.
        assertTrue(neupisaniPredmeti.contains("IM"))
        assertFalse(neupisaniPredmeti.contains("SI"))
    }

    // lista grupa po predmetima
    @Test
    fun getGroupsByPredmet() {
        val grupeZaPredmet = upisPredmetViewModel.getNaziviGroupaZaPredmet("IM")
        assertEquals(grupeZaPredmet.size, 3)
        assertTrue(grupeZaPredmet.containsAll(listOf("IM-Grupa1", "IM-Grupa2", "IM-Grupa3")))
        assertFalse(grupeZaPredmet.contains("DM-Grupa1"))
    }

    @Test
    fun upisiPredmet() {
        var neupisaniPredmeti: List<String>
        neupisaniPredmeti = upisPredmetViewModel.getNeupisaniNazivi(1)
        assertEquals(neupisaniPredmeti.size, 3)
        upisPredmetViewModel.upisiPredmet("IM", "IM-Grupa1", "1")
        neupisaniPredmeti = upisPredmetViewModel.getNeupisaniNazivi(1)
        assertEquals(neupisaniPredmeti.size, 2) // nakon upisa smanjuje se broj neupisanih
    }
}
