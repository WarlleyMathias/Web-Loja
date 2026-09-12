$(document).ready(function() {
    function limpa_formulario_cep() {
        // Limpa valores do formulário de cep.
        $("#ruaAvenida").val("");
        $("#bairro").val("");
        $("#cidade").val("");
        $("#estado").val("");
    }
    
    //Quando o campo cep perde o foco.
    $("#cep").blur(function() {

        //Nova variável "cep" somente com dígitos.
        const cep = $(this).val().replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep !== "") {

            //Expressão regular para validar o CEP.
            const validacep = /^[0-9]{8}$/;

            //Valida o formato do CEP.
            if(validacep.test(cep)) {

                //Preenche os campos com "..." enquanto consulta webservice.
                $("#ruaAvenida").val("...");
                $("#bairro").val("...");
                $("#cidade").val("...");
                $("#estado").val("...");

                //Consulta o webservice viacep.com.br/
                $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                    if (!("erro" in dados)) {
                        //Atualiza os campos com os valores da consulta.
						if(dados.logradouro !== "")
						$("#ruaAvenida").prop( "readonly", true );
						else
						$("#ruaAvenida").prop( "readonly", false );

						if(dados.bairro !== "")
						$("#bairro").prop( "readonly", true );
						else
						$("#bairro").prop( "readonly", false );						

						if(dados.localidade !== "")
						$("#cidade").prop( "readonly", true );
						else
						$("#cidade").prop( "readonly", false );

						if(dados.uf !== "")
						$("#estado").prop( "readonly", true );
						else
						$("#estado").prop( "readonly", false );
                    } //end if.
                    else {
                        //CEP pesquisado não foi encontrado.
                        limpa_formulario_cep();
                        alert("CEP não encontrado.");
                    }
                });
            } //end if.
            else {
                //cep é inválido.
                limpa_formulario_cep();
                alert("Formato de CEP inválido.");
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            limpa_formulario_cep();
        }
    });
});