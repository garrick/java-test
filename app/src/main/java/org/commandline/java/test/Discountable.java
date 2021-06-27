package org.commandline.java.test;

import java.time.LocalDateTime;

public interface Discountable {
    DiscountItem check(Basket basket);
    boolean isCurrentlyAvailable(LocalDateTime localDateTime);
}
