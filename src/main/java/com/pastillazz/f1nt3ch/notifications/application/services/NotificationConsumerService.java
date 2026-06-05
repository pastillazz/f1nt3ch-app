package com.pastillazz.f1nt3ch.notifications.application.services;

import com.pastillazz.f1nt3ch.auth.application.events.UserCreatedEvent;
import com.pastillazz.f1nt3ch.notifications.domain.model.Notification;
import com.pastillazz.f1nt3ch.notifications.domain.port.NotificationRepository;
import com.pastillazz.f1nt3ch.transactions.application.events.TransactionEvent;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumerService
{
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;
    @KafkaListener(topics = "user-topic", groupId = ("notification-group"),
            concurrency = "6")
    public void sendUser(UserCreatedEvent notification)
    {
        log.info("Received notification: {}", notification);

        Notification userCreatedNotification = Notification
                .setUserNotification(notification);

        notificationRepository.save(userCreatedNotification);

        String text="""
        Hi, %s! 👋✨
    
        Your account is now fully active and everything is ready to go! 🟢💼
        
        Thank you for joining F1nt3ch! 🙌 From now on, you can manage your finances quickly, smartly, and securely. 💸🔒📈
    
        If you have any questions or need help, just reply to this email and our team will assist you right away! 🤝💬
    
        Wishing you the absolute best on your financial journey! 🚀🔥
        The F1nt3ch Team 💻🌐
        """.formatted(notification.alias());

        emailService
                .sendEmail(notification.email(),
                        "Welcome to F1nt3ch! 🚀🎉",text);

    }

    @KafkaListener(topics = "transfer-topic", groupId = ("notification-group"),
            concurrency = "6")
    public void sendTransaction(TransactionEvent notification)
    {
        if (notification.status().equals(TransactionStatus.PENDING))
        {
            return;
        }

        Notification transactionNotification = Notification
                .setTransactionNotification(notification);

        notificationRepository.save(transactionNotification);

        String statusIcon = notification.status() == TransactionStatus.COMPLETED ? "🟢 Success!" : "🔴 Attention!";

        String text = """
            %s Transaction Details 🏦
            ----------------------------------------
            🆔 Tracking ID: %s
            🔄 Operation Type: %s
            📤 From Wallet ID: %s
            📥 To Wallet ID: %s
            💰 Amount: %s %s
            📅 Date & Time: %s
            📊 Current Status: %s
            📝 Details/Reason: %s
            ----------------------------------------
            ✨ Thanks for using F1nt3ch. ✨
            """.formatted(
                statusIcon,
                notification.id(),
                notification.type(),
                notification.fromWalletId(),
                notification.toWalletId(),
                notification.amount(),
                notification.currency(),
                notification.transactionDate(),
                notification.status(),
                notification.reason()
        );
        emailService
                .sendEmail(notification.email(),
                        "🔔 F1nt3ch: Transaction Notification - ",
                        text);
    }

}
