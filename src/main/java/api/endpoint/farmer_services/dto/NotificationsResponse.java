package api.endpoint.farmer_services.dto;

import api.endpoint.farmer_services.enums.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationsResponse {
    private Long id;
    private NotificationStatus notificationStatus;
    private LocalDateTime createdAt;
}
