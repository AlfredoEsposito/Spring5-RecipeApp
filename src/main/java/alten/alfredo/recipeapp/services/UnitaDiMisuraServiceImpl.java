package alten.alfredo.recipeapp.services;

import alten.alfredo.recipeapp.commands.UnitaDiMisuraCommand;
import alten.alfredo.recipeapp.converters.UnitaDiMisuraToUnitaDiMisuraCommand;
import alten.alfredo.recipeapp.repositories.UnitaDiMisuraRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitaDiMisuraServiceImpl implements UnitaDiMisuraService {

    private final UnitaDiMisuraRepository udmRepository;
    private final UnitaDiMisuraToUnitaDiMisuraCommand udmConverter;

    public UnitaDiMisuraServiceImpl(UnitaDiMisuraRepository udmRepository, UnitaDiMisuraToUnitaDiMisuraCommand udmConverter) {
        this.udmRepository = udmRepository;
        this.udmConverter = udmConverter;
    }

    @Override
    public Set<UnitaDiMisuraCommand> listAllUdm() {
        return StreamSupport.stream(udmRepository.findAll().spliterator(),false).map(udmConverter::convert)
                .collect(Collectors.toSet());
    }
}
