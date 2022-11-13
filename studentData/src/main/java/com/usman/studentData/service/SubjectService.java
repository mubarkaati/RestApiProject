package com.usman.studentData.service;

import com.usman.studentData.model.dto.request.SubjectRequestDto;
import com.usman.studentData.model.dto.response.SubjectResponseDto;

import java.util.List;

public interface SubjectService {
    SubjectResponseDto addSubject(SubjectRequestDto requestDto);
    SubjectResponseDto updateSubject(SubjectRequestDto requestDto, int subjectId);
    List<SubjectResponseDto> getAllSubject();
    void deleteSubject(int subjectId);
}