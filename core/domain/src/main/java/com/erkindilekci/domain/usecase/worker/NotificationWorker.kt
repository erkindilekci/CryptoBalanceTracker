package com.erkindilekci.domain.usecase.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.erkindilekci.domain.usecase.coin.GetCoinUseCase
import com.erkindilekci.domain.usecase.notification.ReadNotificationPreferenceUseCase
import com.erkindilekci.util.FormatCoinPrice
import com.erkindilekci.util.Notification
import com.erkindilekci.util.Response
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val getCoinUseCase: GetCoinUseCase,
    private val readNotificationPreferenceUseCase: ReadNotificationPreferenceUseCase
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            if (readNotificationPreferenceUseCase.invoke()) {
                getCoinUseCase.invoke().collect {
                    when (it) {
                        is Response.Success -> {
                            it.data.data.map { i ->
                                if (i.symbol == "BTC") {
                                    Notification.showNotification(
                                        context = applicationContext,
                                        FormatCoinPrice.formatPrice(i.quote.USD.price)
                                    )
                                }
                            }
                        }

                        else -> {}
                    }
                }
            }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
