#include <stdio.h>

int main() {
    // Vetor simples para armazenar os números inteiros
    int numeros[10];
    int i, maior, posicao;

    // Lê 10 números inteiros e armazena no vetor
    for (i = 0; i < 10; ++i) {
        printf("Digite o %dº número: ", i + 1);
        scanf("%d", &numeros[i]);
    }

    // Inicializa a variável 'maior' com o primeiro elemento do vetor e 'posicao' com 0
    maior = numeros[0];
    posicao = 0;

    // Encontra o maior elemento e sua posição
    for (i = 1; i < 10; ++i) {
        if (numeros[i] > maior) {
            maior = numeros[i];
            posicao = i;     // Irá guardar a posição do maior elemento
        }
    }

    // Imprime os números no vetor
    printf("Números no vetor: ");
    for (i = 0; i < 10; ++i) {
        printf("%d ", numeros[i]);
    }
    printf("\n");

    // Imprime o maior elemento e sua posição
    printf("Maior elemento: %d\n", maior);
    printf("Posição do maior elemento: %d\n", posicao);

    return 0;
}
