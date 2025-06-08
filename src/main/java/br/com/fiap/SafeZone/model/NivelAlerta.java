package br.com.fiap.SafeZone.model;

public enum NivelAlerta {
    BAIXO,
    MEDIO,
    ALTO;
    public static NivelAlerta fromString(String value) {
        try {
            return NivelAlerta.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de Nivel de Alerta inválido: " + value +
                    ". Valores válidos: BAIXO, MEDIO, ALTO");
        }
    }
    }

