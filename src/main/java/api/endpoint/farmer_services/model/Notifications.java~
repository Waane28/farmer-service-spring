package api.endpoint.farmer_services.model;

import api.endpoint.farmer_services.enums.MessageType;
import api.endpoint.farmer_services.enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn( name = "user_id")
    private Users users;

    @Enumerated(EnumType.STRING)
    @Column( name = "message_type")
    private MessageType messageType;

    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column( name = "notification_status")
    private NotificationStatus notificationStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
