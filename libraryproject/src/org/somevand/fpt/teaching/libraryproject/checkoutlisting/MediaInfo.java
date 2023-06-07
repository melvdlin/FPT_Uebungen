package org.somevand.fpt.teaching.libraryproject.checkoutlisting;

import java.util.Collection;

public record MediaInfo(int uid, String title, Collection<String> Authors) {
}
