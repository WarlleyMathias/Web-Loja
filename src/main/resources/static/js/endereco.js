function MascaraInteiro(num) {
    const er = /[^0-9]/;
    Mascara(num,er);
}
function MascaraFloat(num) {
    const er = /[^0-9.,]/;
    Mascara(num,er);
}
function Mascara(num,er){
    er.lastIndex = 0;
    const campo = num;
    if (er.test(campo.value)) {///verifica se é string, caso seja então apaga
        const texto = $(campo).val();
        $(campo).val(texto.substring(0, texto.length - 1));
        return false;
    } else {
        return true;
    }
}

 //formata de forma generica os campos
function formataCampo(campo, Mascara) {
    let texto;
    const er = /[^0-9/ (),.-]/;
    er.lastIndex = 0;

    if (er.test(campo.value)) {///verifica se é string, caso seja então apaga
        texto = $(campo).val();
        $(campo).val(texto.substring(0, texto.length - 1));
    }
    let boleanoMascara;
    const exp = /[-.\/() ]/g;
    const campoSoNumeros = campo.value.toString().replace(exp, "");
    let posicaoCampo = 0;
    let NovoValorCampo = "";
    let TamanhoMascara = campoSoNumeros.length;
    for (let i = 0; i <= TamanhoMascara; i++) {
        boleanoMascara = ((Mascara.charAt(i) === "-") || (Mascara.charAt(i) === ".")
                || (Mascara.charAt(i) === "/"))
        boleanoMascara = boleanoMascara || ((Mascara.charAt(i) === "(")
                || (Mascara.charAt(i) === ")") || (Mascara.charAt(i) === " "))
        if (boleanoMascara) {
            NovoValorCampo += Mascara.charAt(i);
            TamanhoMascara++;
        } else {
            NovoValorCampo += campoSoNumeros.charAt(posicaoCampo);
            posicaoCampo++;
        }
    }
    campo.value = NovoValorCampo;
    ////LIMITAR TAMANHO DE CARACTERES NO CAMPO DE ACORDO COM A MASCARA//
    if (campo.value.length > Mascara.length) {
        texto = $(campo).val();
        $(campo).val(texto.substring(0, texto.length - 1));
    }
    //////////////
    return true;
}

function MascaraMoeda(i) {
    let v;
    v = i.value.replace(/\D/g, '');
    v = (v / 100).toFixed(2) + '';
    v = v.replace(".", ",");
    v = v.replace(/(\d)(\d{3})(\d{3}),/g, "$1.$2.$3,");
    v = v.replace(/(\d)(\d{3}),/g, "$1.$2,");
    i.value = v;
}

function MascaraGenerica(seletor, tipoMascara) {
    setTimeout(function () {
        if (tipoMascara === 'CPFCNPJ') {
            if (seletor.value.length <= 14) { //cpf
                formataCampo(seletor, '000.000.000-00');
            } else { //cnpj
                formataCampo(seletor, '00.000.000/0000-00');
            }
        } else if (tipoMascara === 'DATA') {
            formataCampo(seletor, '00/00/0000');
        } else if (tipoMascara === 'CEP') {
            formataCampo(seletor, '00000-000');
        } else if (tipoMascara === 'TELEFONE') {
            formataCampo(seletor, '(00) 0 0000-0000');
        } else if (tipoMascara === 'INTEIRO') {
            MascaraInteiro(seletor);
        } else if (tipoMascara === 'FLOAT') {
            MascaraFloat(seletor);
        } else if (tipoMascara === 'CPF') {
            formataCampo(seletor, '000.000.000-00');
        } else if (tipoMascara === 'CNPJ') {
            formataCampo(seletor, '00.000.000/0000-00');
        } else if (tipoMascara === 'MOEDA') {
            MascaraMoeda(seletor);
        }
    }, 200);
}

function SomenteNumero(event, seletor, tipoMascara){
    const tecla = (window) ? event.keyCode : event.which;
    if((tecla>47 && tecla<58)) {
	MascaraGenerica(seletor, tipoMascara); 
	return true;
	}
    else{
    	if (tecla===8 || tecla===0) {
	MascaraGenerica(seletor, tipoMascara); 
	return true;
	}
	else  {
	MascaraGenerica(seletor, tipoMascara);
	return false;
	}
    }
}

function SoNumber(event){
    const tecla = (window) ? event.keyCode : event.which;
    if((tecla>47 && tecla<58)) return true;
    else{
    	return tecla === 8 || tecla === 0;
    }
}