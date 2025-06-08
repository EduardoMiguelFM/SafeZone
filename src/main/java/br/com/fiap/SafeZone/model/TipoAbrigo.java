package br.com.fiap.SafeZone.model;

public enum TipoAbrigo {
    ESCOLA,
    GINASIO,
    IGREJA,
    HOSPITAL;

    public static TipoAbrigo fromString(String value) {
        try {
            return TipoAbrigo.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de abrigo inválido: " + value +
                    ". Valores válidos: ESCOLA, GINASIO, IGREJA, HOSPITAL");
        }
    }
}