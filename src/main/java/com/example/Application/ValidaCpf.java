package com.example.Application;

import br.com.caelum.stella.tinytype.CPF;

public class ValidaCpf {

    public static boolean valida(String cpf) {
        // CPFValidator cpfValidator = new CPFValidator();
        CPF cpfValidar = new CPF(cpf);
        if (cpfValidar.isValido())
            return true;
        else
            return false;

        // java.util.List<ValidationMessage> erros =
        // cpfValidator.invalidMessagesFor(cpf);
        // if (erros.size() > 0) {
        // System.out.println(erros);
        // return false;
        // } else {
        // return true;
        // }

    }

    public static String formatado(String cpf){
        CPF cpfFormatado = new CPF(cpf);
        return cpfFormatado.getNumeroFormatado();
    }
}
