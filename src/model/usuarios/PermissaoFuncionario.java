package model.usuarios;

import java.io.Serializable;

/**
 * Enum que representa as permissoes dos usuarios do sistema
 */
public enum PermissaoFuncionario implements Serializable {

	CRIACAO_USUARIOS, CRIACAO_PACIENTES, LER_SENHAS, EXCLUSAO_FUNCIONARIOS, CRIACAO_MEDICAMENTOS, CADASTRO_ORGAOS, CRIACAO_PROCEDIMENTOS;
}
