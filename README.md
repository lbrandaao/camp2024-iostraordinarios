# Journey

## Descrição

Journey é um aplicativo Android desenvolvido em Kotlin utilizando Jetpack Compose. Este documento fornece instruções detalhadas sobre como configurar e executar o projeto em um ambiente de desenvolvimento.

## Requisitos

### Sistema Operacional

- **Windows** 10 ou superior
- **macOS** 11.0 (Big Sur) ou superior

### Ferramentas Necessárias

- **Android Studio** Flamingo ou superior
- **Java Development Kit (JDK)** 8 ou superior
- **Kotlin** 1.9.0
- **Android SDK** compilado para API 34
- **Gradle** 8.4.0

## Configuração do Ambiente

### Passos para Configurar no Windows

1. **Instalar o Android Studio**
   - Baixe e instale o Android Studio a partir do [site oficial](https://developer.android.com/studio).
   - Siga as instruções de instalação.

2. **Configurar o JDK**
   - Baixe e instale o JDK 8 ou superior.
   - Configure a variável de ambiente `JAVA_HOME` para apontar para o diretório de instalação do JDK.

3. **Configurar o Android SDK**
   - Abra o Android Studio.
   - Vá para `File > Settings > Appearance & Behavior > System Settings > Android SDK`.
   - Selecione e instale o SDK para a API 34.

### Passos para Configurar no macOS

1. **Instalar o Android Studio**
   - Baixe e instale o Android Studio a partir do [site oficial](https://developer.android.com/studio).
   - Arraste o ícone do Android Studio para a pasta Aplicativos.

2. **Configurar o JDK**
   - Baixe e instale o JDK 8 ou superior do [site da Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) ou use `brew`:
     ```sh
     brew install openjdk@8
     ```
   - Configure a variável de ambiente `JAVA_HOME` adicionando a linha abaixo ao arquivo `~/.zshrc` ou `~/.bash_profile`:
     ```sh
     export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)
     ```

3. **Configurar o Android SDK**
   - Abra o Android Studio.
   - Vá para `Preferences > Appearance & Behavior > System Settings > Android SDK`.
   - Selecione e instale o SDK para a API 34.


## Dependências

O projeto utiliza as seguintes dependências:

- **Core KTX**: 1.13.1
- **JUnit**: 4.13.2
- **JUnit AndroidX**: 1.1.5
- **Espresso Core**: 3.5.1
- **Lifecycle Runtime KTX**: 2.7.0
- **Activity Compose**: 1.9.0
- **Compose BOM**: 2023.08.00
- **Navigation Compose**: 2.7.7
- **Retrofit**: 2.11.0
- **OkHttp**: 4.12.0


## Clonando o Repositório

Clone o repositório do projeto para o seu ambiente de desenvolvimento:

```sh
git clone https://github.com/lbrandaao/camp2024-iostraordinarios.git
```

## Executando o Projeto

1. **Abrir o Projeto no Android Studio**
   - Abra o Android Studio.
   - Selecione `Open an existing project`.
   - Navegue até o diretório onde você clonou o repositório, selecione o diretório clonado e escolha a pasta do projeto (_Journey_).

2. **Sincronizar o Projeto com Gradle**
   - O Android Studio solicitará que você sincronize o projeto com os arquivos Gradle. Clique em `Sync Now`.

3. **Executar o Projeto**
   - Conecte um dispositivo Android via USB ou configure um emulador Android no Android Studio.
   - Clique no botão `Run` (ícone de um triângulo verde) ou pressione `Shift + F10`.