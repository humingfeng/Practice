package com.practice.mapper;

import com.practice.po.ParentPhotos;
import com.practice.po.ParentPhotosExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParentPhotosMapper {
    long countByExample(ParentPhotosExample example);

    int deleteByExample(ParentPhotosExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ParentPhotos record);

    int insertSelective(ParentPhotos record);

    List<ParentPhotos> selectByExample(ParentPhotosExample example);

    ParentPhotos selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ParentPhotos record, @Param("example") ParentPhotosExample example);

    int updateByExample(@Param("record") ParentPhotos record, @Param("example") ParentPhotosExample example);

    int updateByPrimaryKeySelective(ParentPhotos record);

    int updateByPrimaryKey(ParentPhotos record);
}