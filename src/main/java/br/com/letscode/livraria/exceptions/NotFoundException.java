package br.com.letscode.livraria.exceptions;

import br.com.letscode.livraria.util.MessageUtils;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super(MessageUtils.NO_RECORDS_FOUND);
    }
}
