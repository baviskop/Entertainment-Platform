package com.long1dep.youtuberef11.repository;

import com.long1dep.youtuberef11.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoEntity,String> {

    List<VideoEntity> findAllByIdIn(List<String> ids);
}
