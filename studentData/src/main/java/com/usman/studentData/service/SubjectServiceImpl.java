package com.usman.studentData.service;

import com.usman.studentData.entities.Subject;
import com.usman.studentData.exception.NoSubjectExistException;
import com.usman.studentData.exception.StudentNotFoundException;
import com.usman.studentData.exception.SubjectNotFoundException;
import com.usman.studentData.model.dto.request.SubjectRequestDto;
import com.usman.studentData.model.dto.response.SubjectResponseDto;
import com.usman.studentData.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public SubjectResponseDto addSubject(SubjectRequestDto requestDto) {
        Subject subject = this.modelMapper.map(requestDto, Subject.class);
        return this.modelMapper.map(subjectRepository.save(subject), SubjectResponseDto.class);
    }

    @Override
    public SubjectResponseDto updateSubject(SubjectRequestDto requestDto, int subjectId) {
        if (subjectRepository.findById(subjectId).isPresent()) {
            Subject subject = this.modelMapper.map(requestDto, Subject.class);
            return this.modelMapper.map(subjectRepository.save(subject), SubjectResponseDto.class);
        }
        else {
            throw new StudentNotFoundException();
        }
    }

    @Override
    public List<SubjectResponseDto> getAllSubject() {
        List<Subject> subjectList = subjectRepository.findAll();
        if (subjectList.size() > 0) {
            return subjectList.stream()
                    .map(subject -> this.modelMapper.map(subject, SubjectResponseDto.class))
                    .collect(Collectors.toList());
        }
        else {
            throw new NoSubjectExistException();
        }
    }

    @Override
    public void deleteSubject(int subjectId) {
        if (subjectRepository.findById(subjectId).isPresent()) {
            subjectRepository.deleteById(subjectId);
        }
        else {
            throw new SubjectNotFoundException();
        }
    }
}