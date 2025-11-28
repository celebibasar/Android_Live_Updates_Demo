package com.basarcelebi.live_updates_demo

enum class DeliveryStatus(
    val title: String,
    val description: String,
    val emoji: String,
    val progress: Int
) {
    ORDERED(
        title = "Sipariş Alındı",
        description = "Siparişiniz başarıyla alındı ve işleme alınıyor",
        emoji = "📋",
        progress = 20
    ),
    PREPARING(
        title = "Hazırlanıyor",
        description = "Siparişiniz şu an hazırlanıyor",
        emoji = "👨‍🍳",
        progress = 40
    ),
    ON_THE_WAY(
        title = "Yolda",
        description = "Siparişiniz kurye ile yola çıktı",
        emoji = "🚗",
        progress = 70
    ),
    DELIVERED(
        title = "Teslim Edildi",
        description = "Siparişiniz teslim edildi. Afiyet olsun!",
        emoji = "✅",
        progress = 100
    ),
    CANCELLED(
        title = "İptal Edildi",
        description = "Sipariş iptal edildi",
        emoji = "❌",
        progress = 0
    )
}