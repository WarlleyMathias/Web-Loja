//Insira o código de checkout gerado no Passo 1
const code = document.getElementById("cod").value;
const callback = {
    success: function (transactionCode) {
        //Insira os comandos para quando o usuário finalizar o pagamento.
        //O código da transação estará na variável "transactionCode"
        console.log("Compra feita com sucesso, código de transação: " + transactionCode);
    },
    abort: function () {
        //Insira os comandos para quando o usuário abandonar a tela de pagamento.
        console.log("abortado");
    }
};
//Chamada do lightbox passando o código de checkout e os comandos para o callback
const isOpenLightbox = PagSeguroLightbox(code, callback);
// Redireciona o comprador, caso o navegador não tenha suporte ao Lightbox
if (!isOpenLightbox){
    location.href="https://pagseguro.uol.com.br/v2/checkout/payment.html?code=" + code;
    console.log("Redirecionamento")
}
