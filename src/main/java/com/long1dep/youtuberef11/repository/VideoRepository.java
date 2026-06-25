package com.long1dep.youtuberef11.repository;

import com.long1dep.youtuberef11.entity.VideoEntity;
import com.long1dep.youtuberef11.entity.enums.VideoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoEntity, String>, JpaSpecificationExecutor<VideoEntity> {
    List<VideoEntity> findAllByIdIn(List<String> ids);

    @Transactional
    @Modifying // giúp query ko mặc định cứ query là chỉ SELECT nữa mà chấp nhận UPDATE
    @Query("UPDATE VideoEntity v set v.views = coalesce(v.views, 0) + 1 where v.id = :id") // coalesce(a, 0) nếu a = null -> a = 0
    int increaseViewsById(@Param("id") String id);

    @Query("SELECT COALESCE(SUM(v.views), 0) FROM VideoEntity v")
    long sumViews();

    @Query("SELECT COUNT(v) FROM VideoEntity v WHERE v.createdDate >= :startOfMonth")
    long countByCreatedDateAfter(@Param("startOfMonth") java.time.Instant startOfMonth);

    long countByStatus(VideoStatus status);
}
