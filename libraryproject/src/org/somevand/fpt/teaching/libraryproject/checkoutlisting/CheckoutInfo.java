package org.somevand.fpt.teaching.libraryproject.checkoutlisting;

import java.time.LocalDateTime;

public record CheckoutInfo(MediaInfo mediaInfo, int instanceUID, String location, LocalDateTime checkoutTime) {
}
