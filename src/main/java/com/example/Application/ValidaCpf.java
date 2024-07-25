package com.example.Application;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;

public class ValidaCpf {

    public static boolean valida(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        java.util.List<ValidationMessage> erros = cpfValidator.invalidMessagesFor(cpf);
        if (erros.size() > 0) {
            System.out.println(erros);
            return false;
        } else {
            return true;
        }

    }
}
