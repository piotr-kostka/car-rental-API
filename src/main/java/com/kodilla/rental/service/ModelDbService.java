package com.kodilla.rental.service;

import com.kodilla.rental.domain.Model;
import com.kodilla.rental.domain.dto.ModelDto;
import com.kodilla.rental.exception.notFound.ModelNotFoundException;
import com.kodilla.rental.mapper.ModelMapper;
import com.kodilla.rental.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelDbService {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    public List<ModelDto> getModels() {
        List<Model> models = modelRepository.findAll();
        return modelMapper.mapToModelDtoList(models);
    }

    public ModelDto getModel(final long modelId) throws ModelNotFoundException {
        Model model = modelRepository.findById(modelId).orElseThrow(() -> new ModelNotFoundException(modelId));
        return modelMapper.mapToModelDto(model);
    }

    @Transactional
    public ModelDto createModel(final ModelDto modelDto) {
        Model model = modelMapper.mapToModel(modelDto);
        Model savedModel = modelRepository.save(model);
        return modelMapper.mapToModelDto(savedModel);
    }

    @Transactional
    public ModelDto updateModel(final ModelDto modelDto) {
        Model model = modelMapper.mapToModel(modelDto);
        Model savedModel = modelRepository.save(model);
        return modelMapper.mapToModelDto(savedModel);
    }

    @Transactional
    public void deleteModel(final long modelId) {
        modelRepository.deleteById(modelId);
    }
}
