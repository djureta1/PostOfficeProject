package com.master.backend.service;

import com.master.backend.domain.Criteria;
import com.master.backend.domain.Grade;
import com.master.backend.domain.Supplier;
import com.master.backend.dto.StatisticsDTO;
import com.master.backend.dto.StatisticsObjectDTO;
import com.master.backend.model.CriteriaEntity;
import com.master.backend.model.GradeEntity;
import com.master.backend.model.SupplierEntity;
import com.master.backend.repository.CriteriaRepository;
import com.master.backend.repository.GradeRepository;
import com.master.backend.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GradeService {
    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    CriteriaRepository criteriaRepository;

    public List<Grade> getAll(){
        return gradeRepository.findAll().stream()
                .map(entity->entity.toDomain())
                .collect(Collectors.toList());
    }

    public List<Grade> getBySupplierId(Integer id) throws Exception {
        if(!supplierRepository.existsById(id)){
            throw new Exception("Supplier with id: "+id+" doesn't exist");
        }
        return gradeRepository.findBySupplierId(id).stream()
                .map(entity->entity.toDomain())
                .collect(Collectors.toList());
    }

    public Grade addGrade(Grade grade){
        CriteriaEntity criteria=criteriaRepository.findById(grade.getCriteria().getId()).get();
        SupplierEntity supplier=supplierRepository.findById(grade.getSupplier().getId()).get();
        GradeEntity gradeEntity=new GradeEntity(grade.getGrade(), grade.getYear(), criteria,supplier);
        return gradeRepository.save(gradeEntity).toDomain();
    }

    public Double getFinalGradeForSuplier(Integer suplierId) throws Exception {
        List<Grade> grades = getBySupplierId(suplierId);
        Double finalGrade = 0d;
        for (Grade grade: grades) {
            finalGrade += grade.getGrade()*grade.getCriteria().getCoefficient();
        }
        return finalGrade;
    }

    public StatisticsObjectDTO getStatistic() throws Exception {
        List<Grade> all = getAll();

        List<Integer> yearsAll = new ArrayList<>();
        List<Integer> supliersAll = new ArrayList<>();
        for(int i=0; i<all.size(); i++) {
            yearsAll.add(all.get(i).getYear());
            supliersAll.add(all.get(i).getSupplier().getId());
        }

        Set<Integer> yearsSet = new HashSet<Integer>(yearsAll);
        Set<Integer> supliersSet = new HashSet<Integer>(supliersAll);

        List<Integer> years = new ArrayList<>(yearsSet);
        List<Integer> supliers = new ArrayList<>(supliersSet);

        List<StatisticsDTO> suplierGradeDtos = new ArrayList<>();

        for(int i=0; i<supliers.size(); i++) {
            for(int j=0; j<years.size(); j++) {
                List<Grade> ocjene = new ArrayList<>();
                Double finalna = 0.;
                for(int k=0; k<all.size(); k++) {
                    Integer pom1 = all.get(k).getYear();
                    Integer pom2 = years.get(j);
                    Integer pom3 = all.get(k).getSupplier().getId();
                    Integer pom4 = supliers.get(i);
                    if(all.get(k).getYear().intValue()==years.get(j).intValue() && all.get(k).getSupplier().getId().intValue() == supliers.get(i).intValue()) {
                        ocjene.add(all.get(k));
                        finalna+=all.get(k).getGrade()*all.get(k).getCriteria().getCoefficient();
                    }
                }
                Supplier supName = supplierRepository.findById(supliers.get(i)).orElse(null).toDomain();
                suplierGradeDtos.add(new StatisticsDTO(supliers.get(i), supName.getName(), ocjene, finalna, years.get(j)));
            }
        }
        return new StatisticsObjectDTO(suplierGradeDtos, supliers, years);
    }

}
