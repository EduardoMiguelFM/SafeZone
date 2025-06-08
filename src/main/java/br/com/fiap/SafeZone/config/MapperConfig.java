package br.com.fiap.SafeZone.config;

import br.com.fiap.SafeZone.dto.AreaSeguraResponseDTO;
import br.com.fiap.SafeZone.model.AreaSegura;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.addMappings(new PropertyMap<AreaSegura, AreaSeguraResponseDTO>() {
            @Override
            protected void configure() {
                map().setCidade(source.getLocalizacao().getCidade());
                map().setEstado(source.getLocalizacao().getEstado());
                map().setEndereco(source.getLocalizacao().getEndereco());
                map().setLocalizacaoId(source.getLocalizacao().getId());
            }
        });

        return mapper;
    }
}
