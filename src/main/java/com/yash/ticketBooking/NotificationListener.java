package com.yash.ticketBooking;

import com.yash.ticketBooking.config.RabbitMQConfig;
import com.yash.ticketBooking.entity.Booking;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleBookingConfirmation(Booking booking) {
        System.out.println("📧 Simulated notification: Booking #" + booking.getId()
                + " confirmed for seat " + booking.getSeat().getSeatNumber());
    }
}