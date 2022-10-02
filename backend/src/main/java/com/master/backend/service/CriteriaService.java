package com.master.backend.service;

import com.master.backend.domain.Criteria;
import com.master.backend.model.CriteriaEntity;
import com.master.backend.model.GradeEntity;
import com.master.backend.repository.CriteriaRepository;
import com.master.backend.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriteriaService {
    @Autowired
    CriteriaRepository criteriaRepository;

    @Autowired
    GradeRepository gradeRepository;

    public List<Criteria> getAll(){
        return criteriaRepository.findAll().stream()
                .map(entity->entity.toDomain())
                .collect(Collectors.toList());
    }

    public Criteria addCriteria(Criteria criteria){
        CriteriaEntity criteriaEntity=new CriteriaEntity(criteria.getName(),criteria.getCoefficient());
        return criteriaRepository.save(criteriaEntity).toDomain();
    }

    public Criteria findById(Integer id) throws Exception {
        if(!criteriaRepository.existsById(id)){
            throw new Exception("Criteria with id: "+id+"doesn't exist!");
        }
        return criteriaRepository.findById(id).get().toDomain();
    }

    public void deleteById(Integer id) throws Exception{
        if(!criteriaRepository.existsById(id)){
            throw new Exception("Criteria with id: "+id+" doesn't exist!");
        }
        /*gradeRepository.findByCriteriaId(id).forEach(grade -> {
            gradeRepository.deleteById(grade.getId());
        } );*/
        gradeRepository.deleteAll(gradeRepository.findByCriteriaId(id));
        criteriaRepository.deleteById(id);
    }

}
