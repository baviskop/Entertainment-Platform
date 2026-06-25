package com.long1dep.youtuberef11.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDto {
    private long totalVideos;
    private long totalViews;
    private long newThisMonth;
    private long pendingApproval;
}
