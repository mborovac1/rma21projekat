package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.*
import retrofit2.Response
import retrofit2.http.*

interface Api {
    @GET("/kviz")
    suspend fun getKvizovi(): Response<List<Kviz>>

    @GET("/kviz/{id}")
    suspend fun getKvizById(
        @Path("id") id: Int
    ): Response<Kviz>

    @GET("/grupa")
    suspend fun getGrupe(): Response<List<Grupa>>

    @GET("/predmet/{id}/grupa")
    suspend fun getGrupeZaPredmet(
        @Path("id") idPredmeta: Int
    ): Response<List<Grupa>>

    @GET("/predmet")
    suspend fun getPredmeti(): Response<List<Predmet>>

    @POST("/grupa/{gid}/student/{id}")
    suspend fun upisiUGrupu(
        @Path("gid") idGrupe: Int,
        @Path("id") hashStudenta: String
    ): Message

    @GET("/student/{id}/grupa")
    suspend fun getUpisaneGrupe(
        @Path("id") hashStudenta: String
    ): Response<List<Grupa>>

    @GET("/kviz/{id}/pitanja")
    suspend fun getPitanja(
        @Path("id") idKviza: Int
    ): Response<List<Pitanje>>

    @GET("/student/{id}/kviztaken/{ktid}/odgovori")
    suspend fun getOdgovoriKviz(
        @Path("id") hashStudenta: String,
        @Path("ktid") ktid: Int
    ): Response<List<Odgovor>>

    @GET("/student/{id}/grupa")
    suspend fun getGrupeZaStudenta(
        @Path("id") hashStudenta: String
    ): Response<List<Grupa>>

    @GET("/grupa/{id}")
    suspend fun getGrupaById(
        @Path("id") grupaId: Int
    ): Response<Grupa>

    // Body
    @POST("/student/{id}/kviztaken/{ktid}/odgovor")
    suspend fun postaviOdgovorKviz(
        @Path("id") hashStudenta: String
    )

    @GET("/student/{id}/kviztaken")
    suspend fun getPocetiKvizovi(
        @Path("id") hashStudenta: String
    ): Response<List<KvizTaken>>

    @POST("/student/{id}/kviz/{kid}")
    suspend fun zapocniKviz(
        @Path("id") hashStudenta: String,
        @Path("kid") idKviza: Int
    ): Response<KvizTaken>

    @GET("/kviz/{id}/grupa")
    suspend fun getGrupeZaKviz(
        @Path("id") idKviza: Int
    ): Response<List<Grupa>>

    @GET("/predmet/{id}")
    suspend fun getPredmetById(
        @Path("id") predmetId: Int
    ): Response<Predmet>

    @GET("/grupa/{id}/kvizovi")
    suspend fun getKvizoviByGrupa(
        @Path("id") idGrupe: Int
    ): Response<List<Kviz>>

    @POST("/student/{id}/kviztaken/{ktid}/odgovor")
    suspend fun postaviOdgovorKviz(
        @Path("id") hashStudenta: String,
        @Path("ktid") idKvizTaken: Int,
        @Body odgovorResponse: GetOdgovorResponse
    ): Response<Odgovor> // ili Call umjesto Response

    @GET("/account/{id}/lastUpdate?date=yyyy-mm-ddThh:mm:ss")
    suspend fun imaLiPromjena(
        @Path("id") hashStudenta: String,
        @Query("date") datum: String?
    ): Response<PromjenaResponse>
}
