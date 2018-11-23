package com.springproject.vmagri.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.springproject.vmagri.domain.enums.TipoCliente;
import com.springproject.vmagri.dto.ClienteNewDTO;
import com.springproject.vmagri.resources.exceptions.FieldMessage;
import com.springproject.vmagri.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> msgList = new ArrayList<>();

		objDTO.setCpfOrCnpj(objDTO.getCpfOrCnpj().replaceAll("\\D", ""));

		if (objDTO.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod())
				&& !BR.isValidCpf(objDTO.getCpfOrCnpj())) {
			msgList.add(new FieldMessage("cpfOrCnpj", "CPF inválido!"));
		}

		if (objDTO.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod())
				&& !BR.isValidCnpj(objDTO.getCpfOrCnpj())) {
			msgList.add(new FieldMessage("cpfOrCnpj", "CNPJ inválido!"));
		}

		for (FieldMessage e : msgList) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return msgList.isEmpty();
	}
}