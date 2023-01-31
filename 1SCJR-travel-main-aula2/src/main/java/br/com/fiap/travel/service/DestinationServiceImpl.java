package br.com.fiap.travel.service;

import br.com.fiap.travel.dto.DestinationCreateUpdateDTO;
import br.com.fiap.travel.dto.DestinationDTO;
import br.com.fiap.travel.dto.DestinationPriceDTO;
import br.com.fiap.travel.dto.DestinationSimpleDTO;
import br.com.fiap.travel.entity.DestinationEntity;
import br.com.fiap.travel.repository.DestinationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    @Override
    public List<DestinationSimpleDTO> list(String country) {
        List<DestinationEntity> destinationEntities;

        if(country != null){
            destinationEntities = destinationRepository.findAllByCountry(country);
        }
        else{
            destinationEntities = destinationRepository.findAll();
        }

        return destinationEntities
                .stream()
                .map(destinationEntity -> new DestinationSimpleDTO(destinationEntity.getId(),
                        destinationEntity.getName(),
                        destinationEntity.getCountry()))
                .collect(Collectors.toList());
    }

    @Override
    public DestinationDTO get(Long id) {
        return null;
    }

    @Override
    public DestinationDTO create(DestinationCreateUpdateDTO destinationCreateUpdateDTO) {

        ObjectMapper objectMapper = new ObjectMapper();

        DestinationEntity destinationEntity = objectMapper.convertValue(destinationCreateUpdateDTO, DestinationEntity.class);

        destinationEntity.setCurrency("BRL");

        DestinationEntity savedDestinationEntity = destinationRepository.save(destinationEntity);

        DestinationDTO destinationDTO = objectMapper.convertValue(savedDestinationEntity, DestinationDTO.class);

        return destinationDTO;
    }

    @Override
    public DestinationDTO update(Long id, DestinationCreateUpdateDTO destinationCreateUpdateDTO) {
        return null;
    }

    @Override
    public DestinationDTO updatePrice(Long id, DestinationPriceDTO destinationPriceDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
