package br.com.fiap.travel.controller;

import br.com.fiap.travel.dto.DestinationCreateUpdateDTO;
import br.com.fiap.travel.dto.DestinationDTO;
import br.com.fiap.travel.dto.DestinationPriceDTO;
import br.com.fiap.travel.dto.DestinationSimpleDTO;
import br.com.fiap.travel.service.DestinationService;
import org.apache.catalina.valves.ExtendedAccessLogValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("destinations")
public class DestinationController {

    //@Autowired Agora o autowired é opcional, desde que tenha criado no construtor
    private DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping
    public List<DestinationSimpleDTO> listAll(@RequestParam(required = false) String country) {
        return destinationService.list(country);
    }

    @GetMapping("{id}")
    public DestinationDTO findById(@PathVariable Long id) {
        return destinationService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DestinationDTO create(@RequestBody DestinationCreateUpdateDTO destinationCreateUpdateDTO){
        return destinationService.create(destinationCreateUpdateDTO);
    }

    @PutMapping("{id}")
    public DestinationDTO update (@PathVariable Long id,
                                  @RequestBody DestinationCreateUpdateDTO destinationCreateUpdateDTO){
        return destinationService.update(id, destinationCreateUpdateDTO);
    }

    @PatchMapping("{id}")
    public DestinationDTO updatePrice(@PathVariable Long id,
                                      @RequestBody DestinationPriceDTO destinationPriceDTO){
        return destinationService.updatePrice(id, destinationPriceDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        destinationService.delete(id);
    }

}