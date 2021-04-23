package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository

class PitanjeKvizViewModel {
    fun getPitanja(nazivKviza: String, nazivPredmeta: String): List<Pitanje> {
        return PitanjeKvizRepository.getPitanja(nazivKviza, nazivPredmeta)
    }

    fun resetujBrojTacnihOdgovora() = PitanjeKvizRepository.resetujBrojTacnihOdgovora()

    fun getNazivKviza(): String = PitanjeKvizRepository.getNazivKviza()

    fun setNazivKviza(naziv: String) = PitanjeKvizRepository.setNazivKviza(naziv)

    fun getNazivPredmeta(): String = PitanjeKvizRepository.getNazivPredmeta()

    fun setNazivPredmeta(naziv: String) = PitanjeKvizRepository.setNazivPredmeta(naziv)

    fun getRezultatKviza(): Double = PitanjeKvizRepository.getRezultatKviza()

    fun validacijaOdgovora(pozicija: Int, pitanje: Pitanje): Boolean {
        if (pozicija == pitanje.tacan) {
            PitanjeKvizRepository.povecajBrojTacnihOdgovora()
            return true
        }
        return false
    }
}
