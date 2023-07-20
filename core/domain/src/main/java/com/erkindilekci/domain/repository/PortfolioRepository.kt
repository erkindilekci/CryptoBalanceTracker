package com.erkindilekci.domain.repository

import com.erkindilekci.domain.model.portfolio.PortfolioModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface PortfolioRepository {

    suspend fun addToPortfolio(
        userUid: String,
        portfolioModel: PortfolioModel
    ): Task<Void?>

    suspend fun getPortfolio(userUid: String): Task<QuerySnapshot?>

    suspend fun deletePortfolio(userUid: String, name: String): Task<Void?>
}
