package com.challange.usermanagerrest.core.usecase;

import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.Arrays;

@Component
public class CpfValidator {

    public boolean validate(String cpf) {
        String formatedCpf = formatCpf(cpf);
        verifyRepeatedSquence(formatedCpf);

        if (isValidCPF(formatedCpf)) return true;

        throw new InvalidParameterException("Invalid CPF!");
    }

    private boolean isValidCPF(String cpf) {
        final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        if ((cpf==null) || (cpf.length()!=11)) return false;

        Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }

    private int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    private void verifyRepeatedSquence(String cpf) {
        final String[] splitedCpf = cpf.split("");

        if(!Arrays.stream(splitedCpf)
                .anyMatch(number -> !number.equals(splitedCpf[0]))){
            throw new InvalidParameterException("Invalid CPF!");
        }
    }

    private String formatCpf(String cpf) {
        return cpf.replaceAll("[\\D]", "");
    }
}
